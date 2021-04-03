/** 
 * An object encapsulating the player's stats such as their lives, bombs, score,
 * etc.
 * @constructor
 * @param {Toupony.MainLevel} mainLevel
 */
Toupony.PlayerStats = function(config, mode) {
  var difficulty = mode.getDifficulty();
  var team = mode.getTeam();
  
  this._lives = mode.getStartLives();
  if(this._lives == -1) {
    this._lives = config.startLives();
  }
  
  this._bombs = mode.getStartBombs();
  if(this._bombs == -1) {
    this._bombs = config.startBombs();
  }
  
  this._score = 0;
  this._power = 0.0;
  this._graze = 0;
  this._continues = 3;
};


Toupony.PlayerStats.prototype = {
  
  constructor: Toupony.PlayerStats,
  
  isaPlayerStats: true,
  
  
  /** 
   * Setter/getter for the player's number of lives.
   * @param {int} n
   * @return {int}
   */
  lives: function(n) {
    if(n !== undefined) {
      this._lives = n;
    }
    return this._lives;
  },
  
  /** 
   * Setter/getter for the player's number of bombs.
   * @param {int} n
   * @return {int}
   */
  bombs: function(n) {
    if(n !== undefined) {
      this._bombs = n;
    }
    return this._bombs;
  },
  
  /** 
   * Setter/getter for the player's power.
   * @param {float} n
   * @return {float}
   */
  power: function(n) {
    if(n !== undefined) {
      n = Math.max(0, Math.min(n, 4.0));
      this._power = n;
    }
    return this._power;
  },
  
  /**
   * Setter/getter for the player's graze.
   * @param {int} n
   * @return {int}
   */
  graze: function(n) {
    if(n !== undefined) {
      this._graze = n;
    }
    return this._graze;
  },
  
  
  /** 
   * Setter/getter for the player's score. 
   * @param {uint} n
   * @return {uint}
   */
  score: function(n) {
    if(n !== undefined) {
      this._score = n;
    }
    return this._score;
  }
};
