package gunpvp.arcade;

import java.util.ArrayList;
import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shampaggon.crackshot.CSUtility;

import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.data.Stats;

public class ArcadeKillstreak {
	
	private static Hashtable<Player, Integer> streak = new Hashtable<Player, Integer>();
	private static CSUtility csu = new CSUtility();
	
	public synchronized static void update() {
		synchronized (streak) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!streak.containsKey(p)) streak.put(p, 0);
			}
			for (Player p : streak.keySet()) {
				if (p.isOnline() == false) {
					streak.remove(p);
				}
			}
		}
	}
	
	public synchronized static void addKill(Player p) {
		synchronized (streak) {
			if (streak.containsKey(p)) {
				int kills = streak.get(p);
				streak.remove(p);
				kills++;
				streak.put(p, kills);
				getStuff(p, kills);
			}
		}
	}
	
	public synchronized static void resetKill(Player p) {
		synchronized (streak) {
			if (streak.containsKey(p)) {
				streak.remove(p);
				streak.put(p, 0);
			}
		}
	}
	
	private static void getStuff(Player p, int kills) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		
		Stats stats = DataManager.getData(p).getStats();
		Settings settings = DataManager.getData(p).getSettings();
		
		switch(kills) {
		case 3:
			
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+3 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7Speed 1 erhalten!");
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0));
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b3er§7-Killstreak erzielt!");
			break;
		case 5:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+5 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §74xGranaten erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §72xMinen erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7Speed 2 erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			csu.giveWeapon(p, "Granate", 4);
			csu.giveWeapon(p, "Mine", 2);
			for (PotionEffect pe : p.getActivePotionEffects()) if (pe.getType() == PotionEffectType.SPEED) p.removePotionEffect(PotionEffectType.SPEED);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1));
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b5er§7-Killstreak erzielt!");
			break;
		case 8:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+8 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §8256xAmmo erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §82xSplittergranaten erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §82xBlendgranaten erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7Speed 3 erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			csu.giveWeapon(p, "Splittergranate", 2);
			csu.giveWeapon(p, "Blendgranate", 2);
			for (PotionEffect pe : p.getActivePotionEffects()) if (pe.getType() == PotionEffectType.SPEED) p.removePotionEffect(PotionEffectType.SPEED);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 3));
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b8er§7-Killstreak erzielt!");
			break;
		case 10:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+10 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7Armor-Lvl-2 erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(getItem(Material.GOLD_BOOTS, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().addItem(getItem(Material.GOLD_LEGGINGS, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().addItem(getItem(Material.GOLD_CHESTPLATE, 1, 0, "§2§lLvl-2-Armor", null));
			p.getInventory().addItem(getItem(Material.GOLD_HELMET, 1, 0, "§2§lLvl-2-Armor", null));
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b10er§7-Killstreak erzielt!");
			break;
		case 15:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+15 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7Armor-Lvl-3 erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(getItem(Material.IRON_BOOTS, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().addItem(getItem(Material.IRON_LEGGINGS, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().addItem(getItem(Material.IRON_CHESTPLATE, 1, 0, "§2§lLvl-3-Armor", null));
			p.getInventory().addItem(getItem(Material.IRON_HELMET, 1, 0, "§2§lLvl-3-Armor", null));
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b15er§7-Killstreak erzielt!");
			break;
		}
	}
	
	private static ItemStack getItem(Material mat, int amount, int data, String name, ArrayList<String> lore) {
		ItemStack item = new ItemStack(mat, amount, (short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
