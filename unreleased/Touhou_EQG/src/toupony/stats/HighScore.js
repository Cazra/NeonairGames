/** 
 * Data about a high score.
 * @param {uint} score    The high score.
 * @param {string} name   The name entered by the player who achieved the high score.
 * @param {enum: Toupony.StageProgress} progress    The ending stage for the high score.
 */
Toupony.HighScore = function(score, name, progress) {
  this._score = score;
  this._name = name;
  this._progress = progress;
  this._dateTime = Util.DateTime.getFormattedDateTime(new Date());
};


/** 
 * Creates a HighScore from its JSON representation. 
 * @param {object}
 * @return {Toupony.HighScore}
 */
Toupony.HighScore.fromJSON = function(jObj) {
  var score = jObj.score;
  var name = jObj.name;
  var dateTime = jObj.dateTime;
  var progress = jObj.progress;
  
  var result = new Toupony.HighScore(score, name, progress);
  result._dateTime = dateTime;
};



Toupony.HighScore.prototype = {
  
  constructor: Toupony.HighScore,
  
  
  /** 
   * Returns the value of the high score.
   * @return {uint}
   */
  getScore: function() {
    return this._score;
  },
  
  /** 
   * Returns the high score's player name.
   * @return {string}
   */
  getName: function() {
    return this._name;
  },
  
  /** 
   * Returns the high score's progress.
   * @return {enum: Toupony.StageProgress}
   */
  getProgress: function() {
    return this._progress;
  },
  
  /** 
   * Returns the date and time string for the high score. 
   * @return {string}
   */
  getDateTime: function() {
    return this._dateTime;
  },
  
  
  /** 
   * Creates a JSON-ified version of the high score.
   * @return {object}
   */
  toJSON: function() {
    var score = this._score;
    var name = this._name;
    var progress = this._progress;
    var dateTime = this._dateTime;
    
    return {
      score: score,
      name: name,
      progress: progress,
      dateTime: dateTime
    };
  }
  
};

