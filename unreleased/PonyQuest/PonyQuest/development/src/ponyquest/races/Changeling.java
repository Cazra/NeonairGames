package ponyquest.races;

public class Changeling implements Race {
  
  private static Changeling instance = null;
  
  public static Changeling getInstance() {
    if(instance == null) {
      instance = new Changeling();
    }
    return instance;
  }
  
  
  public String getName() {
    return "Changeling";
  }
  
  public int getHPModifier() {
    return 16;
  }
  
  public int getSPModifier() {
    return 16;
  }
  
  public int getStrModifier() {
    return 16;
  }
  
  public int getVitModifier() {
    return 16;
  }
  
  public int getDexModifier() {
    return 16;
  }
  
  public int getAgiModifier() {
    return 16;
  }
  
  public int getKnoModifier() {
    return 16;
  }
  
  public int getInsModifier() {
    return 16;
  }
  
  public int getChaModifier() {
    return 16;
  }
  
  public int getCritModifier() {
    return 10;
  }
}