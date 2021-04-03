/** 
 * A pre-fabricated shader program that applies a model-view-projection 
 * transform to vertices in the scene and colors texels using a texture.
 * Lighting/Shading is not provided in this program.
 * @param {WebGLRenderingContext} gl
 */
Toupony.AddColorShader = function(gl) {
  var shaderRoot = TentaGL.ShaderLib.getDefaultShaderPath(gl);
  
  var vertURL = shaderRoot + "simple.vert";
  var fragURL = shaderRoot + "addColor.frag";
  
  var self = this;
  TentaGL.ShaderLoader.load(vertURL, fragURL, function(vertSrc, fragSrc) {
    console.log("\nCreating AddColor shader");
    TentaGL.ShaderProgram.call(self, gl, vertSrc, fragSrc);
    
    self.setAttrGetter("vertexPos", TentaGL.Vertex.prototype.xyz);
    self.setAttrGetter("vertexNormal", TentaGL.Vertex.prototype.normal);
    self.setAttrGetter("vertexTexCoords", TentaGL.Vertex.prototype.texST);
    
    self._opacityUni = self.getUniform("opacity");
    
    self._fogEqUni = self.getUniform("fogEquation");
    self._fogColorUni = self.getUniform("fogColor");
    self._fogDensityUni = self.getUniform("fogDensity");
    
    self._mvpUni = self.getUniform("mvpTrans");
    self._normalUni = self.getUniform("normalTrans");
    
    self._colorUni = self.getUniform("addedColor");
    self._texUni = self.getUniform("tex");
  });
};

Toupony.AddColorShader.prototype = {
  
  constructor: Toupony.AddColorShader,
  
  isaAddColorShader: true,
  
  /** 
   * Sets the uniform variables for using a solid color instead of using a 
   * texture for color. Solid colors are compatible with bump maps! Just call 
   * setBump after setColor, since setColor unsets the useBumpTex uniform.
   * @param {WebGLRenderingContext} gl
   * @param {vec4} rgba
   */
  setColor: function(gl, rgba) {
    this._colorUni.set(gl, rgba);
  },
  
  /** 
   * Sets the value of the uniform variable for the primary texture offset. 
   * @param {WebGLRenderingContext} gl
   * @param {int} value
   */
  setTex: function(gl, value) {
    this._texUni.set(gl, [value]);
  }
};


/** 
 * Loads SimpleShader into the ShaderLib, with the specified name. 
 * @param {WebGLRenderingContext} gl
 * @param {name}  The name used to reference the shader program from the ShaderLib.
 * @return {TentaGL.ShaderProgram}
 */
Toupony.AddColorShader.load = function(gl, name) {
  var program = new Toupony.AddColorShader(gl);
  TentaGL.ShaderLib.add(gl, name, program);
  
  return program;
};


Util.Inheritance.inherit(Toupony.AddColorShader, TentaGL.SimpleShader);

