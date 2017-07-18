package gunpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import gunpvp.arcade.ArcadeKillstreak;
import gunpvp.arcade.ArcadeSignUpdater;
import gunpvp.classic.ClassicKillstreak;
import gunpvp.classic.ClassicSignUpdater;
import gunpvp.commands.Commands;
import gunpvp.listener.Listeners;
import gunpvp.scoreboard.GunpvpScoreboard;
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
				ArcadeKillstreak.update();
				ClassicSignUpdater.update();
				ClassicKillstreak.update();
				GunpvpScoreboard.update();
			}
		}, 0, 1);
		
	}
	
	public static Gunpvp getPlugin() {
		return plugin;
	}
	
}
