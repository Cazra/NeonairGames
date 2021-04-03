/** 
 * The gameplay mode for playing the extra stage. 
 */
Toupony.ExtraMode = function(team) {
  Toupony.GameplayMode.call(this, Toupony.Difficulty.EXTRA, team);
};

Toupony.ExtraMode.prototype = {
  constructor: Toupony.ExtraMode,
  
  isaExtraMode: true,
  
  
  getStartLives: function() {
    return 3;
  },
  
  
  getStartBombs: function() {
    return 3;
  },
  
  
  getStartStage: function() {
    return Toupony.Stages.ExtraStage();
  },
  
  
  continuesAllowed: function() {
    return false;
  }
  
};

Util.Inheritance.inherit(Toupony.ExtraMode, Toupony.GameplayMode);