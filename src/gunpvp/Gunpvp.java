package gunpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import gunpvp.adventurerush.AdventureRushMaster;
import gunpvp.adventurerush.AdventureRushSpawner;
import gunpvp.arcade.ArcadeKillstreak;
import gunpvp.arcade.ArcadeSignUpdater;
import gunpvp.classic.ClassicItems;
import gunpvp.classic.ClassicKillstreak;
import gunpvp.classic.ClassicSignUpdater;
import gunpvp.commands.Commands;
import gunpvp.inventories.Inventories;
import gunpvp.listener.Listeners;
import gunpvp.scoreboard.GunpvpScoreboard;
import gunpvp.util.Action;
import gunpvp.util.Console;
import gunpvp.util.Database;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class Gunpvp extends JavaPlugin {
	
	private static Gunpvp plugin;
	private static int change_time_task;
	
	/**
	 * 
	 * @author Bernhard Scharrer
	 * 
	 */
	public void onEnable() {
		
		for (Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("�8[�2Gunpvp�8] �7Server wird reloaded!");
		
		plugin = this;
		
		Database.init();
		Listeners.init();
		Commands.init();
		Locations.init();
		Inventories.init();
		AdventureRushSpawner.loadSpawnerFile();
		
		Timer.repeat(new Action() {
			public void perform() {
				ArcadeSignUpdater.update();
				ArcadeKillstreak.update();
				ClassicSignUpdater.update();
				ClassicKillstreak.update();
				GunpvpScoreboard.update();
				AdventureRushMaster.update();
			}
		}, 0, 1);
		
		change_time_task = Timer.repeat(() -> {
			
			if (Bukkit.getWorld("AdventureRush") != null) {
				Bukkit.getWorld("AdventureRush").setTime(18000);
				Timer.cancel(change_time_task);
				Console.info("Succesfully set time in map AdventureRush");
			}
			
		}, 20, 20);
		
	}
	
	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.getWorld().getName().startsWith("Classic")) Inventories.saveInventory(p);
			ClassicItems.removeFromClassic(p);
		}
	}
	
	public static Gunpvp getPlugin() {
		return plugin;
	}
	
}
