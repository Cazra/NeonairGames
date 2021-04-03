package ponyquest.data;

import java.awt.Image;
import java.io.Serializable;

import ponyquest.jobs.Job;
import ponyquest.races.Race;
import ponyquest.sprites.CharaSprite;

/** Game data a player character and their stats. */
public abstract class PCData implements Serializable {
  
  //////// Basic stats
  
  /** The character's current power level. */
  protected int level;
  
  /** The character's current hit points. */
  protected int hpCur;
  
  /** The character's maximum hit points. */
  protected int hpMax;
  
  /** The character's current skill points. */
  protected int spCur;
  
  /** The character's maximum skill points. */
  protected int spMax;
  
  /** The character's Strength stat. */
  protected int str;
  
  /** The character's Vitality stat. */
  protected int vit;
  
  /** The character's Dexterity stat. */
  protected int dex;
  
  /** The character's Agility stat. */
  protected int agi;
  
  /** The character's Knowledge stat. */
  protected int kno;
  
  /** The character's Insight stat. */
  protected int ins;
  
  /** The character's Charisma stat. */
  protected int cha;
  
  /** The character's Speed stat. */
  protected int spd;
  
  /** The character's Charge Time stat. */
  protected int ct;
  
  /** The character's combat EXP total. */
  protected int cexp;
  
  /** The character's non-combat EXP total. */
  protected int ncexp;
  
  
  //////// Status effects TODO
  
  
  
  
  //////// XP and leveling up
  
  /** Returns the character's experience level. */
  public int getLevel() {
    return level;
  }
  
  /** Gets the total of the character's combat and noncombat EXP. */
  public int getEXP() {
    return cexp + ncexp;
  }
  
  /** Gets the total EXP needed for this character to reach the next level. */
  public int getEXPNext() {
    return level*1000;
  }
  
  //////// Race and Job TODO
  
  /** Get the character's current job. */
  public abstract Job getJob();
  
  public abstract Race getRace();
  
  ///////// Stat calculations
  
  /** Returns the current HP for this character. */
  public int getHP() {
    return hpCur;
  }
  
  /** Returns the final max HP for this character. */
  public int getMaxHP() {
    int result = getBaseHP(); // TODO compute stats from level up
    result += getRace().getHPModifier();
    result += getJob().getHPModifier();
    return result;
  }
  
  /** Returns the current SP for this character. */
  public int getSP() {
    return spCur;
  }
  
  /** Returns the final max SP for this character. */
  public int getMaxSP() {
    int result = getBaseSP(); // TODO compute stats from level up
    result += getRace().getSPModifier();
    result += getJob().getSPModifier();
    return result;
  }
  
  /** Returns the final Str for this character. */
  public int getStr() {
    int result = getBaseStr(); // TODO compute stats from level up
    result += getRace().getStrModifier();
    result += getJob().getStrModifier();
    return result;
  }
  
  /** Returns the final Vit for this character. */
  public int getVit() {
    int result = getBaseVit(); // TODO compute stats from level up
    result += getRace().getVitModifier();
    result += getJob().getVitModifier();
    return result;
  }
  
  /** Returns the final Dex for this character. */
  public int getDex() {
    int result = getBaseDex(); // TODO compute stats from level up
    result += getRace().getDexModifier();
    result += getJob().getDexModifier();
    return result;
  }
  
  /** Returns the final Agi for this character. */
  public int getAgi() {
    int result = getBaseAgi(); // TODO compute stats from level up
    result += getRace().getAgiModifier();
    result += getJob().getAgiModifier();
    return result;
  }
  /** Returns the final Kno for this character. */
  public int getKno() {
    int result = getBaseKno(); // TODO compute stats from level up
    result += getRace().getKnoModifier();
    result += getJob().getKnoModifier();
    return result;
  }
  
  /** Returns the final Ins for this character. */
  public int getIns() {
    int result = getBaseIns(); // TODO compute stats from level up
    result += getRace().getInsModifier();
    result += getJob().getInsModifier();
    return result;
  }
  
  /** Returns the final Cha for this character. */
  public int getCha() {
    int result = getBaseCha(); // TODO compute stats from level up
    result += getRace().getChaModifier();
    result += getJob().getChaModifier();
    return result;
  }
  
  /** Returns the final Spd for this character.*/
  public int getSpd() {
    int result = getBaseSpd();
    // TODO
    return result;
  }
  
  /** Returns the final CT for this character. */
  public int getCT() {
    int result = getBaseCT();
    // TODO
    return result;
  }
  
  
  /** Returns this character's base HP. */
  public int getBaseHP() {
    return hpMax;
  }
  
  public int getBaseSP() {
    return spMax;
  }
  
  public int getBaseStr() {
    return str;
  }
  
  public int getBaseVit() {
    return vit;
  }
  
  public int getBaseDex() {
    return vit;
  }
  
  public int getBaseAgi() {
    return agi;
  }
  
  public int getBaseKno() {
    return kno;
  }
  
  public int getBaseIns() {
    return ins;
  }
  
  public int getBaseCha() {
    return cha;
  }
  
  public int getBaseSpd() {
    return spd;
  }
  
  public int getBaseCT() {
    return ct;
  }
  
  
  //////// View components
  
  /** Returns the name of the character. */
  public abstract String getName();
  
  
  /** 
   * Returns the portrait image for this character with the specified 
   * expression and direction. 
   */
  public abstract Image getPortrait(int emoteCode);
  
  
  /** 
   * Returns a sprite image for this character for a specified animation, 
   * direction, and frame number. 
   * See CharaSprite for animation and direction enumeration constants.
   */
  public abstract Image getImage(int animation, int direction, int frame);
  
  /** 
   * Produces a sprite to represent this character. 
   */
  public abstract CharaSprite createSprite();
  
  
}

