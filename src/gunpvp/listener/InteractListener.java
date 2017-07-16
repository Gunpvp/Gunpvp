package gunpvp.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener extends Listener {
	
	@EventHandler
	public void onInterct(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.CHEST) e.setCancelled(true);
			if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST) e.setCancelled(true);
			if (e.getClickedBlock().getType() == Material.STONE_BUTTON) e.setCancelled(true);
			if (e.getClickedBlock().getType() == Material.WOOD_BUTTON) e.setCancelled(true);
			if (e.getClickedBlock().getType() == Material.TRAP_DOOR) e.setCancelled(true);
		}
	}

}
