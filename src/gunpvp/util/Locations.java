package gunpvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {
	
	public static World GUNPVP;
	public static World ARCADE_GRIND;
	public static World ARCADE_RAID;
	public static World ARCADE_CARRIER;
	public static World CLASSIC_BAYVIEW;
	public static World CLASSIC_STUDIO;
	public static World CLASSIC_MELTDOWN;
	
	public static Location LOBBY;
	
	public static void init() {
		GUNPVP = Bukkit.getWorld("Gunpvp");
		ARCADE_GRIND = Bukkit.getWorld("ArcadeGrind");
		ARCADE_RAID = Bukkit.getWorld("ArcadeRaid");
		ARCADE_CARRIER = Bukkit.getWorld("ArcadeCarrier");
		CLASSIC_BAYVIEW = Bukkit.getWorld("ClassicBayview");
		CLASSIC_STUDIO = Bukkit.getWorld("ClassicStudio");
		CLASSIC_MELTDOWN = Bukkit.getWorld("ClassicMeltdown");
		
		LOBBY = new Location(GUNPVP, 0.5, 151.5, 0.5, 0, 0);
	}
	
}
