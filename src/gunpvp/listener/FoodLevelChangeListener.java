package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener extends Listener {
	
	@EventHandler
	public void onDamage(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
}
