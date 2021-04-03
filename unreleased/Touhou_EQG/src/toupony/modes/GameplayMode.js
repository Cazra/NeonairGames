/** 
 * Base class for various gameplay modes.
 * @abstract
 * @constructor
 * @param {enum: Toupony.Difficulty} difficulty
 * @param {enum: Toupony.PlayerTeams} team
 */
Toupony.GameplayMode = function(difficulty, team) {
  this._difficulty = difficulty;
  this._team = team;
};

Toupony.GameplayMode.prototype = {
  
  constructor: Toupony.GameplayMode,
  
  isaGameplayMode: true,
  
  
  /** 
   * Returns the difficulty set for this gameplay mode.
   * @return {enum: Toupony.Difficulty}
   */
  getDifficulty: function() {
    return this._difficulty;
  },
  
  
  /** 
   * Returns the player team code.
   * @return {enum: Toupony.PlayerTeams}
   */
  getTeam: function() {
    return this._team;
  },
  
  
  /** 
   * Returns the starting lives for this gameplay mode, or -1 to use the default
   * starting lives value from the game's configurations.
   * @return {int}
   */
  getStartLives: function() {},
  
  
  /** 
   * Returns the starting bombs for this gameplay mode, or -1 to use the default
   * starting bombs value from the game's configurations.
   * @return {int}
   */
  getStartBombs: function() {},
  
  
  /** 
   * Returns the starting stage for this gameplay mode. 
   * @return {Toupony.Stage}
   */
  getStartStage: function() {},
  
  
  /** 
   * Whether this mode allows the player to use continues.
   * @return {boolean}
   */
  continuesAllowed: function() {}
};

