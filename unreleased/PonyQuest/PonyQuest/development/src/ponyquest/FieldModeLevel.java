package ponyquest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import javax.swing.JOptionPane;

import pwnee.*;

import ponyquest.dialog.DialogSystem;
import ponyquest.json.*;
import ponyquest.maps.AreaMap;
import ponyquest.maps.AnimationTestMap;
import ponyquest.maps.Layer;
import ponyquest.menu.MenuResult;
import ponyquest.menu.PauseMenu;
import ponyquest.Player;
import ponyquest.sprites.characters.*;
import ponyquest.sprites.tiles.WarpTile;


/** 
 * This is the root level for the game which implements the field movement. From
 * here the combat system, dialogs, pause menu, and several other sub-modes can 
 * be accessed.
 * 
 * Most of the actual game logic is delegated to the current AreaMap 
 * the player is playing in though.
 */
public class FieldModeLevel extends Level {
  
  /** The most recently created instance of this. */
  private static FieldModeLevel instance = null;
  
  /** Camera centered on player. */
  public Camera camera;
  
  /** The map used by this test level. */
  public AreaMap map;
  
  
  //////// Initialization 
  
  private FieldModeLevel(PonyPanel panel) {
    super(panel);    
    camera = new Camera(panel);
    camera.focalX = PonyMain.resWidth/2;
    camera.focalY = PonyMain.resHeight/2;
  }
  
  /** Gets the singleton instance for this. */
  public static FieldModeLevel getInstance() {
    if(instance == null) {
      instance = new FieldModeLevel(PonyPanel.getInstance());
      instance.reset();
    }
    return instance;
  }
  
  
  /** Disposes of the singleton instance so that a fresh one can be made. */
  public void clean() {
    instance = null;
  }
  
  
  
  /** Resets the field mode to its initial state. */
  public void reset() {
  //  String ponyName = JOptionPane.showInputDialog("Enter pony name.");
  //  Player.sprite = PonyFactory.getPony(ponyName);
    
    map = new AnimationTestMap();
  }
  
  
  //////// Loading
  
  /** Loads all the pony sprites into the game's ImageLibrary. */
  public void loadData() {
    PonyFactory.loadImages();
  }
  
  
  
  
  //////// Update game model
  
  public void logic() {
    if(game.isLoading) {
      return;
    }    
    
    if(PauseMenu.isActive()) {
      MenuResult result = PauseMenu.getInstance().runMenu(keyboard);
    }
    else if(DialogSystem.run(keyboard)) {
      // 
    }
    else {
      Player.move(keyboard);
      if(keyboard.justPressed(KeyEvent.VK_Z)) {
        Player.check(map);
      }
      
      if(keyboard.justPressed(KeyEvent.VK_Q)) {
        PauseMenu.activate();
      }
    }
    map.logic();
    
    Player.animate();
    if(Player.sprite != null) {
      camera.x = Player.sprite.x;
      camera.y = Player.sprite.y;
    }
  }
  
  
  
  //////// Rendering
  
  
  public void render(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    
    // apply camera transform.
    camera.update();
    g.transform(camera.getTrans());
    
    drawOrigin(g);
    map.render(g);
    
    if(Layer.drawCollisionPolygons) {
      Player.getTalkBox().getCollisionPoly().draw(g);
    }
    
    g.setTransform(origTrans);
    
    // Render the pause menu if it is active.
    if(PauseMenu.isActive()) {
      PauseMenu.getInstance().render(g);
    }
    
    // Render the Dialogs UI.
    DialogSystem.render(g);
  }
  
  
  
  
  /** 
   * Draws a red grid centered at the origin with lines spaced 32 pixels 
   * apart, assuming no scaling is involved in the current transform. 
   */
  public static void drawOrigin(Graphics2D g) {
    g.setColor(new Color(0xFFCCCC));
    
    int min = -32*10;
    int max = 32*10;
    for(int i = min; i <= max; i += 32) {
      g.drawLine(min, i, max, i);
      g.drawLine(i, min, i, max);
    }
    
    g.setColor(new Color(0xFF0000));
    g.drawLine(min, 0, max, 0);
    g.drawLine(0, min, 0, max);
  }
}

