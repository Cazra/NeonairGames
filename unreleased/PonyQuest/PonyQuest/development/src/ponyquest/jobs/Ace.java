package ponyquest.jobs;


/** Pegasus-exclusive.
Focused aerial fighter. Attacks quickly and can remain airborne nearly forever. */
public class Ace implements Job {
  
  private static Ace instance = null;
  
  public static Ace getInstance() {
    if(instance == null) {
      instance = new Ace();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Ace";
  }
  
  @Override
  public String getAbbrev() {
    return "Ace";
  }
  
  @Override
  public int getHPModifier() {
    return 0;
  }
  
  @Override
  public int getSPModifier() {
    return 0;
  }
  
  @Override
  public int getStrModifier() {
    return 0;
  }
  
  @Override
  public int getVitModifier() {
    return -10;
  }
  
  @Override
  public int getDexModifier() {
    return 20;
  }
  
  @Override
  public int getAgiModifier() {
    return 10;
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
    return 10;
  }
}
