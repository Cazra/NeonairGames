/** 
 * The bullet for Rarity's Cyclone Diamond weapon. It creates a whirlwind of 
 * diamonds which then curve toward the top of the screen.
 * @constructor
 * @param {vec2} xy
 * @param {boolean} isLarge
 * @param {number} damage
 */
Toupony.CycloneDiamondBullet = function(xy, radius, damage) {
  Toupony.PlayerBullet.call(this, xy, radius, damage);
  
  var cycloneMovement = new Toupony.CycloneDiamondMovement();
  this.movement(cycloneMovement);
};



/** 
 * Creates a small bullet.
 * @param {vec2} xy
 * @return {Toupony.CycloneDiamondBullet}
 */
Toupony.CycloneDiamondBullet.createSmall = function(xy) {
  var radius = 16;
  var damage = 2;
  return new Toupony.CycloneDiamondBullet(xy, radius, damage);
};


/** 
 * Creates a large bullet.
 * @param {vec2} xy
 * @return {Toupony.CycloneDiamondBullet}
 */
Toupony.CycloneDiamondBullet.createLarge = function(xy) {
  var radius = 24;
  var damage = 3;
  return new Toupony.CycloneDiamondBullet(xy, radius, damage);
};



/** 
 * Loads the sprite's WebGL resources. 
 * @param {WebGLRenderingContext} gl
 */
Toupony.CycloneDiamondBullet.load = function(gl) {
  TentaGL.MaterialLib.add(gl, "RarityBullet", TentaGL.Texture.fromURL(gl, "img/bullet/RarityBullet.png"));
  TentaGL.MaterialLib.add(gl, "RarityBulletTrail", TentaGL.Texture.fromURL(gl, "img/bullet/RarityBulletTrail.png"));
};

/** 
 * Unloads the sprite's WebGL resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.CycloneDiamondBullet.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "RarityBullet");
  TentaGL.MaterialLib.remove(gl, "RarityBulletTrail");
};


Toupony.CycloneDiamondBullet.prototype = {
  constructor: Toupony.CycloneDiamondBullet,
  
  isaCycloneDiamondBullet: true,
  
  /** 
   * @inheritDoc Toupony.Bullet
   */
  getMaterialName: function() {
    return "RarityBullet";
  },
  
  /** 
   * @inheritDoc Toupony.PlayerBullet
   */
  getTrailMaterialName: function() {
    return "RarityBulletTrail";
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

Util.Inheritance.inherit(Toupony.CycloneDiamondBullet, Toupony.PlayerBullet);
