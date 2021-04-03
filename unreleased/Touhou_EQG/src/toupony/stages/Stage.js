/**
 * Base class for the game's stages.
 * @abstract
 * @constructor
 */
Toupony.Stage = function() {
  this._time = 0;
}

Toupony.Stage.prototype = {
  constructor: Toupony.Stage, 
  
  isaStage: true,
  
  /** 
   * Runs one iteration through the stage's events and then increments the
   * stage's internal timer. 
   * @param {Toupony.MainLevel} mainLevel
   */
  run: function(mainLevel) {
    this._timeWasSet = false;
    this.runCurrentFrame(mainLevel);
    
    // Increment the time value if the time wasn't set during the the current frame.
    if(this._timeWasSet) {
      this._timeWasSet = false;
    }
    else {
      this._time++;
    }
  },
  
  
  /** 
   * Runs the stage's events for the current time value. Override this!
   * @abstract
   * @param {Toupony.MainLevel} mainLevel
   * @param {uint} time
   */
  runCurrentFrame: function(mainLevel) {},
  
  
  /** 
   * Setter/getter for the stage's current time.
   * @param {uint} time   Optional.
   * @return {uint}
   */
  time: function(time) {
    if(time !== undefined) {
      this._time = time;
      this._timeWasSet = true;
    }
    return this._time;
  }
};

