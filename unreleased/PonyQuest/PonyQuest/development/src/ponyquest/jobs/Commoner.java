package ponyquest.jobs;


/** A boring job with no abilities, used only for testing. */
public class Commoner implements Job {
  
  private static Commoner instance = null;
  
  public static Commoner getInstance() {
    if(instance == null) {
      instance = new Commoner();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Commoner";
  }
  
  @Override
  public String getAbbrev() {
    return "Com";
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
    return 0;
  }
  
  @Override
  public int getDexModifier() {
    return 0;
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
    return 0;
  }
  
  @Override
  public int getChaModifier() {
    return 0;
  }
}
