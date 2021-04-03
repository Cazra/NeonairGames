package ponyquest.sprites.characters;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

public class GrannySmithSprite extends PonySprite {
  
  private static final String imgKey = "GrannySmith";
  
  public GrannySmithSprite(double x, double y) {
    super(x,y);
    walkAnimSpeed *= 2;
    runAnimSpeed *= 2;
  }
  
  /** Loads the pony's images into the game's ImageLibrary. */
  public static void loadImages() {
    PonySprite.loadImages(imgKey, "graphics/ponies/GrannySmith.png");
  }
  
  /** Gets the image key prefix for this pony. */
  public String getImgKey() {
    return imgKey;
  }
  
  
  public double getBaseSpeed() {
    return 0.5;
  }
  
}

