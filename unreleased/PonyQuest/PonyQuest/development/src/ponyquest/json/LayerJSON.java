package ponyquest.json;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;


/** Represents a yarhar map. */
public class LayerJSON {
  
  /** The name of this layer. */
  public String name;
  
  /** List of sprites on this layer. */
  public List<SpriteJSON> sprites = new ArrayList<>();
  
  /** Opacity of the layer */
  public double opacity;
  
  /** Is the layer visible? */
  public boolean visible;
  
  
  public LayerJSON(JSONObject json) {
    name = JSONParser.getString(json, "name");
    
    for(Object spriteObj : JSONParser.getList(json, "sprites")) {
      JSONObject spriteJSON = (JSONObject) spriteObj;
      sprites.add(new SpriteJSON(spriteJSON));
    }
    
    opacity = JSONParser.getDouble(json, "opacity");
    
    visible = JSONParser.getBoolean(json, "visible");
  }
  
  
  /** toString converts this back to json code. */
  public String toString() {
    String result = "{";
    result += "name:" + name + ",\n";
    
    result += "sprites:[\n";
    for(SpriteJSON sprite : sprites) {
      result += sprite.toString() + "\n";
    }
    result += "]\n";
    
    result += "}";
    return result;
  }
}
