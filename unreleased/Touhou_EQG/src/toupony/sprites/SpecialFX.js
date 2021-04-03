/**
 * Base class for flashy, temporary sprites used for special particle effects.
 * @abstract
 * @constructor
 * @param {vec2} xy
 * @param {uint} lifeSpan   The number of frames before the effect is destroyed.
 * @param {Toupony.Movement} movement    Optional. Default, stationary movement.
 */
Toupony.SpecialFX = function(xy, lifeSpan, movement) {
  TentaGL.Sprite.call(this, xy);
  this._lifeSpan = lifeSpan;
  this._age = 0;
  
  if(movement === undefined) {
    movement = new Toupony.Movement(); // Doesn't move.
  }
  this._movement = movement;
};

Toupony.SpecialFX.prototype = {
  constructor: Toupony.SpecialFX,
  
  isaSpecialFX: true,
  
  /** 
   * Returns the life span of the effect.
   * @return {uint}
   */
  getLifeSpan: function() {
    return this._lifeSpan;
  },
  
  /** 
   * Returns the current age of the effect.
   * @return {uint}
   */
  getAge: function() {
    return this._age;
  },
  
  /** 
   * @inheritDoc Toupony.Animatable
   */
  animate: function() {
    this._age++;
    if(this._age == this._lifeSpan) {
      this.destroy();
    }
    
    this._movement.move(this);
    
    var alpha = this._age/this._lifeSpan;
    this.fadeEffect(alpha);
  },
  
  
  /** 
   * Sets the properties of the effect based on the a 
   * parametric value of its age.
   * @param {float} alpha   The parameterized value of the effect's age, 
   *                        in the range [0, 1].
   */
  fadeEffect: function(alpha) {}
  
};

Util.Inheritance.inherit(Toupony.SpecialFX, TentaGL.Sprite);
Util.Inheritance.inherit(Toupony.SpecialFX, Toupony.Animatable);
Util.Inheritance.inherit(Toupony.SpecialFX, Toupony.Destroyable);
