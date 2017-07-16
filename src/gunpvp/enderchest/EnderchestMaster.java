package gunpvp.enderchest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import gunpvp.listener.Listener;

public class EnderchestMaster extends Listener {
	
	private static List<StorageChest> chests = new ArrayList<>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		chests.add(new StorageChest(e.getPlayer()));
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		chests.remove(getChest(e.getPlayer()));
		
	}
	
	@EventHandler
	public void onInventroyClose(InventoryClickEvent e) {
		
		if (e.getInventory().getName().equals(StorageChest.NAME)) {
			
			StorageChest chest = getChest(e.getWhoClicked());
			chest.storeInventory(e.getInventory());
			
		}
		
	}
	
	private static StorageChest getChest(HumanEntity p) {
		for (StorageChest chest : chests) if (chest.getPlayer().getName().equals(p.getName())) return chest;
		return null;
	}
	
	public static void openEnderchest(Player p) {
		
		p.openInventory(getChest(p).generateInventory());
		p.updateInventory();
		
	}
	
}
