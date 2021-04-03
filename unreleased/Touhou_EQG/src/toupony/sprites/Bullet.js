/**
 * Base class for player and enemy bullet sprites.
 * @abstract
 * @constructor
 * @param {vec2} xy
 * @param {number} radius
 */
Toupony.Bullet = function(xy, radius) {
  TentaGL.Sprite.call(this, xy);
  this._radius = radius;
  
  this._movement = new Toupony.LinearMovement(0,0);
  this._velocity = [0,0];
  this._angle = 0;
};

Toupony.Bullet.prototype = {
  
  constructor: Toupony.Bullet,
  
  isaBullet: true,
  
  
  /** 
   * Setter/getter for the bullet's movement physics.
   * @param {Toupony.Movement} newMovement    optional. If given, this becomes the bullet's new movement.
   * @return {Toupony.Movement}
   */
  movement: function(newMovement) {
    if(newMovement) {
      this._movement = newMovement;
    }
    return this._movement;
  },
  
  
  /** 
   * Runs the movement physics for this bullet.
   * @param {Toupony.MainLevel} context
   */
  move: function(context) {
    this._velocity = this._movement.move(this, context);
  },
  
  
  /** 
   * Returns the current velocity vector of the bullet.
   * @return {vec2}
   */
  getVelocity: function() {
    return this._velocity;
  },
  
  
  /** 
   * Returns the angle of the direction the bullet is currently moving in,
   * in radians in the range [-PI, PI].
   * @return {number} 
   */
  getAngle: function() {
    var dx = this._velocity[0];
    var dy = this._velocity[1];
    return 0-Math.atan(dy, dx);
  },
  
  
  /** 
   * Setter/getter for the radius of the bullet.
   * @param {number} radius
   * @return {number}
   */
  radius: function(radius) {
    if(radius !== undefined) {
      this._radius = radius;
    }
    return this._radius;
  },
  
  
  /** 
   * Returns the name of the material (most likely a Texture) used to render 
   * this bullet.
   * @abstract
   * @return {string}
   */
  getMaterialName: function() {}
};


Util.Inheritance.inherit(Toupony.Bullet, TentaGL.Sprite);
Util.Inheritance.inherit(Toupony.Bullet, Toupony.Animatable);
Util.Inheritance.inherit(Toupony.Bullet, Toupony.Destroyable);

