package gunpvp.util;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import gunpvp.Titles;
import gunpvp.classic.ClassicItems;

public class Lobby {
	
	public static void send(Player p) {
		
		Titles.sendTitle(p, "§2Gunpvp", "§7Willkommen §a" + p.getName());
		for (int n = 0; n<100; n++) p.sendMessage("§a");
		p.sendMessage("§8§l\\______[§2§l===§8§l|§2§l===§8§l]______/");
		p.sendMessage("");
		p.sendMessage("  §2§lGunpvp   §8§l-   §7The Original");
		p.sendMessage("");
		p.sendMessage("§8§l\\______[§2§l===§8§l|§2§l===§8§l]______/");
		Titles.sendBar(p, "§a");
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
		if (p.isOp()) p.setPlayerListName("§c" + p.getName());
		else p.setPlayerListName("§a" + p.getName());
		
	}
	
	public static void reset(Player p) {
		
		p.teleport(Locations.LOBBY);
		p.setFireTicks(0);
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
		p.setHealth(20.0);
		p.setLevel(0);
		
		ClassicItems.removeFromClassic(p);
		
		p.setExp(0);
		p.setCompassTarget(Locations.LOBBY);
		p.getInventory().setHeldItemSlot(4);
		p.setGameMode(GameMode.SURVIVAL);
		
	}
	
	public static void giveItems(Player p) {
		
		p.getInventory().setItem(8, Items.generate("§b§lSpielmodus wählen", Material.COMPASS, 1, 0));
		p.getInventory().setItem(6, Items.generate("§b§lLucky Packs", Material.TRAPPED_CHEST, 1, 0));
		p.getInventory().setItem(7, Items.generate("§b§lEinstellungen", Material.IRON_INGOT, 1, 0));
	}
	
	
}
