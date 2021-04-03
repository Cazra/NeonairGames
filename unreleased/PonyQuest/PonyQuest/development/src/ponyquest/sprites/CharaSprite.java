package ponyquest.sprites;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyEvent;

import pwnee.GameMath;
import pwnee.geom.*;
import pwnee.image.*;
import pwnee.input.Keyboard;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.Directions;

/** 
 * Superclass for all character sprites. This includes ponies, monsters, and 
 * other things with personalities.
 */
public abstract class CharaSprite extends LayerSprite {
  
  
  /** Stopped animation ID */
  public static int STOPPED = 0;
  
  /** Walking animation ID */
  public static int WALKING = 1;
  public int walkAnimSpeed = 10;
  
  /** Running animation ID */
  public static int RUNNING = 2;
  public int runAnimSpeed = 5;
  
  /** Flying animation ID */
  public static int FLYING = 3;
  
  
  
  /** The sprite's current animation. */
  public int animation = STOPPED;
  
  /** The direction the sprite is currently facing. */
  public int direction = Directions.SOUTH;
  
  
  
  
  /** The timer for controlling which frame the animation is currently on. */
  public int animTimer = 0;
  
  /** The current animation frame used to display the sprite. */
  protected Image curImg;
  
  
  
  
  public CharaSprite(double x, double y) {
    super(x, y);
  }
  
  
  //////// Animation
  
  
  /** 
   * Makes this character face toward another sprite. 
   * Returns the new direction this character is facing. 
   */
  public int lookAt(Sprite other) {
    return lookInDirection(GameMath.angleTo(this.x, this.y, other.x, other.y));
  }
  
  /** 
   * Sets the character's direction to be the one closest to the given angle. 
   * Returns the new direction this character is facing
   */
  public int lookInDirection(double angle) {
    if(angle <= 45) {
      direction = Directions.EAST;
    }
    else if(angle <= 135) {
      direction = Directions.NORTH;
    }
    else if(angle <= 225) {
      direction = Directions.WEST;
    }
    else if(angle <= 315) {
      direction = Directions.SOUTH;
    }
    else {
      direction = Directions.EAST;
    }
    
    return direction;
  }
  
  
  /** Sets the current animation for this sprite and resets the animation timer to 0. */
  public void setAnimation(int animID) {
    int oldAnim = animation;
    animation = animID;
    
    if(oldAnim != animation) {
      animTimer = 0;
    }
  }
  
  /** Performs 1 step of animation on this sprite. */
  public abstract void animate();
  
  
  /** 
   * Retrieves a frame for a character sprite, given the character name, 
   * animation, direction, and frame number from the game's ImageLibrary. 
   */
  public static Image getImage(String character, int animation, int direction, int frame) {
    String imgKey = character;
    
    if(animation == CharaSprite.STOPPED) {
      imgKey += "Stop";
    }
    else if(animation == CharaSprite.WALKING) {
      imgKey += "Walk";
    }
    else if(animation == CharaSprite.RUNNING) {
      imgKey += "Run";
    }
    else {
      throw new RuntimeException("Invalid animation value: " + animation);
    }
    
    if(direction == Directions.NORTH) {
      imgKey += "N";
    }
    else if(direction == Directions.EAST) {
      imgKey += "E";
    }
    else if(direction == Directions.SOUTH) {
      imgKey += "S";
    }
    else if(direction == Directions.WEST) {
      imgKey += "W";
    }
    else {
      throw new RuntimeException("Invalid direction value: " + direction);
    }
    
    imgKey += frame;
    
    return PonyMain.imageLib.get(imgKey);
  }
  
  
  //////// Movement
  
  /** The base movement speed for the character. */
  public double getBaseSpeed() {
    return 1.5;
  }
  
  
  /** 
   * Makes the character walk toward another sprite. 
   * Returns the vector of the movement.
   */
  public Vector2D aiWalkTowards(Sprite other) {
    double speed = getBaseSpeed();
    double angle = GameMath.angleTo(this.x, this.y, other.x, other.y);
    animation = WALKING;
    lookInDirection(angle);
    
    return move(speed, angle);
  }
  
  /** 
   * Makes the character run toward another sprite. 
   * Returns the vector of the movement.
   */
  public Vector2D aiRunTowards(Sprite other) {
    double speed = getBaseSpeed() * 2;
    double angle = GameMath.angleTo(this.x, this.y, other.x, other.y);
    animation = RUNNING;
    lookInDirection(angle);
    
    return move(speed, angle);
  }
  
  
  /** 
   * Moves the character at the given speed in the direction of the given angle. 
   * Returns the vector for the movement.
   */
  public Vector2D move(double speed, double angle) {
    double origX = x;
    double origY = y;
    
    x += speed*GameMath.cos(angle);
    y -= speed*GameMath.sin(angle);
    
    return new Vector2D(x - origX, y - origY);
  }
  
  
  //////// Collisions
  
  @Override
  public Polygon2D getCollisionPoly() {
    return Polygon2D.rectToPoly(getCollisionBox());
  }
  
  
  /** Is this sprite colliding with any ObstacleTiles in the its current layer? */
  public boolean overlappingObstacle() {
    Polygon2D myPoly = getCollisionPoly();
    for(Sprite obstacle : curLayer.getAllObstacles()) {
      if(myPoly.intersects(obstacle.getCollisionPoly())) {
        return true;
      }
    }
    return false;
  }
  
}