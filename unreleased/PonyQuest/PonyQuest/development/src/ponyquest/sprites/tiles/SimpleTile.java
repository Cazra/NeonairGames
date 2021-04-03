package ponyquest.sprites.tiles;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;

import pwnee.geom.Polygon2D;
import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.sprites.LayerSprite;

/** A single-frame ground or scenery tile that might be tiled (its image repeats along its X and/or Y axes). */
public class SimpleTile extends LayerSprite {

  public Image img;
  
  public String type;
  
  public double tileX = 1.0;
  
  public double tileY = 1.0;
  
  public SimpleTile(String type, double x, double y) {
    super(x, y);
    this.type = type;
    
    // Try to load the tile's image.
    img = PonyMain.imageLib.get("tile:" + type);
    if(img == null) {
      try {
        img = loadTile(PonyMain.imageLib, type);
      }
      catch(Exception e) {
        img = LayerSprite.getBadImage();
      }
    }
  }
  
  public SimpleTile(String type) {
    this(type, 0, 0);
  }
  
  
  /** Loads the placeholder graphic for bad tile images. */
  public static void loadBadTile(ImageLibrary imageLib) {
    ImageLoader il = new ImageLoader();
    Image img = il.loadResource("graphics/tiles/badTile.png");
    imageLib.put("tile:badTile", img);
    il.addImage(img);
    il.waitForAll();
  }
  
  /** Automatically loads the image of the tile. */
  public static Image loadTile(ImageLibrary imageLib, String tileName) {
    ImageLoader il = new ImageLoader();
    Image img = il.loadResource("graphics/tiles/" + tileName + ".png");
    img = ImageEffects.setTransparentColor(img, new Color(0xFF00FF));
    imageLib.put("tile:" + tileName, img);
    il.addImage(img);
    il.waitForAll();
    return img;
  }
  
  
  public void draw(Graphics2D g) {
    
    if(tileX == 0 && tileY == 0) {
      g.drawImage(img, 0, 0, null);
    }
    else {
      width = img.getWidth(null);
      height = img.getHeight(null);
      
      // Repeat the image of the tile if either of its tiling values are set.
      BufferedImage bi = new BufferedImage((int) (width*tileX), (int) (height*tileY), BufferedImage.TYPE_INT_ARGB);
      Graphics2D bg = bi.createGraphics();
      
      for(int i = 0; i <= tileX; i++) {
        for(int j = 0; j <= tileY; j++) {
          bg.drawImage(img, (int) width*i, (int) height*j, null);
        }
      }
      
      bg.dispose();
      g.drawImage(bi, 0, 0, null);
    }
  }
  
  
  @Override
  public Polygon2D getCollisionPoly() {
    return Polygon2D.rectToPoly(getCollisionBox());
  }
  
}
