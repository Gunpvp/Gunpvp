package gunpvp.adventurerush;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import gunpvp.listener.Listener;
import gunpvp.util.Items;

public class AdventureRushZombieDeathListener extends Listener {
	
	@EventHandler
	public void onZombieDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Zombie && e.getEntity().getKiller() != null) {
			Player p = e.getEntity().getKiller();
			if (p.getWorld().getName().equals("AdventureRush")) {
				p.getInventory().addItem(Items.generate("§2§lAmmo", Material.SEEDS, 10));
			}
		}
	}
	
}
