package ponyquest.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pwnee.fileio.*;

/** Saved game file data. */
public class SavedGameData implements Serializable {
  
  /** Used to save and load the serialized game data. */
  private static ObjectFileIO ofio = new ObjectFileIO();
  
  /** A convenient reference to the currently loaded saved game data. */
  private static SavedGameData loadedData = null;
  
  /** Configuration data. */
  private GameConfig config;
  
  /** Party data. */
  private PartyData party;
  
  /** The party's money. */
  private int bits;
  
  /** The save file's total playtime, in milliseconds. */
  private long playtime;
  
  /** The timestamp for when this file was loaded. Used to calculate total playtime. */
  private long loadTime;
  
  /** The total amount of saved time on this file. Used to calculate total playtime. */
  private long savedTime;
  
  /** Inventory data. */
  
  /** Elements of Harmony data. */
  
  
  
  /** Creates a blank, new SavedGameData. TODO */
  public SavedGameData() {
    party = new PartyData();
    config = new GameConfig();
    bits = 0;
    playtime = 0;
    loadTime = System.currentTimeMillis();
    savedTime = 0;
  }
  
  
  /** Returns the game configurations for this file. */
  public GameConfig getConfig() {
    return config;
  }
  
  
  /** Returns the party data for this file. */
  public PartyData getPartyData() {
    return party;
  }
  
  
  
  /** Lazy initializer for the ObjectFileIO object. */
  private static ObjectFileIO getOFIO() {
    if(ofio == null) {
      ofio = new ObjectFileIO();
      ofio.encryptorString = "SpectrumSquare";
    }
    return ofio;
  }
  
  
  //////// General save file data
  
  /** Returns the bits total for this file. */
  public int getBits() {
    return bits;
  }
  
  /** Sets the bits total for this file. */
  public void setBits(int bits) {
    this.bits = bits;
  }
  
  
  
  /** Returns the playtime as a displayable string in this format: hours:minutes */
  public String getPlaytimeString() {
    int minutes = getPlaytimeMinutes();
    int hours = minutes/60;
    minutes = minutes % 60;
    
    // playtime cap
    if(hours > 99) {
      hours = 999;
      minutes = 59;
    }
    
    // minutes must be displayed as 2 digits, so if it's < 10, pad with a "0".
    String minutesStr = "";
    if(minutes < 10) {
      minutesStr += "0";
    }
    minutesStr += minutes;
    
    return hours + ":" + minutesStr;
  }
  
  /** Returns the total amount of playtime for this file in milliseconds. */
  public long getPlaytimeMillis() {
    return savedTime + (System.currentTimeMillis() - loadTime);
  }
  
  /** Returns the playtime in minutes. */
  public int getPlaytimeMinutes() {
    return (int) getPlaytimeMillis()/60000;
  }
  
  
  /** Returns the playtime in hours. */
  public int getPlaytimeHours() {
    return getPlaytimeMinutes()/60;
  }
  
  
  //////// Access the loaded data
  
  /** 
   * Returns the currently loaded save data. If there isn't one, a new save 
   * data is created, cached, and returned. 
   */
  public static SavedGameData getData() {
    if(loadedData == null) {
      loadedData = new SavedGameData();
    }
    return loadedData;
  }
  
  
  
  
  
  //////// File IO
  
  /** Returns the filename for a save slot. */
  public static String getSaveFileName(int slot) {
    return "ponyquestData" + slot + ".dat";
  }
  
  
  /** Opens the data from the saved game files. */
  public static List<SavedGameData> getSavedFiles() {
    List<SavedGameData> result = new ArrayList<>();
    
    for(int i = 0; i < 4; i++) {
      result.add(getSavedFile(i));
    }
    
    return result;
  }
  
  /** Returns the save game file for the specified slot. */
  public static SavedGameData getSavedFile(int slot) {
    String filename = getSaveFileName(slot);
    int loadOptions = ObjectFileIO.DECOMPRESS | ObjectFileIO.DECRYPT;
    Object obj = getOFIO().loadObject(filename, loadOptions);
    
    if(obj instanceof SavedGameData) {
      return (SavedGameData) obj;
    }
    else {
      return null;
    }
  }
  
  
  /** Sets this save file as the currently loaded game data. */
  public void load() {
    SavedGameData.loadedData = this;
    this.loadTime = System.currentTimeMillis();
  }
  
  
  
  /** Saves this SavedGameData to a file. */
  public void save(int saveSlot) {
    // Update playtime data for the file.
    long currentTime = System.currentTimeMillis();
    savedTime = savedTime + (currentTime - loadTime);
    loadTime = currentTime;
    
    // Save the save data to its file. 
    String filename = getSaveFileName(saveSlot);
    int saveOptions = ObjectFileIO.COMPRESS | ObjectFileIO.ENCRYPT;
    getOFIO().saveObject(this, filename, saveOptions);
  }
  
 
  
}

