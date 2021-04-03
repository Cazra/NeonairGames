package ponyquest.dialog.model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import ponyquest.xml.XMLParser;

/** 
 * A model for a "dia" node, representing a single dialog graph in a 
 * collection of dialogs. 
 * The Dialog manages a collection of nodes mapped by their id attributes.
 * Is also used to decide what node to go to next in the dialog graph.
 */
public class DialogModel {
  
  /** An identifier for this */
  public String id = "";
  
  /** A mapping of the dialog nodes. The dialog state machine basically works like a directed graph. */
  public Map<String, DiaNode> nodes = new HashMap<>();
  
  /** The starting node for this dialog. */
  public DiaNode startNode = null;
  
  /** Creates the dialog from a "dia" xml element. */
  public DialogModel(Element diaElem) {
    id = XMLParser.getAttribute(diaElem, "id");
    
    // Produces the nodes in the dialog from the "node" elements in the XML.
    List<Element> nodeElems = XMLParser.getChildren(diaElem, "node");
    DiaNode prev = null;
    for(Element nodeElem : nodeElems) {
      // Create our node and add it to our map.
      DiaNode node = DiaNode.createFromXML(nodeElem, this);
      
      // Set this node as the previous node's default "next".
      if(prev != null) {
        prev.setNextID(node.id);
      }
      else {
        startNode = node;
      }
      prev = node;
    }
  }
  
  
  
  /** Creates a collection of dialogs mapped by their IDs from an XML source file. */
  public static Map<String, DialogModel> createDialogsFromXMLFile(File f) {
    Element dialogsElem = XMLParser.parse(f);
    return createDialogsFromXML(dialogsElem);
  }
  
  /** Creates a collection of dialogs mapped by their IDs from an XML resource file. */
  public static Map<String, DialogModel> createDialogsFromXMLResource(String f) {
    Element dialogsElem = XMLParser.parse(f);
    return createDialogsFromXML(dialogsElem);
  }
  
  /** Creates a collection of dialogs mapped by their IDs from an XML element. */
  public static Map<String, DialogModel> createDialogsFromXML(Element dialogsElem) {
  
    // Produce our Dialogs from the "dia" elements in the XML.
    List<Element> diaList = XMLParser.getChildren(dialogsElem, "dia");
    Map<String, DialogModel> result = new HashMap();
    for(Element dia : diaList) {
      DialogModel dialog = new DialogModel(dia);
      result.put(dialog.id, dialog);
    }
    
    return result;
  }
  
  
  
  /** 
   * Decides what node comes next after some node, given a string argument. 
   * If null or "" is given as the argument, then the default next node in the 
   * sequence for the dialog is returned.
   */
  public DiaNode getNext(DiaNode node, String arg) {
    String nextID = node.getNextID(arg);
    if(nextID == null) {
      return null;
    }
    else {
      return nodes.get(nextID);
    }
  }
  
  /** Get the default next node for some node. */
  public DiaNode getNext(DiaNode node) {
    String nextID = node.getNextID();
    if(nextID == null) {
      return null;
    }
    else {
      return nodes.get(nextID);
    }
  }
  
  
  /** Gets the starting DiaNode in this dialog. */
  public DiaNode getFirst() {
    return startNode;
  }
  
}
