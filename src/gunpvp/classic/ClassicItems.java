package gunpvp.classic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shampaggon.crackshot.CSUtility;

import de.ShortByte.sbTitleAPI.sbTitleAPI;

public class ClassicItems {
	
	private static CSUtility csu = new CSUtility();
	private static Hashtable<Player, String> kit  = new Hashtable<Player, String>();
	private static Random r = new Random();
	
	public static void equip(Player p, String type, String map) {
		
		//******************** MAP CHOOSE + CLEAR ********************
		Classic cla = null;
		switch (map) {
		case "Bayview": cla = new ClassicBayview(); break;
		case "Studio": cla = new ClassicStudio(); break;
		case "Meltdown": cla = new ClassicMetldown(); break;
		}
		if (cla != null) {
			cla.teleport(p);
			cla.deleteObject();
		}
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.setFireTicks(0);
		p.setHealth(p.getMaxHealth());
		p.setLevel(0);
		p.setExp(0);
		for (PotionEffect pe : p.getActivePotionEffects()) p.removePotionEffect(pe.getType());
		addToClassic(p, type);
		if (p.getWorld() != Bukkit.getWorld("Gunpvp")) {
			
			//******************** KITS ********************
			switch (type) {
			// KITS
			case "Soldier":
				csu.giveWeapon(p, "RPK", 1);
				csu.giveWeapon(p, "Granate", 4);
				p.getInventory().addItem(getItem(Material.APPLE, 1, 0, "§2§lMedipack", null));
				equipArmor(p, "Lvl-2");
				equipAmmo(p, 4);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));
				break;
			case "Gunner":
				csu.giveWeapon(p, "Colt45", 1);
				csu.giveWeapon(p, "M16", 1);
				p.getInventory().addItem(getItem(Material.APPLE, 2, 0, "§2§lMedipack", null));
				csu.giveWeapon(p, "Granate", 2);
				equipArmor(p, "Lvl-3");
				equipAmmo(p, 16);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
				p.updateInventory();
				break;
			case "Rambo":
				csu.giveWeapon(p, "GL1", 1);
				csu.giveWeapon(p, "Blendgranate", 1);
				equipArmor(p, "Lvl-1");
				equipAmmo(p, 1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));
				break;
			case "Pyro":
				csu.giveWeapon(p, "Barret", 1);
				csu.giveWeapon(p, "USP", 1);
				csu.giveWeapon(p, "Blendgranate", 2);
				csu.giveWeapon(p, "Granate", 2);
				csu.giveWeapon(p, "Splittergranate", 3);
				equipAmmo(p, 4);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));
				break;
			case "Jugger":
				p.getInventory().addItem(getItem(Material.IRON_SWORD, 2, 0, "§2§lKnife Lvl-2", null));
				csu.giveWeapon(p, "DesertEagle", 1);
				csu.giveWeapon(p, "C4", 1);
				equipArmor(p, "Lvl-2");
				equipAmmo(p, 2);
				p.updateInventory();
				break;
			case "Healer":
				csu.giveWeapon(p, "L86", 1);
				p.getInventory().addItem(getItem(Material.APPLE, 6, 0, "§2§lMedipack", null));
				csu.giveWeapon(p, "Granate", 2);
				equipArmor(p, "Lvl-1");
				equipAmmo(p, 8);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0));
				p.updateInventory();
				break;
			case "Bomber":
				csu.giveWeapon(p, "AT4", 1);
				csu.giveWeapon(p, "G3", 1);
				csu.giveWeapon(p, "Granate", 2);
				p.getInventory().addItem(getItem(Material.POTATO, 3, 0, "§2§lAT4-Rocket", null));
				if (r.nextInt(4) == 1) csu.giveWeapon(p, "Luftschlag", 1);
				else if (r.nextInt(4) == 1) csu.giveWeapon(p, "Bomb", 1);
				else if (r.nextInt(4) == 1) csu.giveWeapon(p, "C4", 1);
				equipArmor(p, "Lvl-1");
				equipAmmo(p, 2);
				p.updateInventory();
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
				break;
			}
			
			//******************** TOOLS ********************
			p.getInventory().setItem(7, null);
			p.getInventory().setItem(8, null);
			ItemStack item1 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName("§b§lEinstellungen");
			meta1.setLore(null);
			item1.setItemMeta(meta1);
			p.getInventory().setItem(7, item1);
			p.updateInventory();
			ItemStack item2 = new ItemStack(Material.BED, 1, (byte) 0);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName("§b§lZurück zur Lobby");
			meta2.setLore(null);
			item2.setItemMeta(meta2);
			p.getInventory().setItem(8, item2);
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 51));
			sbTitleAPI.clear(p);
			sbTitleAPI.sendActionBar(p, "§2§l" + type + "-Kit §a§lerhalten");
		}
	}
	
	//******************** ARMOR- METHOD ********************
	private static void equipArmor(Player p, String type) {
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		switch (type) {
		case "Lvl-1":
			p.getInventory().setBoots(getItem(Material.LEATHER_BOOTS, 1, 0, "§2§lLvl-1-Armor", null));
			p.getInventory().setLeggings(getItem(Material.LEATHER_LEGGINGS, 1, 0, "§2§lLvl-1-Armor", null));
			p.getInventory().setChestplate(getItem(Material.LEATHER_CHESTPLATE, 1, 0, "§2§lLvl-1-Armor", null));
			p.getInventory().setHelmet(getItem(Material.LEATHER_HELMET, 1, 0, "§2§lLvl-1-Armor", null));
			break;
		case "Lvl-2":
			p.getInventory().setBoots(getItem(Material.GOLD_BOOTS, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().setLeggings(getItem(Material.GOLD_LEGGINGS, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().setChestplate(getItem(Material.GOLD_CHESTPLATE, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().setHelmet(getItem(Material.GOLD_HELMET, 1, 0, "§2§lLvl-2-Armor", null));
			break;
		case "Lvl-3":
			p.getInventory().setBoots(getItem(Material.IRON_BOOTS, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().setLeggings(getItem(Material.IRON_LEGGINGS, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().setChestplate(getItem(Material.IRON_CHESTPLATE, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().setHelmet(getItem(Material.IRON_HELMET, 1, 0, "§2§lLvl-3-Armor", null));
			break;
		case "Lvl-4":
			p.getInventory().setBoots(getItem(Material.DIAMOND_BOOTS, 1, 0, "§2§lLvl-4-Armor", null));
			p.getInventory().setLeggings(getItem(Material.DIAMOND_LEGGINGS, 1, 0, "§2§lLvl-4-Armor", null));
			p.getInventory().setChestplate(getItem(Material.DIAMOND_CHESTPLATE, 1, 0, "§2§lLvl-4-Armor", null));
			p.getInventory().setHelmet(getItem(Material.DIAMOND_HELMET, 1, 0, "§2§lLvl-4-Armor", null));
			break;
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
	
	//******************** TOOLS FOR CLASSIC ********************
	public static void removeFromClassic(Player p) {
		kit.remove(p);
	}
	
	public static void addToClassic(Player p, String kit) {
		if (!ClassicItems.kit.containsKey(p)) ClassicItems.kit.put(p, kit);
	}
	
	public static String getKitFromClassic(Player p) {
		if (kit.containsKey(p)) return kit.get(p);
		else return null;
	}
	
}
