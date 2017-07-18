package gunpvp.arcade;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import de.ShortByte.sbTitleAPI.sbTitleAPI;
import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.data.Stats;
import gunpvp.listener.Listener;
import gunpvp.util.Action;
import gunpvp.util.ArmorManager;
import gunpvp.util.Autorespawn;
import gunpvp.util.Lobby;
import gunpvp.util.Locations;

public class ArcadeDeathListener extends Listener {

	private static final int KILL_REWARD = 5;

	/**
	 * kill player if he/she jumps into water
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld() == Locations.ARCADE_CARRIER && p.getGameMode() == GameMode.SURVIVAL) {
			if (p.getLocation().getBlockY() < 25) {
				if (p.getHealth() > 0.0) {
					p.damage(100.0);
					return;
				}
			}
			if (p.getWorld().getBlockAt(p.getLocation()).getType() == Material.WATER
					|| p.getWorld().getBlockAt(p.getLocation()).getType() == Material.STATIONARY_WATER) {
				if (p.getHealth() > 0.0) {
					p.damage(100.0);
					return;
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
		ArcadeKillstreak.resetKill(p);
		stats.addDeath();
		
		/**
		 * calc armor damage
		 */
		ArmorManager.damageArmor(p.getInventory());
		
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

		/**
		 * check if player has autorespawning enabled
		 */
		if (settings.hasAutoEnabled()) {

			Autorespawn.respawn(p, new Action() {
				public void perform() {
					
					Arcade arcade = null;
					if (p.getWorld() == Locations.ARCADE_GRIND) arcade = new Arcade1();
					if (p.getWorld() == Locations.ARCADE_GRIND) arcade = new Arcade2();
					if (p.getWorld() == Locations.ARCADE_GRIND) arcade = new Arcade3();
					if (arcade != null) {
						arcade.teleport(p);
						sbTitleAPI.clear(p);
						p.setGameMode(GameMode.SURVIVAL);
						arcade.equip(p);
						arcade.deleteObject();
					}
					
				}
			});
			
		}
		/**
		 * else do normal spawn reset
		 */
		else {
			
			Lobby.reset(p);
			Lobby.giveItems(p);
			
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
