/** 
 * An interface for sprites that are animated.
 */
Toupony.Animatable = function() {};

Toupony.Animatable.prototype = {
  constructor: Toupony.Animatable,
  
  isaAnimatable: true,
  
  /** 
   * Runs one iteration of the sprite's animation.
   */
  animate: function() {}
};
