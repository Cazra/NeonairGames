package ponyquest.jobs;


/** Party member class. Not selectable for the main character.
Hybrid of Elementalist and Telekinetic, with unique skills. */
public class Mochaccino implements Job {
  
  private static Mochaccino instance = null;
  
  public static Mochaccino getInstance() {
    if(instance == null) {
      instance = new Mochaccino();
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
    return 20;
  }
  
  @Override
  public int getSPModifier() {
    return 200;
  }
  
  @Override
  public int getStrModifier() {
    return -2;
  }
  
  @Override
  public int getVitModifier() {
    return -10;
  }
  
  @Override
  public int getDexModifier() {
    return -10;
  }
  
  @Override
  public int getAgiModifier() {
    return 2;
  }
  
  @Override
  public int getKnoModifier() {
    return 10;
  }
  
  @Override
  public int getInsModifier() {
    return -7;
  }
  
  @Override
  public int getChaModifier() {
    return 15;
  }
}
