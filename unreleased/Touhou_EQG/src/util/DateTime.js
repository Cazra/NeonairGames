/** 
 * An API for formatting the date and time.
 */
Util.DateTime = {
  
  MILLIS_PER_MINUTE: 60000,
  
  _monthStrsShort: [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
  ],
  
  /** 
   * Returns a formatted date-time string for a Date object. 
   * @return {string}
   */
  getFormattedDateTime: function(date) {
    var month = date.getMonth();
    var date = date.getDate();
    var year = date.getFullYear();    
    
    var hours = date.getHours();
    var minutes = date.getMinutes();
    
    var dateStr = this._formatDateString(month, date, year);
    var timeStr = this._formatTimeString(hours, minutes);
    return dateStr + " - " + timeStr;
  },
  
  
  _formatDateString: function(month, date, year) {
    var monthStr = this.monthToShortString(month);
    return monthStr + " " + date + ", " + year;
  },
  
  
  _formatTimeString: function(hours, minutes) {
    var ampm = "AM";
    if(hours > 12) {
      hours -= 12;
      ampm = "PM";
    }
    return hours + ":" + minutes + " " + ampm;
  },
  
  
  /** 
   * Converts a month numerical value to a 3-letter string for the month. 
   * E.g. 1 -> "Jan", 2 -> "Feb", etc.
   * @param {int} monthNum
   */
  monthToShortString: function(monthNum) {
    if(monthNum >= 1 && monthNum <= 12) {
      return this._monthStrsShort[monthNum];
    }
    else {
      throw new Error("Invalid month value: " + monthNum);
    }
  }
  
};