package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
Not that you'd want to. These girls are useless. */
public class Crusaders implements Job {
  
  private static Crusaders instance = null;
  
  public static Crusaders getInstance() {
    if(instance == null) {
      instance = new Crusaders();
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
    return -5;
  }
  
  @Override
  public int getDexModifier() {
    return 5;
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
    return -5;
  }
  
  @Override
  public int getChaModifier() {
    return 5;
  }
}
