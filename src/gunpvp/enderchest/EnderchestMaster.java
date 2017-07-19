package gunpvp.enderchest;

import java.io.File;
import java.util.Vector;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import gunpvp.listener.Listener;

public class EnderchestMaster extends Listener {
	
	private static Vector<StorageChest> chests = new Vector<>();
	private static FileConfiguration config;
	
	public EnderchestMaster() {
		super();
		config = YamlConfiguration.loadConfiguration(new File("plugins/Gunpvp/enderchests.yml"));
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		chests.add(new StorageChest(e.getPlayer()));
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		chests.remove(getChest(e.getPlayer()));
		
	}
	
	@EventHandler
	public void onInventroyClick(InventoryClickEvent e) {
		
		if (e.getInventory().getName()!=StorageChest.NAME) return;
		
		StorageChest chest = getChest(e.getWhoClicked());
		chest.storeInventory(e.getInventory());
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				
				e.setCancelled(true);
				Player p = e.getPlayer();
				p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
				EnderchestMaster.openEnderchest(p);
				
			}
			
		}
	}
	
	@EventHandler
	public void onInventroyClose(InventoryCloseEvent e) {
		
		if (e.getInventory().getName()!=StorageChest.NAME) return;
		
		if (e.getPlayer() instanceof Player) {
			Player p = (Player) e.getPlayer();
			p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
		}
		StorageChest chest = getChest(e.getPlayer());
		chest.storeInventory(e.getInventory());
		
	}
	
	@EventHandler
	public void onInventroyClick(InventoryInteractEvent e) {
		
		if (e.getInventory().getName()!=StorageChest.NAME) return;
		
		StorageChest chest = getChest(e.getWhoClicked());
		chest.storeInventory(e.getInventory());
		
	}
	
	private static StorageChest getChest(HumanEntity p) {
		for (StorageChest chest : chests) if (chest.getPlayer().getName().equals(p.getName())) return chest;
		return null;
	}
	
	public static void openEnderchest(Player p) {
		
		p.openInventory(getChest(p).generateInventory());
		p.updateInventory();
		
	}
	
	public static FileConfiguration getConfig() {
		return config;
	}
	
}
