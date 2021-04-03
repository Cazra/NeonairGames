package ponyquest.jobs;


/** Variety of skills ranging from debuffs to aggro transfer. Can annihilate enemies who aren't paying attention. */
public class Prankster implements Job {
  
  private static Prankster instance = null;
  
  public static Prankster getInstance() {
    if(instance == null) {
      instance = new Prankster();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Prankster";
  }
  
  @Override
  public String getAbbrev() {
    return "Pra";
  }
  
  @Override
  public int getHPModifier() {
    return -200;
  }
  
  @Override
  public int getSPModifier() {
    return 0;
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
    return 20;
  }
  
  @Override
  public int getAgiModifier() {
    return 10;
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
