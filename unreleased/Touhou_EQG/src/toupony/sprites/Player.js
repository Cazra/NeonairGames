/** 
 * Base class for player sprites.
 * @abstract
 * @constructor
 * @param {Toupony.PlayerStats} stats
 */
Toupony.Player = function(stats) {
  TentaGL.Sprite.call(this);
  
  this._stats = stats;
  
  this._focused = false;
  this._hitBoxRotation = 0;
  this._direction = TentaGL.Direction.UP;
  
  this._timer = new TentaGL.FrameTimer();
  this._frameAnimator = Toupony.Player.getFrameAnimator(this._timer);
  this._hitboxAnimator = Toupony.Player.getHitboxAnimator(this._timer);
  this._hitboxOpacity = 0.0;
  
  this._animTex = undefined;
};


/** 
 * Unloads the object's GL memory resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.Player.clean = function(gl) {
  TentaGL.MaterialLib.remove(gl, "hitBox");
};


/** 
 * Loads the object's GL memory resources.
 * @param {WebGLRenderingContext} gl
 */
Toupony.Player.load = function(gl) {
  TentaGL.MaterialLib.add(gl, "hitBox", TentaGL.Texture.fromURL(gl, "img/char/hitBox.png"));
};


/** 
 * Obtains an animator for the player's animation frame.
 * @param {TentaGL.Timer} timer
 * @return {TentaGL.Animator}
 */
Toupony.Player.getFrameAnimator = function(timer) {
  if(!Toupony.Player._frameAnimation) {
    Toupony.Player._frameAnimation = new TentaGL.DiscreteAnimation(
      [
        new TentaGL.DiscreteKeyframe("1", 0),
        new TentaGL.DiscreteKeyframe("2", 0.25),
        new TentaGL.DiscreteKeyframe("3", 0.5),
        new TentaGL.DiscreteKeyframe("4", 0.75)
      ], 
      500
    );
  }
  var animator = Toupony.Player._frameAnimation.getAnimator(timer);
  animator.start();
  return animator;
};


/** 
 * Obtains an animator for the player's hitbox rotation animation.
 * @param {TentaGL.Timer} timer
 * @return {TentaGL.Animator}
 */
Toupony.Player.getHitboxAnimator = function(timer) {
  if(!Toupony.Player._hitboxAnimation) {
    Toupony.Player._hitboxAnimation = new TentaGL.ScalarAnimation(
      [
        new TentaGL.ScalarKeyframe(0, 0.0),
        new TentaGL.ScalarKeyframe(TentaGL.TAU, 1.0)
      ],
      2000
    );
  }
  var animator =  Toupony.Player._hitboxAnimation.getAnimator(timer);
  animator.start();
  return animator;
};


Toupony.Player.prototype = {
  
  constructor: Toupony.Player, 
  
  isaPlayerSprite: true,
  
  /** 
   * Returns the player's gameplay stats.
   * @return {Toupony.PlayerStats}
   */
  getStats: function() {
    return this._stats;
  },
  
  
  /** 
   * Controls the player with keyboard input.
   * @param {TentaGL.Keyboard} keyboard
   * @param {Toupony.MainLevel} context
   */
  control: function(keyboard, context) {
    this._controlFocus(keyboard, context);
    this._controlMovement(keyboard, context);
    
    this._controlWeapon(keyboard, context);
  // TODO: this._controlBomb(keyboard, context);
    
    this._stayInPlayableArea();
  },
  
  /** 
   * Use the shift key to control whether the player is in focused movement
   * mode or un-focused movement mode.
   * While focused, the player's hit box becomes visible.
   */
  _controlFocus: function(keyboard, context) {
    if(keyboard.isPressed(KeyCode.SHIFT)) {
      this._speed = this.getFocusedSpeed();
      this._focused = true;
      this._fadeHitBoxOpacity(1.0);
    }
    else {
      this._speed = this.getSpeed();
      this._focused = false;
      this._fadeHitBoxOpacity(0.0);
    }
  },
  
  /** 
   * Fades the hit box's opacity gradually to the specified value. 
   * @param {float} targetOpacity
   */
  _fadeHitBoxOpacity: function(targetOpacity) {
    this._hitboxOpacity += (targetOpacity - this._hitboxOpacity)/10;
  },
  
  /** 
   * Uses arrow keys to move the player at their current speed.
   * @param {TentaGL.Keyboard} keyboard
   */
  _controlMovement: function(keyboard, context) {
    if(keyboard.isPressed(KeyCode.LEFT)) {
      this.x(this.x() - this._speed);
      this._direction = TentaGL.Direction.LEFT;
    }
    if(keyboard.isPressed(KeyCode.RIGHT)) {
      this.x(this.x() + this._speed);
      this._direction = TentaGL.Direction.RIGHT;
    }
    
    if(keyboard.isPressed(KeyCode.UP)) {
      this.y(this.y() - this._speed);
      this._direction = TentaGL.Direction.UP;
    }
    if(keyboard.isPressed(KeyCode.DOWN)) {
      this.y(this.y() + this._speed);
      this._direction = TentaGL.Direction.DOWN;
    }
  },
  
  /** 
   * Prevents the player from moving outside the playable area.
   */
  _stayInPlayableArea: function() {
    if(this.x() < 16) {
      this.x(16);
    }
    if(this.x() > Toupony.STAGE_WIDTH - 16) {
      this.x(Toupony.STAGE_WIDTH - 16);
    }
    if(this.y() < 16) {
      this.y(16);
    }
    if(this.y() > Toupony.STAGE_HEIGHT - 16) {
      this.y(Toupony.STAGE_HEIGHT - 16);
    }
  },
  
  
  /** 
   * Cools down the player's weapons and tries to fire the player's weapons
   * if they are holding the shot button.
   * @param {TentaGL.Keyboard} keyboard
   * @param {Toupony.MainLevel} context
   */
  _controlWeapon: function(keyboard, context) {
    var weapon = this.getWeapon();
    
    weapon.update(this, context);
    if(keyboard.isPressed(KeyCode.Z)) {
      weapon.fire(this, context);
    }
  },
  
  /** 
   * Returns whether the player is currently focused.
   * @return {boolean}
   */
  isFocused: function() {
    return this._focused;
  },
  
  
  /**
   * @inheritDoc TentaGL.Sprite
   */
  getWidth: function() {
    return this.getHitboxRadius()*2;
  },
  
  /** 
   * @inheritDoc TentaGL.Sprite
   */
  getHeight: function() {
    return this.getHitboxRadius()*2;
  },
  
  
  /** 
   * @inheritDoc TentaGL.Sprite
   */
  getBounds2D: function() {
    var radius = this.getHitboxRadius();
    var dia = radius*2;
    var x = this.x() - radius;
    var y = this.y() - radius;
    return new TentaGL.Math.Rect2D([x, y], dia, dia);
  },
  
  
  
  /** 
   * Runs one timer tick of animation. 
   * This updates the player's current texture key and the rotation of their hitbox.
   */
  animate: function() {
    this._timer.tick();
    var frame = this.getTexBaseName();
    
    if(this._direction == TentaGL.Direction.LEFT) {
      frame += "Left";
    }
    else if(this._direction == TentaGL.Direction.RIGHT) {
      frame += "Right";
    }
    else {
      frame += "Up";
    }
    
    frame += this._frameAnimator.animate();
    
    this._animTex = frame;
    this._hitBoxRotation = this._hitboxAnimator.animate();
  },
  
  
  
  /** 
   * Renders the player's hitbox. 
   * @param {WebGLRenderingContext} gl
   */
  renderHitbox: function(gl) {
    TentaGL.ViewTrans.push(gl);
    
    var program = TentaGL.ShaderLib.current(gl);
    if(program.setOpacity) {
      program.setOpacity(gl, this._hitboxOpacity);
    }
    
    var dia = this.getHitboxRadius() * 2;
    
    TentaGL.ViewTrans.scale(gl, [dia, dia]);
    TentaGL.ViewTrans.rotate(gl, [0, 0, 1], this._hitBoxRotation);
    TentaGL.ViewTrans.translate(gl, [-0.5, -0.5]);
    
    TentaGL.MaterialLib.use(gl, "hitBox");
    TentaGL.ModelLib.render(gl, "unitSprite");
    
    TentaGL.ViewTrans.pop(gl);
  },
  
  
  render: function(gl) {
    TentaGL.Sprite.prototype.render.call(this, gl);
    this.getWeapon().render(gl);
  //  this.getBounds2D().render(gl, "red");
  },
  
  
  draw: function(gl) {
    var playerFrame = TentaGL.MaterialLib.get(gl, this._animTex);
    playerFrame.render(gl, true);
    
    TentaGL.ViewTrans.translate(gl, this.anchor());
    this.renderHitbox(gl);
  },
  
  
  //////// Abstract methods
  
  /** 
   * Returns the player's team code.
   * @return {enum: Toupony.PlayerTeams}
   */
  getTeam: function() {},
  
  
  /** 
   * Returns the base name of the textures used for the player's animations.
   * @return {String}
   */
  getTexBaseName: function() {},
  
  
  /** 
   * Returns the player's unfocused speed.
   * @return {number}
   */
  getSpeed: function() {},
  
  
  /** 
   * Returns the player's focused speed.
   * @return {number}
   */
  getFocusedSpeed: function() {},
  
  
  /** 
   * Returns the radius of the player's hitbox.
   * @return {number}
   */
  getHitboxRadius: function() {},
  
  
  /** 
   * Returns the player's current weapon.
   * @return {Toupony.Weapon}
   */
  getWeapon: function() {}
  
  
  
};


Util.Inheritance.inherit(Toupony.Player, TentaGL.Sprite);
Util.Inheritance.inherit(Toupony.Player, Toupony.Animatable);

