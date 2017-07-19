package gunpvp.inventories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Inventories {
	
	private static final int SIZE = 36;
	private static FileConfiguration config;
	
	public static void init() {
		config = YamlConfiguration.loadConfiguration(new File("plugins/Gunpvp/inventories.yml"));
	}
	
	public static void saveInventory(Player p) {
		
		for (int n = 0;n < SIZE;n++) {
			config.set(p.getName()+"."+ n, p.getInventory().getItem(n));
		}
		
		try {
			config.save("plugins/Gunpvp/inventories.yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadInventory(Player p) {
		try {
			config.load("plugins/Gunpvp/inventories.yml");
		} catch (FileNotFoundException e) {
			try {
				config.save("plugins/Gunpvp/inventories.yml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Bukkit.getConsoleSender().sendMessage("§8[§2Gunpvp§8] §aNew entry in inventory file for player " + p.getName() + " has been created!");
			try {
				config.load("plugins/Gunpvp/inventories.yml");
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
		
		p.getInventory().clear();
		for (int n = 0;n < SIZE;n++) {
			ItemStack item = config.getItemStack(p.getName()+"."+n);
			p.getInventory().setItem(n, item);
		}
		
	}
	
}
