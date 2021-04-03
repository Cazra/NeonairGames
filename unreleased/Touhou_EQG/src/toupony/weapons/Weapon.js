/** 
 * Base class for player weapons.
 * @abstact
 * @constructor
 */
Toupony.Weapon = function() {};

Toupony.Weapon.prototype = {
  
  constructor: Toupony.Weapon,
  
  isaWeapon: true,
  
  
  
  /** 
   * Updates the frame state of the weapon. This might update things such as 
   * cool-down timers or satellite positions.
   * @abstract
   * @param {Toupony.Player} player
   * @param {Toupony.MainLevel} context
   */
  update: function(player, context) {},
  
  
  /** 
   * Fires the weapon. Override this!
   * @abstract
   * @param {Toupony.Player} player
   * @param {Toupony.MainLevel} context
   */
  fire: function(player, context) {},
  
  
  /** 
   * Renders any renderable parts of the weapon, such as its satellites.
   * @abstract
   * @param {WebGLRenderingContext} gl
   */
  render: function(gl) {}
  
};


Util.Inheritance.inherit(Toupony.Weapon, TentaGL.Renderable);

