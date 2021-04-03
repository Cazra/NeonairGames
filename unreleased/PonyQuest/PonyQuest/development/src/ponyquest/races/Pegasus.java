package ponyquest.races;

public class Pegasus implements Race {

  @Override
  public String getName() {
    return "Pegasus";
  }
  
  @Override
  public int getHPModifier() {
    return 6;
  }
  
  @Override
  public int getSPModifier() {
    return 6;
  }
  
  @Override
  public int getStrModifier() {
    return 19;
  }
  
  @Override
  public int getVitModifier() {
    return 18;
  }
  
  @Override
  public int getDexModifier() {
    return 20;
  }
  
  @Override
  public int getAgiModifier() {
    return 19;
  }
  
  @Override
  public int getKnoModifier() {
    return 18;
  }
  
  @Override
  public int getInsModifier() {
    return 17;
  }
  
  @Override
  public int getChaModifier() {
    return 16;
  }
  
  @Override
  public int getCritModifier() {
    return 10;
  }
  
}