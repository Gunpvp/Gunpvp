package gunpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class EntityDamageListener extends Listener {
	
	@EventHandler
	public void onExplode(ExplosionPrimeEvent e) {
		if (e.getEntity().getWorld() == Bukkit.getWorld("Gunpvp")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
				Player d = (Player) e.getDamager();
				if (d.getItemInHand() != null) {
					if (!(d.getItemInHand().getType() == Material.WOOD_SWORD
							|| d.getItemInHand().getType() == Material.IRON_SWORD
							|| d.getItemInHand().getType() == Material.DIAMOND_SWORD)) {
						e.setDamage(0.0);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity().getType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if (p.getWorld() == Bukkit.getWorld("ZombieForest")
					|| p.getWorld() == Bukkit.getWorld("ZombieForest")
					|| p.getWorld() == Bukkit.getWorld("ZombieForest")
					|| p.getWorld() == Bukkit.getWorld("ZombieForest")) {
				if (e.getDamager().getType() == EntityType.SNOWBALL) {
					e.setCancelled(true);
					e.setDamage(0);
				}
			}
			if (e.getDamager().getType() == EntityType.PLAYER) {
				if (p.getWorld() == Bukkit.getWorld("ZombieForest")
						|| p.getWorld() == Bukkit.getWorld("ZombieForest")
						|| p.getWorld() == Bukkit.getWorld("ZombieForest")
						|| p.getWorld() == Bukkit.getWorld("ZombieForest")) {
					e.setCancelled(true);
					e.setDamage(0);
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.DROWNING) {
			e.setCancelled(true);
		}
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getInventory().getHelmet() != null
					&& p.getInventory().getChestplate() != null
					&& p.getInventory().getLeggings() != null
					&& p.getInventory().getBoots() != null) {
				if (p.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET
						|| p.getInventory().getHelmet().getType() == Material.DIAMOND_CHESTPLATE
						|| p.getInventory().getHelmet().getType() == Material.DIAMOND_LEGGINGS
						|| p.getInventory().getHelmet().getType() == Material.DIAMOND_BOOTS) {
					e.setDamage(e.getDamage()+1);
				}
			}
		}
		if (e.getEntity().getWorld() == Bukkit.getWorld("Gunpvp")) {
			if (e instanceof EntityDamageByEntityEvent) {
				Player p = (Player) ((EntityDamageByEntityEvent) e).getDamager();
				if (p != null) {
					if (p.getGameMode() != GameMode.CREATIVE) {
						e.setCancelled(true);
						e.setDamage(0);
					}
				}
			}
		}
	}
	
}
