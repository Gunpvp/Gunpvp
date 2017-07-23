package gunpvp.classic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import gunpvp.Titles;
import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.data.Stats;
import gunpvp.inventories.Inventories;
import gunpvp.listener.DeathListener;
import gunpvp.listener.Listener;
import gunpvp.util.Action;
import gunpvp.util.Autorespawn;
import gunpvp.util.Lobby;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class ClassicDeathListener extends Listener {

	private static final int KILL_REWARD = 5;
	private static List<String> water_timeout = new ArrayList<String>();
	/**
	 * kill player if he/she jumps into water
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SURVIVAL && !water_timeout.contains(p.getName())) {
			
			if (p.getWorld() == Locations.CLASSIC_BAYVIEW)
				if (p.getLocation().getBlockY() < 8) {
					water_timeout.add(p.getName());
					DeathListener.getInstance().damagePlayer(100, p);
					Timer.delay(new Action() {
						public void perform() {
							water_timeout.remove(p.getName());
						}
					}, 0.5f);
				}
			
			if (p.getWorld() == Locations.CLASSIC_MELTDOWN) {
				if (p.getLocation().getBlockY() < 27) {
					DeathListener.getInstance().damagePlayer(100, p);
					water_timeout.add(p.getName());
					Timer.delay(new Action() {
						public void perform() {
							water_timeout.remove(p.getName());
						}
					}, 0.5f);
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
					if (p.isOnline()) {
						Timer.sync(new Action() {
							public void perform() {
								Classic classic = null;
								if (p.getWorld().getName().endsWith("Bayview")) classic = new ClassicBayview();
								if (p.getWorld().getName().endsWith("Studio")) classic = new ClassicStudio();
								if (p.getWorld().getName().endsWith("Meltdown")) classic = new ClassicMetldown();
								if (classic != null) {
									classic.teleport(p);
									Titles.clear(p);
									p.setGameMode(GameMode.SURVIVAL);
									ClassicItems.equip(p, ClassicItems.getKitFromClassic(p), p.getWorld().getName());
									classic.deleteObject();
								} 
							}
						}, 0.1f);
					}
				}
			});
			
		}
		/**
		 * else do normal spawn reset
		 */
		else {
			
			Inventories.loadInventory(p);
			
			Timer.sync(new Action() {
				public void perform() {
					Lobby.reset(p);
					Lobby.giveItems(p);
				}
			}, 0.05f);

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
			stats_killer.addKill();
			stats_killer.editMoney(KILL_REWARD);
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
