/** 
 * Application and namespace for the Equestria Girls Touhou project.
 * @constructor
 * @param {div element} container
 */
var Toupony = function(container) {
  TentaGL.Application.call(this, container, {stencil:true, alpha: false});
  
  // TODO: Initialize global variables from localStorage.
};


Toupony.MAX_LIVES = 7;
Toupony.MAX_BOMBS = 7;

/** 
 * The game's width.
 */
Toupony.WIDTH = 640;

/** 
 * The game's height.
 */
Toupony.HEIGHT = 480;

/** 
 * The width of the danmaku play area.
 */
Toupony.STAGE_WIDTH = 400;

/** 
 * The height of the danmaku play area.
 */
Toupony.STAGE_HEIGHT = 448;

/** 
 * X offset of the danmaku play area from the left edge of the screen. 
 */
Toupony.STAGE_X = 32;

/** 
 * Y offset of the danmaku play area from the top edge of the screen.
 */
Toupony.STAGE_Y = 16;

/** 
 * Returns the main purple-border font for the game.
 * @return {TentaGL.BlitteredFont}
 */
Toupony.getFont = function() {
  if(!Toupony._font) {
    Toupony._font = TentaGL.BlitteredFont.fromURL("img/menu/font.png", false, [50, 56], [0, 4], 0);
  }
  return Toupony._font;
};

/** 
 * Returns the player dialog blue-border font for the game.
 * @return {TentaGL.BlitteredFont}
 */
Toupony.getPlayerFont = function() {
  if(!Toupony._fontPlayer) {
    Toupony._fontPlayer = TentaGL.BlitteredFont.fromURL("img/menu/fontPlayer.png", false, [50, 56], [0, 4], 0);
  }
  return Toupony._fontPlayer;
};

/** 
 * Returns the enemy dialog red-border font for the game.
 * @return {TentaGL.BlitteredFont}
 */
Toupony.getEnemyFont = function() {
  if(!Toupony._fontEnemy) {
    Toupony._fontEnemy = TentaGL.BlitteredFont.fromURL("img/menu/fontEnemy.png", false, [50, 56], [0, 4], 0);
  }
  return Toupony._fontEnemy;
};

Toupony.prototype = {
  
  constructor: Toupony,
  
  /** 
   * Returns the GameConfig object containing the game's save data.
   * @return {Toupony.GameConfig}
   */
  getGameData: function() {
    return this._config;
  },
  
  /** 
   * Loads game data from local storage.
   */
  loadGameData: function() {
    var gameConfigStr = localStorage.getItem("Toupony");
    if(gameConfigStr) {
      try {
        var gameConfig = JSON.parse(gameConfigStr);
        this._config = Toupony.GameConfig.fromJSON(gameConfig);
      }
      catch(err) {
        console.log("Error loading saved game data. Loading default settings instead.", err);
        this._config = new Toupony.GameConfig();
      }
    }
    else {
      this._config = new Toupony.GameConfig();
    }
  },
  
  /** 
   * Saves game data from local storage.
   */
  saveGameData: function() {
    var gameConfig = this._config.toJSON();
    var gameConfigStr = JSON.stringify(gameConfig);
    localStorage.setItem("Toupony", gameConfigStr);
  },
  
  
  
  /** 
   * Returns the game's 2D camera.
   * @return {TentaGL.Camera2D}
   */
  getCamera2D: function() {
    if(!this._cam2D) {
      this._cam2D = new TentaGL.Camera2D([0, 0], 
                                        Toupony.WIDTH, Toupony.HEIGHT, 
                                        true);
      this._cam2D.anchor([0,0]);
    }
    return this._cam2D;
  },
  
  
  /** 
   * Returns the main blittered font for the game.
   * @return {TentaGL.BlitteredFont}
   */
  getFont: function() {
    return this._font;
  },
  
  
  //////// TentaGL.Application implementations
  
  initShaders: function(gl) {
    TentaGL.SimpleShader.load(gl, "simple");
    TentaGL.PhongShader.load(gl, "phong");
    TentaGL.NormalShader.load(gl, "normal");
    TentaGL.GradientShader.load(gl, "gradient");
    TentaGL.PlaneSimpleShader.load(gl, "plane");
    TentaGL.CircleShader.load(gl, "circle");
    
    Toupony.AddColorShader.load(gl, "addColor");
  },
  
  initMaterials: function(gl) {
    TentaGL.MaterialLib.add(gl, "red", TentaGL.Color.RED);
  },
  
  
  initModels: function(gl) {
  
  },
  
  
  
  reset: function(gl) {
    this.loadGameData();
    // TODO: Go to title level
    
    var difficulty = Toupony.Difficulty.NORMAL;
    var team = Toupony.PlayerTeams.TWI_RARE;
    var mode = new Toupony.NormalMode(difficulty, team);
    
    var mainLevel = new Toupony.MainLevel(this, mode);
    this.setLevel(mainLevel);
  },
  
  
  update: function(gl) {
    if(TentaGL.ShaderLoader.isLoading()) {
      console.log("Loading shaders..." + TentaGL.ShaderLoader.getNumLoading());
      return;
    }
    if(TentaGL.ImageLoader.isLoading()) {
      console.log("Loading image data...");
      return;
    }
    if(TentaGL.Model.ObjReader.isLoading()) {
      console.log("Loading models..." + TentaGL.Model.ObjReader.getNumLoading());
      return;
    }
    if(TentaGL.AudioLoader.isLoading()) {
      console.log("Loading audio data...");
      return;
    }
    
    this.getLevelManager().run(gl);
  }
  
};


Util.Inheritance.inherit(Toupony, TentaGL.Application);

Toupony.main = function() {
  var container = document.getElementById("TouponyContainer");
  (new Toupony(container)).start();
};