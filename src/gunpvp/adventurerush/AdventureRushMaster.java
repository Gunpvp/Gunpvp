package gunpvp.adventurerush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class AdventureRushMaster {
	
	private static List<ARPlayer> players = new ArrayList<>();
	private static List<ARPlayer> to_remove = new ArrayList<>();
	
	public static void addARPlayer(Player p, int world) {
		
		players.remove(getARP(p));
		players.add(new ARPlayer(p, world));
		
	}
	
	public static void removeARPlayer(ARPlayer arp) {

		players.remove(arp);
		
	}
	
	public static void update() {
		
		for (ARPlayer arp : players) {
			
			Player p = arp.getPlayer();
			
			if (p != null && p.getGameMode() == GameMode.SURVIVAL && p.isOnline()) {
				
				int world = (int) ((30+p.getLocation().getX())/1000);
				float percent = (float) ((p.getLocation().getX()%1000)/10f);
				
				arp.update(world, percent);
				
			} else {
				to_remove.add(arp);
			}
			
		}
		
	}
	
	public static ARPlayer getARP(Player p) {
		for (ARPlayer arp : players) if (arp.getPlayer().getName().equals(p.getName())) return arp; 
		return null;
	}
	
}
