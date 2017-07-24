package gunpvp.adventurerush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class AdventureRushMaster {
	
	private static List<ARPlayer> players = new ArrayList<>();
	private static List<ARPlayer> to_remove = new ArrayList<>();
	
	public static void addARPlayer(Player p, int world) {
		
		players.add(new ARPlayer(p, world));
		
	}
	
	public static void update() {
		
		for (ARPlayer arp : players) {
			
			Player p = arp.getPlayer();
			
			if (p != null && p.isOnline()) {
				
				int world = (int) ((30+p.getLocation().getX())/1000);
				float percent = (float) ((p.getLocation().getX()%1000)/10f);
				
				arp.update(world, percent);
				
			} else {
				to_remove.add(arp);
			}
			
		}
		
	}
	
}
