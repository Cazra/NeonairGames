package ponyquest.sprites.characters;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import pwnee.geom.*;
import pwnee.image.*;
import pwnee.input.Keyboard;
import pwnee.sprites.Sprite;

import ponyquest.Directions;
import ponyquest.PonyMain;
import ponyquest.sprites.CharaSprite;

/** 
 * Superclass for all pony sprites. This provides their common 
 * code for animation and loading image data.
 */
public abstract class PonySprite extends CharaSprite {
  
  
  
  public PonySprite(double x, double y) {
    super(x, y);
  }
  
  
  
  //////// Collisions
  
  @Override
  public Rectangle2D getCollisionBox() {
    double w = 20;
    double h = 15;
    double x = this.x - w/2;
    double y = this.y - 10;
    return new Rectangle2D.Double(x, y, w, h);
  }
  
  
  //////// Graphics
  
  /** 
   * Load the pony's image frames into the game's ImageLibrary. 
   * @param name        The beginning of all image keys for the pony being loaded. 
   * @param imgPath     The path to the image file for the pony.
   * @return            The source image w/ transparency applied, 
   *                    in case a derived class has additional frames to load.
   */
  public static Image loadImages(String name, String imgPath) {
    ImageLoader il = new ImageLoader();
    
    // load the source image and set pink as the transparent color.
    Image srcImg = il.loadResource(imgPath);
    srcImg = ImageEffects.setTransparentColor(srcImg, new Color(0xFF00FF));
    
    // stopped
    PonyMain.imageLib.put(name + "StopE0", loadFrame(il, srcImg, 1, 1));
    PonyMain.imageLib.put(name + "StopN0", loadFrame(il, srcImg, 52, 1));
    PonyMain.imageLib.put(name + "StopW0", loadFrame(il, srcImg, 103, 1));
    PonyMain.imageLib.put(name + "StopS0", loadFrame(il, srcImg, 154, 1));
    
    // walking
    PonyMain.imageLib.put(name + "WalkE0", loadFrame(il, srcImg, 1, 38));
    PonyMain.imageLib.put(name + "WalkE1", loadFrame(il, srcImg, 52, 38));
    PonyMain.imageLib.put(name + "WalkE2", loadFrame(il, srcImg, 103, 38));
    PonyMain.imageLib.put(name + "WalkE3", loadFrame(il, srcImg, 154, 38));
    
    PonyMain.imageLib.put(name + "WalkN0", loadFrame(il, srcImg, 1, 75));
    PonyMain.imageLib.put(name + "WalkN1", loadFrame(il, srcImg, 52, 75));
    PonyMain.imageLib.put(name + "WalkN2", loadFrame(il, srcImg, 103, 75));
    PonyMain.imageLib.put(name + "WalkN3", loadFrame(il, srcImg, 154, 75));
    
    PonyMain.imageLib.put(name + "WalkW0", loadFrame(il, srcImg, 1, 112));
    PonyMain.imageLib.put(name + "WalkW1", loadFrame(il, srcImg, 52, 112));
    PonyMain.imageLib.put(name + "WalkW2", loadFrame(il, srcImg, 103, 112));
    PonyMain.imageLib.put(name + "WalkW3", loadFrame(il, srcImg, 154, 112));
    
    PonyMain.imageLib.put(name + "WalkS0", loadFrame(il, srcImg, 1, 149));
    PonyMain.imageLib.put(name + "WalkS1", loadFrame(il, srcImg, 52, 149));
    PonyMain.imageLib.put(name + "WalkS2", loadFrame(il, srcImg, 103, 149));
    PonyMain.imageLib.put(name + "WalkS3", loadFrame(il, srcImg, 154, 149));
    
    // running
    PonyMain.imageLib.put(name + "RunE0", loadFrame(il, srcImg, 206, 38));
    PonyMain.imageLib.put(name + "RunE1", loadFrame(il, srcImg, 257, 38));
    PonyMain.imageLib.put(name + "RunE2", loadFrame(il, srcImg, 308, 38));
    PonyMain.imageLib.put(name + "RunE3", loadFrame(il, srcImg, 359, 38));
    
    PonyMain.imageLib.put(name + "RunN0", loadFrame(il, srcImg, 206, 75));
    PonyMain.imageLib.put(name + "RunN1", loadFrame(il, srcImg, 257, 75));
    PonyMain.imageLib.put(name + "RunN2", loadFrame(il, srcImg, 308, 75));
    PonyMain.imageLib.put(name + "RunN3", loadFrame(il, srcImg, 359, 75));
    
    PonyMain.imageLib.put(name + "RunW0", loadFrame(il, srcImg, 206, 112));
    PonyMain.imageLib.put(name + "RunW1", loadFrame(il, srcImg, 257, 112));
    PonyMain.imageLib.put(name + "RunW2", loadFrame(il, srcImg, 308, 112));
    PonyMain.imageLib.put(name + "RunW3", loadFrame(il, srcImg, 359, 112));
    
    PonyMain.imageLib.put(name + "RunS0", loadFrame(il, srcImg, 206, 149));
    PonyMain.imageLib.put(name + "RunS1", loadFrame(il, srcImg, 257, 149));
    PonyMain.imageLib.put(name + "RunS2", loadFrame(il, srcImg, 308, 149));
    PonyMain.imageLib.put(name + "RunS3", loadFrame(il, srcImg, 359, 149));
    
    il.waitForAll();
    
    return srcImg;
  }
  
  protected static Image loadFrame(ImageLoader il, Image srcImg, int x, int y) {
    Image img = ImageEffects.crop(srcImg, x, y, 50, 36);
    il.addImage(img);
    return img;
  }
  
  
  
  /** Gets the image key prefix for this pony (usually the pony's name). */
  public abstract String getImgKey();
  
  /** Does 1 step of animation. */
  @Override
  public void animate() {  
    // set focal point based on direction.
    if(direction == Directions.EAST) {
      focalX = 30;
      focalY = 30;
    }
    else if(direction == Directions.NORTH) {
      focalX = 25;
      focalY = 27;
    }
    else if(direction == Directions.WEST) {
      focalX = 19;
      focalY = 30;
    }
    else if(direction == Directions.SOUTH) {
      focalX = 25;
      focalY = 30;
    }
    else {
      throw new RuntimeException("Invalid direction value: " + direction);
    }
    
    // select the frame to use for the animation. 
    int frame = -1;
    if(animation == STOPPED) {
      frame = _getStoppedFrame();
    }
    else if(animation == WALKING) {
      frame = _getWalkingFrame();
    }
    else if(animation == RUNNING) {
      frame = _getRunningFrame();
    }
    else {
      throw new RuntimeException("Invalid animation value: " + animation);
    }
    
    curImg = CharaSprite.getImage(getImgKey(), animation, direction, frame);
    animTimer++;
  }
  
  
  protected int _getStoppedFrame() {
    return 0;
  }
  
  
  protected int _getWalkingFrame() {
    int frameNum = animTimer/walkAnimSpeed;
    if(frameNum >= 4) {
      frameNum = 0;
      animTimer = 0;
    }
    return frameNum;
  }
  
  
  protected int _getRunningFrame() {
    int frameNum = animTimer/runAnimSpeed;
      if(frameNum >= 4) {
        frameNum = 0;
        animTimer = 0;
      }
      return frameNum;
  }
  
  
  public void draw(Graphics2D g) {
    g.drawImage(curImg, 0, 0, null);
  }
}

