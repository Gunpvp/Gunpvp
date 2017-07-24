package adventurerush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gunpvp.inventories.Inventories;
import gunpvp.listener.Listener;

public class AdventureRushSignListener extends Listener {
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null) {
			
			if (e.getClickedBlock().getType() == Material.SIGN_POST) {
				
				Sign sign = (Sign) e.getClickedBlock().getState();
				Player p = e.getPlayer();
				
				if (p.getWorld().getName().equals("Gunpvp")) {
					
					if (sign.getLine(0).equals("§8[§aAR§8]")) {
						
						int world = Integer.parseInt(sign.getLine(1).split(" ")[1]);
						
						Location world_loc = new Location(Bukkit.getWorld("AdventureRush"), 2000*(world-1)+0.5, 100.5, 0.5, -90, 0);
						
						Inventories.saveInventory(p);
						p.teleport(world_loc);
						
					}
					
				}
				
				if (p.getWorld().getName().equals("AdventureRush")) {
					
					
					
				}
				
			}
			
		}
	}
	
}
