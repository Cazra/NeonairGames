package ponyquest.sprites.hidden;

import java.awt.*;

import pwnee.image.*;

import ponyquest.Directions;
import ponyquest.PonyMain;
import ponyquest.sprites.HiddenSprite;
import ponyquest.sprites.CharaSprite;


/** A hidden collision sprite used to change the direction of characters. */
public class DirectionSprite extends HiddenSprite {

  /** The direction of this DirectionSprite. */
  public int direction;
  
  
  /** Creates the DirectionSprite with the given direction value (see ponyquest.Directions). */
  public DirectionSprite(int direction) {
    super(0,0);
    if(!loadedImages) {
      loadImages();
    }
    this.direction = direction;
  }
  
  
  
  //////// application
  
  /** Sets a CharaSprite's direction to the direction of this sprite. */
  public void applyTo(CharaSprite sprite) {
    sprite.direction = this.direction;
  }
  
  
  //////// Graphics
  
  private static boolean loadedImages = false;
  
  public static void loadImages() {
    loadedImages = true;
    ImageLoader il = new ImageLoader();
    
    // load the source image and set pink as the transparent color.
    Image srcImg = il.loadResource("graphics/arrows.png");
    Image img;
    
    // east
    img = ImageEffects.crop(srcImg, 0, 0, 32, 32);
    il.addImage(img);
    PonyMain.imageLib.put("dirEAST", img);
    
    // north
    img = ImageEffects.crop(srcImg, 32, 0, 32, 32);
    il.addImage(img);
    PonyMain.imageLib.put("dirNORTH", img);
    
    // south
    img = ImageEffects.crop(srcImg, 64, 0, 32, 32);
    il.addImage(img);
    PonyMain.imageLib.put("dirSOUTH", img);
    
    // west
    img = ImageEffects.crop(srcImg, 96, 0, 32, 32);
    il.addImage(img);
    PonyMain.imageLib.put("dirWEST", img);
    
    il.waitForAll();
  }
  
  
  public void draw(Graphics2D g) {
    Image img = null;
    if(direction == Directions.NORTH) {
      img = PonyMain.imageLib.get("dirNORTH");
    }
    else if(direction == Directions.SOUTH) {
      img = PonyMain.imageLib.get("dirSOUTH");
    }
    else if(direction == Directions.EAST) {
      img = PonyMain.imageLib.get("dirEAST");
    }
    else {
      img = PonyMain.imageLib.get("dirWEST");
    }
    
    g.drawImage(img, 0, 0, null);
  }
}


