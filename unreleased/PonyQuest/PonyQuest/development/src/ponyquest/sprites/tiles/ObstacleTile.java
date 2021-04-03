package ponyquest.sprites.tiles;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.sprites.LayerSprite;

/** 
 * Superclass for invisible obstacle tiles.
 */
public abstract class ObstacleTile extends LayerSprite {
  
  public static boolean imgLoaded = false;
  
  public static Map<String, Image> images = new HashMap<>();
  
  /** 
   * Whether to show obstacle tiles (which should normally be invisible), 
   * for debugging purposes. 
   */
  public static boolean showObstacles = true;
  
  
  public ObstacleTile(double x, double y) {
    super(x, y);
    if(!imgLoaded) {
      loadImages();
    }
  }
  
  public ObstacleTile() {
    this(0, 0);
  }
  
  
  public static void loadImages() { 
    ImageLoader il = new ImageLoader(); 
    
    Image srcImg = il.loadResource("graphics/tiles/collisions.png");
    srcImg = ImageEffects.setTransparentColor(srcImg, new Color(0xFF00FF));
    
    Image img;
    // square
    img = ImageEffects.crop(srcImg, 1, 1, 32, 32);
    images.put("square", img);
    il.addImage(img);
    
    // triangle
    img = ImageEffects.crop(srcImg, 35, 1, 32, 32);
    images.put("triangle", img);
    il.addImage(img);
    
    il.waitForAll();
  }
  
  
  /** Only draw ObstacleTiles if ObstacleTiles.showObstacles is true. */
  public void render(Graphics2D g) {
    if(showObstacles) {
      super.render(g);
    }
  }
 
}

