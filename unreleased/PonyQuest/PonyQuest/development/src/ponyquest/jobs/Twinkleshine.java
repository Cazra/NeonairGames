package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
 Hybrid of Medic and Telekinetic, with unique skills. */
public class Twinkleshine implements Job {
  
  private static Twinkleshine instance = null;
  
  public static Twinkleshine getInstance() {
    if(instance == null) {
      instance = new Twinkleshine();
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
    return 40;
  }
  
  @Override
  public int getStrModifier() {
    return -10;
  }
  
  @Override
  public int getVitModifier() {
    return -2;
  }
  
  @Override
  public int getDexModifier() {
    return -2;
  }
  
  @Override
  public int getAgiModifier() {
    return 10;
  }
  
  @Override
  public int getKnoModifier() {
    return 4;
  }
  
  @Override
  public int getInsModifier() {
    return 20;
  }
  
  @Override
  public int getChaModifier() {
    return 10;
  }
}
