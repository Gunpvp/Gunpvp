package gunpvp.arcade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;

public class ArcadeSignUpdater {
	
	public static void update() {
		Sign s = null;
		if (Bukkit.getWorld("Gunpvp").getBlockAt(1, 151, 19) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(1, 151, 19).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(1, 151, 19).getState();
				s.setLine(2, Bukkit.getWorld("ArcadeGrind").getPlayers().size() +"/16");
				s.update();
			}
		}
		if (Bukkit.getWorld("Gunpvp").getBlockAt(0, 151, 19) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(0, 151, 19).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(0, 151, 19).getState();
				s.setLine(2, Bukkit.getWorld("ArcadeRaid").getPlayers().size() +"/16");
				s.update();
			}
		}
		if (Bukkit.getWorld("Gunpvp").getBlockAt(-1, 151, 19) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(-1, 151, 19).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(-1, 151, 19).getState();
				s.setLine(2, Bukkit.getWorld("ArcadeCarrier").getPlayers().size() +"/16");
				s.update();
			}
		}
	}
	
}
