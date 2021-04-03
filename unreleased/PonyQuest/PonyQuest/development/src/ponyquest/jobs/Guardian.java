package ponyquest.jobs;


/** Protects their friends. Excels at holding aggro and absorbing damage in lieu of the rest of the party. */
public class Guardian implements Job {
  
  private static Guardian instance = null;
  
  public static Guardian getInstance() {
    if(instance == null) {
      instance = new Guardian();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Guardian";
  }
  
  @Override
  public String getAbbrev() {
    return "Grd";
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
    return 20;
  }
  
  @Override
  public int getDexModifier() {
    return 0;
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
    return 10;
  }
  
  @Override
  public int getChaModifier() {
    return 0;
  }
}
