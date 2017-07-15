package gunpvp.arcade;

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
import gunpvp.data.Settings;
import gunpvp.data.Stats;
import gunpvp.listener.Listener;
import gunpvp.settings.GunpvpScoreboard;

public class ArcadeDeathListener extends Listener {

	private static HashMap<String, Integer> i = new HashMap<String, Integer>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld() == Bukkit.getWorld("ArcadeCarrier")) {
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if (p.getLocation().getBlockY() < 25) {
					if (p.getHealth() > 0.0) {
						p.setHealth(0.0);
					}
				}
				if (p.getWorld().getBlockAt(p.getLocation()).getType() == Material.WATER
						|| p.getWorld().getBlockAt(p.getLocation()).getType() == Material.STATIONARY_WATER) {
					if (p.getHealth() > 0.0) {
						p.setHealth(0.0);
					}
				}
			}
		}
	}

	public static void onDeath(final Player p, final Player k) {
		
		ArcadeKillstreak.resetKill(p);
		DataManager.getData(p).getStats().addDeath();
		
		for (ItemStack item : p.getInventory()) {
			if (item != null) {
				switch (item.getType()) {
				case LEATHER_BOOTS:
				case LEATHER_LEGGINGS:
				case LEATHER_CHESTPLATE:
				case LEATHER_HELMET:
				case CHAINMAIL_BOOTS:
				case CHAINMAIL_LEGGINGS:
				case CHAINMAIL_CHESTPLATE:
				case CHAINMAIL_HELMET:
				case GOLD_BOOTS:
				case GOLD_LEGGINGS:
				case GOLD_CHESTPLATE:
				case GOLD_HELMET:
				case IRON_BOOTS:
				case IRON_LEGGINGS:
				case IRON_CHESTPLATE:
				case IRON_HELMET:
				case DIAMOND_BOOTS:
				case DIAMOND_LEGGINGS:
				case DIAMOND_CHESTPLATE:
				case DIAMOND_HELMET:
					if (item.getDurability() + 50 < 0) {
						item.setDurability((short) 0);
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				default:
					item = null;
					break;
				}
			}
		}
		for (ItemStack item : p.getInventory()) {
			if (item != null) {
				if (item.getType() != Material.IRON_SPADE && item.getType() != Material.SEEDS
						&& item.getType() != Material.BED && item.getType() != Material.IRON_INGOT) {
					p.getWorld().dropItem(p.getLocation(), item);
				}
			}
		}
		for (ItemStack item : p.getInventory().getArmorContents()) {
			if (item != null) {
				switch (item.getType()) {
				case LEATHER_BOOTS:
				case LEATHER_LEGGINGS:
				case LEATHER_CHESTPLATE:
				case LEATHER_HELMET:
					if (item.getDurability() + 50 >= 80) {
						item = null;
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				case CHAINMAIL_BOOTS:
				case CHAINMAIL_LEGGINGS:
				case CHAINMAIL_CHESTPLATE:
				case CHAINMAIL_HELMET:
					if (item.getDurability() + 50 >= 250) {
						item = null;
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				case GOLD_BOOTS:
				case GOLD_LEGGINGS:
				case GOLD_CHESTPLATE:
				case GOLD_HELMET:
					if (item.getDurability() + 50 >= 100) {
						item = null;
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				case IRON_BOOTS:
				case IRON_LEGGINGS:
				case IRON_CHESTPLATE:
				case IRON_HELMET:
					if (item.getDurability() + 50 >= 400) {
						item = null;
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				case DIAMOND_BOOTS:
				case DIAMOND_LEGGINGS:
				case DIAMOND_CHESTPLATE:
				case DIAMOND_HELMET:
					if (item.getDurability() + 50 >= 500) {
						item = null;
					} else {
						item.setDurability((short) (item.getDurability() + 50));
					}
					break;
				default:
					item = null;
					break;
				}
			}
		}
		
		p.getInventory().clear();
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
			@Override
			public void run() {

				Stats stats = DataManager.getData(p).getStats();
				Settings settings = DataManager.getData(p).getSettings();

				if (!settings.hasAutoEnabled()) {
					p.teleport(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 153.5, 0.5, 0, 0));
					ItemStack item1 = new ItemStack(Material.COMPASS, 1, (byte) 0);
					ItemMeta meta1 = item1.getItemMeta();
					meta1.setDisplayName("§b§lSpielmodus wählen");
					meta1.setLore(null);
					item1.setItemMeta(meta1);
					p.getInventory().setItem(8, item1);
					ItemStack item2 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
					ItemMeta meta2 = item2.getItemMeta();
					meta2.setDisplayName("§b§lEinstellungen");
					meta2.setLore(null);
					item2.setItemMeta(meta2);
					p.getInventory().setItem(7, item2);
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
										x--;
										i.remove(p);
										i.put(p.getName(), x);
										sbTitleAPI.sendSubTitle(p, "§7" + x + "s");
									}
								}, 20L, 20L);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
							@Override
							public void run() {
								Bukkit.getScheduler().cancelTask(task);
								if (i.containsKey(p)) {
									i.remove(p);
									i.put(p.getName(), 5);
								}
								Arcade arc = null;
								switch (p.getWorld().getName()) {
								case "ArcadeGrind":
									arc = new Arcade1();
									break;
								case "ArcadeRaid":
									arc = new Arcade2();
									break;
								case "ArcadeCarrier": arc = new Arcade3(); break;
								}
								if (arc != null) {
									arc.teleport(p);
									sbTitleAPI.clear(p);
									p.setGameMode(GameMode.SURVIVAL);
									arc.equip(p);
								}
							}
						}, 100L);
					}
				}
				GunpvpScoreboard.drawScoreBoard(p);
			}
		}, 2L);
		if (k != null) {
			ArcadeKillstreak.addKill(k);

			Stats stats = DataManager.getData(k).getStats();

			DataManager.getData(k).getStats().addKill();
			DataManager.getData(k).getStats().editMoney(5);

			k.sendMessage("§aKillbonus §2>>> §b+5 Guncoins erhalten");
			ArcadeKillItemGetter.giveKillPresent(k);
			k.sendMessage("§8§l< §7§lK:§a" + stats.getKills() + "§8§l | §7§lD:§a" + stats.getDeaths()
					+ "§8§l | §7§lKD:§a" + stats.getKD() + "§8§l >");
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
