package gunpvp.classic;

import gunpvp.chestlottery.LuckyPack;
import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassicKillstreak {
	
	private static Map<Player, Integer> streak = new HashMap<Player, Integer>();
	private static List<Player> to_remove = new ArrayList<>();
	
	public static void update() {
		synchronized (streak) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!streak.containsKey(p)) streak.put(p, 0);
			}
			for (Player p : streak.keySet()) {
				if (!p.isOnline()) {
					to_remove.add(p);
				}
			}
			for (Player p : to_remove) {
				streak.remove(p);
			}
			to_remove.clear();
		}
	}
	
	public static void addKill(Player p) {
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
	
	public static void resetKill(Player p) {
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

		Settings settings = DataManager.getData(p).getSettings();
		
		switch(kills) {
		case 3:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+3 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b3er§7-Killstreak erzielt!");
			break;
		case 5:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+5 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
            LuckyPack.addChest(1,p);
            p.sendMessage("§2§lNormal-Pack erhalten");
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b5er§7-Killstreak erzielt!");
			break;
		case 8:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+8 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b8er§7-Killstreak erzielt!");
			break;
		case 10:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+10 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b10er§7-Killstreak erzielt!");
			break;
		case 15:
			if (settings.hasInfoEnabled()) {
				p.sendMessage("§2§lKillstreak §8>>> §b+15 §7Guncoins erhalten!");
				p.sendMessage("§2§lKillstreak §8>>> §7256xAmmo erhalten!");
			}
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			for (Player all : p.getWorld().getPlayers())
				if (DataManager.getData(all).getSettings().hasInfoEnabled())
					all.sendMessage("§2§lKillstreak §8>>> §a" + p.getName() + "§7 hat eine §b15er§7-Killstreak erzielt!");
			break;
		}
	}
	
}
