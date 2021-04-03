package ponyquest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

import pwnee.*;

import ponyquest.dialog.DialogSystem;
import ponyquest.dialog.DialogBox;
import ponyquest.json.*;
import ponyquest.menu.MenuImages;
import ponyquest.sprites.characters.*;
import ponyquest.sprites.tiles.SimpleTile;


/** The GamePanel, providing the top-level logic and rendering canvas for the game. */
public class PonyPanel extends GamePanel {
  
  /** Singleton instance. */
  public static PonyPanel instance = null;
  
  private PonyPanel() {
    super();
    MenuImages.loadImages();
  }
  
  public static PonyPanel getInstance() {
    if(instance == null) {
      instance = new PonyPanel();
      instance.reset();
    }
    return instance;
  }
  
  
  /** Resets the PonyPanel to the start of the game. */
  public void reset() {
    changeLevel(FieldModeLevel.getInstance());
  }
  
  
  public void logic() {
    super.logic();
    if(this.isLoading) {
      return;
    }    
    
    if(curLevel != null) {
      // DialogSystem.runDialog(keyboard);
      curLevel.logic();
    }
  }
  
  
  
  /** The game is resized to x2 to look all pixelly and retro-style. */
  public void paint(Graphics gg) {
    if(PonyMain.instance == null) {
      return;
    }
    
    Graphics2D g2D = (Graphics2D) gg;
    g2D.setTransform(PonyMain.getBaseTransform());
    
    // set the background color to white.
    this.setBackground(new Color(0xFFFFFF));
    super.paint(gg);
    
    BufferedImage bi = new BufferedImage((int) PonyMain.instance.getWidth(), (int) PonyMain.instance.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bi.createGraphics();
    
    if(curLevel != null) {
      curLevel.render(g);
    }
    
    // Set our drawing color to black
    g.setColor(new Color(0x000000));
    
    // display the current frame rate.
    g.drawString("" + timer.fpsCounter, 10,32);
    
    g.dispose();
    gg.drawImage(bi, 0, 0, null);
  }
  
  
  
  
  
}
