package ponyquest.maps;

import java.util.Comparator;

import pwnee.sprites.Sprite;

/** A comparator for ordering sprites from north to south. */
public class NorthToSouthComparator implements Comparator<Sprite> {
  
  public int compare(Sprite s1, Sprite s2) {
    return (int) (s1.y - s2.y);
  }
  
  public boolean equals(Object o) {
    return (o == this);
  }
}
