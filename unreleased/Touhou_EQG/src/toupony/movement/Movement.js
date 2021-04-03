/**
 * Base class for 2D sprite movement patterns. For these movements, we assume
 * a traditional 2D graphics system where the positive Y axis points down.
 * @constructor
 */
Toupony.Movement = function() {};

Toupony.Movement.prototype = {
  
  constructor: Toupony.Movement,
  
  isaMovement: true,
  
  
  
  
  /** 
   * Moves a sprite with this movement.
   * @abstract
   * @param {TentaGL.Sprite} sprite   The sprite being moved.
   * @param {object} context          Some object providing context to the 
   *                                  sprite's environment. E.G. The Level 
   *                                  containing it.
   * @return {vec2} The velocity for the frame of movement.
   */
  move: function(sprite, context) {}
};
