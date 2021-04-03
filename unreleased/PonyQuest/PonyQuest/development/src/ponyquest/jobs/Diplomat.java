package ponyquest.jobs;


/** Focused on abusing the Diplomacy System in combat to solve encounters peacefully. */
public class Diplomat implements Job {
  
  private static Diplomat instance = null;
  
  public static Diplomat getInstance() {
    if(instance == null) {
      instance = new Diplomat();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Diplomat";
  }
  
  @Override
  public String getAbbrev() {
    return "Dpl";
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
    return -10;
  }
  
  @Override
  public int getVitModifier() {
    return 0;
  }
  
  @Override
  public int getDexModifier() {
    return -10;
  }
  
  @Override
  public int getAgiModifier() {
    return 0;
  }
  
  @Override
  public int getKnoModifier() {
    return 10;
  }
  
  @Override
  public int getInsModifier() {
    return 10;
  }
  
  @Override
  public int getChaModifier() {
    return 20;
  }
}
