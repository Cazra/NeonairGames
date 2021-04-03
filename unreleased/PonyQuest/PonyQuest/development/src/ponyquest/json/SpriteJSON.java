package ponyquest.json;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;

import pwnee.sprites.Sprite;

import ponyquest.sprites.tiles.SimpleTile;

/** Represents a yarhar map. */
public class SpriteJSON {
  
  /** The type of this sprite. */
  public String type;
  
  /** The x position. */
  public double x;
  
  /** The y position. */
  public double y;
  
  /** The rotation angle of the sprite. */
  public double angle;
  
  /** The opacity of the sprite */
  public double opacity;
  
  /** The uniform scale of the sprite. */
  public double scale;
  
  /** X scale */
  public double scaleX;
  
  /** Y scale */
  public double scaleY;
  
  /** X tiling */
  public double tileX;
  
  /** Y tiling */
  public double tileY;
  
  
  
  public SpriteJSON(JSONObject json) {
    type = JSONParser.getString(json, "type");
    
    x = JSONParser.getDouble(json, "x");
    y = JSONParser.getDouble(json, "y");
    
    angle = JSONParser.getDouble(json, "a");
    opacity = JSONParser.getDouble(json, "o");
    scale = JSONParser.getDouble(json, "s");
    scaleX = JSONParser.getDouble(json, "sx");
    scaleY = JSONParser.getDouble(json, "sy");
    tileX = JSONParser.getDouble(json, "tx");
    tileY = JSONParser.getDouble(json, "ty");
  }
  
  
  /** Copy the sprite json properties of this to an actual sprite. */
  public void copyPropsTo(Sprite sprite) {
    sprite.x = x;
    sprite.y = y;
    sprite.setAngle(angle);
    sprite.setOpacity(opacity);
    sprite.setScale(scale);
    sprite.setScale(scaleX, scaleY);
    
    if(sprite instanceof SimpleTile) {
      SimpleTile tile = (SimpleTile) sprite;
      tile.tileX = tileX;
      tile.tileY = tileY;
    }
  }
  
  
  /** toString converts this back to json code. */
  public String toString() {
    String result = "{";
    result += "type:" + type + ",\n";
    result += "x:" + x + ",\n";
    result += "y:" + y + ",\n";
    
    result += "}";
    return result;
  }
  
}
