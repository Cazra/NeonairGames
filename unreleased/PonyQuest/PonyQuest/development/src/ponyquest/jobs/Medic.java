package ponyquest.jobs;


/** Unicorn-exclusive.
 Magically mends friends with unicorn magic and medical supplies. */
public class Medic implements Job {
  
  private static Medic instance = null;
  
  public static Medic getInstance() {
    if(instance == null) {
      instance = new Medic();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Medic";
  }
  
  @Override
  public String getAbbrev() {
    return "Med";
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
    return 0;
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
    return 0;
  }
  
  @Override
  public int getKnoModifier() {
    return 20;
  }
  
  @Override
  public int getInsModifier() {
    return 20;
  }
  
  @Override
  public int getChaModifier() {
    return 20;
  }
}
