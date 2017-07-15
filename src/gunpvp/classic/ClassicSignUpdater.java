package gunpvp.classic;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;

public class ClassicSignUpdater {
	
	public static void update() {
		Sign s = null;
		if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 1) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 1).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 1).getState();
				s.setLine(2, Bukkit.getWorld("ClassicBayview").getPlayers().size() +"/16");
				s.update();
			}
		}
		if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 0) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 0).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, 0).getState();
				s.setLine(2, Bukkit.getWorld("ClassicStudio").getPlayers().size() +"/16");
				s.update();
			}
		}
		if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, -1) != null) {
			if (Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, -1).getType() == Material.WALL_SIGN) {
				s=(Sign) Bukkit.getWorld("Gunpvp").getBlockAt(-19, 151, -1).getState();
				s.setLine(2, Bukkit.getWorld("ClassicMeltdown").getPlayers().size() +"/16");
				s.update();
			}
		}
	}
	
}
