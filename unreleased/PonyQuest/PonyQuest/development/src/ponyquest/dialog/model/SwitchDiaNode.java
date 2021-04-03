package ponyquest.dialog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import ponyquest.xml.XMLParser;

/** A textless node that branches the conversation based on the value of some in-game variable. */
public class SwitchDiaNode extends DiaNode implements Serializable {
  
  /** 
   * An identifying String for an in-game variable to test. The value of this 
   * variable is used to decide which node to go to next in the dialog. 
   */
  public String variable;
  
  /** The non-default cases for this switch. */
  public List<String> cases;
  
  
  
  public SwitchDiaNode(String id, String variable) {
    super(id, null, null, null);
    this.variable = variable;
    cases = new ArrayList<>();
  }
  
  
  public SwitchDiaNode(String variable) {
    this("anonNode:" + nextAnonID, variable);
    nextAnonID++;
  }
  
  /** 
   * Creates a SwitchDiaNode of the appropriate type from a "node" XML element
   * and adds it into a dialog. 
   * @param nodeElem  The XML element representing this node.
   * @param dialog    The DialogModel containing this node.
   */
  public static SwitchDiaNode createFromXML(Element nodeElem, DialogModel dialog) {
    SwitchDiaNode node;
    
    String id = XMLParser.getAttribute(nodeElem, "id");
    
    // extract the var string for identifying the in-game variable this node needs to reference.
    String var = XMLParser.getAttribute(nodeElem, "var");
    
    // Create the node.
    if(id.equals("")) {
      node = new SwitchDiaNode(var);
    }
    else {
      node = new SwitchDiaNode(id, var);
    }
    
    // Create each case.
    List<Element> caseElems = XMLParser.getChildren(nodeElem, "case");
    for(Element caseElem : caseElems) {
      
      String caseVal = XMLParser.getAttribute(caseElem, "val");
      String caseGoto = XMLParser.getAttribute(caseElem, "goto");
      List<Element> caseNodeElems = XMLParser.getChildren(caseElem, "node");
      
      node.cases.add(caseVal);
      
      // The ID for the next node is provided.
      if(!caseGoto.equals("")) {
        node.putNextID(caseVal, caseGoto);
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
            node.putNextID(caseVal, caseNode.id);
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
      }
    }
    
    // Create the default case.
    Element defaultElem = XMLParser.getChild(nodeElem, "default");
    if(defaultElem != null) {
    
      String caseGoto = XMLParser.getAttribute(defaultElem, "goto");
      List<Element> caseNodeElems = XMLParser.getChildren(defaultElem, "node");
      
      // The ID for the next node is provided.
      if(!caseGoto.equals("")) {
        node.setDefault(caseGoto);
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
            node.setDefault(caseNode.id);
          }
          else {
            prev.setNextID(caseNode.id);
          }
          prev = caseNode;
        }
      }
      
      // No goto or nested sequence was provided. 
      else {
        // do nothing. The default node should be the parent's default next.
      }
    }
    
    return node;
  }
  
  
  /** 
   * Sets the default next node to return, in case none of the 
   * cases match our query for the next node. 
   */
  public void setDefault(String destID) {
    putNextID("#default", destID);
  }
  
  /** Gets the default case's next node. */
  public String getDefault() {
    return nextIDs.get("#default");
  }
  
  
  
  /** 
   * Get the next node based on some string argument. 
   * If null is returned, then an ending point for the dialog has been reached.
   */
  public String getNextID(String arg) {
    String nextID = nextIDs.get(arg);
    
    // Is a default case available?
    if(nextID == null) {
      nextID = getDefault();
    }
    
    // If the next ID could not be found for either the given case or the 
    // default case, try to use the default next ID of this node's parent.
    if(nextID == null && parent != null) {
      nextID = parent.getNextID();
    }
    
    return nextID;
  }
  
  
  /** Tries to match some value to one of the cases for the switch variable, with support for inequalities. */
  public String matchValueToCase(String valStr) {
    
    double valNum =0;
    boolean isNum;
    try {
      valNum = Double.parseDouble(valStr);
      isNum = true;
    }
    catch(NumberFormatException e) {
      isNum = false;
    }
    
    // Attempt to match each case.
    for(String caze : cases) {
      
      // Exact match?
      if(caze.equals(valStr)) {
        return caze;
      }
      
      // Check inequality prefixes. 
      else if(isNum) {
        
        if(caze.startsWith(">=") && matchGE(valNum, caze.substring(2))) {
          return caze;
        }
        if(caze.startsWith(">") && matchG(valNum, caze.substring(1))) {
          return caze;
        }
        if(caze.startsWith("<>") && matchNE(valNum, caze.substring(2))) {
          return caze;
        }
        if(caze.startsWith("<=") && matchLE(valNum, caze.substring(2))) {
          return caze;
        }
        if(caze.startsWith("<") && matchL(valNum, caze.substring(1))) {
          return caze;
        }
      }
    } // for cases
    
    // No cases matched.
    return "#default";
  }
  
  
  //////// Inequality matching helpers
  private boolean matchGE(double valNum, String caze) {
    try {
      double cazeNum = Double.parseDouble(caze);
      return (valNum >= cazeNum);
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  
  
  private boolean matchG(double valNum, String caze) {
    try {
      double cazeNum = Double.parseDouble(caze);
      return (valNum > cazeNum);
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  
  private boolean matchNE(double valNum, String caze) {
    try {
      double cazeNum = Double.parseDouble(caze);
      return (valNum != cazeNum);
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  
  private boolean matchLE(double valNum, String caze) {
    try {
      double cazeNum = Double.parseDouble(caze);
      return (valNum <= cazeNum);
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  
  private boolean matchL(double valNum, String caze) {
    try {
      double cazeNum = Double.parseDouble(caze);
      return (valNum < cazeNum);
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
}
