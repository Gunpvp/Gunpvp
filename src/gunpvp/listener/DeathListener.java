package gunpvp.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import gunpvp.arcade.ArcadeDeathListener;
import gunpvp.classic.ClassicDeathListener;
import gunpvp.scoreboard.GunpvpScoreboard;
import gunpvp.util.Action;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class DeathListener extends Listener {
	
	private static DeathListener instance;
	
	public DeathListener() {
		instance = this;
	}
	
	
	@EventHandler (priority=EventPriority.LOWEST)
	public void onDamage(EntityDamageEvent e) {
		
		if (e.getCause() == DamageCause.FALL) {
			e.setCancelled(true);
			return;
		}
		
		if (e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.FIRE) e.setCancelled(true);
		
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
	
	@EventHandler (priority=EventPriority.LOWEST)
	public void onShoot(WeaponDamageEntityEvent e) {

		if (e.getPlayer().getWorld().getName().equals("Gunpvp")) {
			e.setCancelled(true);
		}

		Player k = e.getPlayer();
		
		if (e.getVictim() instanceof Player) {
			Player p = (Player) e.getVictim();
			
			if (calculateWeaponDamage(e, p)) executeVirtualDeath(p, k);
		}

	}

	private boolean calculateDamage(EntityDamageEvent e, Player p) {
		if (e.getFinalDamage() >= p.getHealth()) {
			
			e.setDamage(0);
			e.setCancelled(true);
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			
			return true;
			
		} else {
			return false;
		}
	}
	
	public void damagePlayer(double damage, Player p) {
		if (damage >= p.getHealth()) {
			
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			
			executeVirtualDeath(p, null);
			
		}
	}
	
	private boolean calculateWeaponDamage(WeaponDamageEntityEvent e, Player p) {
		
		double damage = e.getDamage();
		
		if (p.getInventory().getChestplate()!=null) {
			if (p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) damage *= 0.72f;
			if (p.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE) damage *= 0.56f;
			if (p.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) damage *= 0.40f;
			if (p.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) damage *= 0.52f;
			if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) damage *= 0.20f;
		}
		
		if (damage >= p.getHealth()) {
			
			e.setDamage(0);
			e.setCancelled(true);
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			
			return true;
			
		} else {
			return false;
		}
	}
	
	private void executeVirtualDeath(Player p, Player k) {
		
		if (p==k) k = null;
		
		p.playSound(p.getLocation(), Sound.ENTITY_IRONGOLEM_DEATH, 1, 1);
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 20));
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 20));
		p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100);
		
		if (k!= null) {
			k.playSound(k.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		}
		
		Player killer = k;
		Player player = p;
		
		Timer.delay(new Action() {
			public void perform() {
				if (p.getWorld().getName().startsWith("Arcade")) ArcadeDeathListener.onDeath(player, killer);
				if (p.getWorld().getName().startsWith("Classic")) ClassicDeathListener.onDeath(player, killer);
			}
		}, 0.1f);
		
		Timer.sync(new Action() {
			public void perform() {
				p.setVelocity(new Vector(0, 2, 0));
				GunpvpScoreboard.drawScoreBoard(player);
				if (killer != null) GunpvpScoreboard.drawScoreBoard(killer);
			}
		}, 0.05f);
		
	}
	
	public static DeathListener getInstance() {
		return instance;
	}
	
}
