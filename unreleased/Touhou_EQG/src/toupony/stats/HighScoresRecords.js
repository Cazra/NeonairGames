/** 
 * The collection of all the saved high scores tables for the game. 
 * The high score tables are separated by difficulty and by player character teams.
 * @constructor
 */
Toupony.HighScoresRecords = function() {
  this._records = {};
  
  this._initForDifficulty(Toupony.Difficulty.EASY);
  this._initForDifficulty(Toupony.Difficulty.NORMAL);
  this._initForDifficulty(Toupony.Difficulty.HARD);
  this._initForDifficulty(Toupony.Difficulty.LUNATIC);
  this._initForDifficulty(Toupony.Difficulty.EXTRA);
};


/** 
 * Produces a HighScoresRecords from a json object representation. 
 * @param {object} jObj
 * @return {Toupony.HighScoresRecords}
 */
Toupony.HighScoresRecords.fromJSON = function(jObj) {
  var result = new Toupony.HighScoresRecords();
  result._records = Util.JSON.clone(jObj);
    
  for(var difficulty in jObj) {
    for(var team in jObj[difficulty]) {
      var jTable = jObj[difficulty][team];
      var table = Toupony.HighScoresTable.fromJSON(jTable);
      
      result._records[difficulty][team] = table;
    }
  }
  
  return result;
};


Toupony.HighScoresRecords.prototype = {
  
  constructor: Toupony.HighScoresRecords,
  
  isaHighScoresRecords: true, 
  
  
  _initForDifficulty: function(dif) {
    this._records[dif] = {};
    
    this._initForTeam(dif, Toupony.PlayerTeams.TWI_RARE);
    this._initForTeam(dif, Toupony.PlayerTeams.DASH_PIE);
    this._initForTeam(dif, Toupony.PlayerTeams.FLUTTER_JACK);
  },
  
  _initForTeam: function(dif, team) {
    this._records[dif][team] = new Toupony.HighScoresTable();
  },
  
  
  /** 
   * Returns the high scores table for a particular difficulty and player team.
   * @param {enum: Toupony.Difficulty} difficulty
   * @param {enum: Toupony.PlayerTeams} team
   * @return {Toupony.HighScoresTable}
   */
  getTable: function(difficulty, team) {
    return this._records[difficulty][team];
  },
  
  
  /** 
   * Produces a JSON representation of the this object. 
   */
  toJSON: function() {
    return Util.JSON.clone(this._records);
  }
};
