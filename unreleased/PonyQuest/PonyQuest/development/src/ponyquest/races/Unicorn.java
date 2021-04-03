package ponyquest.races;

public class Unicorn implements Race {

  @Override
  public String getName() {
    return "Unicorn";
  }
  
  @Override
  public int getHPModifier() {
    return 5;
  }
  
  @Override
  public int getSPModifier() {
    return 7;
  }
  
  @Override
  public int getStrModifier() {
    return 16;
  }
  
  @Override
  public int getVitModifier() {
    return 17;
  }
  
  @Override
  public int getDexModifier() {
    return 18;
  }
  
  @Override
  public int getAgiModifier() {
    return 18;
  }
  
  @Override
  public int getKnoModifier() {
    return 20;
  }
  
  @Override
  public int getInsModifier() {
    return 19;
  }
  
  @Override
  public int getChaModifier() {
    return 19;
  }
  
  @Override
  public int getCritModifier() {
    return 5;
  }
}