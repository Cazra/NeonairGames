/** 
 * Base class for background scenes. Backgrounds are rendered to an offscreen 
 * buffer so that a uniform opacity can be applied to the entire background
 * for smooth transitions between backgrounds.
 * @abstract
 * @constructor
 * @param {Toupony.Stage} stage
 */
Toupony.Background = function(stage) {
  this._stage;
  
  this._opacity = 1;
  this._buffer = new TentaGL.BufferTexture(stage.getApp().getGL(), Toupony.STAGE_WIDTH, Toupony.STAGE_HEIGHT);
};


/** 
 * Gets the camera used to render the background scene onto a sprite. 
 */
Toupony.Background.getCamera2D = function() {
  if(Toupony.Background._cam2D === undefined) {
    Toupony.Background._cam2D = new TentaGL.Camera2D([0,0], Toupony.STAGE_WIDTH, Toupony.STAGE_HEIGHT, true);
  }
  return Toupony.Background._cam2D;
};


Toupony.Background.prototype = {
  
  constructor: Toupony.Background,
  
  
  /** 
   * Returns the stage this background is used for. 
   * @return {Toupony.Stage}
   */
  getStage: function() {
    return this._stage;
  },
  
  
  
  /** 
   * Setter/getter for the opacity of the background. 
   * @param {float} opacity   Optional.
   * @return {float}
   */
  opacity: function(opacity) {
    if(opacity !== undefined) {
      this._opacity = opacity;
    }
    return this._opacity;
  },
  
  
  
  /** 
   * Renders the background. 
   * @param {WebGLRenderingContext} gl
   */
  render: function(gl) {
    this._buffer.renderToMe(gl, this.draw.bind(this));
    
    TentaGL.ShaderLib.use(gl, "simple");
    TentaGL.RenderMode.set2D(gl);
    var aspect = Toupony.STAGE_WIDTH/Toupony.STAGE_HEIGHT;
    TentaGL.ViewTrans.setCamera(gl, Toupony.Background.getCamera2D(), aspect);
    this._buffer.useMe(gl);
    
    TentaGL.ShaderLib.current(gl).setOpacity(gl, this._opacity);
    
    TentaGL.ViewTrans.push(gl);
    
    TentaGL.ViewTrans.scale(gl, [Toupony.STAGE_WIDTH, Toupony.STAGE_HEIGHT]);
    TentaGL.ModelLib.render(gl, "unitSprite");
    
    TentaGL.ViewTrans.pop(gl);
  },
  
  
};

