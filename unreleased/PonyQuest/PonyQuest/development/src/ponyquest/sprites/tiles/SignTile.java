package ponyquest.sprites.tiles;

import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.Map;

import pwnee.image.*;
import pwnee.geom.Polygon2D;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.sprites.LayerSprite;

/** A sprite for various types of sign tiles. */
public class SignTile extends SimpleTile {

  public final static String SIGN1 = "sign01";
  
  public Image img;
  
  public SignTile(String type, double x, double y) {
    super(type, x, y);
    
    if(type.equals(SIGN1)) {
      focalX = 12;
      focalY = 22;
      isObstacle = true;
    }
  } 
  
  public SignTile(String type) {
    this(type, 0, 0);
  }
  
  
  /** Determine the collision box from the rock's type. */
  public Rectangle2D getCollisionBox() {
    double left = x;
    double top = y; 
    double w = 0; 
    double h = 0;
    
    if(type.equals(SIGN1)) {
      left -= 12;
      top -= 4;
      w = 24;
      h = 6;
    }
    
    return new Rectangle2D.Double(left, top, w, h);
  }

}
