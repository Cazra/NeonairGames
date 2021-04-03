package ponyquest.sprites;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.maps.Layer;

/** 
 * Any kind of sprite that resides in a Layer in an AreaMap. This class
 * provides support for maintaining the sprite's layer state and moving the sprite
 * to other layers if necessary.
 */
public abstract class LayerSprite extends Sprite {
  
  /** The layer this Sprite currently is residing in. */
  public Layer curLayer;
  
  /** Does this sprite count as an obstacle for the player's movement? */
  public boolean isObstacle = false;
  
  public LayerSprite(double x, double y) {
    super(x,y);
  }
  
  
  /** When the sprite is destroyed, remove it from its layer. */
  public void destroy() {
    super.destroy();
    if(curLayer != null) {
      curLayer.remove(this);
    }
  }
  
  
  // Gets a placeholder for images not loaded in the game's ImageLibrary.
  public static Image getBadImage() {
    if(PonyMain.imageLib.get("tile:badTile") == null) {
      System.out.println("loading badTile");
      
      ImageLoader il = new ImageLoader();
      Image img = il.loadResource("graphics/tiles/badTile.png");
      PonyMain.imageLib.put("tile:badTile", img);
      il.addImage(img);
      il.waitForAll();
    }
    return PonyMain.imageLib.get("tile:badTile");
  }
  
  
  
  
  /** Obtains the layer that this sprite is in. */
  public Layer getLayer() {
    return curLayer;
  }
  
  /** Safely changes the layer that this sprite currently resides in. */
  public void setLayer(Layer newLayer) {
    if(curLayer != null) {
      curLayer.remove(this);
    }
    curLayer = newLayer;
    newLayer.add(this);
  }
  
  /** Returns true iff this and another sprite are in the same layer. */
  public boolean inSameLayer(LayerSprite other) {
    return (this.curLayer == other.curLayer);
  }
  
  
  
  /** See if this sprite is colliding with another sprite in the same layer. */
  public boolean collideWith(LayerSprite other) {
    return (this.inSameLayer(other) && this.getCollisionPoly().intersects(other.getCollisionPoly()));
  }
}

