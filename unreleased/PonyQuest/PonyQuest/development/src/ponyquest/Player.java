package ponyquest;

import java.awt.event.KeyEvent;

import pwnee.geom.*;
import pwnee.image.*;
import pwnee.input.Keyboard;
import pwnee.sprites.Sprite;

import ponyquest.Directions;
import ponyquest.maps.AreaMap;
import ponyquest.sprites.CharaSprite;
import ponyquest.sprites.HiddenSprite;

/** Provides player control for a CharaSprite. */
public class Player {
  
  /** The character sprite currently being controlled by the player. */
  public static CharaSprite sprite;
  
  /** Invisible box for detecting things in front of the player to talk to. */
  private static HiddenSprite talkBox = null;
  
  /** Whether player field movement is enabled. */
  public static boolean enableMovement = true;
  
  /** Speed multiplier. E.G. 2.0 if the player has equipped Horseshoes of Running Really Fast. */
  public static double speedMultiplier = 1.0;
  
  
  /** 
   * Moves the character sprite using player input. 
   * @param keyboard  The Keyboard used to control the player.
   * @return a vector for the net movement that occurs. 
   */
  public static Vector2D move(Keyboard keyboard) {
    if(!enableMovement || sprite == null) {
      return new Vector2D(0, 0);
    }
    
    getTalkBox();
    
    double oldX = sprite.x;
    double oldY = sprite.y;
    
    // Do keyboard controls.
    boolean isMoving = false;
    boolean isRunning = false;
    double speed = sprite.getBaseSpeed() * speedMultiplier;
    if(keyboard.isPressed(KeyEvent.VK_X)) {
      speed *= 2;
      isRunning = true;
    }
    
    
    if(keyboard.isPressed(KeyEvent.VK_RIGHT)) {
      sprite.direction = Directions.EAST;
      sprite.x += speed;
      isMoving = true;
    }
    if(keyboard.isPressed(KeyEvent.VK_LEFT)) {
      sprite.direction = Directions.WEST;
      sprite.x -= speed;
      isMoving = true;
    }
    if(keyboard.isPressed(KeyEvent.VK_UP)) {
      sprite.direction = Directions.NORTH;
      sprite.y -= speed;
      isMoving = true;
    }
    if(keyboard.isPressed(KeyEvent.VK_DOWN)) {
      sprite.direction = Directions.SOUTH;
      sprite.y += speed;
      isMoving = true;
    }
    
    // Set the direction for the talk box.
    if(sprite.direction == Directions.EAST) {
      talkBox.setDimensions(32, 15);
      talkBox.setFocalPoint(0, 10);
    }
    else if(sprite.direction == Directions.NORTH) {
      talkBox.setDimensions(20, 32);
      talkBox.setFocalPoint(10, 32);
    }
    else if(sprite.direction == Directions.WEST) {
      talkBox.setDimensions(32, 15);
      talkBox.setFocalPoint(32, 10);
    } 
    else {
      talkBox.setDimensions(20, 32);
      talkBox.setFocalPoint(10, 0);
    }
    
    // Create the movement vector
    Vector2D vector = new Vector2D(sprite.x - oldX, sprite.y - oldY); 
    
    // Are we colliding with an obstacle in this layer?
    if(sprite.overlappingObstacle()) {
      sprite.x = oldX;
      sprite.y = oldY;
      
      vector = vector.scale(0.7);
      
      // if we are, try to move around it.
      vector = vector.rotate(45);
      sprite.x += vector.dx;
      sprite.y += vector.dy;
      
      if(sprite.overlappingObstacle()) {
        sprite.x = oldX;
        sprite.y = oldY;
        
        vector = vector.rotate(-90);
        sprite.x += vector.dx;
        sprite.y += vector.dy;
        
        // if we're still colliding, try moving around it the other way.
        if(sprite.overlappingObstacle()) {
          // We couldn't move around it. Just prevent movement.
          sprite.x = oldX;
          sprite.y = oldY;
          vector = new Vector2D(0,0);
        }
      }
    }
    
    // Figure out which animation to use.
    if(isMoving && isRunning) {
      sprite.setAnimation(CharaSprite.RUNNING);
    }
    else if(isMoving) {
      sprite.setAnimation(CharaSprite.WALKING);
    }
    else {
      sprite.setAnimation(CharaSprite.STOPPED);
    }
    
    
    talkBox.x = sprite.x;
    talkBox.y = sprite.y;
    return vector;
  }
  
  
  /** Animates the player's sprite by 1 animation frame. */
  public static void animate() {
    if(sprite != null) {
      sprite.animate();
    }
  }
  
  
  
  /** Gets the talk box collider for the player. */
  public static HiddenSprite getTalkBox() {
    if(talkBox == null) {
      talkBox = new HiddenSprite(0,0);
    }
    return talkBox;
  }
  
  /** 
   * Attempts to check whatever the player is facing with its talkBox in the 
   * current map. 
   * Checking something generally causes a dialog to start, but could also
   * cause other events.
   */
  public static void check(AreaMap map) {
    if(sprite != null) {
      map.tryCheckObjects();
    }
  }
  
  
  /** Returns true if the player's sprite is facing and is next to some other sprite. */
  public static boolean isFacing(Sprite sprite) {
    if(sprite == null) {
      return false;
    }
    else {
      return getTalkBox().getCollisionPoly().intersects(sprite.getCollisionPoly());
    }
  }
  
  
  /** Creates and returns the sprite for the player's OC. */
  public static CharaSprite getOC() {
    return null; // TODO
  }
}