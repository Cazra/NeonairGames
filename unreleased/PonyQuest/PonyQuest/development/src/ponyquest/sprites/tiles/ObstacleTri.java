package ponyquest.sprites.tiles;

import java.awt.*;
import java.awt.geom.*;

import pwnee.GameMath;
import pwnee.geom.Polygon2D;
import pwnee.image.*;
import pwnee.sprites.Sprite;

/** 
 * A triangular obstacle tile.
 */
public class ObstacleTri extends ObstacleTile {
  
  public ObstacleTri(double x, double y) {
    super(x, y);
    width = 32;
    height = 32;
  }
  
  public ObstacleTri() {
    this(0, 0);
  }
  
  
  public Polygon2D getCollisionPoly() {
    double scaleX = this.scaleX*scaleUni;
    double scaleY = this.scaleY*scaleUni;
    
    double left = -focalX*scaleX;
    double top = -focalY*scaleY;
    double right = left + width*scaleX;
    double bottom = top + height*scaleY;
    
    int numPts = 3;
    
    // define the rectangle's points in clockwise order (in game coordinates, y axis is down).
    Point2D[] p = new Point2D[numPts];
    p[0] = new Point2D.Double(left, top);
    p[1] = new Point2D.Double(right, bottom);
    p[2] = new Point2D.Double(left, bottom);
    
    double[] x = new double[numPts];
    double[] y = new double[numPts];
    
    for(int i = 0; i < numPts; i++) {
      // rotate, then translate the point.
      AffineTransform transform = AffineTransform.getTranslateInstance(this.x, this.y);
      transform.rotate(0-GameMath.d2r(angle));
      
      p[i] = transform.transform(p[i], null);
      
      x[i] = p[i].getX();
      y[i] = p[i].getY();
    }
    
    return new Polygon2D(x, y);
  } 
  
  
  
  public void draw(Graphics2D g) {
    Image curImg = ObstacleTile.images.get("triangle");
    g.drawImage(curImg, 0, 0, null);
  }
}

