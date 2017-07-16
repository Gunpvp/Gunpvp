package gunpvp.enderchest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import gunpvp.listener.Listener;

public class EnderchestMaster extends Listener {
	
	private List<StorageChest> chests = new ArrayList<>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		chests.add(new StorageChest(e.getPlayer()));
		e.getPlayer().openInventory(getChest(e.getPlayer()).generateInventory());
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		chests.remove(getChest(e.getPlayer()));
		
	}
	
	@EventHandler
	public void onInventroyClose(InventoryCloseEvent e) {
		
		if (e.getInventory().getName().equals("&2&lWaffenbox")) {
			
			StorageChest chest = getChest(e.getPlayer());
			chest.storeInventory(e.getInventory());
			
		}
		
	}
	
	private StorageChest getChest(HumanEntity p) {
		for (StorageChest chest : chests) if (chest.getPlayer().getName().equals(p.getName())) return chest;
		return null;
	}
	
}
