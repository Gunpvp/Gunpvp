package gunpvp.classic;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ClassicBayview implements Classic {
	
	private static int classic = 1;
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
		World w = Bukkit.getWorld("ClassicBayview");
		Location loc0 = new Location(w, -118.5, 8.9, -20.5, -85f, 0.0f);
		Location loc1 = new Location(w, -15.5, 21.4, -4.5, 0f, 0.0f);
		Location loc2 = new Location(w, 36.5, 13.4, -34.5, 35f, 0.0f);
		Location loc3 = new Location(w, -45.5, 15.4, -86.5, -45f, 0.0f);
		Location loc4 = new Location(w, -38.5, 19.4, -39.5, 0f, 0.0f);
		Location loc5 = new Location(w, -23.5, 13.4, -36.5, -95f, 0.0f);
		Location loc6 = new Location(w, 40.5, 18.4, -58.5, 165f, 0.0f);
		Location loc7 = new Location(w, 28.5, 13.4, -31.5 ,175f, 0.0f);
		Location loc8 = new Location(w, 0.5, 14.5, -18.5, 35f, 0.0f);
		Location loc9 = new Location(w, 10.5, 16.4, -73.5, 175f, 0.0f);
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
