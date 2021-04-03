package ponyquest.menu;

/** 
 * The return result of a menu logic iteration. It contains a boolean and 
 * a result Object representing some choice made by the player. 
 * 
 * A menu that hasn't completed yet should return the static instance result, 
 * incompleteResult. incompleteResult's completed flag is false, and its result
 * Object is null.
 * Otherwise, the returned MenuResult should have completed set to true and 
 * result can optionally be set to some Object.
 */
public class MenuResult {
  
  /** Static instance representing an incomplete result returned from a menu. */
  private static MenuResult incompleteResult = null;
  
  
  /** Static instance representing a completed result with no effect. */
  private static MenuResult noEffectResult = null;
  
  
  /** Whether the Menu returning this result has terminated. */
  private boolean completed;
  
  /** 
   * An object representing the result of some choice made by the user in the 
   * menu returning this object. 
   */
  private Object result;
  
  
  /** Creates a completed MenuResult with some result Object. */
  public MenuResult(Object result) {
    this.completed = completed;
    this.result = result;
  }
  
  
  /** 
   * Returns an incomplete result whose hasCompleted method returns false and 
   * whose getResult method returns null.
   */
  public static MenuResult incompleteResult() {
    if(incompleteResult == null) {
      incompleteResult = new MenuResult(null);
      incompleteResult.completed = false;
    }
    return incompleteResult;
  }
  
  /** 
   * Returns a completed result with no effect, usually returned from 
   * cancelling out of a menu. Its hasCompleted method returns true, but its
   * getResult method returns null.
   */
  public static MenuResult cancelledResult() {
    if(noEffectResult == null) {
      noEffectResult = new MenuResult(null);
    }
    return noEffectResult;
  }
  
  
  
  /** 
   * Returns the completed status of the menu result. This is true, iff the Menu
   * returning it has completed.
   */
  public boolean hasCompleted() {
    return completed;
  }
  
  
  /** 
   * Returns an Object representing the choice made by the player in the menu.
   * Returns null if the player's choice in the menu has no effect.
   */
  public Object getResult() {
    return result;
  }
}
