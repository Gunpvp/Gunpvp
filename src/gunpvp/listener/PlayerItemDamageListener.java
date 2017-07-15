package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerItemDamageListener extends Listener {
	
	@EventHandler
	public void onItemDamage(PlayerItemDamageEvent e) {
		e.setCancelled(true);
	}
	
}
