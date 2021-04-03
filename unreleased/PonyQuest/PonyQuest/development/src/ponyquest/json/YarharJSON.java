package ponyquest.json;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;


/** Represents a yarhar map. */
public class YarharJSON {
  
  /** The name of this map. */
  public String name;
  
  /** Ther list of layers for this map, ordered from bottom to top.*/
  public List<LayerJSON> layers = new ArrayList<>();
  
  /** Description string for the map. */
  public String desc;
  
  public int gridW;
  
  public int gridH;
  
  /** Create from a yarhar json file. */
  public YarharJSON(String path) {
    try {
      InputStream is = getClass().getClassLoader().getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      
      String jsonString = "";
      String line = br.readLine();
      while(line != null) {
        jsonString += line;
        line = br.readLine();
      }
      
      
      JSONObject json = JSONParser.parse(jsonString);
      json = JSONParser.getObject(json, "yarmap");
      name = JSONParser.getString(json, "name");
      gridW = JSONParser.getInt(json, "gridW");
      gridH = JSONParser.getInt(json, "gridH");
      
      // reorder the layers from bottom to top.
      List<Object> jLayers = JSONParser.getList(json, "layers");
      for(int i = jLayers.size() - 1; i >= 0; i--) {
        Object layerObj = jLayers.get(i);
        JSONObject layerJSON = (JSONObject) layerObj;
        layers.add(new LayerJSON(layerJSON));
      }
      
      desc = JSONParser.getString(json, "desc");
      
      
      //System.out.println("Json for " + path + " : " + jsonString);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /** toString converts this back to json code. */
  public String toString() {
    String result = "{yarmap:{\n";
    result += "name:" + name + ",\n";
    result += "desc:" + desc + ",\n";
    result += "gridW:" + gridW + ",\n";
    result += "gridH:" + gridH + ",\n";
    
    result += "layers:[\n";
    for(LayerJSON layer : layers) {
      result += layer.toString() + "\n";
    }
    result += "]\n";
    
    result += "}}";
    return result;
  }
  
}
