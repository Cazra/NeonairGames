/** 
 * Rarity's Cyclone Diamond weapon fires a whirlwind of diamonds forming a 
 * defensive barrier, which gradually steer towards the top of the screen.
 */
Toupony.CycloneDiamondWeapon = function() {
  this._timer = 0;
  this._satAngle = 0;
};


Toupony.CycloneDiamondWeapon.load = function(gl) {
  Toupony.CycloneDiamondBullet.load(gl);
};

Toupony.CycloneDiamondWeapon.clean = function(gl) {
  Toupony.CycloneDiamondBullet.clean(gl);
};


Toupony.CycloneDiamondWeapon.prototype = {
  constructor: Toupony.CycloneDiamondWeapon,
  
  isaCycloneDiamondWeapon: true,
  
  /** 
   * @inheritDoc Toupony.Weapon
   */
  update: function(player, context) {
    var power = player.getStats().power();
    this._coolDown(power);
  },
  
  
  /** 
   * Advances the cool-down timer on the weapon.
   */
  _coolDown: function(power) {
    if(this._timer > 0) {
      this._timer++;
    }
    
    var cooldown;
    if(power >= 3.0) {
      cooldown = 10;
    }
    else if(power >= 1.0) {
      cooldown = 15;
    }
    else {
      cooldown = 20;
    }
    
    
    if(this._timer >= cooldown) {
      this._timer = 0;
    }
  },
  
  
  
  /** 
   * @inheritDoc Toupony.Weapon
   */
  fire: function(player, context) {
    var xy = player.xy();
    var power = player.getStats().power();
    
    if(this._timer == 0) {
      this._timer = 1;
      
      var bullet = Toupony.CycloneDiamondBullet.createSmall(xy);
      context.getPlayerBullets().add(bullet);
    }
  }
  
};

Util.Inheritance.inherit(Toupony.CycloneDiamondWeapon, Toupony.Weapon);