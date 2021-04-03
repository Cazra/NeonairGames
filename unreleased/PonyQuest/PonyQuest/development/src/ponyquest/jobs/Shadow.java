package ponyquest.jobs;


/** Evasion tank. No AoE aggro skills; more effective than GRD in some situations, but harder to play. */
public class Shadow implements Job {
  
  private static Shadow instance = null;
  
  public static Shadow getInstance() {
    if(instance == null) {
      instance = new Shadow();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Shadow";
  }
  
  @Override
  public String getAbbrev() {
    return "Sha";
  }
  
  @Override
  public int getHPModifier() {
    return -200;
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
    return 0;
  }
  
  @Override
  public int getAgiModifier() {
    return 20;
  }
  
  @Override
  public int getKnoModifier() {
    return 0;
  }
  
  @Override
  public int getInsModifier() {
    return 0;
  }
  
  @Override
  public int getChaModifier() {
    return -10;
  }
}
