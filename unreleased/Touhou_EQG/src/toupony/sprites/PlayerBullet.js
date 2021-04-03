/**
 * Base class for player bullet sprites.
 * @abstract
 * @constructor
 */
Toupony.PlayerBullet = function(xy, radius, damage) {
  Toupony.Bullet.call(this, xy, radius);
  this._damage = damage;
  this.opacity(0.7);
};


Toupony.PlayerBullet.prototype = {
  constructor: Toupony.PlayerBullet,
  
  isaPlayerBullet: true,
  
  /** 
   * Returns the amount of damage this bullet does to enemies and obstacles.
   * @return {number}
   */
  getDamage: function() {
    return this._damage;
  },
  
  
  /** 
   * @inheritDoc Toupony.Bullet
   */
  move: function(context) {
    var trailEffect = this.createTrailEffect(); //Toupony.MagicMissileBullet.createTrail(this.xy(), this.radius(), angle);
    context.getSpecialFX().add(trailEffect);
    
    // Move the bullet.
    Toupony.Bullet.prototype.move.call(this, context);
    trailEffect.setAxisRotation([0,0,-1], this.getAngle());
  },
  
  
  /** 
   * Produces a trail special effect for the bullet. 
   * @return {Toupony.SpecialFX} 
   */
  createTrailEffect: function() {
    var dia = this.radius()*2;
    var lifeSpan = this.getTrailLifeSpan();
    var material = this.getTrailMaterialName();
    
    var effect = new Toupony.SpecialFX(this.xy(), lifeSpan);
    effect.fadeEffect = this.getTrailFadeFunction();    
    effect.draw = function(gl) {
      TentaGL.ShaderLib.use(gl, "simple");
      var shader = TentaGL.ShaderLib.current(gl);
      shader.setOpacity(gl, this.opacity());
      
      TentaGL.ViewTrans.scale(gl, [dia, dia]);
      TentaGL.ViewTrans.translate(gl, [-0.5, -0.5]);
      
      TentaGL.MaterialLib.use(gl, material);
      TentaGL.ModelLib.render(gl, "unitSprite");
    };
    
    return effect;
  },
  
  
  /** 
   * @inheritDoc TentaGL.Sprite
   */
  draw: function(gl) {
    var diameter = this.radius()*2;
    
    TentaGL.ViewTrans.scale(gl, [diameter, diameter]);
    TentaGL.ViewTrans.translate(gl, [-0.5, -0.5]);
    
    TentaGL.ShaderLib.use(gl, "simple");
    TentaGL.MaterialLib.use(gl, this.getMaterialName());
    TentaGL.ModelLib.render(gl, "unitSprite");
  },
  
  
  //////// Abstract methods
  
  /** 
   * Returns the life span of the bullet's trail effect particles, in frames.
   * @abstract
   * @return {uint}
   */
  getTrailLifeSpan: function() {},
  
  /** 
   * Returns the name of the material used for the bullet's 
   * trail effect particles.
   * @abstract
   * @return {string}
   */
  getTrailMaterialName: function() {},
  
  /** 
   * Returns the fade function for the bullet's trail effect.
   * @abstract
   * @return {function(alpha: float)}
   */
  getTrailFadeFunction: function() {}
};

Util.Inheritance.inherit(Toupony.PlayerBullet, Toupony.Bullet);