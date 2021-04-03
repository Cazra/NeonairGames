package ponyquest.sprites.characters;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

public class MayorMareSprite extends PonySprite {
  
  private static final String imgKey = "MayorMare";
  
  public MayorMareSprite(double x, double y) {
    super(x,y);
  }
  
  /** Loads the pony's images into the game's ImageLibrary. */
  public static void loadImages() {
    PonySprite.loadImages(imgKey, "graphics/ponies/MayorMare.png");
  }
  
  /** Gets the image key prefix for this pony. */
  public String getImgKey() {
    return imgKey;
  }
  
}

