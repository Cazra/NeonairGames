/** 
 * The movement pattern for Rarity's bullets.
 */
Toupony.CycloneDiamondMovement = function() {
  var angleDecel = this.ANGLE_SPEED/2/60;
  Toupony.CurvingMovement.call( this, Math.random()*TentaGL.TAU, 
                                this.SPEED, this.ANGLE_SPEED, angleDecel);
  this._age = 0;
};


Toupony.CycloneDiamondMovement.prototype = {
  constructor: Toupony.CycloneDiamondMovement,
  
  isaCycloneDiamondMovement: true, 
  
  SPEED: 5,
  
  ANGLE_SPEED: TentaGL.TAU*2/60,
  
  
  move: function(sprite, context) {
    this._age++;
    
    if(this._age >= 60) {
      this.angleSpeed(0);
      this.angleDeceleration(0);
      
      var angleDir = 0-TentaGL.Math.vectorAngle2D(this.vector(), [0, -1]);
      
      if(angleDir < 0) {
        this.angle(this.angle() - this.ANGLE_SPEED);
      }
      else {
        this.angle(this.angle() + this.ANGLE_SPEED);
      }
      
      this.speed(this.speed() + 5/60);
    }
    
    return Toupony.CurvingMovement.prototype.move.call(this, sprite, context);
  }
};

Util.Inheritance.inherit(Toupony.CycloneDiamondMovement, Toupony.CurvingMovement);

