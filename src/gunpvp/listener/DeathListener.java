package gunpvp.listener;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import gunpvp.arcade.ArcadeDeathListener;
import gunpvp.classic.ClassicDeathListener;
import gunpvp.util.Locations;

public class DeathListener extends Listener {
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onDamage(EntityDamageEvent e) {
		
		if (e.getCause() == DamageCause.FALL) {
			e.setCancelled(true);
			return;
		}
		
		if (e.getEntity().getWorld().getName().equals(Locations.GUNPVP.getName())) {
			e.setCancelled(true);
		}
		
		if (e.getEntity() instanceof Player) {
			
			Player p = (Player) e.getEntity();
			
			if (e instanceof EntityDamageByEntityEvent) {
				
				EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) e;
				
				if (edbe.getDamager() instanceof Player) {
					
					Player k = (Player) edbe.getDamager();
					
					if (calculateDamage(e, p)) executeVirtualDeath(p, k);
					
				}
				
			} else {
				
				if (calculateDamage(e, p)) executeVirtualDeath(p, null);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onShoot(WeaponDamageEntityEvent e) {

		if (e.getPlayer().getWorld().getName().equals(Locations.GUNPVP.getName())) {
			e.setCancelled(true);
		}

		Player k = e.getPlayer();
		
		if (e.getVictim() instanceof Player) {
			Player p = (Player) e.getVictim();
			
			if (calculateWeaponDamage(e, p)) executeVirtualDeath(p, k);
		}

	}

	private boolean calculateDamage(EntityDamageEvent e, Player p) {
		if (e.getDamage() >= p.getHealth()) {
			
			e.setDamage(0);
			e.setCancelled(true);
			p.setHealth(p.getMaxHealth());
			
			return true;
			
		} else {
			return false;
		}
	}
	
	private boolean calculateWeaponDamage(WeaponDamageEntityEvent e, Player p) {
		if (e.getDamage() >= p.getHealth()) {
			
			e.setDamage(0);
			e.setCancelled(true);
			p.setHealth(p.getMaxHealth());
			
			return true;
			
		} else {
			return false;
		}
	}
	
	private void executeVirtualDeath(Player p, Player k) {
		
		if (p==k) k = null;
		
		p.playSound(p.getLocation(), Sound.IRONGOLEM_DEATH, 1, 1);
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 20));
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 20));
		p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100);
		
		if (k!= null) {
			k.playSound(k.getLocation(), Sound.LEVEL_UP, 1, 1);
		}
		
		if (p.getWorld().getName().startsWith("Arcade")) ArcadeDeathListener.onDeath(p, k);
		if (p.getWorld().getName().startsWith("Classic")) ClassicDeathListener.onDeath(p, k);
		
	}
	
}