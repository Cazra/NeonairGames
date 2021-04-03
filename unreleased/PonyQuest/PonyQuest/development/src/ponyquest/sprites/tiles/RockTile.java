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

/** A sprite for various types of 1-frame rock tiles. */
public class RockTile extends SimpleTile {

  public final static String LARGE1 = "largeRock01";
  
  public Image img;
  
  public RockTile(String type, double x, double y) {
    super(type, x, y);
    
    if(type.equals(LARGE1)) {
      focalX = 21;
      focalY = 41;
      isObstacle = true;
    }
  } 
  
  public RockTile(String type) {
    this(type, 0, 0);
  }
  
  
  /** Determine the collision box from the rock's type. */
  public Rectangle2D getCollisionBox() {
    double left = x;
    double top = y; 
    double w = 0; 
    double h = 0;
    
    if(type.equals(LARGE1)) {
      left += (3-focalX)*scaleX;
      top += -13*scaleY;
      w = (focalX-3) * 2;
      h = 20;
    }
    w*=scaleX;
    h*=scaleY;
    
    return new Rectangle2D.Double(left, top, w, h);
  }
  
  
  public Polygon2D getCollisionPoly() {
    Rectangle2D rect = getCollisionBox();
    return Polygon2D.octToPoly(rect);
  }

}
