package gunpvp.zombie;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Arena {
	
	private int wave;
	private World world;
	private List<ZombiePlayer> players;
	
	private ArenaStatus status;
	
	/**
	 * 
	 * init arena
	 * 
	 * @param world_name
	 */
	public Arena(String world_name) {
		this.wave = 0;
		this.world = Bukkit.getWorld(world_name);
		this.players = new ArrayList<>();
		
		this.status = ArenaStatus.READY_TO_JOIN;
	}
	
	/**
	 * 
	 * triggered when player click join sign
	 * 
	 * @param player
	 */
	public void join(Player p) {
		
		if (players.size() == 0) {
			
			if (status == ArenaStatus.READY_TO_JOIN) {
				this.wave = 1;
			}
			
		}
		
		ZombiePlayer zp = new ZombiePlayer(p);
		players.add(zp);
		
	}
	
	/**
	 * 
	 * triggered when player dies or leaves;
	 * 
	 * @param player
	 */
	public void quit(Player p) {
		players.remove(getZP(p));
	}
	
	/**
	 * @param player to find
	 * @return zombieplayer
	 */
	public ZombiePlayer getZP(Player p) {
		for (ZombiePlayer zp : players) {
			if (zp.getPlayer().getName().equals(p.getName())) return zp;
		}
		return null;
	}
	
	/**
	 * 
	 * reset arena and clear entities
	 * 
	 */
	public void reset() {
		
		this.status = ArenaStatus.RESETTING;
		
		for (Entity entity : world.getEntities()) {
			
			if (!(entity instanceof Player)) {
				
				entity.remove();
				
			}
			
		}
		
		this.wave = 0;
		this.status = ArenaStatus.READY_TO_JOIN;
		this.players.clear();
		
	}
	
	public int getWave() {
		return wave;
	}
	
	public ArenaStatus getStatus() {
		return status;
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
}
