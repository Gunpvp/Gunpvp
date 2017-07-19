package gunpvp.enderchest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StorageChest {
	
	public static final String NAME = "§2§lWaffenbox";
	private static int SIZE = 54;
	
	private Player p;
	
	public StorageChest(Player p) {
		this.p = p;
		try {
			EnderchestMaster.getConfig().load("plugins/Gunpvp/enderchests.yml");
		} catch (FileNotFoundException e) {
			try {
				EnderchestMaster.getConfig().save("plugins/Gunpvp/enderchests.yml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Bukkit.getConsoleSender().sendMessage("§8[§2Gunpvp§8] §aNew config file for player " + p.getName() + " has been created!");
			try {
				EnderchestMaster.getConfig().load("plugins/Gunpvp/enderchests.yml");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InvalidConfigurationException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void storeInventory(Inventory inventory) {
		
		for (int n = 0;n<inventory.getSize();n++) {
			EnderchestMaster.getConfig().set(p.getName()+"."+n, inventory.getItem(n));
		}
		
		try {
			EnderchestMaster.getConfig().save("plugins/Gunpvp/enderchests.yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Inventory generateInventory() {
		
		Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
		
		for (int n = 0;n < SIZE;n++) {
			inv.setItem(n, EnderchestMaster.getConfig().getItemStack(p.getName()+"."+n));
		}
		
		return inv;
	}

	public Player getPlayer() {
		return p;
	}
	
	
	
}
