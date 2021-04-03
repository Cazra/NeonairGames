package ponyquest.data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import ponyquest.jobs.Commoner;
import ponyquest.jobs.Job;
import ponyquest.races.Changeling;
import ponyquest.races.Race;
import ponyquest.sprites.CharaSprite;
import ponyquest.sprites.characters.PonyFactory;


/** PCData used to test out character sprites. */
public class ChangelingPCData extends PCData implements Serializable {
  
  private String disguisedAs;
  
  
  public ChangelingPCData() {
    level = 1;
    hpMax = 100;
    hpCur = 100;
    spCur = 50;
    spMax = 50;
    
    str = 5;
    vit = 5;
    dex = 5;
    agi = 5;
    kno = 5;
    ins = 5;
    cha = 5;
    spd = 5;
    ct = 5;
    
    cexp = 0;
    ncexp = 0;
    
    disguisedAs = "";
  }
  
  
  /** The changeling takes the form of another character. */
  public void transform(String character) {
    disguisedAs = character;
  }
  
  
  
  @Override
  public Job getJob() {
    return Commoner.getInstance();
  }
  
  
  @Override
  public Race getRace() {
    return Changeling.getInstance();
  }
  
  
  @Override
  public String getName() {
    return "Changeling";
  }
  
  
  @Override
  public Image getPortrait(int emote) {
    return new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);
  }
  
  
  @Override
  public Image getImage(int animation, int direction, int frame) {
    return CharaSprite.getImage(disguisedAs, animation, direction, frame);
  } 
  
  
  @Override
  public CharaSprite createSprite() {
    return PonyFactory.getPony(disguisedAs);
  }
  
}

