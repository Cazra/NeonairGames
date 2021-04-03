package ponyquest.maps;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pwnee.GameMath;
import pwnee.image.*;
import pwnee.input.Keyboard;
import pwnee.sprites.Sprite;

import ponyquest.Directions;
import ponyquest.Player;
import ponyquest.PonyMain;
import ponyquest.data.ChangelingPCData;
import ponyquest.data.PartyData;
import ponyquest.dialog.DialogSystem;
import ponyquest.dialog.model.DialogModel;
import ponyquest.json.*;
import ponyquest.sprites.*;
import ponyquest.sprites.hidden.DirectionSprite;
import ponyquest.sprites.characters.ApplebloomSprite;
import ponyquest.sprites.characters.LyraHeartstringsSprite;
import ponyquest.sprites.characters.DerpySprite;
import ponyquest.sprites.characters.DinkySprite;
import ponyquest.sprites.characters.PonyFactory;
import ponyquest.sprites.characters.PonySprite;
import ponyquest.sprites.tiles.*;


/** A map used as a testing ground for several of the game's basic field mode features. */
public class AnimationTestMap extends AreaMap {
  
  public HiddenSprite startPoint;
  
  public ApplebloomSprite applebloom;
  public DerpySprite derpy;
  public DinkySprite dinky;
  public LyraHeartstringsSprite lyra;
  
  public DirectionSprite applebloomGoSouth;
  public DirectionSprite applebloomGoNorth;
  
  public Sprite sign1;
  
  public List<WarpTile> warpTiles = new ArrayList<>();
  
  public List<Sprite> rocks = new ArrayList<>();
  
  
  /** The number of times you've talked to Lyra on this test map. */
  private int lyraTalk = 0;
  
  
  /** Construct the test map from its json code. */
  public AnimationTestMap() {
    loadImages(PonyMain.imageLib);
    makeMap(new YarharJSON("maps/obstacleTest.jmap"));
    
    dialogs = DialogSystem.readDialogs("scripts/AnimationTestLevel.txt");
    
    DialogSystem.showDialog(dialogs.get("selectPony"));
    
    
  }
  
  
  public void loadImages(ImageLibrary imgLib) {
    ImageLoader il = new ImageLoader();
    String imgKey;
    Image img;
   
    il.addImage(SimpleTile.loadTile(imgLib, "grass01"));
    il.addImage(SimpleTile.loadTile(imgLib, "largeRock01"));
    
    il.waitForAll();
  }
  
  
  protected LayerSprite interpretSprite(SpriteJSON sj) {
    LayerSprite sprite = null;
    if(sj.type.equals("Obstacle")) {
      sprite = new ObstacleSquare();
    }
    else if(sj.type.equals("ObstacleTri")) {
      sprite = new ObstacleTri();
    }
    else if(sj.type.equals("startPoint")) {
      startPoint = new HiddenSprite(sj.type);
      sprite = startPoint;
    }
    else if(sj.type.equals("ApplebloomGoSouth")) {
      applebloomGoSouth = new DirectionSprite(Directions.SOUTH);
      sprite = applebloomGoSouth;
    }
    else if(sj.type.equals("ApplebloomGoNorth")) {
      applebloomGoNorth = new DirectionSprite(Directions.NORTH);
      sprite = applebloomGoNorth;
    }
    else if(sj.type.equals("Applebloom")) {
      applebloom = new ApplebloomSprite(0,0) {
        
        // Whether Applebloom is at the north or south end of her "follow me!" routine.
        // true if south.
        String northsouth = "south";
        
        @Override
        public double getBaseSpeed() {
          return 2;
        }
        
        
        @Override
        public void setStringProperty(String name, String value) {
          if("applebloomLocation".equals(name)) {
            northsouth = value;
          }
        }
        
        @Override
        public String getStringProperty(String name) {
          if("applebloomLocation".equals(name)) {
            return northsouth;
          }
          else {
            return null;
          }
        }
      };
      applebloom.direction = Directions.SOUTH;
      applebloom.isObstacle = true;
      sprite = applebloom;
    }
    else if(sj.type.equals("Dinky")) {
      dinky = new DinkySprite(0,0);
      dinky.direction = Directions.SOUTH;
      dinky.isObstacle = true;
      sprite = dinky;
    }
    else if(sj.type.equals("Derpy")) {
      derpy = new DerpySprite(0,0);
      derpy.direction = Directions.WEST;
      derpy.isObstacle = true;
      sprite = derpy;
    }
    else if(sj.type.equals("LyraHeartstrings")) {
      lyra = new LyraHeartstringsSprite(0,0);
      lyra.direction = Directions.EAST;
      lyra.isObstacle = true;
      sprite = lyra;
    }
    else if(sj.type.equals("toLayer1")) {
      sprite = new WarpTile(0);
      warpTiles.add((WarpTile) sprite);
    }
    else if(sj.type.equals("toLayer2")) {
      sprite = new WarpTile(1);
      warpTiles.add((WarpTile) sprite);
    }
    else if(sj.type.equals("toLayer3")) {
      sprite = new WarpTile(2);
      warpTiles.add((WarpTile) sprite);
    }
    else if(sj.type.equals("largeRock01")) {
      sprite = new RockTile(sj.type);
      rocks.add(sprite);
    }
    else if(sj.type.equals("sign01")) {
      sprite = new SignTile(sj.type);
      sign1 = sprite;
    }
    else {
      sprite = new SimpleTile(sj.type);
    }
    return sprite;
  }
  
  
  @Override
  public void logic() {
    Keyboard keyboard = PonyMain.getKeyboard();
    
    if(Player.sprite != null) {
      // Is the player standing on a warp tile?
      // If so, warp them to that warp tile's target layer.
      for(WarpTile warpTile : this.warpTiles) {
        if(Player.sprite.collideWith(warpTile)) {
          warpTile.warp(Player.sprite, this);
        }
      }
      
      // switch ponies.
      if(keyboard.justPressed(KeyEvent.VK_R)) {
        DialogSystem.showDialog(dialogs.get("selectPony"));
        Player.sprite.destroy();
        Player.sprite = null;
      }
    }
    
    applebloom.animate();
    lyra.animate();
    derpy.animate();
    dinky.animate();
  }
  
  
  public void tryCheckObjects() {
    
    // Applebloom wants the player to follow her back and forth from the south 
    // to north ends of the map.
    if(Player.isFacing(applebloom)) {
      DialogSystem.showDialog(dialogs.get("Applebloom"));
      applebloom.lookAt(Player.sprite);
    }
    
    // Dinky's dialog is a cheap reference to Rainbow Dash Presents.
    if(Player.isFacing(dinky)) {
      DialogSystem.showDialog(dialogs.get("Dinky"));
      dinky.lookAt(Player.sprite);
    }
    
    // ...Derp.
    if(Player.isFacing(derpy)) {
      DialogSystem.showDialog(dialogs.get("Derpy"));
      derpy.lookAt(Player.sprite);
    }
  
    // Lyra's dialog demonstrates a variable switch dialog. 
    if(Player.isFacing(lyra)) {
      DialogSystem.showDialog(dialogs.get("Lyra"));
      lyra.lookAt(Player.sprite);
    }
    
    // The sign has some things to say about the dialog system. 
    if(Player.isFacing(sign1)) {
      DialogSystem.showDialog(dialogs.get("1"));
    }
    
    // talking rocks!
    for(Sprite rock : rocks) {
      if(Player.isFacing(rock)) {
        DialogSystem.showDialog(dialogs.get("rock"));
      }
    }
  }
  
  public String getVarString(String var) {
    if("lyraTalk".equals(var)) {
      lyraTalk++;
      return "" + lyraTalk;
    }
    else if("applebloomLocation".equals(var)) {
      return applebloom.getStringProperty(var);
    }
    else {
      return null;
    }
  }
  
  
  /** Runs the game logic for a dialog event node in context to this map. */
  public void doEventString(String event) {
    
    if(event.startsWith("be")) {
    
      // The player is assumed to be the changeling character for this map 
      // for sprite testing.
      ChangelingPCData changeling = PartyData.getData().getChangelingData();
      changeling.transform(event.substring(2));
      
      Player.sprite = PartyData.getData().getChangelingData().createSprite(); //PonyFactory.getPony(event.substring(2));
      Player.sprite.x = startPoint.x;
      Player.sprite.y = startPoint.y;
      Player.sprite.setLayer(startPoint.curLayer);
    }
    else if("ApplebloomGoNorth".equals(event)) {
      applebloom.aiRunTowards(applebloomGoSouth);
      
      if(applebloom.isOverlapping(applebloomGoSouth)) {
        applebloomGoSouth.applyTo(applebloom);
        DialogSystem.unblock();
        applebloom.setStringProperty("applebloomLocation", "north");
        applebloom.animation = CharaSprite.STOPPED;
      }
    }
    else if("ApplebloomGoSouth".equals(event)) {
      applebloom.aiRunTowards(applebloomGoNorth);
      
      if(applebloom.isOverlapping(applebloomGoNorth)) {
        applebloomGoNorth.applyTo(applebloom);
        DialogSystem.unblock();
        applebloom.setStringProperty("applebloomLocation", "south");
        applebloom.animation = CharaSprite.STOPPED;
      }
    }
  }
  
  
  
  //////// Rendering
  
  /** We do some fancy rendering tricks in this map, just to show off the layer engine. */
  @Override
  public void render(Graphics2D g) {
    int playerLayer = 0; 
    if(Player.sprite != null) {
      playerLayer = layers.indexOf(Player.sprite.curLayer);
    }
    
    for(int i = 0; i < layers.size(); i++) {
      Layer layer = layers.get(i);
      
      layer.opacity = 1.0;
      if(i > playerLayer) {
        layer.opacity /= i - playerLayer + 1;
      }
      
      layer.render(g);
      
      if(i < playerLayer) {
        fadeBelow(g);
      }
    }
  }
  
  private void fadeBelow(Graphics2D g) {
    AffineTransform origTrans = g.getTransform();
    g.setTransform(new AffineTransform());
    
    g.setColor(new Color(0x77FFFFFF, true));
    g.fillRect(0, 0, PonyMain.resWidth, PonyMain.resHeight);
    
    g.setTransform(origTrans);
  }
}
