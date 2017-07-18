package gunpvp.classic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.data.Stats;
import gunpvp.listener.Listener;
import gunpvp.util.Action;
import gunpvp.util.Autorespawn;
import gunpvp.util.Lobby;
import gunpvp.util.Locations;

public class ClassicDeathListener extends Listener {

	private static final int KILL_REWARD = 5;

	/**
	 * kill player if he/she jumps into water
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SURVIVAL) {
			
			if (p.getWorld() == Locations.CLASSIC_BAYVIEW)
				if (p.getLocation().getBlockY() < 8) p.damage(100);
			
			if (p.getWorld() == Locations.CLASSIC_MELTDOWN) {
				if (p.getLocation().getBlockY() < 27) {
					if (p.getHealth() > 0.0) p.setHealth(0.0);
				}
			}
			
		}
	}

	public static void onDeath(final Player p, final Player k) {

		/**
		 * get data from datamanager
		 */
		Stats stats = DataManager.getData(p).getStats();
		Settings settings = DataManager.getData(p).getSettings();
		
		/**
		 * reset killstreak and add death to player
		 */
		ClassicKillstreak.resetKill(p);
		stats.addDeath();

		p.getInventory().clear();
		
		/**
		 * check if player has autorespawning enabled
		 */
		if (settings.hasAutoEnabled()) {
			
			Autorespawn.respawn(p, new Action() {
				public void perform() {
					if (p.isOnline()) ClassicItems.equip(p, ClassicItems.getKitFromClassic(p), p.getWorld().getName());
				}
			});
			
		}
		/**
		 * else do normal spawn reset
		 */
		else {
			
			Lobby.reset(p);
			Lobby.giveItems(p);
			ClassicItems.removeFromClassic(p);
			
			stats.showStats(p);

		}
		
		if (k != null) {
			
			/**
			 * increment killstreak
			 * add kill to killer
			 * add kill reward to killer money
			 * inform killer 
			 */
			ClassicKillstreak.addKill(k);
			Stats stats_killer = DataManager.getData(k).getStats();
			stats.addKill();
			stats.editMoney(KILL_REWARD);
			k.sendMessage("§aKillbonus §2>>> §b"+KILL_REWARD+" Guncoins erhalten");
			ClassicKillBonus.givePlayerKillBonus(k);
			
			stats_killer.showStats(k);
			
			/**
			 * send broadcast msg
			 */
			Bukkit.broadcastMessage("§8>>> §a" + k.getName() + "§7 tötete §c" + p.getName());
			
		} else {
			
			/**
			 * send broadcast msg
			 */
			Bukkit.broadcastMessage("§8>>> §c" + p.getName() + "§7 starb");
			
		}
	}

}
