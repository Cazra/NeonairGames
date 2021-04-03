package ponyquest.jobs;


/** Unicorn-exclusive.
 Standard Black Mage-type class. Uses attack spells to deal high damage at high SP costs. */
public class Elementalist implements Job {
  
  private static Elementalist instance = null;
  
  public static Elementalist getInstance() {
    if(instance == null) {
      instance = new Elementalist();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Elementalist";
  }
  
  @Override
  public String getAbbrev() {
    return "Ele";
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
    return -10;
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
