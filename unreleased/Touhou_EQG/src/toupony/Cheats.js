Toupony.Cheats = {
  
  run: function(keyboard, context) {
    var player = context.getPlayer();
    var playerStats = player.getStats();
    
    // Set the player's power.
    if(keyboard.justPressed(KeyCode.NUM0)) {
      playerStats.power(0.0);
    }
    if(keyboard.justPressed(KeyCode.NUM1)) {
      playerStats.power(1.0);
    }
    if(keyboard.justPressed(KeyCode.NUM2)) {
      playerStats.power(2.0);
    }
    if(keyboard.justPressed(KeyCode.NUM3)) {
      playerStats.power(3.0);
    }
    if(keyboard.justPressed(KeyCode.NUM4)) {
      playerStats.power(4.0);
    }
    
  }
  
};