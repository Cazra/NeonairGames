package ponyquest.sprites.tiles;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

/** 
 * A square (or rectangular if scaled) obstacle tile.
 */
public class ObstacleSquare extends ObstacleTile {
  
  public ObstacleSquare(double x, double y) {
    super(x, y);
    width = 32;
    height = 32;
  }
  
  public ObstacleSquare() {
    this(0, 0);
  }
  
  
  
  
  public void draw(Graphics2D g) {
    Image curImg = ObstacleTile.images.get("square");
    g.drawImage(curImg, 0, 0, null);
  }
}

