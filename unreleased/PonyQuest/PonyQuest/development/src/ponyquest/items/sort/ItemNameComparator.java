package ponyquest.items.sort;

import java.util.Comparator;

import ponyquest.items.Item;

/** 
 * Compares Items in increasing order by name. In the event of a tie, 
 * Items are compared by their type ID. 
 */
public class ItemNameComparator implements Comparator<Item> {
  
  public int compare(Item item1, Item item2) {
    int compare = item1.getName().compareTo(item2.getName());
    if(compare != 0) {
      return item1.getType().compareTo(item2.getType());
    }
    else {
      return compare;
    }
  }
  
}
