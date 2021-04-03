/** 
 * A movement which moves the sprite at a constant speed, but with changing (but
 * decelerating) direction.
 * @param {number} angle              The starting angle, in radians.
 * @param {number} speed              The constant speed, in pixels per frame.
 * @param {number} angleSpeed         The rate at which the angle changes, in radians per frame.
 * @param {number} angleDeceleration  The rate of the angle's deceleration, in radians per square frame.
 */
Toupony.CurvingMovement = function(angle, speed, angleSpeed, angleDeceleration) {
  Toupony.LinearMovement.call(this, angle, speed);
  this._angleSpeed = angleSpeed;
  this._angleDecel = angleDeceleration;
};

Toupony.CurvingMovement.prototype = {
  constructor: Toupony.CurvingMovement,
  
  isaCurvingMovement: true, 
  
  /** 
   * Setter/getter for the angular velocity.
   * Setting this to a negative value will make it rotate clockwise.
   * Setting this to a positive value will make it rotate counter-clockwise.
   */
  angleSpeed: function(speed) {
    if(speed !== undefined) {
      this._angleSpeed = speed;
    }
    return this._angleSpeed;
  },
  
  
  angleDeceleration: function(decel) {
    if(decel !== undefined) {
      this._angleDecel = decel;
    }
    return this._angleSpeed;
  },
  
  /** 
   * @inheritDoc Toupony.Movement
   */
  move: function(sprite, context) {
    var vector = this.vector();
    sprite.xy(vec2.add([], sprite.xy(), vector));
    sprite.setAxisRotation([0,0,-1], this.angle());
    
    var maxDist = 300;
    if( sprite.x() < -maxDist || sprite.x() > Toupony.STAGE_WIDTH + maxDist || 
        sprite.y() < -maxDist || sprite.y() > Toupony.STAGE_HEIGHT + maxDist) {
      sprite.destroy();
    }
    
    // Update the angular velocity.
    this.angle(this.angle() + this._angleSpeed);
    if(this._angleDecel >= Math.abs(this._angleSpeed)) {
      this._angleDecel = 0;
      this._angleSpeed = 0;
    }
    else if(this._angleSpeed > 0) {
      this._angleSpeed -= this._angleDecel;
    }
    else {
      this._angleSpeed += this._angleDecel;
    }
    
    return vector;
  }
  
};

Util.Inheritance.inherit(Toupony.CurvingMovement, Toupony.LinearMovement);

