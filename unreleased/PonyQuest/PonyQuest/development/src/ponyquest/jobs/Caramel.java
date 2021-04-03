package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
Hybrid of Stampeder and Guardian, with unique skills. */
public class Caramel implements Job {
  
  private static Caramel instance = null;
  
  public static Caramel getInstance() {
    if(instance == null) {
      instance = new Caramel();
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
    return -20;
  }
  
  @Override
  public int getStrModifier() {
    return 20;
  }
  
  @Override
  public int getVitModifier() {
    return 10;
  }
  
  @Override
  public int getDexModifier() {
    return 2;
  }
  
  @Override
  public int getAgiModifier() {
    return -10;
  }
  
  @Override
  public int getKnoModifier() {
    return -10;
  }
  
  @Override
  public int getInsModifier() {
    return 2;
  }
  
  @Override
  public int getChaModifier() {
    return 4;
  }
}
