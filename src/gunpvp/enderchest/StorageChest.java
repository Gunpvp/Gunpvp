package gunpvp.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import gunpvp.Gunpvp;

public class StorageChest {
	
	private static int SIZE = 56;
	
	private Player p;
	
	public StorageChest(Player p) {
		this.p = p;
		
	}
	
	public void storeInventory(Inventory inventory) {
		
		for (int n = 0;n<inventory.getSize();n++) {
			Gunpvp.getPlugin().getConfig().set(p.getName()+"."+n, inventory.getItem(n));
		}
		
	}
	
	public Inventory generateInventory() {
		
		Inventory inv = Bukkit.createInventory(null, SIZE, "&2&lWaffenbox");
		
		for (int n = 0;n < SIZE;n++) {
			inv.setItem(n, Gunpvp.getPlugin().getConfig().getItemStack(p.getName()+"."+n));
		}
		
		return inv;
	}

	public Player getPlayer() {
		return p;
	}
	
	
	
}
