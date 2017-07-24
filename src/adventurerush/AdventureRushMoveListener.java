package adventurerush;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import gunpvp.listener.Listener;

public class AdventureRushMoveListener extends Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().getWorld().getName().equals("AdventureRush")) {
			
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				
				Player p = e.getPlayer();
				int world = (int) ((30+p.getLocation().getX())/1000);
				float percent = (float) ((p.getLocation().getX()%1000)/10f);
				
				p.sendMessage("World: " +world + "Percent: "+percent+"%");
				
			}
			
		}
	}
	
}
