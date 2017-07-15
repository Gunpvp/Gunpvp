package gunpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import gunpvp.Titles;
import gunpvp.data.DataManager;
import gunpvp.settings.GunpvpScoreboard;
import gunpvp.util.Action;
import gunpvp.util.Console;
import gunpvp.util.Items;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class PlayerJoinListener extends Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		final Player p = event.getPlayer();
		
		event.setJoinMessage("§a>> §7" + p.getName());
		Console.info(p.getName() + " has joined to the server!");
		DataManager.add(p);
		
		Timer.delay(new Action() {
			public void perform() {
				
				reset(p);
				send(p);
				giveItems(p);
				
				GunpvpScoreboard.drawScoreBoard(p);
				
			}
		}, 0.1f);
	}
	
	private void send(Player p) {
		Titles.sendTitle(p, "§2Gunpvp");
		Titles.sendSubTitle(p, "§7Willkommen §a" + p.getName());
		for (int n = 0; n<100; n++) p.sendMessage("§a");
		p.sendMessage("§8§l\\______[§2§l===§8§l|§2§l===§8§l]______/");
		p.sendMessage("");
		p.sendMessage("  §2§lGunpvp   §8§l-   §7The Original");
		p.sendMessage("");
		p.sendMessage("§8§l\\______[§2§l===§8§l|§2§l===§8§l]______/");
		Titles.sendBar(p, "§a");
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 3, 1);
		if (p.isOp()) p.setPlayerListName("§c" + p.getName());
		else p.setPlayerListName("§a" + p.getName());
	}
	
	private void reset(Player p) {
		if (Locations.LOBBY!=null) p.teleport(Locations.LOBBY);
		p.setFireTicks(0);
		p.setHealth(p.getMaxHealth());
		p.setLevel(0);
		p.setExp(0);
		p.setCompassTarget(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 151.5, 0.5, 0, 0));
		p.getInventory().setHeldItemSlot(4);
		p.setGameMode(GameMode.SURVIVAL);
	}
	
	private void giveItems(Player p) {
		p.getInventory().setItem(8, Items.generate("§b§lSpielmodus wählen", Material.COMPASS, 1, 0));
		p.getInventory().setItem(7, Items.generate("§b§lEinstellungen", Material.IRON_INGOT, 1, 0));
	}
	
}
