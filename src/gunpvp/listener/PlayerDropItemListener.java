package gunpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener extends Listener {
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (e.getItemDrop().getItemStack().getType() == Material.IRON_INGOT ||
				e.getItemDrop().getItemStack().getType() == Material.COMPASS ||
				e.getItemDrop().getItemStack().getType() == Material.BED) {
			e.setCancelled(true);
		}
		if (p.getWorld() == Bukkit.getWorld("ClassicBayview")
				|| p.getWorld() == Bukkit.getWorld("ClassicStudio")
				|| p.getWorld() == Bukkit.getWorld("ClassicMeltdown")) {
			e.setCancelled(true);
		}
		if (p.getWorld() == Bukkit.getWorld("Gunpvp")) {
			if (e.getItemDrop().getItemStack().getType() == Material.SEEDS || e.getItemDrop().getItemStack().getType() == Material.IRON_SPADE) {
				e.setCancelled(true);
				p.updateInventory();
				p.sendMessage("§8[§2Gunpvp§8] §cDu kannst hier nichts fallen lassen");
			}
		}
	}
	
}
