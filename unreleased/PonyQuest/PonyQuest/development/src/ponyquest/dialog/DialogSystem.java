package ponyquest.dialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.Serializable;
import java.util.Map;

import pwnee.image.*;
import pwnee.input.Keyboard;
import pwnee.sprites.Sprite;
import pwnee.sprites.TextSprite;
import pwnee.text.BlitteredFont;

import ponyquest.PonyMain;
import ponyquest.dialog.model.*;
import ponyquest.menu.MenuImages;

/** 
 * This class provides an API for the game's dialog system as well 
 * as global state for the look and feel of the dialogs.
 */
public class DialogSystem implements Serializable {
  
  // configurations
  
  private static final int READ_FROM_FILE = 1;
  
  private static final int READ_FROM_RESOURCE = 2;
  
  private static int readXmlFrom = READ_FROM_FILE;
  
  
  
  
  // default dimensions.
  public static final int DEF_WIDTH = 300;
  
  public static final int DEF_HEIGHT = 80;
  
  public static final int MAX_LINES = 3;
  
  
  
  
  
  
  /** Whether or not to block player control while the current dialog is running. */
  public static boolean blockPlayerMovement = true;
  
  
  /** The current DialogModel being used. */
  public static DialogModel model = null;
  
  /** The current DiaNode in the dialogModel. */
  public static DiaNode curNode = null;
  
  /** 
   * The DialogBox containing the text that you mash ENTER
   * to skip past in RPGs.
   */
  public static DialogBox textBox = null;
  
  /** 
   * The DialogBox for input choices that you regret mashing
   * ENTER to skip past.
   */
  public static DialogBox choiceBox = null;
  
  /** 
   * Whether the dialog is currently blocking, waiting for some in-game event 
   * to complete before continuing. 
   */
  private static boolean isBlocking = false;
  
  
  //////// Dialog maker API
  
  /** 
   * Read a map of dialogs from an XML file, external or as a resource, 
   * depending on the value of readXmlFrom. 
   */
  public static Map<String, DialogModel> readDialogs(String path) {
    if(readXmlFrom == READ_FROM_FILE) {
      Map<String, DialogModel> result = DialogModel.createDialogsFromXMLFile(new File(path));
      
      // If we couldn't read the file for the script, try loading it from the game's resources.
      if(result == null) {
        result = DialogModel.createDialogsFromXMLResource(path);
      }
      
      return result;
    }
    else {
      return DialogModel.createDialogsFromXMLResource(path);
    }
  }
  
  
  
  
  //////// Rendering
  
  /** Renders the dialogs. */
  public static void render(Graphics2D g) {
    if(textBox != null && !isBlocking) {
      textBox.render(g);
    }
    if(choiceBox != null) {
      choiceBox.render(g);
      
      double cursorY = choiceBox.textSprite.getRowYOffset(inputIndex);
      Image cursorImg = MenuImages.pointer();
      g.drawImage(cursorImg, (int) (choiceBox.x+5), (int) (choiceBox.y + 16 + cursorY) , null);
    }
  }
  
  
  //////// Dialog control
  
  /** 
   * Tries to run the interactive view for the current dialog model. 
   * Returns true iff there is a dialog active. 
   */
  public static boolean run(Keyboard keyboard) {
    if(textBox == null) {
      return false;
    }
    
    textBox.textSprite.advOne();
    
    if(curNode instanceof InputDiaNode) {
      runInputDiaNode(keyboard, (InputDiaNode) curNode);
    }
    else if(curNode instanceof SwitchDiaNode) {
      // TODO
    }
    else if(curNode instanceof EventDiaNode) {
      
      // Run the event until it unblocks.
      EventDiaNode eNode = (EventDiaNode) curNode;
      PonyMain.doEventString(eNode.event);
      if(!isBlocking()) {
        setDialogNode(model.getNext(curNode));
      }
    }
    else {
      runSequenceDiaNode(keyboard, curNode);
    }
    
    
    return true;
  }
  
  
  /** Run the interactive view for an ordinary DiaNode. */
  private static void runSequenceDiaNode(Keyboard keyboard, DiaNode node) {
  
    // Enter either finishes the current paragraph or proceeds to next paragraph/node.
    if(keyboard.justPressed(KeyEvent.VK_Z)) {
      if(textBox.textSprite.isParaDone()) {
          
        // Advance to next paragraph in the current node.
        if(textBox.textSprite.hasNextPara()) {
          textBox.textSprite.nextPara();
        }
        
        // If we've run out of text for the node, go to the next node.
        else {
          setDialogNode(model.getNext(node));
        }
      }
      else {
        textBox.textSprite.advAll();
      }
    }
  }
  
  
  
  private static int inputIndex = 0;
  
  /** Run the interactive view for an InputDiaNode. */
  private static void runInputDiaNode(Keyboard keyboard, InputDiaNode node) {
    
    // Display and interact with the choices when the prompt is displayed entirely.
    if(textBox.textSprite.isParaDone()) {
      
      // Create the display for the choices if it doesn't exist.
      if(choiceBox == null) {
        inputIndex = 0;
        
        // Create the text string
        String choices = "";
        boolean first = true;
        for(String choice : node.choices) {
          if(!first) {
            choices += "\n";
          }
          first = false;
          choices += choice;
        }
        
        // Measure the text so we know how big to make the choiceBox.
        Dimension size = MenuImages.font().getDimensions(choices);
        size = new Dimension(size.width + 32+16, size.height + 32);
        
        // Make the choiceBox. 
        choiceBox = new DialogBox( textBox.x + textBox.getWidth() - size.width-16, textBox.y + 16 - size.height, size.width, size.height);
        choiceBox.textSprite.setText(choices);
        choiceBox.textSprite.advAll();
      }
      
      // Pressing up/down moves the choice selection cursor.
      if(keyboard.justPressedRep(KeyEvent.VK_UP)) {
        // TODO : sound
        
        inputIndex--;
        if(inputIndex < 0) {
          inputIndex = node.choices.size() - 1;
        }
      }
      
      if(keyboard.justPressedRep(KeyEvent.VK_DOWN)) {
        // TODO : sound
      
        inputIndex++;
        if(inputIndex >= node.choices.size()) {
          inputIndex = 0;
        }
      }
      
      // Pressing Enter proceeds to the appropriate dialog branch for the selected input.
      if(keyboard.justPressed(KeyEvent.VK_Z)) {
        String choice = node.choices.get(inputIndex);
        setDialogNode(model.getNext(node, choice));
      }
      
    }
    
    // If the prompt is not done printing, Enter makes it finish printing.
    else if(keyboard.justPressed(KeyEvent.VK_Z)) {
      textBox.textSprite.advAll();
    }
    
  }
  
  
  
  
  /** Begin displaying a dialog using a DialogModel. */
  public static void showDialog(DialogModel dialog) {
    if(textBox == null) {
      textBox = new DialogBox(10, PonyMain.resHeight - DEF_HEIGHT - 10, DEF_WIDTH, DEF_HEIGHT);
      model = dialog;
      setDialogNode(dialog.getFirst());
    }
  }
  
  
  
  /** Sets the current dialog node to display and interact with in the dialog system. */
  private static void setDialogNode(DiaNode node) {
    
    curNode = node;
    choiceBox = null;
    
    // End the dialog if node is null.
    if(node == null) {
      endDialog();
    }
    else {
      textBox.textSprite.paragraphs.clear();
      
      // Otherwise, advance the dialog depending on the context of the new node.
      if(node instanceof InputDiaNode) {
        
        InputDiaNode iNode = (InputDiaNode) node;
        textBox.textSprite.setText(iNode.prompt);
        
      }
      else if(node instanceof SwitchDiaNode) {
        
        SwitchDiaNode sNode = (SwitchDiaNode) node;
        String caseMatch = sNode.matchValueToCase(PonyMain.getVarString(sNode.variable));
        setDialogNode(model.getNext(node, caseMatch));
      }
      else if(node instanceof EventDiaNode) {
        
        EventDiaNode eNode = (EventDiaNode) node;
        if(eNode.blocking) {
          isBlocking = true;
        }
        else {
          // nonblocking events run their logic only 1 time.
          PonyMain.doEventString(eNode.event);
          setDialogNode(model.getNext(node));
        }
      }
      else {
        
        textBox.textSprite.makeParagraphs(node.text, MAX_LINES);
        textBox.textSprite.setPara(0);
      }
    }
    
  }
  
  
  /** 
   * Returns true iff the dialog is currently blocking, waiting for some 
   * in-game event to complete before continuing. 
   */
  public static boolean isBlocking() {
    return isBlocking;
  }
  
  /** Stops the dialog from blocking so that it may continue. */
  public static void unblock() {
    isBlocking = false;
  }
  
  
  /** Ends the current dialog. */
  public static void endDialog() {
    textBox = null;
    choiceBox = null;
  }
  
}