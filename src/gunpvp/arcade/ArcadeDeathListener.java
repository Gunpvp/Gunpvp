package gunpvp.arcade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import gunpvp.Titles;
import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.data.Stats;
import gunpvp.listener.DeathListener;
import gunpvp.listener.Listener;
import gunpvp.util.Action;
import gunpvp.util.ArmorManager;
import gunpvp.util.Autorespawn;
import gunpvp.util.Lobby;
import gunpvp.util.Locations;
import gunpvp.util.Timer;

public class ArcadeDeathListener extends Listener {

	private static final int KILL_REWARD = 5;
	private static List<String> water_timeout = new ArrayList<String>();
	
	/**
	 * kill player if he/she jumps into water
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld() == Locations.ARCADE_CARRIER && p.getGameMode() == GameMode.SURVIVAL && !water_timeout.contains(p.getName())) {
			if (p.getLocation().getBlockY() < 25) {
				DeathListener.getInstance().damagePlayer(100, p);
				water_timeout.add(p.getName());
				Timer.delay(new Action() {
					public void perform() {
						water_timeout.remove(p.getName());
					}
				}, 2f);
				return;
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
		ArcadeKillstreak.resetKill(p);
		stats.addDeath();
		
		/**
		 * calc armor damage
		 */
		ArmorManager.damageArmor(p.getInventory());
		
		/**
		 * sync with server
		 */
		Timer.sync(new Action() {
			public void perform() {
				/**
				 * drop items of inventory with some exceptions
				 */
				for (ItemStack item : p.getInventory()) {
					if (item != null) {
						if (
								item.getType() != Material.IRON_SPADE
								&& item.getType() != Material.SEEDS
								&& item.getType() != Material.BED
								&& item.getType() != Material.IRON_INGOT) {
							
							p.getWorld().dropItem(p.getLocation(), item);
							
						}
					}
				}
				
				/**
				 * clear inventory
				 */
				p.getInventory().clear();
			}
		}, 0.1f);

		/**
		 * check if player has autorespawning enabled
		 */
		if (settings.hasAutoEnabled()) {

			Autorespawn.respawn(p, new Action() {
				public void perform() {
					
					Timer.sync(new Action() {
						public void perform() {
							Arcade arcade = null;
							if (p.getWorld() == Locations.ARCADE_GRIND) arcade = new ArcadeGrind();
							if (p.getWorld() == Locations.ARCADE_RAID) arcade = new ArcadeRaid();
							if (p.getWorld() == Locations.ARCADE_CARRIER) arcade = new ArcadeCarrier();
							if (arcade != null) {
								arcade.teleport(p);
								arcade.equip(p);
								arcade.deleteObject();
								Titles.clear(p);
								p.setGameMode(GameMode.SURVIVAL);
							}
						}
					}, 0.1f);

				}
			});
			
		}
		/**
		 * else do normal spawn reset
		 */
		else {
			
			Timer.sync(new Action() {
				public void perform() {
					Lobby.reset(p);
					Lobby.giveItems(p);
				}
			}, 0.05f);
			
			stats.showStats(p);
			
		}
		
		/**
		 * if the killer is identifyable
		 */
		if (k != null) {
			
			/**
			 * increment killstreak
			 * add kill to killer
			 * add kill reward to killer money
			 * inform killer
			 */
			ArcadeKillstreak.addKill(k);
			Stats stats_killer = DataManager.getData(k).getStats();
			stats_killer.addKill();
			stats_killer.editMoney(KILL_REWARD);
			k.sendMessage("§aKillbonus §2>>> §b"+KILL_REWARD+" Guncoins erhalten");
			stats_killer.showStats(k);
			
			/**
			 * give player item reward
			 */
			ArcadeKillItemGetter.giveKillPresent(k);
			
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
