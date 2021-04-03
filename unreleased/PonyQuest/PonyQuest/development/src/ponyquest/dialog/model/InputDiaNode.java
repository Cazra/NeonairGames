package ponyquest.dialog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import ponyquest.xml.XMLParser;

/** A node in a conversation for prompting the user to make a choice. */
public class InputDiaNode extends DiaNode implements Serializable { 

  /** The prompt text presented to the player. e.g. "Allow Twinkle Shine to join the party?" */
  public String prompt;
  
  /** The list of choices available to the player. e.g. "Yes", "No" */
  public List<String> choices = new ArrayList<>();
  
  
  public InputDiaNode(String id, String who, String emote, String prompt) {
    super(id, who, emote, prompt);
    this.prompt = prompt;
  }
  
  public InputDiaNode(String id, String prompt) {
    this(id, null, null, prompt);
  }
  
  /** Creates an anonymous prompt. */
  public InputDiaNode(String prompt) {
    this("anonNode:" + nextAnonID, null, null, prompt);
    nextAnonID++;
  }
  
  
  /** 
   * Creates a InputDiaNode of the appropriate type from a "node" XML element
   * and adds it into a dialog. 
   * @param nodeElem  The XML element representing this node.
   * @param dialog    The DialogModel containing this node.
   */
  public static InputDiaNode createFromXML(Element nodeElem, DialogModel dialog) {
    InputDiaNode node;
    
    String id = XMLParser.getAttribute(nodeElem, "id");
    String who = XMLParser.getAttribute(nodeElem, "who");
    String emote = XMLParser.getAttribute(nodeElem, "emote");
    
    // extract the prompt string.
    String prompt;
    Element promptElem = XMLParser.getChild(nodeElem, "prompt");
    if(promptElem != null) {
      prompt = XMLParser.getContent(promptElem);
    }
    else {
      prompt = "ERROR - prompt missing";
    }
    
    // Create the node.
    if(id.equals("")) {
      node = new InputDiaNode(prompt);
      node.who = who;
      node.emote = emote;
    }
    else {
      node = new InputDiaNode(id, who, emote, prompt);
    }
    
    // Create each case.
    List<Element> caseElems = XMLParser.getChildren(nodeElem, "case");
    for(Element caseElem : caseElems) {
      
      String caseVal = XMLParser.getAttribute(caseElem, "val");
      String caseGoto = XMLParser.getAttribute(caseElem, "goto");
      List<Element> caseNodeElems = XMLParser.getChildren(caseElem, "node");
      
      // The ID for the next node is provided.
      if(!caseGoto.equals("")) {
      //  node.putNextID(caseVal, caseGoto);
        node.addChoice(caseVal, caseGoto);
      }
      
      // A nested sequence of nodes is provided in the case element.
      else if(caseNodeElems.size() > 0) {
        
        // Create the nested nodes and set this as their parent.
        DiaNode prev = null;
        for(Element caseNodeElem : caseNodeElems) {
          DiaNode caseNode = DiaNode.createFromXML(caseNodeElem, dialog);
          caseNode.parent = node;
          
          // map the input value to the first node in the nested sequence.
          if(prev == null) {
          //  node.putNextID(caseVal, caseNode.id);
            node.addChoice(caseVal, caseNode.id);
          }
          else {
            prev.setNextID(caseNode.id);
          }
          prev = caseNode;
        }
      }
      
      // No goto or nested sequence was provided. 
      else {
        // do nothing. The next node should be the parent's default next.
        
        node.addChoice(caseVal, "#next");
      }
    }
    
    return node;
  }
  
  
  /** Adds a choice to this prompt and maps it to the ID of another node in the dialog.*/
  public void addChoice(String choice, String nextID) {
    if(choice != null && !choices.contains(choice)) {
      choices.add(choice);
      putNextID(choice, nextID);
    }
  }
  
}
