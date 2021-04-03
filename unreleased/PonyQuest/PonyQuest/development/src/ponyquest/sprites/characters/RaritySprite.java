package ponyquest.sprites.characters;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

public class RaritySprite extends PonySprite {
  
  private static final String imgKey = "Rarity";
  
  public RaritySprite(double x, double y) {
    super(x,y);
  }
  
  /** Loads the pony's images into the game's ImageLibrary. */
  public static void loadImages() {
    PonySprite.loadImages(imgKey, "graphics/ponies/Rarity.png");
  }
  
  /** Gets the image key prefix for this pony. */
  public String getImgKey() {
    return imgKey;
  }
  
}

