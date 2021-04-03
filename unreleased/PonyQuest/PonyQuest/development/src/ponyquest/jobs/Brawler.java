package ponyquest.jobs;


/** Heavy-hitting basic Warrior-type class. Has AoE skills and accumulates aggro quickly. */
public class Brawler implements Job {
  
  private static Brawler instance = null;
  
  public static Brawler getInstance() {
    if(instance == null) {
      instance = new Brawler();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Brawler";
  }
  
  @Override
  public String getAbbrev() {
    return "Brl";
  }
  
  @Override
  public int getHPModifier() {
    return 0;
  }
  
  @Override
  public int getSPModifier() {
    return -100;
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
    return 10;
  }
  
  @Override
  public int getAgiModifier() {
    return -10;
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
    return 0;
  }
}
