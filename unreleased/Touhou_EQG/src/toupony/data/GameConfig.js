/** 
 * The game's configurable data.
 * The constructor sets the data up with default values,
 * as thought the user has never played the game before.
 * @constructor
 */
Toupony.GameConfig = function() {
  this.startLives(3);
  this.startBombs(3);
  this.extraUnlocked(false);
  
  this._highScores = new Toupony.HighScoresRecords();
  this._spellCards = {}; // TODO new Toupony.SpellCardRecords();
  this._achievements = {}; // TODO new Toupony.AchievementRecords();
};


/** 
 * Produces a GameConfig from a json object representation. 
 * @param {object} jObj
 * @return {Toupony.GameConfig}
 */
Toupony.GameConfig.fromJSON = function(jObj) {
  this.startLives(jObj.startLives);
  this.startBombs(jObj.startBombs);
  this.extraUnlocked(jObj.extraUnlocked);
  
  this._highScores = Toupony.HighScoresRecords.fromJSON(jObj.highScores);
  this._spellCards = {}; // TODO new Toupony.SpellCardRecords(jObj.spellCards);
  this._achievements = {}; // TODO new Toupony.AchievementRecords(jObj.achievements);
};


Toupony.GameConfig.prototype = {
  
  constructor: Toupony.GameConfig,
  
  isaGameConfig: true,
  
  
  
  /** 
   * Setter/getter for the starting number of lives. 
   * @param {uint} n    Optional
   * @return {uint}
   */
  startLives: function(n) {
    if(n !== undefined) {
      this._startLives = Math.max(0, Math.min(n, Toupony.MAX_LIVES));
    }
    return this._startLives;
  },
  
  
  /** 
   * Setter/getter for the starting number of lives. 
   * @param {uint} n    Optional.
   * @return {uint}
   */
  startBombs: function(n) {
    if(n !== undefined) {
      this._startBombs = Math.max(0, Math.min(n, Toupony.MAX_BOMBS));
    }
    return this._startBombs;
  },
  
  /** 
   * Setter/getter for whether the extra stage has been 
   * unlocked.
   * @param {boolean} flag    Optional.
   * @return {boolean}
   */
  extraUnlocked: function(flag) {
    if(flag !== undefined) {
      this._extraUnlocked = flag;
    }
    return this._ExtraUnlocked;
  },
  
  
  /** 
   * Returns the highscores table for the specified difficulty and team. 
   * @param {enum: Toupony.Difficulty} dif
   * @param {enum: Toupony.PlayerTeams} team
   * @return {Toupony.HighScoresTable}
   */
  getHighScoresTable: function(dif, team) {
    return this._highScores.getTable(dif, team);
  },
  
  
  /** 
   * Returns the value of the highest score for the specified difficulty and
   * team. If there is no high score, 0 is returned.
   * @param {enum: Toupony.Difficulty} dif
   * @param {enum: Toupony.PlayerTeams} team
   * @return {int}
   */
  getHighestScore: function(dif, team) {
    var table = this.getHighScoresTable(dif, team);
    if(table.getCount() > 0) {
      return table.getHighScore(0).getScore();
    }
    else {
      return 0;
    }
  },
  
  
  /** 
   * Produces a JSON representation of the configurations.
   * @return {object}
   */
  toJSON: function() {
    var startLives = this._startLives;
    var startBombs = this._startBombs;
    var extraUnlocked = this._extraUnlocked;
    var highScores = this._highScores;
    var spellCardRecords = this._spellCardRecords;
    var achievements = this._achievements;
  }
};