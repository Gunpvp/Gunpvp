package gunpvp.adventurerush;

import org.bukkit.entity.Player;

public class ARPlayer {
	
	private Player player;
	private float percent;
	private int world;
	
	public ARPlayer(Player player, int world) {
		this.player = player;
		this.world = world;
		this.percent = 0;
	}

	public Player getPlayer() {
		return player;
	}

	public float getPercent() {
		return percent;
	}

	public int getWorld() {
		return world;
	}
	
	public void update(int world, float percent) {
		if (((int)percent)>((int)this.percent)) {
			spawnCreatures(percent);
			this.percent = percent;
		}
	}
	
	public void spawnCreatures(float percent) {
		if (percent>=2) {
			
			//TODO summon zombie 
			
		}
	}

}
