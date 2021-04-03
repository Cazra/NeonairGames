package ponyquest.dialog.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import ponyquest.xml.XMLParser;

/** A paragraph node for part of a scripted conversation. */
public class DiaNode implements Serializable {
  
  public static long nextAnonID = 0;
  
  /** Each node in a conversation has a unique id. */
  public String id;
  
  /** Optional speaking character for this node. */
  public String who;
  
  /** Optional emotional state for the speaking character. */
  public String emote;
  
  /** The text content of this node. */
  public String text;
  
  /** The parent node in the dialog graph. (The node that was encapsulating it in the XML) */
  public DiaNode parent = null;
  
  /** A mapping of the string arguments to next node ids that this node can proceed to. */
  public Map<String, String> nextIDs = new HashMap<>();
  
  
  public DiaNode(String id, String who, String emote, String text) {
    this.id = id;
    this.who = who;
    this.emote = emote;
    this.text = text;
  }
  
  /** Creates an anonymous node. */
  public DiaNode(String text) {
    this("anonNode:" + nextAnonID, null, null, text);
    nextAnonID++;
  }
  
  
  
  /** 
   * Creates a DiaNode of the appropriate type from a "node" XML element
   * and adds it into a dialog. 
   * @param nodeElem  The XML element representing this node.
   * @param dialog    The DialogModel containing this node.
   */
  public static DiaNode createFromXML(Element nodeElem, DialogModel dialog) {
    String nodeType = XMLParser.getAttribute(nodeElem, "type");
    DiaNode node;
    
    // Create a node of the appropriate type.
    if(nodeType.equals("input")) {
      node = InputDiaNode.createFromXML(nodeElem, dialog);
    }
    else if(nodeType.equals("switch")) {
      node = SwitchDiaNode.createFromXML(nodeElem, dialog);
    }
    else if(nodeType.equals("event")) {
      node = EventDiaNode.createFromXML(nodeElem, dialog);
    }
    else {
      String id = XMLParser.getAttribute(nodeElem, "id");
      String who = XMLParser.getAttribute(nodeElem, "who");
      String emote = XMLParser.getAttribute(nodeElem, "emote");
      String text = XMLParser.getContent(nodeElem);
      
      if(id.equals("")) {
        node = new DiaNode(text);
        node.who = who;
        node.emote = emote;
      }
      else {
        node = new DiaNode(id, who, emote, text);
      }
    }
    
    dialog.nodes.put(node.id, node);
    return node;
  }
  
  
  /** Maps an argument to a next node's id. */
  public void putNextID(String arg, String nextID) {
    nextIDs.put(arg, nextID);
  }
  
  
  /** Sets the ID of the default next node in the dialog. */
  public void setNextID(String nextID) {
    nextIDs.put("#next", nextID);
  }
  
  
  /** Gets the ID of the default next node in the dialog. */
  public String getNextID() {
    String nextID = getNextID("#next");
    return nextID;
  }
  
  
  /** 
   * Get the next node based on some string argument. 
   * If null is returned, then an ending point for the dialog has been reached.
   */
  public String getNextID(String arg) {
    String nextID = nextIDs.get(arg);
    
    if("#next".equals(nextID)) {
      nextID = nextIDs.get("#next");
    }
    
    // If the next ID could not be found, try to use the default next ID of this node's parent.
    if(nextID == null && parent != null) {
      nextID = parent.getNextID();
    }
    
    return nextID;
  }
}
