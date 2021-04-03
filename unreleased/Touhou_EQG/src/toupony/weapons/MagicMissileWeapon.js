/** 
 * Twilight Sparkle's Magic Missile weapon fires weak homing shots.
 */
Toupony.MagicMissileWeapon = function() {
  this._timer = 0;
  this._satAngle = 0;
};


Toupony.MagicMissileWeapon.load = function(gl) {
  Toupony.MagicMissileBullet.load(gl);
  // TODO: Load bullet trail graphics.
  // TODO: Load satellite graphics.
};

Toupony.MagicMissileWeapon.clean = function(gl) {
  Toupony.MagicMissileBullet.clean(gl);
  
  // TODO: Unload bullet trail graphics.
  // TODO: Unload satellite graphics.
};


Toupony.MagicMissileWeapon.prototype = {
  constructor: Toupony.MagicMissileWeapon,
  
  isaMagicMissileWeapon: true,
  
  /** 
   * The amount of time needed for the primary weapon to recharge.
   */
  COOL_DOWN_TIME: 30,
  
  /** 
   * The distance of the satellites from the player.
   */
  SAT_DISTANCE: 48,
  
  
  /** 
   * @inheritDoc Toupony.Weapon
   */
  update: function(player, context) {
    this._coolDown();
    this._moveSatellites(player);
  },
  
  
  /** 
   * Advances the cool-down timer on the weapon.
   */
  _coolDown: function() {
    if(this._timer > 0) {
      this._timer++;
    }
    if(this._timer == this.COOL_DOWN_TIME) {
      this._timer = 0;
    }
  },
  
  /** 
   * Moves the satellites relative to the player.
   * @param {Toupony.Player} player
   */
  _moveSatellites: function(player) {
    this._satAngle += TentaGL.TAU/60;
    
    var satAngle1 = this._satAngle
    var satAngle2 = this._satAngle + TentaGL.TAU/2;
    
    var xy = player.xy();
    // this._satellite1.xy([xy[0] + satDistance*Math.cos(satAngle1), xy[1] - satDistance*Math.sin(satAngle1)]);
    // this._satellite2.xy([xy[0] + satDistance*Math.cos(satAngle2), xy[1] - satDistance*Math.sin(satAngle2)]);
  },
  
  
  /** 
   * @inheritDoc Toupony.Weapon
   */
  fire: function(player, context) {
    var power = player.getStats().power();
    
    if(this._timer == 0) {
      this._timer = 1;
      
      this._firePlayer(player, power, context);
    }
    if(this._timer == Math.floor(this.COOL_DOWN_TIME * 1/3) && power >= 1.0) {
      // TODO: this._fireSatellite(this._satellite1, power, context);
    }
    if(this._timer == Math.floor(this.COOL_DOWN_TIME * 2/3) && power >= 3.0) {
      // TODO: this._fireSatellite(this._satellite2, power, context);
    }
  },
  
  /** Fires a bullet from the player. */
  _firePlayer: function(player, power, context) {
    var xy = player.xy();
    
    var bullet;
    if(power >= 2.0) {
      bullet = Toupony.MagicMissileBullet.createLarge(xy);
    }
    else {
      bullet = Toupony.MagicMissileBullet.createSmall(xy);
    }
    context.getPlayerBullets().add(bullet);
  },
  
  /** Fires a bullet from a satellite. */
  _fireSatellite: function(satellite, power, context) {
    var xy = satellite.xy();
    
    var bullet;
    if(power >= 4.0) {
      bullet = Toupony.MagicMissileBullet.createLarge(xy);
    }
    else {
      bullet = Toupony.MagicMissileBullet.createSmall(xy);
    }
    context.getPlayerBullets().add(bullet);
  },
  
  
  /** 
   * @inheritDoc Toupony.Weapon
   */
  render: function(gl) {
    // TODO: this._satellite1.render(gl);
    // TODO: this._satellite2.render(gl);
  }
  
};

Util.Inheritance.inherit(Toupony.MagicMissileWeapon, Toupony.Weapon);