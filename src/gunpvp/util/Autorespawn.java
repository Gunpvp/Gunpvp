package gunpvp.util;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.ShortByte.sbTitleAPI.sbTitleAPI;

public class Autorespawn {
	
	/**
	 * this map represents all players that are currently autorespawning
	 * String... name of player
	 * Integer... seconds until he/she respawns
	 */
	private static HashMap<String, Integer> autorespawn = new HashMap<String, Integer>();
	
	/**
	 * represents respawn timeout
	 */
	private static final int SECONDS = 5;
	
	public static void respawn(Player p, Action on_respawn) {
		
		/**
		 * bind respawn effects to player
		 */
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
		p.setGameMode(GameMode.SPECTATOR);
		p.getLocation().setY(p.getLocation().getY() + 5);
		p.getLocation().setPitch(90);
		
		/**
		 * add player into map
		 */
		if (!autorespawn.containsKey(p)) {
			autorespawn.put(p.getName(), SECONDS);
		}
		
		/**
		 * start one second repeating timer
		 */
		final int task = Timer.repeat(new Action() {
			public void perform() {
				
				int time_remaining = Math.max(0, autorespawn.get(p.getName()) - 1);
				autorespawn.remove(p);
				autorespawn.put(p.getName(), time_remaining);
				
				sbTitleAPI.reset(p);
				sbTitleAPI.sendTitle(p, "§2§lAutorespawn");
				sbTitleAPI.sendSubTitle(p, "§7" + time_remaining + "s");
				
			}
		}, 0f, 1f);
		
		/**
		 * start timer for respawning
		 */
		Timer.delay(new Action() {
			public void perform() {
				
				Timer.cancel(task);
				
				autorespawn.remove(p);
				
				on_respawn.perform();
				
			}
		}, SECONDS);
		
	}
	
}
