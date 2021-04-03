/** 
 * The homing movement for Twilight's magic missile bullets.
 */
Toupony.MagicMissileMovement = function() {
  Toupony.LinearMovement.call(this, TentaGL.TAU/4, this.SPEED);
};


Toupony.MagicMissileMovement.prototype = {
  constructor: Toupony.MagicMissileMovement,
  
  isaMagicMissileMovement: true,
  
  SPEED: 6,
  
  ANGLE_SPEED: TentaGL.TAU/2/60,
  
  
  /** 
   * @inheritDoc Toupony.LinearMovement
   */
  move: function(bullet, context) {
    // Figure out which enemy is closest.
    var enemies = context.getEnemies();
    var bestDistSq = 200*200;
    var bestEnemy;
    enemies.forEach(function(enemy) {
      var distSq = vec2.sqrDist(bullet.xy(), enemy.xy());
      
      if(distSq < bestDistSq) {
        bestEnemy = enemy;
        bestDistSq = distSq;
      }
    });
    
    // If the bullet is nearby an enemy, change its direction toward that enemy.
    if(bestEnemy !== undefined) {
      var vectorToEnemy = vec2.sub([], bestEnemy.xy(), bullet.xy());
      var angleDir = 0-TentaGL.Math.vectorAngle2D(this.vector(), vectorToEnemy);
      
      if(angleDir < 0) {
        this.angle(this.angle() - this.ANGLE_SPEED);
      }
      else {
        this.angle(this.angle() + this.ANGLE_SPEED);
      }
    }
    
    return Toupony.LinearMovement.prototype.move.call(this, bullet, context);
  }
  
};

Util.Inheritance.inherit(Toupony.MagicMissileMovement, Toupony.LinearMovement);
