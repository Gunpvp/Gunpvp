package gunpvp.classic;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Classic3 implements Classic {
	
	private static int classic = 3;
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
		World w = Bukkit.getWorld("ClassicMeltdown");
		Location loc0 = new Location(w, -1103.5, 37.5, -992.5, 135f, 4f);
		Location loc1 = new Location(w, -1095.5, 37.5, -1016.5, 0f, 0f);
		Location loc2 = new Location(w, -1125.5, 33.5, -1009.5, -80f, 0f);
		Location loc3 = new Location(w, -1103.5, 33.5, -970.5, 100f, 0f);
		Location loc4 = new Location(w, -1072.5, 37.5, -978.5, -145f, 0f);
		Location loc5 = new Location(w, -1025.5, 33.5, -963.5, 145f, 0f);
		Location loc6 = new Location(w, -1017.5 , 37.5, -994.5, 145f, 0f);
		Location loc7 = new Location(w, -1016.5, 33.5, -1016.5, 40f, 0f);
		Location loc8 = new Location(w, -1059.5, 37.5, -1017.5, 0f, 0f);
		Location loc9 = new Location(w, -1044.5, 37.5, -963.5, 125f, 0f);
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
