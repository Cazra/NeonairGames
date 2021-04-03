package ponyquest.items;

import ponyquest.data.PCData;

/** 
 * An Item representing some sort of equipment worn by a character. 
 * E.G. armor, shoes, amulets, etc.
 */
public abstract class Equipment extends Item {
  
  
  
  /** 
   * Whether the Equipment can be equipped by a particular character. 
   * Some types of equipment may have race, job, or gender restrictions.
   * By default, this returns true.
   */
  public boolean equippableBy(PCData character) {
    return true;
  }
  
  
  /** Equipment items don't stack. */
  @Override
  public final int stackSize(Inventory inv) {
    return 1;
  }
  
  
  /** 
   * The equipment's bonus to maximum HP. 
   * By default, this returns 0. 
   */
  public int getMaxHPBonus(PCData character) {
    return 0;
  }
  
  
  /** 
   * The equipment's bonus to maximum SP. 
   * By default, this returns 0. 
   */
  public int getMaxSPBonus(PCData character) {
    return 0;
  }
  
  /** 
   * The equipement's Attack, which augments a character's total 
   * Strength. 
   * By default, this returns 0.
   */
  public int getAtk(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Defense, which augments a character's total 
   * Vitality. 
   * By default, this returns 0.
   */
  public int getDef(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Accuracy, which augments a character's total 
   * Dexterity. 
   * By default, this returns 0.
   */
  public int getAcc(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Evasion, which augments a character's total 
   * Agility. 
   * By default, this returns 0.
   */
  public int getEva(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Special Attack, which augments a character's total 
   * Knowledge. 
   * By default, this returns 0.
   */
  public int getSAtk(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Special Defense, which augments a character's total 
   * Insight. 
   * By default, this returns 0.
   */
  public int getSDef(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's Style, which augments a character's total 
   * Charisma. 
   * By default, this returns 0.
   */
  public int getStyle(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's bonus to Speed. 
   * By default, this returns 0.
   */
  public int getSpd(PCData character) {
    return 0;
  }
  
  /** 
   * The equipment's bonus to Charge Time. 
   * By default, this returns 0.
   */
  public int getCT(PCData character) {
    return 0;
  }
}
