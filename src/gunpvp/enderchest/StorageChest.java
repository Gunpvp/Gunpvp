package gunpvp.enderchest;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import gunpvp.Gunpvp;

public class StorageChest {
	
	public static final String NAME = "§2§lWaffenbox";
	private static int SIZE = 54;
	
	private Player p;
	
	public StorageChest(Player p) {
		this.p = p;
		
	}
	
	public void storeInventory(Inventory inventory) {
		
		for (int n = 0;n<inventory.getSize();n++) {
			Gunpvp.getPlugin().getConfig().set(p.getName()+"."+n, inventory.getItem(n));
		}
		
		try {
			Gunpvp.getPlugin().getConfig().save("enderchest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Inventory generateInventory() {
		
		Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
		
		for (int n = 0;n < SIZE;n++) {
			inv.setItem(n, Gunpvp.getPlugin().getConfig().getItemStack(p.getName()+"."+n));
		}
		
		return inv;
	}

	public Player getPlayer() {
		return p;
	}
	
	
	
}
