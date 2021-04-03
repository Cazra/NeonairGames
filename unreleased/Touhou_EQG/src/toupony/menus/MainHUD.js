/** 
 * The HUD for the MainLevel, displaying the player's score, lives, etc..
 * @param {Toupony.GameplayMode} mode
 */
Toupony.MainHUD = function(mode) {
  this._mode = mode;
};


/** 
 * Unloads the object's GL memory resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.MainHUD.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "mainHUD");
};


/** 
 * Loads the object's GL memory resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.MainHUD.load = function(gl) {
  TentaGL.MaterialLib.add(gl, "mainHUD", TentaGL.Texture.fromURL(gl, "img/menu/mainHUD.png"));
};


Toupony.MainHUD.prototype = {
  
  constructor: Toupony.MainHUD,
  
  /** 
   * Renders the player's stats.
   * @param {WebGLRenderingContext} gl
   * @param {PlayerStats} stats
   */
  renderWithStats: function(gl, stats, highScore) {
    this._setShaderOpaque(gl);
    
    this._renderBackdrop(gl);
    
    var highScoreStr = this._padString("" + highScore, "0", 8);
    var scoreStr = this._padString("" + stats.score(), "0", 8);
    var powerStr = stats.power().toFixed(1);
    var grazeStr = "" + stats.graze();
    
    this._renderHUDString(gl, highScoreStr, [520,45]);
    this._renderHUDString(gl, scoreStr, [520,75]);
    this._renderHUDString(gl, powerStr, [520,175]);
    this._renderHUDString(gl, grazeStr, [520,205]);
  },
  
  
  _setShaderOpaque: function(gl) {
    var program = TentaGL.ShaderLib.current(gl);
    if(program.setOpacity) {
      program.setOpacity(gl, 1.0);
    }
  },
  
  
  /** 
   * Renders the HUD backdrop.
   * @param {WebGLRenderingContext} gl
   */
  _renderBackdrop: function(gl) {
    TentaGL.ViewTrans.push(gl);
    
    TentaGL.MaterialLib.use(gl, "mainHUD");
    TentaGL.ViewTrans.scale(gl, [640, 480]);
    TentaGL.ModelLib.render(gl, "unitSprite");
    
    TentaGL.ViewTrans.pop(gl);
  },
  
  /** 
   * Pads a string with a character until it is the desired length.
   * @param {string} text
   * @param {char} padChar
   * @param {uint} length
   * @return {string}
   */
  _padString: function(text, padChar, length) {
    while(text.length < length) {
      text = padChar + text;
    }
    return text;
  },
  
  
  /**
   * Renders a string for something in the HUD.
   * @param {WebGLRenderingContext} gl
   * @param {string} text
   * @param {vec2} xy
   */
  _renderHUDString: function(gl, text, xy) {
    var font = Toupony.getFont();
    var fontHeight = 30;
    font.renderString(gl, text, xy, true, fontHeight);
  }
  
};


Util.Inheritance.inherit(Toupony.MainHUD, TentaGL.Renderable);