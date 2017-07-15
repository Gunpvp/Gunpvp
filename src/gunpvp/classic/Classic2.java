package gunpvp.classic;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Classic2 implements Classic {
	
	private static int classic = 2;
	private static int beforebeforelast = new Random().nextInt(10);
	private static int beforelast = new Random().nextInt(10);
	private static int last = new Random().nextInt(10);
	private static int r = new Random().nextInt(10);
	
	@Override
	public int getClassic() {
		return classic;
	}
	
	@Override
	public void teleport(Player p) {
		do {
			r = new Random().nextInt(10);
		} while(r == last  || r == beforelast || r == beforebeforelast);
		beforebeforelast = beforelast;
		beforelast = last;
		last = r;
		World w = Bukkit.getWorld("ClassicStudio");
		Location loc0 = new Location(w, -1092.5, 12.3, 898.5, 165f, 0.0f);
		Location loc1 = new Location(w, -1112.5, 15, 900.5, -90f, 10.0f);
		Location loc2 = new Location(w, -1169.5, 13.5, 907.5, -170f, 0.0f);
		Location loc3 = new Location(w, -1166.5, 10.5, 883.55, -15f, 0.0f);
		Location loc4 = new Location(w, -1124.5, 16.7, 914.5, -55f, 0.0f);
		Location loc5 = new Location(w, -1124.5, 16, 864.5, 125f, 10f);
		Location loc6 = new Location(w, -1135.5, 19.5, 892.5, -65f, 0.0f);
		Location loc7 = new Location(w, -1101.5, 12.5, 936.5, 100f, 0.0f);
		Location loc8 = new Location(w, -1185.5, 10.5, 885.5, -140f, 0.0f);
		Location loc9 = new Location(w, -1150.5, 11.5, 858.5, 90f, 10.0f);
		switch(r) {
		case 0: p.teleport(loc0); break;
		case 1: p.teleport(loc1); break;
		case 2: p.teleport(loc2); break;
		case 3: p.teleport(loc3); break;
		case 4: p.teleport(loc4); break;
		case 5: p.teleport(loc5); break;
		case 6: p.teleport(loc6); break;
		case 7: p.teleport(loc7); break;
		case 8: p.teleport(loc8); break;
		case 9: p.teleport(loc9); break;
		default: p.teleport(loc1); break;
		}
	}
	
	@Override
	public void deleteObject() {
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
