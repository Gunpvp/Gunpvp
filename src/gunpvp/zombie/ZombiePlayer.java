package gunpvp.zombie;

import org.bukkit.entity.Player;

public class ZombiePlayer {
	
	private int entity_kills;
	private int boss_kills;
	private Player p;
	
	public ZombiePlayer(Player p) {
		this.entity_kills = 0;
		this.boss_kills = 0;
		this.p = p;
	}

	public int getEntityKills() {
		return entity_kills;
	}

	public int getBossKills() {
		return boss_kills;
	}

	public Player getPlayer() {
		return p;
	}
	
}
