/**
 * A grouping of similar sprite objects which may be Destroyable or Animatable.
 * A SpriteGroup may even be composed of a hierarchy of other SprittGroups.
 * Destroyed sprites are automatically removed from the group.
 * @constructor
 * @param {array: TentaGL.Renderable} sprites   Optional. An initial list of sprites
 *                                              for the group. 
 */
Toupony.SpriteGroup = function(sprites) {
  if(sprites === undefined) {
    sprites = [];
  }
  this._sprites = sprites;
};

Toupony.SpriteGroup.prototype = {
  
  constructor: Toupony.SpriteGroup,
  
  isaSpriteGroup: true, 
  
  /**
   * Returns a list of the sprites in this group. Destroyable sprites that
   * have been destroyed are automatically removed from the group when they are 
   * encountered.
   * @return {array: Sprite}
   */
  getAll: function() {
    return this._sprites.slice(0);
  },
  
  
  /**
   * Returns a filtered list of the sprites in this group. 
   * @param {function(sprite: TentaGL.Sprite): boolean} filterFunc. 
   *            The result list will only contain the sprites for which this
   *            function returns true.
   * @return {array: Sprite}
   */ 
  filter: function(filterFunc) {
    var result = [];
    
    this.forEach(function(sprite) {
      if(filterFunc(sprite)) {
        result.push(sprite);
      }
    });
    
    return result;
  },
  
  
  /** 
   * Executes a function over each sprite in this group.
   * @param {function(sprite: TentaGL.Sprite)} func
   */
  forEach: function(func) {
    for(var i=0; i < this._sprites.length; i++) {
      var sprite = this._sprites[i];
      func(sprite);
    }
  },
  
  
  /** 
   * Adds a sprite to the end of this group. 
   * @param {TentaGL.Sprite} sprite
   */
  add: function(sprite) {
    this._sprites.push(sprite);
    
    // Automatically remove the sprite from this group if it is destroyed. 
    if(sprite.isaDestroyable) {
      var self = this;
      sprite.addDestroyHandler(function(destroyedSprite) {
        self.remove(destroyedSprite);
      });
    }
  },
  
  /** 
   * Adds a list of sprites to the sprite group.
   * @param {array: TentaGL.Sprite} sprites
   */
  addAll: function(sprites) {
    for(var i=0; i < sprites.length; i++) {
      var sprite = sprites[i];
      this.add(sprite);
    }
  },
  
  
  /** 
   * Removes a sprite from this group.
   * @param {TentaGL.Sprite} sprite
   */
  remove: function(sprite) {
    var index = this._sprites.indexOf(sprite);
    if(index >= 0) {
      this._sprites.splice(index, 1);
    }
  },
  
  /** 
   * Removes all sprites from this group. 
   */
  removeAll: function() {
    this._sprites = [];
  },
  
  
  /**
   * Returns the number of sprites in this group.
   * @return {uint}
   */
  size: function() {
    return this._sprites.length;
  },
  
  /** 
   * Sorts the sprites in the group according to the ordering defined
   * by some comparison function.
   * @param {function(a: TentaGL.Sprite, b: TentaGL.Sprite)} func   The comparison function.
   */
  sort: function(func) {
    this._sprites.sort(func);
  },
  
  
  /**
   * Animates all Animatable sprites in the group.
   */ 
  animate: function() {
    this.forEach(function(sprite) {
      if(sprite.isaAnimatable) {
        sprite.animate();
      }
    });
  },
  
  
  /**
   * Renders all sprites in the group.
   * @param {WebGLRenderingContext} gl
   */
  render: function(gl) {
    this.forEach(function(sprite) {
      sprite.render(gl);
    });
  }
};

Util.Inheritance.inherit(Toupony.SpriteGroup, TentaGL.Renderable);
Util.Inheritance.inherit(Toupony.SpriteGroup, Toupony.Animatable);
Util.Inheritance.inherit(Toupony.SpriteGroup, Toupony.Destroyable);


