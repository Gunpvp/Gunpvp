package gunpvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {
	
	public static World GUNPVP;
	
	public static Location LOBBY;
	
	public static void init() {
		GUNPVP = Bukkit.getWorld("Gunpvp");
		
		LOBBY = new Location(GUNPVP, 0.5, 151.5, 0.5, 0, 0);
	}
	
}
