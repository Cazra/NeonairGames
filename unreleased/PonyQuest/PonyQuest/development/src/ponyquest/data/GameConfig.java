package ponyquest.data;

import java.io.Serializable;
import java.util.HashMap;

import pwnee.fileio.*;

/** A saveable/loadable Map of persistent game configuration variables. */
public class GameConfig implements Serializable {
  
  /** This map holds most global variables used in the game. */
  private HashMap<String, String> vars = new HashMap<String,String>();
  
  public GameConfig() {
    int diaColor = 0x444488;
    vars.put("diaColor", "" + diaColor);
  }
  
  
  public static GameConfig getConfig() {
    return SavedGameData.getData().getConfig();
  }
  
  /** Shorthand to get a variable's value from the map. */
  public static String get(String key) {
    return getConfig().vars.get(key);
  }
  
  /** Shorthand to set a variable in the map. */
  public static void put(String key, String value) {
    getConfig().vars.put(key, value);
  }
  
  
  /** Gets the rgb color for dialog and menu boxes. */
  public static int getDiaColor() {
    return Integer.parseInt(get("diaColor"));
  }
  
  /** Sets the rgb color for dialog and menu boxes. */
  public static void setDiaColor(int rgb) {
    put("diaColor", "" + rgb);
  }
}
