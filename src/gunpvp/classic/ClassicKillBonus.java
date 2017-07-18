package gunpvp.classic;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shampaggon.crackshot.CSUtility;

import gunpvp.data.DataManager;
import gunpvp.data.Settings;

public class ClassicKillBonus {
	
	private static CSUtility csu = new CSUtility();
	private static Random r = new Random();
	
	//******************** KILLBONUS ********************
	public static void givePlayerKillBonus(Player p) {
		if (p != null) {
			String kit = ClassicItems.getKitFromClassic(p);
			if (kit != null) {
				Settings settings = DataManager.getData(p).getSettings();
				if (settings.hasInfoEnabled()) p.sendMessage("§aKillbonus §2>>> §2Killbonus-" + (kit.equalsIgnoreCase("Jugger") ? "Juggernaut" : kit) + "§7 erhalten");
				switch (kit) {
				case "Soldier":
					csu.giveWeapon(p, "Granate", 1);
					p.getInventory().addItem(getItem(Material.APPLE, 1, 0, "§2§lMedipack", null));
					equipAmmo(p, 1);
					break;
				case "Gunner":
					csu.giveWeapon(p, "Granate", 2);
					equipAmmo(p, 2);
					break;
				case "Healer":
					p.getInventory().addItem(getItem(Material.APPLE, 4, 0, "§2§lMedipack", null));
					equipAmmo(p, 1);
					break;
				case "Rambo":
					csu.giveWeapon(p, "Blendgranate", 2);
					equipAmmo(p, 1);
					break;
				case "Jugger":
					csu.giveWeapon(p, "Granate", 2);
					equipAmmo(p, 1);
					break;
				case "Bomber":
					p.getInventory().addItem(getItem(Material.POTATO, 1, 0, "§2§lAT4-Rocket", null));
					if (r.nextInt(6) == 1) csu.giveWeapon(p, "Luftschlag", 1);
					if (r.nextInt(4) == 1) csu.giveWeapon(p, "Bomb", 1);
					if (r.nextInt(8) == 1) csu.giveWeapon(p, "C4", 1);
					break;
				case "Pyro":
					csu.giveWeapon(p, "Granate", 2);
					csu.giveWeapon(p, "Splittergranate", 1);
					csu.giveWeapon(p, "Blendgranate", 3);
					equipAmmo(p, 1);
					break;
				default:
					equipAmmo(p, 1);
					break;
				}
			}
		}
		p.updateInventory();
	}
	
	//******************** AMMO-METHOD ********************
	private static void equipAmmo(Player p, int stacks) {
		for (int x = 1 ; x <= stacks ; x++) {
			ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
			ItemMeta meta = ammo.getItemMeta();
			meta.setDisplayName("§2§lAmmo");
			meta.setLore(null);
			ammo.setItemMeta(meta);
			p.getInventory().addItem(ammo);
		}
	}
	
	//******************** ITEM-METHOD ********************
	private static ItemStack getItem(Material mat, int amount, int data, String name, ArrayList<String> lore) {
		ItemStack item = new ItemStack(mat, amount, (short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
