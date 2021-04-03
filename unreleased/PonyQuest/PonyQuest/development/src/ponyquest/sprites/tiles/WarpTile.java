package ponyquest.sprites.tiles;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.maps.AreaMap;
import ponyquest.sprites.LayerSprite;

/** An invisible tile used to allow another sprite to move to some other layer. */
public class WarpTile extends LayerSprite {
  
  public static boolean showWarpTiles = true;
  
  public int toLayer;
  
  public WarpTile(int layer, double x, double y) {
    super(x, y);
    toLayer = layer;
    width = 32;
    height = 32;
  }
  
  public WarpTile(int layer) {
    this(layer, 0, 0);
  }
  
  
  
  /** Warp a sprite to this tile's target layer in a map.*/
  public void warp(LayerSprite sprite, AreaMap map) {
    sprite.setLayer(map.layers.get(toLayer));
  }
  
  
  public void draw(Graphics2D g) {
    Image img = LayerSprite.getBadImage();
    g.drawImage(img, 0, 0, null);
  }
}
