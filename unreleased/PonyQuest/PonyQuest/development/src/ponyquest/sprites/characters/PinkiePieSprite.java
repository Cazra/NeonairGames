package ponyquest.sprites.characters;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;

public class PinkiePieSprite extends PonySprite {
  
  private static final String imgKey = "PinkiePie";
  
  public PinkiePieSprite(double x, double y) {
    super(x,y);
  }
  
  /** Loads the pony's images into the game's ImageLibrary. */
  public static void loadImages() {
    Image srcImg = PonySprite.loadImages(imgKey, "graphics/ponies/PinkiePie.png");
    ImageLoader il = new ImageLoader();
    
    // running
    PonyMain.imageLib.put(imgKey + "RunE4", PonySprite.loadFrame(il, srcImg, 410, 38));
    PonyMain.imageLib.put(imgKey + "RunE5", PonySprite.loadFrame(il, srcImg, 461, 38));
    
    PonyMain.imageLib.put(imgKey + "RunN4", PonySprite.loadFrame(il, srcImg, 410, 75));
    PonyMain.imageLib.put(imgKey + "RunN5", PonySprite.loadFrame(il, srcImg, 461, 75));
    
    PonyMain.imageLib.put(imgKey + "RunW4", PonySprite.loadFrame(il, srcImg, 410, 112));
    PonyMain.imageLib.put(imgKey + "RunW5", PonySprite.loadFrame(il, srcImg, 461, 112));
    
    PonyMain.imageLib.put(imgKey + "RunS4", PonySprite.loadFrame(il, srcImg, 410, 149));
    PonyMain.imageLib.put(imgKey + "RunS5", PonySprite.loadFrame(il, srcImg, 461, 149));
    
    il.waitForAll();
  }
  
  /** Gets the image key prefix for this pony. */
  public String getImgKey() {
    return imgKey;
  }
  
  
  @Override
  protected int _getRunningFrame() {
    int frameNum = animTimer/runAnimSpeed;
    if(frameNum >= 6) {
      frameNum = 0;
      animTimer = 0;
    }
    return frameNum;
  }
  
}

