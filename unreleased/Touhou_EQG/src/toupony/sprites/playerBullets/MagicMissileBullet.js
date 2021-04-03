/**
 * The bullet for Twilight's Magic Missile weapon. Magical stars home in on nearby enemies.
 * @constructor
 * @param {vec2} xy
 * @param {boolean} isLarge
 * @param {number} damage
 */
Toupony.MagicMissileBullet = function(xy, radius, damage) {
  var homingMovement = new Toupony.MagicMissileMovement();
  
  Toupony.PlayerBullet.call(this, xy, radius, damage);
  this.movement(homingMovement);
};


/** 
 * Creates a small bullet.
 * @param {vec2} xy
 * @return {Toupony.MagicMissileBullet}
 */
Toupony.MagicMissileBullet.createSmall = function(xy) {
  var radius = 16;
  var damage = 1;
  return new Toupony.MagicMissileBullet(xy, radius, damage);
};


/** 
 * Creates a large bullet.
 * @param {vec2} xy
 * @return {Toupony.MagicMissileBullet}
 */
Toupony.MagicMissileBullet.createLarge = function(xy) {
  var radius = 24;
  var damage = 2;
  return new Toupony.MagicMissileBullet(xy, radius, damage);
};




/** 
 * Loads the sprite's WebGL resources. 
 * @param {WebGLRenderingContext} gl
 */
Toupony.MagicMissileBullet.load = function(gl) {
  TentaGL.MaterialLib.add(gl, "TwilightBullet", TentaGL.Texture.fromURL(gl, "img/bullet/TwilightBullet.png"));
  TentaGL.MaterialLib.add(gl, "TwilightBulletTrail", TentaGL.Texture.fromURL(gl, "img/bullet/TwilightBulletTrail.png"));
};


/** 
 * Unloads the sprite's WebGL resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.MagicMissileBullet.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "TwilightBullet");
  TentaGL.MaterialLib.remove(gl, "TwilightBulletTrail");
};





Toupony.MagicMissileBullet.prototype = {
  constructor: Toupony.MagicMissileBullet,
  
  isaMagicMissileBullet: true,
  
  
  /** 
   * @inheritDoc Toupony.Bullet
   */
  getMaterialName: function() {
    return "TwilightBullet";
  },
  
  /** 
   * @inheritDoc Toupony.PlayerBullet
   */
  getTrailMaterialName: function() {
    return "TwilightBulletTrail";
  },
  
  /** 
   * @inheritDoc Toupony.PlayerBullet
   */
  getTrailLifeSpan: function() {
    return 20;
  },
  
  /** 
   * @inheritDoc Toupony.PlayerBullet
   */
  getTrailFadeFunction: function() {
    return function(alpha) {
      var opacity = TentaGL.Math.mix(0.0, 0.7, alpha);
      this.opacity(opacity);
      
      var scale = TentaGL.Math.mix(1.0, 0.2, alpha);
      this.scale([scale, scale]);
    };
  }
};

Util.Inheritance.inherit(Toupony.MagicMissileBullet, Toupony.PlayerBullet);
