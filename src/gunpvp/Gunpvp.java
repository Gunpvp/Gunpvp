package gunpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import gunpvp.arcade.ArcadeSignUpdater;
import gunpvp.classic.ClassicSignUpdater;
import gunpvp.commands.Commands;
import gunpvp.listener.Listeners;
import gunpvp.util.Action;
import gunpvp.util.Database;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class Gunpvp extends JavaPlugin {
	
	private static Gunpvp plugin;
	
	/**
	 * 
	 * @author Bernhard Scharrer
	 * 
	 */
	public void onEnable() {
		
		for (Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("Server wird reloaded!");
		
		plugin = this;
		
		Database.init();
		Listeners.init();
		Commands.init();
		Locations.init();
		
		Timer.repeat(new Action() {
			public void perform() {
				ArcadeSignUpdater.update();
				ClassicSignUpdater.update();
			}
		}, 0, 10);
		
	}
	
	public static Gunpvp getPlugin() {
		return plugin;
	}
	
}
