package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerChangeHandListener extends Listener {
	
	@EventHandler
	public void onChangeHand(PlayerSwapHandItemsEvent e) {
		e.setCancelled(true);
	}
	
}
