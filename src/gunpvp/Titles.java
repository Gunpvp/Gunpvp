package gunpvp;

import org.bukkit.entity.Player;

import de.ShortByte.sbTitleAPI.sbTitleAPI;

public class Titles {
	
	public static void sendBar(Player p, String msg) {
		sbTitleAPI.sendActionBar(p, msg);
	}

	public static void sendTitle(Player p, String msg) {
		sbTitleAPI.sendTitle(p, msg);
	}
	
	public static void sendSubTitle(Player p, String msg) {
		sbTitleAPI.sendSubTitle(p, msg);
	}
	
}
