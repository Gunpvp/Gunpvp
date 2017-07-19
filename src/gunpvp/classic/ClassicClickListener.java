package gunpvp.classic;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import gunpvp.Gunpvp;
import gunpvp.Titles;
import gunpvp.data.Classic;
import gunpvp.data.DataManager;
import gunpvp.listener.Listener;
import gunpvp.scoreboard.GunpvpScoreboard;

public class ClassicClickListener extends Listener {
	
	private static final int PRIZE = 300;
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		//******************** SIGNCLICK ********************
		if (e.getPlayer().getWorld() == Bukkit.getWorld("Gunpvp")) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player p = e.getPlayer();
				if (p.getGameMode() == GameMode.SURVIVAL) {
					if (e.getClickedBlock() != null) {
						if (e.getClickedBlock().getType() == Material.CHEST) {
							e.setCancelled(true);
						}
						if (e.getClickedBlock().getType() == Material.WALL_SIGN) {
							if (((Sign) e.getClickedBlock().getState()).getLine(1).equalsIgnoreCase("Bayview") ||
									((Sign) e.getClickedBlock().getState()).getLine(1).equalsIgnoreCase("Studio") ||
									((Sign) e.getClickedBlock().getState()).getLine(1).equalsIgnoreCase("Meltdown")) {
								Sign s = (Sign) e.getClickedBlock().getState();
								p.openInventory(openInv(s.getLine(1), p));
							}
						}
					}
				}
			}
		}
	}
	
	//******************** PREPARE_INVENTORY-METHOD ********************
	private Inventory makeInventory(Inventory before, Player p) {
		Inventory inv = before;
		if (inv.getSize() == 27) {
			for (int x = 0 ; x < 27; x++) {
				if (x != 10 && x != 12 && x != 14 && x != 16 && x != 2 && x != 6 && x != 22) {
					int y = 7;
					if (x%2==0) y = 15; 
					ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) y);
					ItemMeta meta = item.getItemMeta();
					meta.setLore(null);
					meta.setDisplayName("§a");
					item.setItemMeta(meta);
					inv.setItem(x, item);
				}
			}
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			ItemStack item1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
			ItemMeta meta1 = item1.getItemMeta();
			lore.add("§aClick to play!");
			meta1.setLore(lore);
			
			lore.clear();
			ItemStack item2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
			ItemMeta meta2 = item2.getItemMeta();
			lore.add("§cClick to buy! $" + PRIZE);
			meta2.setLore(lore);
			
			Classic classic = DataManager.getData(p).getClassic();
			
			if (true) {
				meta1.setDisplayName("§2§lSoldier");
				item1.setItemMeta(meta1);
				inv.setItem(10, item1);
			}
			if (classic.getGunner()>0) {
				meta1.setDisplayName("§2§lGunner");
				item1.setItemMeta(meta1);
				inv.setItem(2, item1);
			} else {
				meta2.setDisplayName("§4§lGunner");
				item2.setItemMeta(meta2);
				inv.setItem(2, item2);
			}
			if (classic.getRambo()>0) {
				meta1.setDisplayName("§2§lRambo");
				item1.setItemMeta(meta1);
				inv.setItem(12, item1);
			} else {
				meta2.setDisplayName("§4§lRambo");
				item2.setItemMeta(meta2);
				inv.setItem(12, item2);
			}
			if (classic.getPyro()>0) {
				meta1.setDisplayName("§2§lPyro");
				item1.setItemMeta(meta1);
				inv.setItem(22, item1);
			} else {
				meta2.setDisplayName("§4§lPyro");
				item2.setItemMeta(meta2);
				inv.setItem(22, item2);
			}
			if (classic.getJugger()>0) {
				meta1.setDisplayName("§2§lJuggernaut");
				item1.setItemMeta(meta1);
				inv.setItem(14, item1);
			} else {
				meta2.setDisplayName("§4§lJuggernaut");
				item2.setItemMeta(meta2);
				inv.setItem(14, item2);
			}
			if (classic.getHealer()>0) {
				meta1.setDisplayName("§2§lHealer");
				item1.setItemMeta(meta1);
				inv.setItem(6, item1);
			} else {
				meta2.setDisplayName("§4§lHealer");
				item2.setItemMeta(meta2);
				inv.setItem(6, item2);
			}
			if (classic.getBomber()>0) {
				meta1.setDisplayName("§2§lBomber");
				item1.setItemMeta(meta1);
				inv.setItem(16, item1);
			} else {
				meta2.setDisplayName("§4§lBomber");
				item2.setItemMeta(meta2);
				inv.setItem(16, item2);
			}
			
		}
		return inv;
	}
	
	//******************** INVENTORY-METHOD ********************
	private Inventory openInv(String map, Player p) {
		Inventory inv = null;
		String invname = "Error happens :/";
		switch (map.toLowerCase()) {
		case "bayview":
			invname = "Bayview";
			inv = Bukkit.createInventory(null, 27, "Classic - " + invname);
			break;
		case "studio":
			invname = "Studio";
			inv = Bukkit.createInventory(null, 27, "Classic - " + invname);
			break;
		case "meltdown":
			invname = "Meltdown";
			inv = Bukkit.createInventory(null, 27, "Classic - " + invname);
			break;
		}
		inv = makeInventory(inv, p);
		return inv;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		//******************** KIT CHOOSE ********************
		if (e.getWhoClicked().getWorld() == Bukkit.getWorld("Gunpvp")) {
			if (e.getWhoClicked() instanceof Player) {
				final Player p = (Player) e.getWhoClicked();
				if (p.getGameMode() == GameMode.SURVIVAL) {
					if (e.getInventory().getName().contains("Classic")) {
						e.setCancelled(true);
						if (e.getCurrentItem() != null) {
							if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
								if (e.getCurrentItem().getItemMeta() != null) {
									String type = "Error";
									if (e.getInventory().getName().contains("Bayview")) type = "Bayview";
									if (e.getInventory().getName().contains("Studio")) type = "Studio";
									if (e.getInventory().getName().contains("Meltdown")) type = "Meltdown";
									//******************** PLAY ********************
									if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§2")) {
										String kit = "Error-Kit";
										switch (e.getSlot()) {
										case 10: kit = "Soldier"; break;
										case 2: kit = "Gunner"; break;
										case 6: kit = "Healer"; break;
										case 12: kit = "Rambo"; break;
										case 14: kit = "Jugger"; break;
										case 16: kit = "Bomber"; break;
										case 22: kit = "Pyro"; break;
										}
										ClassicItems.equip(p, kit, type);
									}
									//******************** BUY ********************
									if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§4")) {
										final String map = type;
										if (DataManager.getData(p).getStats().getMoney() >= PRIZE) {
											String kit = "Error-Kit";
											String name = "Error-Name";
											switch (e.getSlot()) {
											case 2: name = "Gunner"; kit = "Gunner"; break;
											case 6: name = "Healer"; kit = "Healer"; break;
											case 12: name = "Rambo"; kit = "Rambo"; break;
											case 14: name = "Juggernaut"; kit = "Jugger"; break;
											case 16: name = "Bomber"; kit = "Bomber"; break;
											case 22: name = "Pyro"; kit = "Pyro"; break;
											}
											DataManager.getData(p).getStats().editMoney(-PRIZE);
											GunpvpScoreboard.drawScoreBoard(p);
											Titles.clear(p);
											Titles.sendTitle(p, "§2" + name + "-Kit §afreigeschaltet", "§7Kontstand§8: §a" + DataManager.getData(p).getStats().getMoney());
											Classic.enableKit(kit, p);
											e.getView().close();
										} else {
											Titles.clear(p);
											Titles.sendTitle(p, "§4Zu wenig Geld", "§7Kontstand§8: §c" + DataManager.getData(p).getStats().getMoney());
											e.getView().close();
										}
										Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
											@Override
											public void run() {
												p.openInventory(openInv(map, p));
											}
										}, 40L);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
}
