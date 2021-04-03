package ponyquest.data;

import java.io.Serializable;

/** Game data about the you and your playable allies. */
public class PartyData implements Serializable {
  
  /** Code constant for no PC. */
  public static final int NONE = -1;
  
  /** Code constant for the player PC. */
  public static final int PLAYER = 0;
  
  /** Code constant for Airheart. */
  public static final int AIRHEART = 1;
  
  /** Code constant for Apple Honey. */
  public static final int APPLEHONEY = 2;
  
  /** Code constant for Caramel. */
  public static final int CARAMEL = 3;
  
  /** Code constant for Mochaccino.  */
  public static final int MOCHA = 4;
  
  /** Code constant for Star Hunter. */
  public static final int STARHUNTER = 5;
  
  /** Code constant for Twinkleshine. */
  public static final int TWINKLESHINE = 6;
  
  /** Code constant for the Cutie Mark Crusaders. */
  public static final int CRUSADERS = 7;
  
  /** Code constant for the character sprite test PCData. */
  public static final int CHANGELING = 8;
  
  
  // Stat data for each character.
  private PCData playerData;
  
  private PCData airheartData;
  
  private PCData applehoneyData;
  
  private PCData caramelData;
  
  private PCData mochaData;
  
  private PCData starhunterData;
  
  private PCData twinkleshineData;
  
  private PCData crusadersData;
  
  private ChangelingPCData changelingData;
  
  
  // Flags for which PCs have joined the party. 
  public boolean airheartJoined;
  
  public boolean applehoneyJoined;
  
  public boolean caramelJoined;
  
  public boolean mochaJoined;
  
  public boolean starhunterJoined;
  
  public boolean twinkleshineJoined;
  
  // Set of characters currently in the party.
  
  /** The slots for characters currently active in the party. */
  private int[] activeSlots;
  
  /** The slots for characters not currently active in the party. */
  private int[] reserveSlots;
  
  
  /** Creates a PartyData with only the player's OC in the party. */
  public PartyData() {
    activeSlots = new int[4];
    reserveSlots = new int[6];
    
    // Slot 0 is CHANGELING by default just for testing purposes.
    // Slot 0 becomes PLAYER when the player creates their OC in normal gameplay.
    activeSlots[0] = CHANGELING; 
    
    // Other slots start out empty.
    activeSlots[1] = NONE;
    activeSlots[2] = NONE;
    activeSlots[3] = NONE;
    
    for(int i = 0; i < 6; i++) {
      reserveSlots[i] = NONE;
    }
    
    // Initialize the PCData objects.
    
  }
  
  
  
  
  /** Get the data for a particular PC, given their code. Returns null if the code is invalid. */
  public static PCData getPC(int code) {
    PartyData party = getData();
    
    PCData result = null;
    switch(code) {
      case PLAYER:
        result = party.playerData;
        break;
      case AIRHEART:
        result = party.airheartData;
        break;
      case APPLEHONEY:
        result = party.applehoneyData;
        break;
      case CARAMEL:
        result = party.caramelData;
        break;
      case MOCHA:
        result = party.mochaData;
        break;
      case STARHUNTER:
        result = party.starhunterData;
        break;
      case TWINKLESHINE:
        result = party.twinkleshineData;
        break;
      case CRUSADERS:
        result = party.crusadersData;
        break;
      case CHANGELING:
        result = party.changelingData;
        break;
      default:
        break;
    }
    return result;
  }
  
  
  
  
  //////// Access to PCData 
  
  /** Gets the PCData for a character in an active party slot (starting from 0). */
  public PCData getActiveData(int slot) {
    return getPC(activeSlots[slot]);
  }
  
  
  /** Returns an array of the active characters' data. */
  public PCData[] getActiveData() {
    PCData[] result = new PCData[4];
    for(int i = 0; i < 4; i++) {
      result[i] = getActiveData(i);
    }
    return result;
  }
  
  /** Returns the number of characters in active slots. */
  public int activeSize() {
    int size = 0;
    for(int slot : activeSlots) {
      if(slot != NONE) {
        size++;
      }
    }
    return size;
  }
  
  
  
  /** Gets the PCData for a character in a reserve party slot (starting from 0). */
  public PCData getReserveData(int slot) {
    return getPC(reserveSlots[slot]);
  }
  
  /** Returns an array of the reserve characters' data. */
  public PCData[] getReserveData() {
    PCData[] result = new PCData[6];
    for(int i = 0; i < 6; i++) {
      result[i] = getReserveData(i);
    }
    return result;
  }
  
  /** Returns the number of characters in reserve slots. */
  public int reserveSize() {
    int size = 0;
    for(int slot : reserveSlots) {
      if(slot != NONE) {
        size++;
      }
    }
    return size;
  }
  
  /** Returns the PCData for the player's OC. */
  public PCData getPlayerData() {
    if(playerData == null) {
      // TODO
    }
    return playerData;
  }
  
  /** Returns the PCData for Air Heart. */
  public PCData getAirheartData() {
    if(airheartData == null) {
      // TODO
    }
    return airheartData;
  }
  
  /** Returns the PCData for Apple Honey. */
  public PCData getApplehoneyData() {
    if(applehoneyData == null) {
      // TODO
    }
    return applehoneyData;
  }
  
  /** Returns the PCData for Caramel. */
  public PCData getCaramelData() {
    if(caramelData == null) {
      // TODO
    }
    return caramelData;
  }
  
  /** Returns the PCData for Mochaccino. */
  public PCData getMochaData() {
    if(mochaData == null) {
      // TODO
    }
    return mochaData;
  }
  
  /** Returns the PCData for Star Hunter. */
  public PCData getStarhunterData() {
    if(starhunterData == null) {
      // TODO
    }
    return starhunterData;
  }
  
  /** Returns the PCData for Twinkleshine. */
  public PCData getTwinkleshineData() {
    if(twinkleshineData == null) {
      // TODO
    }
    return twinkleshineData;
  }
  
  /** Returns the PCData for the Cutie Mark Crusaders. */
  public PCData getCrusadersData() {
    if(crusadersData == null) {
      // TODO
    }
    return crusadersData;
  }
  
  /** Returns the PCData for the changeling. */
  public ChangelingPCData getChangelingData() {
    if(changelingData == null) {
      changelingData = new ChangelingPCData();
    }
    return changelingData;
  }
  
  //////// Slot manipulation (switching which allies are in which slots)
  
  /** 
   * Sets one of the active PC slots. Returns the constant for whatever PC 
   * was currently occupying that slot. 
   */
  public int setActiveSlot(int slot, int pc) {
    if(slot <= 0 || slot < 4) {
      int oldPC = activeSlots[slot];
      activeSlots[slot] = pc;
      return oldPC;
    }
    else {
      return -1;
    }
  }
  
  
  /** Swap an active and reserve slot. */
  public void swap(int aSlot, int rSlot) {
    int temp = activeSlots[aSlot];
    activeSlots[aSlot] = reserveSlots[rSlot];
    reserveSlots[rSlot] = temp;
  }
  
  /** Swap two active slots. */
  public void swapActives(int slot1, int slot2) {
    int temp = activeSlots[slot1];
    activeSlots[slot1] = activeSlots[slot2];
    activeSlots[slot2] = temp;
  }
  
  /** Swap two reserve slots. */
  public void swapReserves(int slot1, int slot2) {
    int temp = reserveSlots[slot1];
    reserveSlots[slot1] = reserveSlots[slot2];
    reserveSlots[slot2] = temp;
  }
  
  
  
  //////// Access the loaded data.
  
  /** Returns the party data for the currently loaded SavedGameData. */
  public static PartyData getData() {
    return SavedGameData.getData().getPartyData();
  }
}

