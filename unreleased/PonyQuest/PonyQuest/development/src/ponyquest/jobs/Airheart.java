package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
Hybrid of Ace and Meteorologist, with unique skills and minor Prankster influence. */
public class Airheart implements Job {
  
  private static Airheart instance = null;
  
  public static Airheart getInstance() {
    if(instance == null) {
      instance = new Airheart();
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
    return 40;
  }
  
  @Override
  public int getSPModifier() {
    return 100;
  }
  
  @Override
  public int getStrModifier() {
    return 10;
  }
  
  @Override
  public int getVitModifier() {
    return -10;
  }
  
  @Override
  public int getDexModifier() {
    return 4;
  }
  
  @Override
  public int getAgiModifier() {
    return 20;
  }
  
  @Override
  public int getKnoModifier() {
    return 2;
  }
  
  @Override
  public int getInsModifier() {
    return -10;
  }
  
  @Override
  public int getChaModifier() {
    return -2;
  }
}
