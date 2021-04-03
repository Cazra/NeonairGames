package ponyquest.races;


/** A race for a player character. */
public interface Race {
  
  /** Returns the name of this job. */
  public String getName();
  
  /** Returns the job's max HP modifier. */
  public int getHPModifier();
  
  /** Returns the job's max SP modifier. */
  public int getSPModifier();
  
  /** Returns the job's Strength modifier. */
  public int getStrModifier();
  
  /** Returns the job's Vitality modifier. */
  public int getVitModifier();
  
  /** Returns the job's Dexterity modifier. */
  public int getDexModifier();
  
  /** Returns the job's Agility modifier. */
  public int getAgiModifier();
  
  /** Returns the job's Knowledge modifier. */
  public int getKnoModifier();
  
  /** Returns the job's Insight modifier. */
  public int getInsModifier();
  
  /** Returns the job's Charisma modifier. */
  public int getChaModifier();
  
  /** Returns the job's Crit modifier. */
  public int getCritModifier();
}
