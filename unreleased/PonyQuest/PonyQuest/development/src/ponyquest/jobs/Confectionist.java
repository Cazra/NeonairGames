package ponyquest.jobs;


/** Earth Pony-exclusive.
 Skills and passives focused on enhancing the effects of confections. Nonmagic healer/debuffer. */
public class Confectionist implements Job {
  
  private static Confectionist instance = null;
  
  public static Confectionist getInstance() {
    if(instance == null) {
      instance = new Confectionist();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Confectionist";
  }
  
  @Override
  public String getAbbrev() {
    return "Con";
  }
  
  @Override
  public int getHPModifier() {
    return 200;
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
    return -10;
  }
  
  @Override
  public int getDexModifier() {
    return 20;
  }
  
  @Override
  public int getAgiModifier() {
    return -10;
  }
  
  @Override
  public int getKnoModifier() {
    return 10;
  }
  
  @Override
  public int getInsModifier() {
    return 0;
  }
  
  @Override
  public int getChaModifier() {
    return 0;
  }
}
