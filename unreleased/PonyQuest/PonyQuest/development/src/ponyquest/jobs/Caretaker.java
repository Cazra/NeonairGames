package ponyquest.jobs;


/** Skills focused on taming and calling pokémon-- er, "monster friends" into battle. */
public class Caretaker implements Job {
  
  private static Caretaker instance = null;
  
  public static Caretaker getInstance() {
    if(instance == null) {
      instance = new Caretaker();
    }
    return instance;
  }
  
  
  @Override
  public String getName( ) {
    return "Caretaker";
  }
  
  @Override
  public String getAbbrev() {
    return "Crt";
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
    return 0;
  }
  
  @Override
  public int getKnoModifier() {
    return 0;
  }
  
  @Override
  public int getInsModifier() {
    return 20;
  }
  
  @Override
  public int getChaModifier() {
    return 10;
  }
}
