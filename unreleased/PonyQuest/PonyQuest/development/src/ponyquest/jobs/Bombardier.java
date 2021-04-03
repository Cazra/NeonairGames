package ponyquest.jobs;


/** Pegasus-exclusive.
Heavyweight flier. Buffs accumulate during flight; sacrifices those buffs to deal massive damage on landing. */
public class Bombardier implements Job {
  
  private static Bombardier instance = null;
  
  public static Bombardier getInstance() {
    if(instance == null) {
      instance = new Bombardier();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Bombardier";
  }
  
  @Override
  public String getAbbrev() {
    return "Bom";
  }
  
  @Override
  public int getHPModifier() {
    return 200;
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
    return 0;
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
