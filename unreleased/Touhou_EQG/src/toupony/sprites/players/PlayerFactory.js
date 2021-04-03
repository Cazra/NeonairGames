/** 
 * Used for creating Player instances.
 */
Toupony.PlayerFactory = {
  
  /** 
   * Creates a player sprite instance based on the specified gameplay settings.
   * @param {Toupony.GameConfig} config
   * @param {Toupony.GameplayMode} mode
   * @return {Toupony.Player}
   */
  createPlayer: function(config, mode) {
    var team = mode.getTeam();
    var stats = new Toupony.PlayerStats(config, mode);
    
    if(team == Toupony.PlayerTeams.TWI_RARE) {
      return new Toupony.TwiPlayer(stats);
    }
    else if (team == Toupony.PlayerTeams.DASH_PIE) {
      return new Toupony.DashPlayer(stats);
    }
    else if(team == Toupony.PlayerTeams.FLUTTER_JACK) {
      return new Toupony.FlutterPlayer(stats);
    }
    else if(team == Toupony.PlayerTeams.DERPY) {
      return new Toupony.DerpyPlayer(stats);
    }
  }
  
};
