package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
Hybrid of Confectionist and Diplomat, with unique Skills. */
public class AppleTart implements Job {
  
  private static AppleTart instance = null;
  
  public static AppleTart getInstance() {
    if(instance == null) {
      instance = new AppleTart();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "";
  }
  
  @Override
  public String getAbbrev() {
    return "";
  }
  
  @Override
  public int getHPModifier() {
    return -200;
  }
  
  @Override
  public int getSPModifier() {
    return 20;
  }
  
  @Override
  public int getStrModifier() {
    return -10;
  }
  
  @Override
  public int getVitModifier() {
    return 2;
  }
  
  @Override
  public int getDexModifier() {
    return 10;
  }
  
  @Override
  public int getAgiModifier() {
    return 4;
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
    return 2;
  }
}
