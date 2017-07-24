package gunpvp.adventurerush;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gunpvp.listener.Listener;

public class AdventureRushBlockMarkListener extends Listener {

	@EventHandler
	public void onMove(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null) {
			
			if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK) {
				
				if (e.getPlayer().getWorld().getName().equals("AdventureRush")) {
					Block block = e.getClickedBlock();
					Player p = e.getPlayer();
					String loc = block.getX() + ";" + block.getY() + ";" + block.getZ();
					p.sendMessage(loc);
					System.out.println(loc);
				}
				
			}
		}
	}
	
}
