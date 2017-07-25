package gunpvp.data;

import org.bukkit.entity.Player;

import gunpvp.util.Database;

public class AdventureRush {
	
	private int world;
	private int steps;
	
	public AdventureRush(int world, int steps) {
		this.world = world;
		this.steps = steps;
	}

	public int getWorld() {
		return world;
	}

	public int getSteps() {
		return steps;
	}
	
	public void setSteps(Player p, float percent) {
		this.steps = (int) (percent * 100);
		Database.execute("UPDATE `GUNPVP_ADVENTURERUSH` SET `STEPS` = "+(this.steps)+" WHERE `UUID` = '"+p.getUniqueId().toString()+"'");
	}
	
}
