package ponyquest.dialog;

import java.awt.*;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.PonyMain;
import ponyquest.menu.MenuImages;


/** Dialog text displayed inside a bordered box. */
public class DialogBox extends Sprite {
  
  /** The width of the dialog box as a multiple of 16. */
  private int w;
  
  /** The height of the dialog box as a multiple of 16. */
  private int h;
  
  /** The text to be displayed in the dialog box. */
  public PacedTextSprite textSprite;
  
  public DialogBox(double x, double y, double width, double height) {
    super(x, y);
    
    // The text sprite. 
    textSprite = new PacedTextSprite(16, 16, MenuImages.font(), "herpity derp");
    textSprite.slowness = 2;
    
    setSize(width, height);
  }
  
  
  /** Changes the dimensions of the DialogBox. */
  public void setSize(double width, double height) {
    // Set the width and height to a multiple of 16, minimum 32.
    this.width = width;
    this.height = height;
    
    this.w = (int) (width/16 + 0.5)*16;
    this.h = (int) (height/16 + 0.5)*16;
    
    textSprite.maxWidth = this.w - 32;
  }
  
  
  /** Returns the width of the dialog box. */
  public double getWidth() {
    return w;
  }
  
  /** Returns the height of the dialog box. */
  public double getHeight() {
    return h;
  }
  
  
  public void draw(Graphics2D g) {
    /*
    // draw corners
    g.drawImage(MenuImages.diaNW(), 0, 0, null);
    g.drawImage(MenuImages.diaNE(), w - 16, 0, null);
    g.drawImage(MenuImages.diaSW(), 0, h - 16, null);
    g.drawImage(MenuImages.diaSE(), w - 16, h - 16, null);
    
    // draw borders
    for(int i = 16; i < w - 16; i+=16) {
      g.drawImage(MenuImages.diaN(), i, 0, null);
      g.drawImage(MenuImages.diaS(), i, h - 16, null);
    }
    for(int j = 16; j < h - 16; j+=16) {
      g.drawImage(MenuImages.diaW(), 0, j, null);
      g.drawImage(MenuImages.diaE(), w - 16, j, null);
    }
    
    // draw interior
    for(int i = 16; i < w - 16; i+=16) {
      for(int j = 16; j < h - 16; j+=16) {
        g.drawImage(MenuImages.diaCenter(), i, j, null);
      }
    }
    */
    
    MenuImages.renderDiaBox(g, 0, 0, w, h);
    textSprite.render(g);
  }
  
}

