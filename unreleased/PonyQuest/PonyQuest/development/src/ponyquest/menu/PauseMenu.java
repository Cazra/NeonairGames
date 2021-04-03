package ponyquest.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pwnee.image.ImageEffects;
import pwnee.image.ImageLoader;
import pwnee.input.Keyboard;

import ponyquest.Directions;
import ponyquest.PonyMain;
import ponyquest.data.PartyData;
import ponyquest.data.PCData;
import ponyquest.data.SavedGameData;
import ponyquest.sprites.CharaSprite;

/** The game's pause menu. */
public class PauseMenu implements Menu {
  
  /** Singleton instance. */
  private static PauseMenu instance = null;
  
  
  /** The list of top-level menu items the player can choose from. */
  private List<Menu> menuItems;
  
  /** Corresponding list of labels for the menu items. */
  private List<String> menuLabels;
  
  /** Corresponding list of icons for the menu items. */
  private List<Image> menuIcons;
  
  /** The index of the currently selected option in the menu. */
  private int choiceIndex;
  
  /** Whether the pause menu is currently active. */
  private boolean isActive;
  
  /** The currently active pause submenu. */
  private Menu activeSubmenu;
  
  
  private PauseMenu() {
    menuItems = new ArrayList<>();
    menuLabels = new ArrayList<>();
    menuIcons = new ArrayList<>();
    choiceIndex = 0;
    isActive = false;
    
    _addMenuOption(MenuImages.iconItem(), "Item", new DummyMenu());
    _addMenuOption(MenuImages.iconAbility(), "Ability", new DummyMenu());
    _addMenuOption(MenuImages.iconEquip(), "Equip", new DummyMenu());
    _addMenuOption(MenuImages.iconStatus(), "Status", new DummyMenu());
    _addMenuOption(MenuImages.iconParty(), "Party", new DummyMenu());
    _addMenuOption(MenuImages.iconConfig(), "Config", new DummyMenu());
    _addMenuOption(MenuImages.iconSave(), "Save", new DummyMenu());
  }
  
  
  /** Returns the singleton instance of the pause menu. */
  public static PauseMenu getInstance() {
    if(instance == null) {
      instance = new PauseMenu();
    }
    return instance;
  }
  
  
  /** Adds a submenu to the pause menu. */
  private void _addMenuOption(Image icon, String label, Menu menu) {
    menuItems.add(menu);
    menuLabels.add(label);
    menuIcons.add(icon);
  }
  
  
  private int _getOptionYOffset(int index) {
    return index * 16 + 10;
  }
  
  
  /** 
   * Returns true iff the pause menu is currently active and being displayed. 
   */
  public static boolean isActive() {
    return getInstance().isActive;
  }
  
  
  /** Activates the pause menu so that it can be run and displayed. */
  public static void activate() {
    getInstance().isActive = true;
  }
  
  
  @Override
  public MenuResult runMenu(Keyboard keyboard) {
    if(activeSubmenu == null) {
      if(keyboard.justPressed(KeyEvent.VK_X) || keyboard.justPressed(KeyEvent.VK_Q)) {
        isActive = false;
        return MenuResult.cancelledResult();
      }
      else if(keyboard.justPressedRep(KeyEvent.VK_UP)) {
        choiceIndex--;
      }
      else if(keyboard.justPressedRep(KeyEvent.VK_DOWN)) {
        choiceIndex++;
      }
      
      // Wrap the choice selection around.
      if(choiceIndex < 0) {
        choiceIndex = menuItems.size()-1;
      }
      else if(choiceIndex >= menuItems.size()) {
        choiceIndex = 0;
      }
      
      
      return MenuResult.incompleteResult();
    }
    else {
      
      return activeSubmenu.runMenu(keyboard);
    }
  }
  
  
  @Override
  public void render(Graphics2D g) {
    drawPauseOptions(g);
    drawGameStats(g);
    drawActivePCs(g);
    drawReservePCs(g);
  }
  
  
  private void drawPauseOptions(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    
    int w = 16*5;
    int h = 16*8;
    int x = PonyMain.resWidth - w - 1;
    int y = 1;
    
    // render the options box
    MenuImages.renderDiaBox(g, x, y, w, h);
    
    // render the options. 
    for(int i = 0; i < menuItems.size(); i++) {
      g.translate(x + 16, y + _getOptionYOffset(i));
      g.drawImage(menuIcons.get(i), 0, 0, null);
      g.translate(16, 0);
      MenuImages.fontCompressed().renderString(g, menuLabels.get(i));
      
      g.setTransform(origTrans);
    }
    
    // render the pointer
    g.drawImage(MenuImages.pointer(), x+4, y + _getOptionYOffset(choiceIndex), null);
    
    g.setTransform(origTrans);
  }
  
  private void drawGameStats(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    
    // render the game stats box.
    int w = 16*5;
    int h = 16*4;
    int x = PonyMain.resWidth - w - 1;
    int y = PonyMain.resHeight - h - 1;
    MenuImages.renderDiaBox(g, x, y, w, h);
    
    // render the game stats
    g.translate(x, y);
    drawGameStat(g, MenuImages.iconTime(), SavedGameData.getData().getPlaytimeString(), w, 0);
    drawGameStat(g, MenuImages.iconResources(), "n/a", w, 1);
    drawGameStat(g, MenuImages.iconBits(), "" + SavedGameData.getData().getBits(), w, 2);
    
    g.setTransform(origTrans);
  }
  
  
  private void drawGameStat(Graphics2D g, Image icon, String str, int w, int row) {
    AffineTransform origTrans = g.getTransform();
    
    int y = _getOptionYOffset(row);
    g.drawImage(icon, 8, y, null);
    g.translate(w - MenuImages.fontCompressed().getDimensions(str).width - 8, y);
    MenuImages.fontCompressed().renderString(g, str);
    
    g.setTransform(origTrans);
  }
  
  
  
  private void drawActivePCs(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    
    int x = 1;
    int y = 1;
    int w = 16*7;
    int h = 48*4+16;
    
    MenuImages.renderDiaBox(g, x, y, w, h);
    g.translate(x + 8, y + 8);
    for(PCData pc : PartyData.getData().getActiveData()) {
      drawPCStatus(g, pc);
      g.translate(0, 48);
    }
    
    g.setTransform(origTrans);
    
    // Draw the "Active" label.
    drawMenuLabel(g, x, y, w, h, "Active");
    
    g.setTransform(origTrans);
  }
  
  
  
  private void drawReservePCs(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    
    int x = 1 + 16*7;
    int y = 1 + 48;
    int w = 16*7;
    int h = 48*3+16;
    
    MenuImages.renderDiaBox(g, x, y, w, h);
    g.translate(x + 8, y + 8);
    for(PCData pc : PartyData.getData().getReserveData()) {
      drawPCStatus(g, pc);
      g.translate(0, 48);
    }
    
    g.setTransform(origTrans);
    
    // Draw the "Active" label.
    drawMenuLabel(g, x, y, w, h, "Reserve");
    
    g.setTransform(origTrans);
  }
  
  
  
  
  
  private void drawMenuLabel(Graphics2D g, int x, int y, int w, int h, String str) {
    AffineTransform origTrans = g.getTransform();
    
    int strW = MenuImages.fontCompressed().getDimensions(str).width;
    int strBoxW = (strW/16)*16+16*2;
    int strBoxH = 25;
    int strBoxX = x + w/2 - strBoxW/2;
    int strBoxY = y + h - 8;
    MenuImages.renderDiaBox(g, strBoxX, strBoxY, strBoxW, strBoxH);
    
    int strX = x + w/2 - strW/2;
    int strY = strBoxY + 8;
    g.translate(strX, strY);
    MenuImages.fontCompressed().renderString(g, str);
    
    g.setTransform(origTrans);
  }
  
  private void drawPCStatus(Graphics2D g, PCData pc) {
    AffineTransform origTrans = g.getTransform();
    
    g.setColor(new Color(0xFF0000));
    g.drawRect(0, 0, 16*6, 45);
    
    if(pc == null) {
      return;
    }
    
    g.drawImage(pc.getImage(CharaSprite.STOPPED, Directions.EAST, 0), -12, -2, null);
    
    g.translate(0,36);
    MenuImages.fontCompressed().renderString(g, "Lv " + pc.getLevel());
    g.setTransform(origTrans);
    
    g.translate(40, 10);
    MenuImages.fontCompressed().renderString(g, "HP " + pc.getHP() + "/" + pc.getMaxHP());
    g.translate(0, 13);
    MenuImages.fontCompressed().renderString(g, "SP " + pc.getSP() + "/" + pc.getMaxSP());
    g.translate(0, 13);
    MenuImages.fontCompressed().renderString(g, pc.getName());
    
    g.setTransform(origTrans);
  }
  
}