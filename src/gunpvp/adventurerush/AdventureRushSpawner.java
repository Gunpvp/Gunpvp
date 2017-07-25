package gunpvp.adventurerush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;

public class AdventureRushSpawner {
	
	private static List<SpawnLocation> spawn_locs = new ArrayList<>();
	private static World world = Bukkit.getWorld("AdventureRush");
	
	/**
	 * @param world
	 * @param percent
	 */
	public static void spawnCreatures(Player p, int world, float percent) {
		
		for (SpawnLocation spawn_loc : spawn_locs) {
			int x = ((int)spawn_loc.getLocation().getX());
			int player_x = (int) (percent*10);
			
			if (x/2000 == world-1) {
				
				if (x > player_x+20 && x <= (player_x+30)) {
					
					spawnCreature(p, spawn_loc, percent);
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * 
	 */
	public static void spawnCreature(Player p, SpawnLocation loc, float percent) {
		switch (loc.getMob()) {
		case "ZOMBIE":
			Zombie zombie = (Zombie) world.spawnEntity(loc.getLocation(), EntityType.ZOMBIE);
			zombie.setHealth(3+percent*0.05f);
			zombie.setTarget(p);
			zombie.getEquipment().setItemInMainHand(null);
			break;
		case "SKELETON":
			Skeleton skeleton = (Skeleton) world.spawnEntity(loc.getLocation(), EntityType.SKELETON);
			skeleton.setHealth(3+percent*0.05f);
			skeleton.setTarget(p);
			break;
		case "SPIDER":
			Spider spider = (Spider) world.spawnEntity(loc.getLocation(), EntityType.SPIDER);
			spider.setHealth(3+percent*0.05f);
			spider.setTarget(p);
			break;
		}
	}
	
	/**
	 * @param loc
	 * @param mob
	 */
	public static void addSpawner(Location loc, String mob) {
		spawn_locs.add(new SpawnLocation(loc, mob));
		saveSpawnerFile();
		loadSpawnerFile();
	}
	
	/**
	 * saves spawnlocs into file
	 */
	public static void saveSpawnerFile() {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("plugins/Gunpvp/spawnlocs.csv")));
			
			for (SpawnLocation spawn_loc : spawn_locs) {
				writer.write(spawn_loc.getLocation().getX()+";"+spawn_loc.getLocation().getY()+";"+spawn_loc.getLocation().getZ()+";"+spawn_loc.getMob().toUpperCase()+"\n");
				writer.flush();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * loads spawnlocs into file
	 */
	public static void loadSpawnerFile() {
		
		spawn_locs.clear();
		
		try {
			World arworld = Bukkit.getWorld("AdventureRush");
			BufferedReader reader = new BufferedReader(new FileReader(new File("plugins/Gunpvp/spawnlocs.csv")));
			String line;
			String parts[];
			
			while ((line = reader.readLine()) != null) {
				parts = line.split(";");
				if (parts.length == 4) {
					spawn_locs.add(new SpawnLocation(new Location(arworld, Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2])), parts[3]));
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @author bernh
	 *
	 */
	private static class SpawnLocation {
		
		private Location loc;
		private String mob;
		
		public SpawnLocation(Location loc, String mob) {
			this.loc = loc;
			this.mob = mob;
		}

		public Location getLocation() {
			return loc;
		}

		public String getMob() {
			return mob;
		}
		
	}
	
}
