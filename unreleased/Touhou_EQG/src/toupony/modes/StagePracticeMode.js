/** 
 * The gameplay mode for practicing a single stage. 
 * @constructor
 * @param {enum: Toupony.Difficulty} difficulty
 * @param {enum: Toupony.PlayerTeams} team
 * @param {Toupony.Stage} stage
 */
Toupony.StagePracticeMode = function(difficulty, team, stage) {
  Toupony.GameplayMode.call(this, difficulty, team);
  this._stage = stage;
};

Toupony.StagePracticeMode.prototype = {
  constructor: Toupony.StagePracticeMode,
  
  isaStagePracticeMode: true,
  
  getStartLives: function() {
    return Toupony.MAX_LIVES;
  },
  
  getStartBombs: function() {
    return 3;
  },
  
  getStartStage: function() {
    return this._stage;
  }
  
};

Util.Inheritance.inherit(Toupony.StagePracticeMode, Toupony.GameplayMode);
