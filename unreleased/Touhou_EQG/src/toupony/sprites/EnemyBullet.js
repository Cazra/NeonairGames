/** 
 * Base class for Bullets produced by an enemy.
 * This implementation is good for bullets that are circular-shaped 
 * in nature. Elliptical shapes are also supported through scaling on the 
 * X or Y axis of the sprite.
 * @abstract
 * @constructor
 * @param {vec2} xy
 * @param {Toupony.Movement} movement   Optional. By default a LinearMovement 
 *                                      with vector [0,1] is provided.
 * @param {number} radius               Optional. Default 8.
 * @param {TentaGL.Color} color         Optional. The color for the bullet. 
 *                                      Default black.
 */
Toupony.EnemyBullet = function(xy, radius, color) {
  if(!radius) {
    radius = 8;
  }
  if(color === undefined) {
    color = TentaGL.Color.BLACK;
  }
  
  Toupony.Bullet.call(this, xy, radius);
  this.color(color);
};

/** 
 * Loads the graphics resources for enemy bullets.
 * @param {WebGLRenderingContext} gl
 */
Toupony.EnemyBullet.load = function(gl) {
  TentaGL.MaterialLib.add(gl, "bulletA", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletA.png"));
  TentaGL.MaterialLib.add(gl, "bulletB", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletB.png"));
  TentaGL.MaterialLib.add(gl, "bulletC", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletC.png"));
  TentaGL.MaterialLib.add(gl, "bulletD", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletD.png"));
  TentaGL.MaterialLib.add(gl, "bulletE", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletE.png"));
  TentaGL.MaterialLib.add(gl, "bulletF", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletF.png"));
  TentaGL.MaterialLib.add(gl, "bulletG", new TentaGL.Texture.fromURL(gl, "img/bullet/bulletG.png"));
};


/** 
 * Unloads the graphics resources for enemy bullets.
 * @param {WebGLRenderingContext} gl
 */
Toupony.EnemyBullet.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "bulletA");
  TentaGL.MaterialLib.remove(gl, "bulletB");
  TentaGL.MaterialLib.remove(gl, "bulletC");
  TentaGL.MaterialLib.remove(gl, "bulletD");
  TentaGL.MaterialLib.remove(gl, "bulletE");
  TentaGL.MaterialLib.remove(gl, "bulletF");
  TentaGL.MaterialLib.remove(gl, "bulletG");
};


Toupony.EnemyBullet.prototype = {
  constructor: Toupony.EnemyBullet,
  
  isaEnemyBullet: true,
  
  
  /** 
   * Setter/getter for the bullet's color. 
   * @param {uint32} newColor
   * 
   */
  color: function(newColor) {
    if(newColor) {
      this._color = newColor;
    }
    return this._color;
  },
  
  
  /** 
   * Draws the bullet using the addColor shader. This allows us to make the
   * bullets any color we want using just one black-and-white texture of the 
   * bullet.
   */
  draw: function(gl) {
    var diameter = this._radius*2;
    
    TentaGL.ViewTrans.scale(gl, [diameter, diameter]);
    TentaGL.ViewTrans.translate(gl, [0.5, 0.5]);
    
    TentaGL.ShaderLib.use(gl, "addColor");
    var shader = TentaGL.ShaderLib.current(gl);
    shader.setColor(gl, this._color);
    
    var material = this.getMaterialName();
    TentaGL.MaterialLib.use(gl, material);
    TentaGL.ModelLib.render(gl, "unitSprite");
  }
  
};

Util.Inheritance.inherit(Toupony.EnemyBullet, Toupony.Bullet);
Util.Inheritance.inherit(Toupony.EnemyBullet, Toupony.Enemy);
