
/** 
 * Twilight/Rarity team.
 * @constructor
 * @param {Toupony.PlayerStats} stats
 */
Toupony.TwiPlayer = function(stats) {
  Toupony.Player.call(this, stats);
  
  this.anchor([18, 22, 0]);
  this._twilightWeapon = new Toupony.MagicMissileWeapon();
  this._rarityWeapon = new Toupony.CycloneDiamondWeapon();
};


Toupony.TwiPlayer.load = function(gl) {
  TentaGL.PixelData.fromURL("img/char/Twilight.png", function(pixels) {
    console.log("Loading Twilight frames.");
    
    var transColor = TentaGL.Color.Hex(0xFFFF00FF);
    pixels = pixels.filter(new TentaGL.RGBAFilter.TransparentColor(transColor));
    
    var frames = pixels.toSpriteSheet(38, 48, 1);
    TentaGL.MaterialLib.add(gl, "TwiUp1", TentaGL.Texture.fromPixelData(gl, frames[0]));
    TentaGL.MaterialLib.add(gl, "TwiUp2", TentaGL.Texture.fromPixelData(gl, frames[1]));
    TentaGL.MaterialLib.add(gl, "TwiUp3", TentaGL.Texture.fromPixelData(gl, frames[2]));
    TentaGL.MaterialLib.add(gl, "TwiUp4", TentaGL.Texture.fromPixelData(gl, frames[3]));
    
    TentaGL.MaterialLib.add(gl, "TwiLeft1", TentaGL.Texture.fromPixelData(gl, frames[4]));
    TentaGL.MaterialLib.add(gl, "TwiLeft2", TentaGL.Texture.fromPixelData(gl, frames[5]));
    TentaGL.MaterialLib.add(gl, "TwiLeft3", TentaGL.Texture.fromPixelData(gl, frames[6]));
    TentaGL.MaterialLib.add(gl, "TwiLeft4", TentaGL.Texture.fromPixelData(gl, frames[7]));
    
    TentaGL.MaterialLib.add(gl, "TwiRight1", TentaGL.Texture.fromPixelData(gl, frames[8]));
    TentaGL.MaterialLib.add(gl, "TwiRight2", TentaGL.Texture.fromPixelData(gl, frames[9]));
    TentaGL.MaterialLib.add(gl, "TwiRight3", TentaGL.Texture.fromPixelData(gl, frames[10]));
    TentaGL.MaterialLib.add(gl, "TwiRight4", TentaGL.Texture.fromPixelData(gl, frames[11]));
    
    Toupony.TwiPlayer._texLoaded = true;
  });
  
  Toupony.MagicMissileWeapon.load(gl);
  Toupony.CycloneDiamondWeapon.load(gl);
};


Toupony.TwiPlayer.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "TwiUp1");
  TentaGL.MaterialLib.remove(gl, "TwiUp2");
  TentaGL.MaterialLib.remove(gl, "TwiUp3");
  TentaGL.MaterialLib.remove(gl, "TwiUp4");
  
  TentaGL.MaterialLib.remove(gl, "TwiLeft1");
  TentaGL.MaterialLib.remove(gl, "TwiLeft2");
  TentaGL.MaterialLib.remove(gl, "TwiLeft3");
  TentaGL.MaterialLib.remove(gl, "TwiLeft4");
  
  TentaGL.MaterialLib.remove(gl, "TwiRight1");
  TentaGL.MaterialLib.remove(gl, "TwiRight2");
  TentaGL.MaterialLib.remove(gl, "TwiRight3");
  TentaGL.MaterialLib.remove(gl, "TwiRight4");
  
  Toupony.TwiPlayer._texLoaded = false;
  
  Toupony.MagicMissileWeapon.clean(gl);
  Toupony.CycloneDiamondWeapon.clean(gl);
};



Toupony.TwiPlayer.prototype = {
  
  constructor: Toupony.TwiRarePlayer,
  
  isaTwiRarePlayer: true,
  
  
  
  render: function(gl) {
    if(!Toupony.TwiPlayer._texLoaded) {
      return;
    }
    else {
      Toupony.Player.prototype.render.call(this, gl);
    }
  },
  
  
  /**
   * @inheritDoc Toupony.Player
   */
  getTeam: function() {
    return Toupony.PlayerTeams.TWI_RARE;
  },
  
  /**
   * @inheritDoc Toupony.Player
   */
  getTexBaseName: function() {
    if(this._focused) {
      return "Twi";
    }
    else {
      return "Twi";
    }
  },
  
  /**
   * @inheritDoc Toupony.Player
   */
  getSpeed: function() {
    return 4;
  },
  
  /**
   * @inheritDoc Toupony.Player
   */
  getFocusedSpeed: function() {
    return 1.5;
  },
  
  /**
   * @inheritDoc Toupony.Player
   */
  getHitboxRadius: function() {
    return 4;
  },
  
  /** 
   * @inheritDoc Toupony.Player
   */
  getWeapon: function() {
    if(this.isFocused()) {
      return this._rarityWeapon;
    }
    else {
      return this._twilightWeapon;
    }
  },
  
};

Util.Inheritance.inherit(Toupony.TwiPlayer, Toupony.Player);

