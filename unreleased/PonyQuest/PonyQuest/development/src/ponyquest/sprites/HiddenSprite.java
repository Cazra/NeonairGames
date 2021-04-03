package ponyquest.sprites;

import java.awt.*;

/** A general purpose invisible sprite. */
public class HiddenSprite extends LayerSprite {
  
  /** Whether to render hidden sprites for debugging purposes. */
  public static boolean drawHiddenSprites = false;
  
  /** An identifier for the hidden sprite. */
  public String id;
  
  public HiddenSprite(String id, double x, double y) {
    super(x, y);
    this.id = id;
  }
  
  public HiddenSprite(double x, double y) {
    this("", x, y);
  }
  
  public HiddenSprite(String id) {
    this(id, 0, 0);
  }
  
  public HiddenSprite() {
    this("");
  }
  
  // Hidden sprites will only be drawn if our debugging flag for drawing them is true.
  public void render(Graphics2D g) {
    if(drawHiddenSprites) {
      super.render(g);
    }
  }
  
  public void draw(Graphics2D g) {
  }
}