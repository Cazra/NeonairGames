/** 
 * A mix-in for destroyable sprites.
 */
Toupony.Destroyable = function() {};


Toupony.Destroyable.prototype = {
  
  constructor: Toupony.Destroyable,
  
  isaDestroyable: true,
  
  _isDestroyed: false,
  
  
  /** 
   * Returns whether this sprite has been destroyed.
   * @return {boolean}
   */
  isDestroyed: function() {
    return this._isDestroyed;
  },
  
  
  /** 
   * Marks this sprite as destroyed.
   */
  destroy: function() {
    this._isDestroyed = true;
    this._notifyDestroyHandlers();
    this._destroyHandlers = [];
  },
  
  
  /** 
   * Runs all the destroy handlers for this.
   */
  _notifyDestroyHandlers: function() {
    if(this._destroyHandlers !== undefined) {
      for(var i=0; i < this._destroyHandlers.length; i++) {
        var handler = this._destroyHandlers[i];
        handler(this);
      }
    }
  },
  
  
  /** 
   * Subscribes a destroy handler function which will be called for this when 
   * this is destroyed.
   * @param {function(this: Destroyable)} handler    
   */
  addDestroyHandler: function(handler) {
    if(this._destroyHandlers === undefined) {
      this._destroyHandlers = [];
    }
    
    this._destroyHandlers.push(handler);
  },
  
  /** 
   * Unsubscribes a destroy handler function from this. 
   * @param {function(this: Destroyable)} handler  
   */
  removeDestroyHandler: function(handler) {
    if(this._destroyHandlers !== undefined) {
      var index = this._destroyHandlers.indexOf(handler);
      if(index >= 0) {
        this._destroyHandlers.splice(index, 1);
      }
    }
  },
  
  
  /** 
   * Unsubscribes all destroy handler functions from this. 
   */
  removeAllDestroyHandlers: function() {
    this._destroyHandlers = [];
  }
};
