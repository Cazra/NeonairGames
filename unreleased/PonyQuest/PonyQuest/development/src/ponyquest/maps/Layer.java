package ponyquest.maps;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.sprites.LayerSprite;

/** 
 * A graphics layer on a map.
 */
public class Layer {
  
  /** If true, draw the collision boxes for sprites in this layer. */
  public static boolean drawCollisionPolygons = false;
  
  
  /** Optional name for this layer. */
  public String name = "";
  
  /** 
   * The list of non-tile sprites residing in this layer. These sprites are 
   * drawn from north to south rather than just in the order they're read from 
   * the map file. 
   */
  public List<Sprite> sprites = new LinkedList<>();
  
  /** The list of tiles residing in this layer. (These are drawn under the other sprites and aren't sorted from north to south) */
  public List<Sprite> tiles = new LinkedList<>();
  
  /** Invisible collision-detection obstacles. */
  public List<Sprite> obstacles = new LinkedList<>();
  
  /** Opacity for the layer. */
  public double opacity = 1.0;
  
  
  
  /** 
   * Renders the layer. Tile sprites are rendered first in list order. 
   * Then non-tile sprites are rendered from north to south.
   * Obstacle colliders are only rendered if ObstacleTile.showObstacles is true.
   */
  public void render(Graphics2D gg) {
    AffineTransform origTrans = gg.getTransform();
    
    // We draw the layer inside an image so that we can apply color filters to 
    // the whole layer if we want.
    BufferedImage layerImg = new BufferedImage(PonyMain.resWidth, PonyMain.resHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = layerImg.createGraphics();
    g.setTransform(gg.getTransform());
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
    
    // tiles don't need to be sorted. We'll just draw them in their list order.
    g.setColor(new Color(0xFF0000));
    for(Sprite sprite : tiles) {
      sprite.render(g);
      if(drawCollisionPolygons) {
        sprite.getCollisionPoly().draw(g);
      }
    }
    
    // draw the sprites in order from north to south.
    Collections.sort(sprites, new NorthToSouthComparator());
    for(Sprite sprite : sprites) {
      sprite.render(g);
      if(drawCollisionPolygons) {
        sprite.getCollisionPoly().draw(g);
      }
    }
    
    // obstacles are invisible, but they may be drawn for debugging. 
    for(Sprite sprite : obstacles) {
      sprite.render(g);
      if(drawCollisionPolygons) {
        sprite.getCollisionPoly().draw(g);
      }
    }
    
    // Draw the image for the layer and then restore our transform.
    gg.setTransform(new AffineTransform());
    gg.drawImage(layerImg, 0, 0, null);
    gg.setTransform(origTrans);
  }
  
  
  /** 
   * Removes a sprite from this layer. 
   * Returns true if successful.
   */
  public boolean remove(Sprite sprite) {
    return sprites.remove(sprite);
  }
  
  /** Adds a sprite to this layer and returns the sprite for chaining. */
  public Sprite add(Sprite sprite) {
    sprites.add(sprite);
    return sprite;
  }
  
  
  /** Adds a tile sprite to this layer and returns the sprite for chaining. */
  public Sprite addTile(Sprite sprite) {
    tiles.add(sprite);
    return sprite;
  }
  
  /** 
   * Adds an invisible collision obstacle to this layer and returns the sprite 
   * for chaining. 
   */
  public Sprite addObstacle(Sprite sprite) {
    obstacles.add(sprite);
    return sprite;
  }
  
  
  /** 
   * Returns the union of all sprites in the obstacles list and all   
   * LayerSprites in the sprites list with the isObstacle flag set to true.
   */
  public List<Sprite> getAllObstacles() {
    List<Sprite> result = new ArrayList<>(obstacles);
    for(Sprite sprite : sprites) {
      if(sprite instanceof LayerSprite && ((LayerSprite) sprite).isObstacle) {
        result.add(sprite);
      }
    }
    return result;
  }
}
