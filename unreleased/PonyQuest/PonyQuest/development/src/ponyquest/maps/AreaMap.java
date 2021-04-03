package ponyquest.maps;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pwnee.image.*;
import pwnee.sprites.Sprite;

import ponyquest.dialog.model.DialogModel;
import ponyquest.json.*;
import ponyquest.sprites.*;
import ponyquest.sprites.tiles.*;

/** 
 * Superclass for area maps loaded from JSON code.
 * Each layer in a map must consist of 3 YarHar layers in the 
 * following order from top to bottom: 
 * Obstacles - invisible collision polygon sprites.
 * Sprites - Above-ground objects drawn in order from north to south.
 * Tiles - Ground tiles. They are rendered in the same order they are given in the json.
 */
public abstract class AreaMap {
  
  /** Width of the map. */
  protected int width = 0;
  
  /** Height of the map. */
  protected int height = 0;
  
  /** The layers for this map, ordered from top to bottom. */
  public List<Layer> layers = new ArrayList<>();
  
  /** Dialogs for this map. */
  public Map<String, DialogModel> dialogs;
  
  /** 
   * Creates the map from its json representation. 
   * Call this from the AreaMap implementation's constructor only once. 
   */
  protected void makeMap(YarharJSON json) {
    System.out.println("Making map: " + json);
    width = json.gridW;
    height = json.gridH;
    
    for(int i = 0; i < json.layers.size(); i+= 3) {
      LayerJSON obstacleLayer = json.layers.get(i+2);
      LayerJSON spriteLayer = json.layers.get(i+1);
      LayerJSON tileLayer = json.layers.get(i);
      
      addLayer(makeLayer(tileLayer, spriteLayer, obstacleLayer));
    }
  }
  
  
  /** Converts json for a layer into an actual layer with actual sprites. */
  protected Layer makeLayer(LayerJSON tileLayer, LayerJSON spriteLayer, LayerJSON obstacleLayer) {
    Layer layer = new Layer();
    layer.name = tileLayer.name;
    
    // create all tiles for this layer.
    for(SpriteJSON sj : tileLayer.sprites) {
      LayerSprite sprite = interpretSprite(sj);
      sj.copyPropsTo(sprite);
      layer.addTile(sprite);
    }
    
    // create all the sprites for this layer.
    for(SpriteJSON sj : spriteLayer.sprites) {
      LayerSprite sprite = interpretSprite(sj);
      sj.copyPropsTo(sprite);
      sprite.setLayer(layer);
    }
    
    // Create all the collision shapes for this layer.
    for(SpriteJSON sj : obstacleLayer.sprites) {
      LayerSprite sprite = interpretSprite(sj);
      sj.copyPropsTo(sprite);
      layer.addObstacle(sprite);
    }
    
    return layer;
  }
  
  
  /** Produces an appropriate sprite from its json representation. */
  protected abstract LayerSprite interpretSprite(SpriteJSON sj);
  
  /**
   * Adds a layer to this map. This becomes the bottom layer.
   */
  public void addLayer(Layer layer) {
    layers.add(layer);
  }
  
  
  /** Returns the width of the map. */
  public int getWidth() {
    return width;
  }
  
  
  /** Returns the height of the map. */
  public int getHeight() {
    return height;
  }
  
  
  /** Runs 1 iteration of game logic specific to this map. */
  public abstract void logic();
  
  /** 
   * Make the player check an object that they are next to and facing. 
   * This generally starts dialogs, but can also create other kinds of events.
   */
  public abstract void tryCheckObjects();
  
  /** Returns the value of some variable specific to this map as a String. */
  public abstract String getVarString(String var);
  
  /** Activates some event specific to this map. */
  public abstract void doEventString(String event); 
  
  /** Renders all the sprites on this map in their appropriate layers. */
  public void render(Graphics2D g) {
    for(Layer layer : layers) {
      layer.render(g);
    }
  }

}

