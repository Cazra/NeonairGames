package ponyquest.jobs;


/** Earth-Pony exclusive.
 Can go berserk and attack enemies relentlessly in exchange for direct player control. */
public class Stampeder implements Job {
  
  private static Stampeder instance = null;
  
  public static Stampeder getInstance() {
    if(instance == null) {
      instance = new Stampeder();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Stampeder";
  }
  
  @Override
  public String getAbbrev() {
    return "Sta";
  }
  
  @Override
  public int getHPModifier() {
    return 200;
  }
  
  @Override
  public int getSPModifier() {
    return 0;
  }
  
  @Override
  public int getStrModifier() {
    return 20;
  }
  
  @Override
  public int getVitModifier() {
    return 0;
  }
  
  @Override
  public int getDexModifier() {
    return 10;
  }
  
  @Override
  public int getAgiModifier() {
    return 0;
  }
  
  @Override
  public int getKnoModifier() {
    return 0;
  }
  
  @Override
  public int getInsModifier() {
    return -10;
  }
  
  @Override
  public int getChaModifier() {
    return -10;
  }
}
