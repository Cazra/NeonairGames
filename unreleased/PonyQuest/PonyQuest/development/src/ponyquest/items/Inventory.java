package ponyquest.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 * An interface for a collection of Items.
 */
public abstract class Inventory {
  
  /** The maximum number of Items allowed in the inventory. */
  private int capacity;
  
  /** The items in the inventory. */
  private Item[] items;
  
  
  
  /** Creates an empty inventory with the specified capacity. */
  public Inventory(int capacity) {
    this.capacity = capacity;
    items = new Item[capacity];
    
    for(int i = 0; i < capacity; i++) {
      items[i] = null;
    }
  }
  
  /** Creates an inventory with an initial list of items and specified capacity. */
  public Inventory(int capacity, List<Item> initItems) {
    this(capacity);
    int size = Math.min(capacity, initItems.size());
    for(int i = 0; i < size; i++) {
      items[i] = initItems.get(i);
    }
  }
  
  
  /** Returns the capacity of the inventory. */
  public int getCapacity() {
    return capacity;
  }
  
  
  /** Sets the capacity of the inventory to a new greater capacity. */
  public void increaseCapacityTo(int capacity) {
    if(this.capacity >= capacity) {
      return;
    }
    
    this.capacity = capacity;
    Item[] temp = new Item[capacity];
    
    for(int i = 0; i < items.length; i++) {
      temp[i] = items[i];
    }
    for(int i = items.length; i < temp.length; i++) {
      temp[i] = null;
    }
    
    items = temp;
  }
  
  
  
  /** Returns a copy of the array of the items in the inventory. */
  public Item[] toArray() {
    Item[] result = new Item[items.length];
    for(int i = 0; i < items.length; i++) {
      result[i] = items[i];
    }
    return result;
  }
  
  
  
  /** Returns the Item at the specified slot. */
  public Item get(int index) {
    if(indexInBounds(index)) {
      return items[index];
    }
    else {
      return null;
    }
  }
  
  
  /** 
   * Attempts to add an Item to the inventory in the first available empty slot. 
   * Returns true iff the Item was added successfully.
   */
  public boolean add(Item item) {
    for(int i = 0; i < items.length; i++) {
      if(items[i] == null) {
        return insert(i, item);
      }
    }
    
    return false;
  }
  
  
  /** 
   * Attempts to insert an Item at the specified slot. 
   * Returns true iff the Item was added successfully.
   */
  public boolean insert(int index, Item item) {
    if(canInsert(index, item)) {
      items[index] = item;
      return true;
    }
    else {
      return false;
    }
  }
  
  /** 
   * Returns true iff the Item can be inserted at the specified slot in this 
   * Inventory. 
   */
  public boolean canInsert(int index, Item item) {
    return (indexInBounds(index) && items[index] == null && item.amount() <= item.stackSize(this));
  }
  
  
  
  /** 
   * Removes and returns the item at the specified slot. 
   */
  public Item remove(int index) {
    if(indexInBounds(index)) {
      return items[index];
    }
    else {
      return null;
    }
  }
  
  
  
  /** 
   * Sorts the inventory according to some Comparator. 
   * Stacks of alike items become merged during this process as well. 
   */
  public void sort(Comparator<Item> comparator) {
    
    // Sort the items
    List<Item> list = new ArrayList<>();
    for(int i = 0; i < items.length; i++) {
      if(items[i] != null) {
        list.add(items[i]);
      }
    }
    Collections.sort(list, comparator);
    
    // Merge alike types of items. TODO
    
  }
  
  
  
  /** 
   * Returns true iff the specified index is within the bounds of the 
   * inventory's array. 
   */
  private boolean indexInBounds(int index) {
    return (index >= 0 && index < items.length);
  }
  
  
  
  /** Swaps two items slots between inventories. */
  public static boolean swap(Inventory inv1, int index1, Inventory inv2, int index2) {
    Item item1 = inv1.remove(index1);
    Item item2 = inv2.remove(index2);
    
    if(inv1.canInsert(index1, item2) && inv2.canInsert(index2, item1)) {
      inv1.insert(index1, item2);
      inv2.insert(index2, item1);
      return true;
    }
    else {
      
      // Undo the remove.
      inv1.items[index1] = item1;
      inv2.items[index2] = item2;
      return false;
    }
  }
}


