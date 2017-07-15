package gunpvp.arcade;

import org.bukkit.entity.Player;

public interface Arcade {
	
	public int getArcade();
	public void equip(Player p);
	public void teleport(Player p);
	public void deleteObject();
	
}
