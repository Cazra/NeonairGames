package ponyquest.dialog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import ponyquest.xml.XMLParser;

/** A textless node that causes some in-game event to occur. */
public class EventDiaNode extends DiaNode implements Serializable {
  
  /** 
   * An identifying String for an in-game event to activate. 
   */
  public String event;
  
  /**
   * Whether this event blocks further dialog until some in-game behavior completes.
   */
  public boolean blocking;
  
  public EventDiaNode(String id, String event, boolean blocking) {
    super(id, null, null, null);
    this.event = event;
    this.blocking = blocking;
  }
  
  
  public EventDiaNode(String event, boolean blocking) {
    this("anonNode:" + nextAnonID, event, blocking);
    nextAnonID++;
  }
  
  /** 
   * Creates a EventDiaNode of the appropriate type from a "node" XML element
   * and adds it into a dialog. 
   * @param nodeElem  The XML element representing this node.
   * @param dialog    The DialogModel containing this node.
   */
  public static EventDiaNode createFromXML(Element nodeElem, DialogModel dialog) {
    EventDiaNode node;
    
    String id = XMLParser.getAttribute(nodeElem, "id");
    
    // extract the event string for identifying the in-game event this node needs to reference.
    String event = XMLParser.getAttribute(nodeElem, "event");
    
    boolean blocking = false;
    if(nodeElem.hasAttribute("blocking") && XMLParser.getAttribute(nodeElem, "blocking").equals("true")) {
      blocking = true;
    }
    
    // Create the node.
    if(id.equals("")) {
      node = new EventDiaNode(event, blocking);
    }
    else {
      node = new EventDiaNode(id, event, blocking);
    }
    
    return node;
  }
  
}
