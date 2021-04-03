package ponyquest.jobs;


/** Pegasus-exclusive.
Abilities change the local environment; manipulates the combat field to their benefit. */
public class Meteorologist implements Job {
  
  private static Meteorologist instance = null;
  
  public static Meteorologist getInstance() {
    if(instance == null) {
      instance = new Meteorologist();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Meteorologist";
  }
  
  @Override
  public String getAbbrev() {
    return "Met";
  }
  
  @Override
  public int getHPModifier() {
    return 0;
  }
  
  @Override
  public int getSPModifier() {
    return 100;
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
    return 20;
  }
  
  @Override
  public int getChaModifier() {
    return -10;
  }
}