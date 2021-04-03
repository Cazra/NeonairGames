package ponyquest.races;

public class EarthPony implements Race {

  @Override
  public String getName() {
    return "Earth Pony";
  }
  
  @Override
  public int getHPModifier() {
    return 7;
  }
  
  @Override
  public int getSPModifier() {
    return 5;
  }
  
  @Override
  public int getStrModifier() {
    return 20;
  }
  
  @Override
  public int getVitModifier() {
    return 19;
  }
  
  @Override
  public int getDexModifier() {
    return 19;
  }
  
  @Override
  public int getAgiModifier() {
    return 17;
  }
  
  @Override
  public int getKnoModifier() {
    return 16;
  }
  
  @Override
  public int getInsModifier() {
    return 18;
  }
  
  @Override
  public int getChaModifier() {
    return 18;
  }
  
  
  @Override
  public int getCritModifier() {
    return 15;
  }
}