/**
 * The main gameplay level, providing the context for our game.
 * @constructor
 * @param {Toupony} app
 * @param {Toupony.GameplayMode} mode
 */
Toupony.MainLevel = function(app, mode) {
  TentaGL.Level.call(this, app);
  this._mode = mode;
};


Toupony.MainLevel.prototype = {
  
  constructor: Toupony.MainLevel,
  
  /** 
   * Returns the gameplay settings.
   * @return {Toupony.GameplayMode}
   */
  getMode: function() {
    return this._mode;
  },
  
  /** 
   * Returns the object containing the game's configurations and saved data.
   * @return {Toupony.GameConfig}
   */
  getConfig: function() {
    return this._app.getGameData();
  },
  
  /** 
   * Returns the player team code.
   * @return {enum: Toupony.PlayerTeams}
   */
  getTeam: function() {
    return this._mode.getTeam();
  },
  
  /** 
   * Returns the gameplay difficulty.
   * @return {enum: Toupony.Difficulty}
   */
  getDifficulty: function() {
    return this._mode.getDifficulty();
  },
  
  
  /** 
   * Returns the player. 
   * @return {Toupony.Player}
   */
  getPlayer: function() {
    return this._player;
  },
  
  
  /** 
   * Returns the player's stats.
   * @return {Toupony.PlayerStats}
   */
  getPlayerStats: function() {
    return this._player.getStats();
  },
  
  
  /** 
   * Returns the high score to be displayed in the HUD. 
   * @return {uint}
   */
  getHighScore: function() {
    var difficulty = this.getDifficulty();
    var team = this.getTeam();
    var config = this.getConfig();
    var highScore = config.getHighestScore(difficulty, team);
    
    return Math.max(highScore, this.getPlayerStats().score());
  },
  
  
  
  /**
   * @inheritDoc TentaGL.Level
   */
  clean: function(gl) {
    Toupony.MainHUD.clean(gl);
    Toupony.Player.clean(gl);
    Toupony.TwiPlayer.clean(gl);
    Toupony.EnemyBullet.clean(gl);
  },
  
  
  /**
   * @inheritDoc TentaGL.Level
   */
  reset: function(gl) {
    this._loadResources(gl);
    
    this._initHUD();
    this._initStage();
    this._initPlayer();
    this._initSpriteGroups();
    
    this._paused = false;
    
    
    this._enemies.add(new TentaGL.Sprite([100, 100]));
    this._enemies.add(new TentaGL.Sprite([200, 200]));
    this._enemies.add(new TentaGL.Sprite([300, 100]));
  },
  
  
  /** 
   * Loads graphics and other resources for the level.
   */
  _loadResources: function(gl) {
    Toupony.MainHUD.load(gl);
    Toupony.Player.load(gl);
    Toupony.TwiPlayer.load(gl);
    Toupony.EnemyBullet.load(gl);
  },
  
  /** 
   * Initializes the HUD for our gameplay mode. 
   */
  _initHUD: function() {
    this._hud = new Toupony.MainHUD(this._mode);
  },
  
  
  /** 
   * Initializes the stage to be the starting stage for our gameplay mode.
   */
  _initStage: function() {
    this._stage = this._mode.getStartStage();
  },
  
  
  /** 
   * Creates the player sprite based on the user's gameplay specifications.
   */
  _initPlayer: function() {
    var config = this.getConfig();
    var mode = this.getMode();
    
    this._player = Toupony.PlayerFactory.createPlayer(config, mode);
    this._player.xyz([Toupony.STAGE_WIDTH*0.5, Toupony.STAGE_HEIGHT*0.8, 0]);
  },
  
  /** 
   * Initializes the various sprite groups managed by the game context.
   */
  _initSpriteGroups: function() {
    this._enemies = new Toupony.SpriteGroup();
    this._playerBullets = new Toupony.SpriteGroup();
    this._enemyBullets = new Toupony.SpriteGroup();
    this._items = new Toupony.SpriteGroup();
    this._sfx = new Toupony.SpriteGroup();
  },
  
  
  /** 
   * Returns the SpriteGroup for the player's bullets.
   * @return {Toupony.SpriteGroup}
   */
  getPlayerBullets: function() {
    return this._playerBullets;
  },
  
  /** 
   * Returns the SpriteGroup for the enemies.
   * @return {Toupony.SpriteGroup}
   */
  getEnemies: function() {
    return this._enemies;
  },
  
  /** 
   * Returns the SpriteGroup for the enemy bullets.
   * @return {Toupony.SpriteGroup}
   */
  getEnemyBullets: function() {
    return this._enemyBullets;
  },
  
  /** 
   * Returns the SpriteGroup for the items.
   * @return {Toupony.SpriteGroup}
   */
  getItems: function() {
    return this._items;
  },
  
  /** 
   * Returns the SpriteGroup for the special effects.
   * @return {Toupony.SpriteGroup}
   */
  getSpecialFX: function() {
    return this._sfx;
  },
  
  
  
  /** 
   * @inheritDoc TentaGL.Level
   */
  update: function(gl) {
    
    var keyboard = this.keyboard();
    var context = this;
    
    Toupony.Cheats.run(keyboard, context);
    
    if(this._paused) {
      // TODO: run pause menu.
    }
    else {
      this._stage.run(context);
      this._player.control(keyboard, context);
      
      // Move all the other sprites. 
      this._playerBullets.forEach(function(bullet) {
        bullet.move(context);
      });
      
      // TODO: handle collisions.
      
      this._animateEverything();
    }
  },
  
  _animateEverything: function() {
    this._player.animate();
    
    this._enemies.animate();
    this._enemyBullets.animate();
    this._playerBullets.animate();
    this._items.animate();
    this._sfx.animate();
  },
  
  
  /** 
   * @inheritDoc TentaGL.Level
   */
  render: function(gl) {
    var self = this;
    var aspect = gl.canvas.width/gl.canvas.height;
    
    TentaGL.clear(gl, new TentaGL.Color([0.1, 0.1, 0.3, 1]));
    
    TentaGL.ShaderLib.use(gl, "simple");
    TentaGL.ViewTrans.setCamera(gl, this.getApp().getCamera2D(), aspect);
    TentaGL.RenderMode.set2D(gl);
    
    this._renderPlayfieldContent(gl);
    
    this._hud.renderWithStats(gl, this.getPlayerStats(), this.getHighScore());
  },
  
  
  /** 
   * Renders all objects in the playable area. 
   * @param {WebGLRenderingContext} gl
   */
  _renderPlayfieldContent: function(gl) {
    TentaGL.ViewTrans.push(gl);
    TentaGL.ViewTrans.translate(gl, [Toupony.STAGE_X, Toupony.STAGE_Y]);
    
    // Render stage's current background.
    // this._stage.renderBackground(gl);
    
    Toupony.getPlayerFont().renderString(gl, 
      " !\"#$%&'()*+,-./\n" +
      "0123456789:;<=>?\n" +
      "@ABCDEFGHIJKLMNO\n" +
      "PQRTSUVWXYZ[\\]^_\n" +
      "`abcdefghijklmno\n" +
      "pqrstuvwxyz{|}~",
      [0,0], true, 28
    );
    
    this._player.render(gl);
    
    this._enemies.render(gl);
    this._enemyBullets.render(gl);
    this._playerBullets.render(gl);
    this._items.render(gl);
    this._sfx.render(gl);
    
    TentaGL.ViewTrans.pop(gl);
  }
  
  
};


Util.Inheritance.inherit(Toupony.MainLevel, TentaGL.Level);


