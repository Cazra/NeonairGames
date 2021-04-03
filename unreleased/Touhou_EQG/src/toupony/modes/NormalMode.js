/** 
 * Normal gameplay mode - nothing special here. We're doing a completely normal 
 * run through the game, stages 1-6 with no special rules.
 */
Toupony.NormalMode = function(difficulty, team) {
  Toupony.GameplayMode.call(this, difficulty, team);
};

Toupony.NormalMode.prototype = {
  constructor: Toupony.NormalMode,
  
  isaNormalMode: true,
    
  getStartLives: function() {
    return -1;
  },

  getStartBombs: function() {
    return -1;
  },
  
  /** 
   * Normal gameplay starts at stage 1. 
   */
  getStartStage: function() {
    return new Toupony.Stage(); // TODO new Toupony.Stage1();
  },
  
  /** 
   * The player is allowed to use continues in normal gameplay.
   */
  continuesAllowed: function() {
    return true;
  }
};

Util.Inheritance.inherit(Toupony.NormalMode, Toupony.GameplayMode);
