package ponyquest.items;

/** 
 * Top level interface for items. 
 * An instance of an Item actually represents a stack of a particular type of 
 * item.
 */
public abstract class Item {
  
  /** The number of items in the stack. */
  private int amount;
  
  
  
  /** The item's name. */
  public abstract String getName();
  
  /** The item's type ID. */
  public abstract String getType();
  
  /** A short description of the item. */
  public abstract String getDescription();
  
  /** The item's prefix. E.G. A "vial of" love poison.   */
  public abstract String getPrefix();
  
  /** Whether the item can be used from the Item pause menu. */
  public abstract boolean usedInMenu();
  
  /** Whether the item can be used from the Item battle menu. */
  public abstract boolean usedInBattle();
  
  /** Whether the item can be sold to shops. */
  public abstract boolean isSellable();
  
  /** The item's base shop value in bits. */
  public abstract int getValue();
  
  
  /** 
   * Sets the quantity of the item in the stack. 
   * If the new quantity is greater than the stack size, then 
   * the quantity is set to the stack size and the excess amount is returned.
   * Else if the new quantity is less than 0, then the quantity is set to 0 
   * and the positive amount is returned.
   * Otherwise, return 0.
   */
  public int setCount(Inventory inv, int amt) {
    int max = stackSize(inv);
    
    if(amt > max) {
      amount = max;
      return amt - max;
    }
    else if(amt < 0) {
      amount = 0;
      return 0-amt;
    }
    else {
      amount = amt;
      return 0;
    }
  }
  
  
  /** 
   * Adds to the quantity of the item in the stack. 
   * If the new quantity is greater than the stack size, then 
   * the quantity is set to the stack size and the excess amount is returned.
   * Otherwise, it returns 0.
   */
  public int add(Inventory inv, int amt) {
    return setCount(inv, amount + amt);
  }
  
  
  /** 
   * Adds to the quantity of the item in the stack. 
   * If the new quantity is less than 0, then 
   * the quantity is set to 0 and the difference is returned.
   * Otherwise, it returns 0.
   */
  public int sub(Inventory inv, int amt) {
    return setCount(inv, amount - amt);
  }
  
  
  /** The quantity of the item in the stack. */
  public int amount() {
    return amount;
  }
  
  
  /** 
   * The maximum number of these items allowed in a stack for a particular 
   * inventory. 
   */
  public abstract int stackSize(Inventory inv);
  
  
  /** 
   * Merges the other stack of items into this one. If the other stack 
   * is completely emptied, this returns true. Otherwise it returns false.
   */
  public boolean merge(Inventory inv, Item other) {
    if(this.equals(other)) {
      int excess = this.add(inv, other.amount);
      other.amount = excess;
      
      return (other.amount == 0);
    }
    return false;
  }
  
  
  /** Items are equal if they are of the same class. */
  public boolean equals(Object obj) {
    return (this.getClass().equals(obj.getClass()));
  }
}
