package gunpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionPrimeListener extends Listener {

	@EventHandler
	public void onPrime(ExplosionPrimeEvent e) {
		
		if (e.getEntity() instanceof Item) {
			Item item = (Item) e.getEntity();
			if (item.getItemStack() != null && item.getItemStack().getType() == Material.GHAST_TEAR) {
				Bukkit.broadcastMessage("Yep... that was a flashbang!");
				e.setCancelled(true);
				e.setRadius(0);
			}
		}
		
	}
	
}
