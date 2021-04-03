package ponyquest.menu;

import java.awt.Graphics2D;

import pwnee.input.Keyboard;

/** 
 * A menu that does has no behavior or graphics. Its behavior returns a 
 * cancelled result immediately. 
 * This DummyMenu is only for testing purposes to "dummy out" menu options
 * that have not yet been implemented. 
 */
public class DummyMenu implements Menu {
  
  public DummyMenu() {
  }
  
  /** The behavior for this menu always returns a cancelled result. */
  public MenuResult runMenu(Keyboard keyboard) {
    return MenuResult.cancelledResult();
  }
  
  /** This menu doesn't render anything.  */
  public void render(Graphics2D g) {
  }
}