package gunpvp.adventurerush;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import gunpvp.listener.Listener;

public class AdventureRushMoveListener extends Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().getWorld().getName().equals("AdventureRush")) {
			
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				
				if (e.getPlayer().getLocation().getBlockY()<50) {
					
					e.getPlayer().getLocation().setY(75);
					e.getPlayer().damage(100.0);
					
				}
				
			}
			
		}
	}
	
}
