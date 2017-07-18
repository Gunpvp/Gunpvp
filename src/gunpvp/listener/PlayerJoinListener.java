package gunpvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import gunpvp.data.DataManager;
import gunpvp.scoreboard.GunpvpScoreboard;
import gunpvp.util.Action;
import gunpvp.util.Console;
import gunpvp.util.Lobby;
import gunpvp.util.Timer;

public class PlayerJoinListener extends Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		final Player p = event.getPlayer();
		
		event.setJoinMessage("§a>> §7" + p.getName());
		Console.info(p.getName() + " has joined to the server!");
		
		Timer.sync(new Action() {
			public void perform() {
				
				DataManager.add(p);
				
				Lobby.reset(p);
				Lobby.send(p);
				Lobby.giveItems(p);
				
				GunpvpScoreboard.drawScoreBoard(p);
			}
		}, 0.1f);
		
	}
	
}
