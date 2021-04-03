package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
 Mostly unique skills, with hybrid elements of Brawler and Shadow. */
public class StarHunter implements Job {
  
  private static StarHunter instance = null;
  
  public static StarHunter getInstance() {
    if(instance == null) {
      instance = new StarHunter();
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
    return 200;
  }
  
  @Override
  public int getSPModifier() {
    return -100;
  }
  
  @Override
  public int getStrModifier() {
    return 2;
  }
  
  @Override
  public int getVitModifier() {
    return 4;
  }
  
  @Override
  public int getDexModifier() {
    return 10;
  }
  
  @Override
  public int getAgiModifier() {
    return -2;
  }
  
  @Override
  public int getKnoModifier() {
    return 20;
  }
  
  @Override
  public int getInsModifier() {
    return 4;
  }
  
  @Override
  public int getChaModifier() {
    return -10;
  }
}
