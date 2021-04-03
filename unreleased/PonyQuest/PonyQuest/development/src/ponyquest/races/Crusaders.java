package ponyquest.races;

public class Crusaders implements Race {

  @Override
  public String getName() {
    return "Crusaders";
  }
  
  @Override
  public int getHPModifier() {
    return 4;
  }
  
  @Override
  public int getSPModifier() {
    return 4;
  }
  
  @Override
  public int getStrModifier() {
    return 15;
  }
  
  @Override
  public int getVitModifier() {
    return 14;
  }
  
  @Override
  public int getDexModifier() {
    return 14;
  }
  
  @Override
  public int getAgiModifier() {
    return 15;
  }
  
  @Override
  public int getKnoModifier() {
    return 13;
  }
  
  @Override
  public int getInsModifier() {
    return 12;
  }
  
  @Override
  public int getChaModifier() {
    return 12;
  }
  
  
  @Override
  public int getCritModifier() {
    return 5;
  }
  
}