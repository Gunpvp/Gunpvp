package gunpvp.zombie;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gunpvp.listener.Listener;

public class ZombieClickListener extends Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (p.getWorld().getName().equals("Gunpvp")) {
			
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				Block block = e.getClickedBlock();
				
				if (e.getClickedBlock().getType() == Material.WALL_SIGN) {
					
					Sign sign = (Sign) e.getClickedBlock().getState();
					
					if (sign.getLine(0).equals("§8[§2Zombie§8]")) {
						
						Arena arena = Arenas.getArena(sign.getLine(1));
						
						if (arena != null) {
							
							arena.join(p);
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
