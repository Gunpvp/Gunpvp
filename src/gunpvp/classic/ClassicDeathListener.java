package gunpvp.classic;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.ShortByte.sbTitleAPI.sbTitleAPI;
import gunpvp.Gunpvp;
import gunpvp.data.DataManager;
import gunpvp.listener.Listener;
import gunpvp.settings.GunpvpScoreboard;

public class ClassicDeathListener extends Listener {
	
	private HashMap<String, Integer> i = new HashMap<String, Integer>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)  {
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
		case "ClassicStudio": break;
		case "ClassicMeltdown":
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if (p.getLocation().getBlockY() < 27) {
					if (p.getHealth() > 0.0) {
						p.setHealth(0.0);
					}
				}
			}
			break;
		default: ClassicItems.removeFromClassic(p); break;
		}
	}
	
	@EventHandler
	public void onDeath(final EntityDeathEvent e) {
		Player p = null;
		Player k = null;
		if (e.getEntity() instanceof Player) {
			p = (Player) e.getEntity();
			ClassicKillstreak.resetKill(p);
			final Location loc = p.getLocation();
			if (p.getWorld() == Bukkit.getWorld("ClassicBayview") ||
					p.getWorld() == Bukkit.getWorld("ClassicStudio") ||
					p.getWorld() == Bukkit.getWorld("ClassicMeltdown")) {
				DataManager.getData(p).getStats().addDeath();
				p.getInventory().clear();
				if (p.getKiller() != null) k = p.getKiller();
				final Player p1 = p;
				Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
					@Override
					public void run() {
						if (!DataManager.getData(p1).getSettings().hasAutoEnabled()) {
							p1.teleport(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 153.5, 0.5, 90, 0));
							ItemStack item1 = new ItemStack(Material.COMPASS, 1, (byte) 0);
							ItemMeta meta1 = item1.getItemMeta();
							meta1.setDisplayName("§b§lSpielmodus wählen");
							meta1.setLore(null);
							item1.setItemMeta(meta1);
							p1.getInventory().setItem(8, item1);
							ItemStack item2 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
							ItemMeta meta2 = item2.getItemMeta();
							meta2.setDisplayName("§b§lEinstellungen");
							meta2.setLore(null);
							item2.setItemMeta(meta2);
							p1.getInventory().setItem(7, item2);
							p1.sendMessage("§8§l< §7§lK:§a" + GunpvpMySQL.getKills(p1) + "§8§l | §7§lD:§a" + GunpvpMySQL.getDeaths(p1) + "§8§l | §7§lKD:§a" + GunpvpMySQL.getKD(p1) + "§8§l >");
						} else {
							p1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
							p1.setGameMode(GameMode.SPECTATOR);
							loc.setY(loc.getY()+5);
							loc.setPitch(90);
							p1.teleport(loc);
							if (!i.containsKey(p1)) {
								i.put(p1.getName(), 5);
							}
							if (i.get(p1.getName()) == 5) {
								final Player p2 = p1;
								final int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Gunpvp, new Runnable() {
									@Override
									public void run() {
										sbTitleAPI.reset(p2);
										sbTitleAPI.sendTitle(p2, "§2§lAutorespawn");
										int x = i.get(p2.getName());
										x--;
										i.remove(p2);
										i.put(p2.getName(), x);
										sbTitleAPI.sendSubTitle(p2, "§7" + x + "s");
									}
								}, 20L, 20L);
								final Player p3 = p1;
								Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp, new Runnable() {
									@Override
									public void run() {
										Bukkit.getScheduler().cancelTask(task);
										sbTitleAPI.reset(p2);
										if (i.containsKey(p3)) {
											i.remove(p3);
											i.put(p3.getName(), 5);
										}
										String map = "Error";
										switch (p1.getWorld().getName()) {
										case "ClassicBayview": map = "Bayview"; break;
										case "ClassicStudio": map = "Studio"; break;
										case "ClassicMeltdown": map = "Meltdown"; break;
										}
										ClassicItems.equip(p1, ClassicItems.getKitFromClassic(p1), map);
									}
								}, 100L);
							}
						}
						GunpvpScoreboard.drawScoreBoard(p1);
					}
				}, 2L);
				if (k!=null) {
					final Player kfinal = k;
					ClassicKillstreak.addKill(k);
					GunpvpMySQL.addKill(k);
					GunpvpMySQL.editMoney(k, +5);
					ClassicKillBonus.givePlayerKillBonus(k);
					k.sendMessage("§8§l< §7§lK:§a" + GunpvpMySQL.getKills(k) + "§8§l | §7§lD:§a" + GunpvpMySQL.getDeaths(k) + "§8§l | §7§lKD:§a" + GunpvpMySQL.getKD(k) + "§8§l >");
					Bukkit.broadcastMessage("§8>>> §a" + k.getName() + "§7 tötete §c" + p.getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp, new Runnable() {
						@Override
						public void run() {
							GunpvpScoreboard.drawScoreBoard(kfinal);
						}
					}, 20L);
				} else {
					Bukkit.broadcastMessage("§8>>> §c" + p.getName() + "§7 starb");
				}
				e.getDrops().clear();
			}
		}
	}
	
}
