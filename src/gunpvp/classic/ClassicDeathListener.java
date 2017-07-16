package gunpvp.classic;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.ShortByte.sbTitleAPI.sbTitleAPI;
import gunpvp.Gunpvp;
import gunpvp.data.DataManager;
import gunpvp.data.Stats;
import gunpvp.listener.Listener;
import gunpvp.settings.GunpvpScoreboard;

public class ClassicDeathListener extends Listener {

	private static HashMap<String, Integer> i = new HashMap<String, Integer>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		switch (p.getWorld().getName()) {
		case "ClassicBayview":
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if (p.getLocation().getBlockY() < 8) {
					if (p.getHealth() > 0.0) {
						p.setHealth(0.0);
					}
				}
			}
			break;
		case "ClassicStudio":
			break;
		case "ClassicMeltdown":
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if (p.getLocation().getBlockY() < 27) {
					if (p.getHealth() > 0.0) {
						p.setHealth(0.0);
					}
				}
			}
			break;
		default:
			ClassicItems.removeFromClassic(p);
			break;
		}
	}

	public static void onDeath(final Player p, final Player k) {

		ClassicKillstreak.resetKill(p);
		DataManager.getData(p).getStats().addDeath();

		p.getInventory().clear();
		
		Stats stats = DataManager.getData(p).getStats();
		if (!DataManager.getData(p).getSettings().hasAutoEnabled()) {
			p.teleport(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 153.5, 0.5, 90, 0));
			ItemStack item1 = new ItemStack(Material.COMPASS, 1, (byte) 0);
			ItemMeta meta1 = item1.getItemMeta();
			meta1.setDisplayName("§b§lSpielmodus wählen");
			meta1.setLore(null);
			item1.setItemMeta(meta1);
			p.getInventory().setItem(8, item1);
			p.updateInventory();
			ItemStack item2 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName("§b§lEinstellungen");
			meta2.setLore(null);
			item2.setItemMeta(meta2);
			p.getInventory().setItem(7,item2);
			p.updateInventory();

			p.sendMessage("§8§l< §7§lK:§a" + stats.getKills() + "§8§l | §7§lD:§a" + stats.getDeaths()
					+ "§8§l | §7§lKD:§a" + stats.getKD() + "§8§l >");
		} else {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
			p.setGameMode(GameMode.SPECTATOR);
			p.getLocation().setY(p.getLocation().getY() + 5);
			p.getLocation().setPitch(90);
			p.teleport(p.getLocation());
			if (!i.containsKey(p)) {
				i.put(p.getName(), 5);
			}
			if (i.get(p.getName()) == 5) {
				final int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Gunpvp.getPlugin(),
						new Runnable() {
							@Override
							public void run() {
								sbTitleAPI.reset(p);
								sbTitleAPI.sendTitle(p, "§2§lAutorespawn");
								int x = i.get(p.getName());
								if (x>0) x--;
								i.remove(p);
								i.put(p.getName(), x);
								sbTitleAPI.sendSubTitle(p, "§7" + x + "s");
							}
						}, 20L, 20L);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
					@Override
					public void run() {
						Bukkit.getScheduler().cancelTask(task);
						sbTitleAPI.reset(p);
						if (i.containsKey(p)) {
							i.remove(p);
							i.put(p.getName(), 5);
						}
						String map = "Error";
						switch (p.getWorld().getName()) {
						case "ClassicBayview":
							map = "Bayview";
							break;
						case "ClassicStudio":
							map = "Studio";
							break;
						case "ClassicMeltdown":
							map = "Meltdown";
							break;
						}
						ClassicItems.equip(p, ClassicItems.getKitFromClassic(p), map);
					}
				}, 100L);
			}
		}
		GunpvpScoreboard.drawScoreBoard(p);
		
		if (k != null) {
			ClassicKillstreak.addKill(k);
			Stats stats_killer = DataManager.getData(k).getStats();
			stats.addKill();
			stats.editMoney(5);
			ClassicKillBonus.givePlayerKillBonus(k);
			k.sendMessage("§8§l< §7§lK:§a" + stats_killer.getKills() + "§8§l | §7§lD:§a" + stats_killer.getDeaths()
					+ "§8§l | §7§lKD:§a" + stats_killer.getKD() + "§8§l >");
			Bukkit.broadcastMessage("§8>>> §a" + k.getName() + "§7 tötete §c" + p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
				@Override
				public void run() {
					GunpvpScoreboard.drawScoreBoard(k);
				}
			}, 20L);
		} else {
			Bukkit.broadcastMessage("§8>>> §c" + p.getName() + "§7 starb");
		}
	}

}
