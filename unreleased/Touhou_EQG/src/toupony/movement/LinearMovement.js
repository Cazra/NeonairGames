/**
 * Moves a sprite at a constant velocity. 
 * The sprite is destroyed if it goes too far offscreen.
 * @constructor
 * @param {float} angle   The angle indicating the direction of the 
 *                            movement, in radians. 
 * @param {float} speed   The magnitude of the movement.
 */
Toupony.LinearMovement = function(angle, speed) {
  this._angle = angle;
  this._speed = speed;
  this._calcVector();
};


Toupony.LinearMovement.fromVector = function(v) {
  var movement = new Toupony.LinearMovement(0, 0);
  movement.vector(v);
  return movement;
};


Toupony.LinearMovement.prototype = {
  
  constructor: Toupony.LinearMovement,
  
  isaLinearMovement: true,
  
  /** 
   * Setter/getter for the angle of the movement, in radians.
   * @param {number} angle  (optional)
   * @return {number}
   */
  angle: function(angle) {
    if(angle !== undefined) {
      this._angle = angle;
      this._calcVector();
    }
    return this._angle;
  },
  
  /** 
   * Recalculates the 2D velocity for this movement from its angle and speed.
   */
  _calcVector: function() {
    var dx = this._speed * Math.cos(this._angle);
    var dy = -this._speed * Math.sin(this._angle);
    this._vector = [dx, dy];
  },
  
  
  /** 
   * Setter/getter for the speed of this movement. 
   * @param {number} speed    (optional)
   * @return {number}
   */
  speed: function(speed) {
    if(speed !== undefined) {
      this._speed = speed;
      this._calcVector();
    }
    return this._speed;
  },
  
  
  /** 
   * Setter/getter for the movement's velocity as a vector. 
   * @param {vec2} velocity   (optional)
   * @return {vec2}
   */
  vector: function(v) {
    if(v !== undefined) {
      this._speed = vec2.length(v);
      this._angle = 0-TentaGL.Math.vectorAngle2D([1,0], v);
      this._calcVector();
    }
    return this._vector.slice(0);
  },
  
  
  /** 
   * @inheritDoc {Toupony.Movement}
   */
  move: function(sprite, context) {
    var vector = this.vector();
    sprite.xy(vec2.add([], sprite.xy(), vector));
    
    sprite.setAxisRotation([0,0,-1], this.angle());
    
    var maxDist = 100;
    if( sprite.x() < -maxDist || sprite.x() > Toupony.STAGE_WIDTH + maxDist || 
        sprite.y() < -maxDist || sprite.y() > Toupony.STAGE_HEIGHT + maxDist) {
      sprite.destroy();
    }
    
    return vector;
  }
};

Util.Inheritance.inherit(Toupony.LinearMovement, Toupony.Movement);

