package ponyquest.menu;

import java.awt.Graphics2D;

import pwnee.input.Keyboard;

/** An interface for various types of interactive, graphical in-game menus. */
public interface Menu {
  
  /**
   * Runs the interactive behavior of the menu. 
   * @param keyboard    The Keyboard object used by the player to interact with
   *                    the menu.
   * @return            A MenuResult object representing the return status of
   *                    the menu. 
   */
  public MenuResult runMenu(Keyboard keyboard);
  
  /** 
   * Renders the menu. 
   * @param g   The Graphics2D object on which the menu is rendered.
   */
  public void render(Graphics2D g);
}