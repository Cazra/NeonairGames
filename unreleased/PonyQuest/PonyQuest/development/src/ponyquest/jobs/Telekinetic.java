package ponyquest.jobs;


/** Unicorn-exclusive.
 Manipulates physics using basic telekinesis. Can bind enemies or deal damage or escape from combat easily. */
public class Telekinetic implements Job {
  
  private static Telekinetic instance = null;
  
  public static Telekinetic getInstance() {
    if(instance == null) {
      instance = new Telekinetic();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Telekinetic";
  }
  
  @Override
  public String getAbbrev() {
    return "Tel";
  }
  
  @Override
  public int getHPModifier() {
    return 0;
  }
  
  @Override
  public int getSPModifier() {
    return 200;
  }
  
  @Override
  public int getStrModifier() {
    return -10;
  }
  
  @Override
  public int getVitModifier() {
    return -10;
  }
  
  @Override
  public int getDexModifier() {
    return 0;
  }
  
  @Override
  public int getAgiModifier() {
    return 10;
  }
  
  @Override
  public int getKnoModifier() {
    return 0;
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
