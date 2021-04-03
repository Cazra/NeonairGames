package ponyquest.jobs;


/** Earth Pony-exclusive.
Controls plantlife. Abilities are all plant-themed and often involve status effects. */
public class Botanist implements Job {
  
  private static Botanist instance = null;
  
  public static Botanist getInstance() {
    if(instance == null) {
      instance = new Botanist();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Botanist";
  }
  
  @Override
  public String getAbbrev() {
    return "Bot";
  }
  
  @Override
  public int getHPModifier() {
    return -200;
  }
  
  @Override
  public int getSPModifier() {
    return 100;
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
    return 0;
  }
  
  @Override
  public int getAgiModifier() {
    return 0;
  }
  
  @Override
  public int getKnoModifier() {
    return 20;
  }
  
  @Override
  public int getInsModifier() {
    return 10;
  }
  
  @Override
  public int getChaModifier() {
    return 0;
  }
}
