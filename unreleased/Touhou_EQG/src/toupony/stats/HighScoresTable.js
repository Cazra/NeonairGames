/** 
 * A table of high scores.
 * @constructor
 * @param {uint} maxSize    Optional. Set a maximum number of high scores for the table. 
 */
Toupony.HighScoresTable = function(maxSize) {
  if(maxSize === undefined) {
    maxSize = 10;
  }
  
  this._highScores = [];
  this._maxSize = maxSize;
};

/** 
 * Constructs a table of high scores from its JSON representation. 
 * @param {object} jObj
 * @return {Toupony.HighScoresTable}
 */
Toupony.HighScoresTable.fromJSON = function(jObj) {
  var maxSize = jObj.maxSize;
  
  var scores = [];
  for(var i=0; i< jObj.scores.length; i++) {
    var highScore = Toupony.HighScore.fromJSON(jObj.scores[i]);
    scores.push(highScore);
  }
  
  var result = new Toupony.HighScoresTable(maxSize);
  result._highScores = scores;
  return result;
};


Toupony.HighScoresTable.prototype = {
  
  constructor: Toupony.HighScoresTable,
  
  isaHighScoresTable : true,
  
  /** 
   * Returns a list of the high scores. 
   * @return {array: Toupony.HighScore}
   */
  getHighScores: function() {
    return this._highScores.slice(0);
  },
  
  /** 
   * Returns the nth high score in the table.
   * @param {int} n
   * @return {Toupony.HighScore}
   */
  getHighScore: function(n) {
    if(n >= 0 && n < this._highScores.length) {
      return this._highScores[n];
    }
    else {
      throw new Error("High score index out of bounds: " + n);
    }
  },
  
  
  /** 
   * Returns the current count of high scores in the table. 
   * @return {uint}
   */
  getCount: function() {
    return this._highScores.length;
  },
  
  
  /** 
   * Attempts to insert a new high score into the table. 
   * @param {Toupony.HighScore}
   */
  addHighScore: function(highScore) {
    var insertIndex = 0;
    for(var i=0; i < this._highScores.length; i++) {
      var current = this._highScores[i];
      
      if(highScore.getScore() > current.getScore()) {
        break;
      }
      else {
        insertIndex++;
      }
    }
    
    this._highScores.splice(insertIndex, 0, highScore);
    this._highScores = this._highScores.slice(0, this._maxSize);
  },
  
  
  /** 
   * Creates a JSON representation of the highscores table.
   * @return {object}
   */
  toJSON: function() {
    var maxSize = this._maxSize;
    var scores = [];
    
    for(var i=0; i< this._highScores.length; i++) {
      var highScore = this._highScores[i];
      scores.push(highScore.toJSON());
    }
    
    return {
      maxSize: maxSize,
      scores: scores
    };
  }
};

