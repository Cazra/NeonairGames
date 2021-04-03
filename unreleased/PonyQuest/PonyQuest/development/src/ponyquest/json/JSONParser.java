package ponyquest.json;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;


/**  
 * This class provides a toolbox of convenient static methods for safely 
 * extracting information from JSON objects.
 */
public class JSONParser {
  
  public static boolean makeLogs = true; // TODO: turn this off for distribution.
  
  /** 
   * Attempts to construct a JSON object from a source string.
   * If it fails to parse the source string, it will write the source string
   * to an error file for logging/debugging convenience.
   * @param src   The source string for the JSON.
   * @return      The JSON object representing src.
   */
  public static JSONObject parse(String src) {
    try {
      JSONObject result = new JSONObject(src);
      return result;
    }
    catch(JSONException e) {
      if(makeLogs) {
        try {
          String filename = "json_error" + System.currentTimeMillis() + ".txt";
          System.err.println("JSON parse failed. Dumping source string to " + filename);
          PrintStream ps = new PrintStream(filename);
          ps.println(src);
          ps.flush();
          ps.close();
        }
        catch(Exception e2) {
          System.err.println("Failed to create JSON error file.");
        }
      }
      return null;
    }
  }
  
  /** 
   * Attempts to construct a JSON object from a file containing a json string.
   * If it fails to parse the source string, it will write the source string
   * to an error file for logging/debugging convenience.
   * @param f     The file containing the JSON source string.
   * @return      The JSON object representing src.
   */
  public static JSONObject parse(File f) {
    String src = "";
    
    try {
      BufferedReader br = new BufferedReader(new FileReader(f));
      String line = br.readLine();
      while(line != null) {
        src += line + "\n";
        line = br.readLine();
      }
    }
    catch (Exception e) {
      
    }
    
    return parse(src);
  }
  
  /** 
   * Attempts to construct a JSON array from a source string.
   * If it fails to parse the source string, it will write the source string
   * to an error file for logging/debugging convenience.
   * @param src   The source string for the JSON.
   * @return      The JSON array representing src.
   */
  public static JSONArray parseArray(String src) {
    try {
      JSONArray result = new JSONArray(src);
      return result;
    }
    catch(JSONException e) {
      if(makeLogs) {
        try {
          String filename = "json_array_error" + System.currentTimeMillis() + ".txt";
          System.err.println("JSON array parse failed. Dumping source string to " + filename);
          PrintStream ps = new PrintStream(filename);
          ps.println(src);
          ps.flush();
          ps.close();
        }
        catch(Exception e2) {
          System.err.println("Failed to create JSON error file.");
        }
      }
      return null;
    }
  }
  
  
  
  /** Converts a JSONObject to a Map of String keys to Objects. */
  public static Map<String, Object> toMap(JSONObject json) {
    Map<String, Object> result = new HashMap<>();
    
    try {
      String[] keys = JSONObject.getNames(json);
      for(String key : keys) {
        Object value = json.get(key);
        result.put(key, value);
      }
    }
    catch (Exception e) {
      System.err.println("Failed to convert json to Map.");
    }
    
    return result;
  }
  
  
  /** Converts a JSONArray into an iterable List of its Objects. */
  public static List<Object> toList(JSONArray array) {
    List<Object> result = new ArrayList<>();
    try {
      for(int i = 0; i < array.length(); i++) {
        Object obj = array.get(i);
        result.add(obj);
      }
    } 
    catch(Exception e) {
      System.err.println("Failed to convert json array to List.");
    }
    
    return result;
  }
  
  
  /** 
   * Safely gets a JSONObject associated with a key in a parent json object.
   * If the key could not be found, then null is returned.
   */
  public static JSONObject getObject(JSONObject json, String key) {
    try {
      return json.getJSONObject(key);
    }
    catch (Exception e) {
      return null;
    }
  }
  
  
  /** 
   * Safely gets a String value associated with a key in a json object. 
   * If the key could not be found in the object, then "" is returned.
   */
  public static String getString(JSONObject json, String key) {
    try {
      return json.getString(key);
    }
    catch(Exception e) {
      return "";
    }
  }
  
  /** 
   * Safely extracts an integer value associated with a key in a json object.
   * If the key could not be found in the object, then 0 is returned.
   */
  public static int getInt(JSONObject json, String key) {
    try {
      return json.getInt(key);
    }
    catch(Exception e) {
      return 0;
    }
  }
  
  /** 
   * Safely extracts a double value associated with a key in a json object.
   * If the key could not be found in the object, then 0 is returned.
   */
  public static double getDouble(JSONObject json, String key) {
    try {
      return json.getDouble(key);
    }
    catch(Exception e) {
      return 0;
    }
  }
  
  /** 
   * Safely extracts a long integer value associated with a key in a json object.
   * If the key could not be found in the object, then 0 is returned.
   */
  public static long getLong(JSONObject json, String key) {
    try {
      return json.getLong(key);
    }
    catch(Exception e) {
      return 0;
    }
  }
  
  /** 
   * Safely extracts a boolean value associated with a key in a json object.
   * If the key could not be found in the object, then false is returned.
   */
  public static boolean getBoolean(JSONObject json, String key) {
    try {
      return json.getBoolean(key);
    }
    catch(Exception e) {
      return false;
    }
  }
  
  /**
   * Safely gets an array associated with a key in a json object, 
   * and returns it as a List.
   * If key could not be found in the object, then null is returned.
   */
  public static List<Object> getList(JSONObject json, String key) {
    try {
      return toList(json.getJSONArray(key));
    }
    catch(Exception e) {
      return null;
    }
  }
  
  /** 
   * Safely gets a JSON object associated with a key in a 
   * parent json object, and returns it as a Map.
   * If key could not be found in the object, then null is returned.
   */
  public static Map<String, Object> getMap(JSONObject json, String key) {
    try {
      return toMap(json.getJSONObject(key));
    }
    catch (Exception e) {
      return null;
    }
  }
}
