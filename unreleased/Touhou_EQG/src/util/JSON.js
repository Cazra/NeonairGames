/**
 * Some JSON utilities.
 */
Util.JSON = {
  
  /** 
   * Produces a deep copy of a JSON object.
   * @param {object} jObj
   * @return {object}
   */
  clone: function(jObj) {
    var jStr = JSON.stringify(jObj);
    return JSON.parse(jStr);
  }
}


