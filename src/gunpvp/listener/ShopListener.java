package gunpvp.listener;

import java.util.ArrayList;
import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shampaggon.crackshot.CSUtility;

import de.ShortByte.sbTitleAPI.sbTitleAPI;
import gunpvp.data.DataManager;
import gunpvp.data.Stats;
import gunpvp.settings.GunpvpScoreboard;

public class ShopListener extends Listener {
	
	private CSUtility csu = new CSUtility();
	private static LinkedList<Player> timeout = new LinkedList<Player>();
	
	@EventHandler
	public void onShop(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (!timeout.contains(p)) {
			timeout.add(p);
			if (p.getWorld() == Bukkit.getWorld("Gunpvp")) {
				if (e.getRightClicked() instanceof ItemFrame) {
					ItemFrame itemframe = (ItemFrame) e.getRightClicked();
					if (itemframe.getItem().getItemMeta() != null) {
						if (p.getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
							if (itemframe.getItem().hasItemMeta()) {
								switch (itemframe.getItem().getItemMeta().getDisplayName().replace('§', '&')) {
								case "&a&lAK-74 &8$-": unbuyableWeapon(p, "Diese Waffe kann nicht gekauft werden!"); break;
								case "&a&lM16 &8$14": buyWeapon(p, "M16", 14); break;
								case "&a&lL86 &8$12": buyWeapon(p, "L86", 12); break;
								case "&a&lL96 &8$35": buyWeapon(p, "L96", 35); break;
								case "&a&lBarret &8$32": buyWeapon(p, "Barret", 32); break;
								case "&a&lDragunov &8$34": buyWeapon(p, "Dragunov", 34); break;
								case "&a&lGL1 &8$15": buyWeapon(p, "GL1", 15); break;
								case "&a&lW1200 &8$22": buyWeapon(p, "W1200", 22); break;
								case "&a&lR870 &8$10": buyWeapon(p, "R870", 10); break;
								case "&a&lRPK &8$18": buyWeapon(p, "RPK", 18); break;
								case "&a&lG3 &8$20": buyWeapon(p, "G3", 20); break;
								case "&a&lSG550 &8$16": buyWeapon(p, "SG550", 16); break;
								case "&a&lRPG &8$50": buyWeapon(p, "RPG", 50); break;
								case "&a&lAT4 &8$40": buyWeapon(p, "AT4", 40); break;
								case "&a&lStinger &8$75": buyWeapon(p, "Stinger", 75); break;
								case "&a&lColt45 &8$7": buyWeapon(p, "Colt45", 7); break;
								case "&a&lDesertEagle &8$8": buyWeapon(p, "DesertEagle", 8); break;
								case "&a&lUSP &8$14": buyWeapon(p, "USP", 14); break;
								case "&2&l64x&a&lAmmo &8$5": buyItem(p, "§2§lAmmo", null, 64, Material.SEEDS, 5); break;
								case "&2&l1x&a&lRPG-Rocket &8$10": buyItem(p, "§2§lRPG-Rocket", null, 1, Material.PUMPKIN_SEEDS, 10); break;
								case "&2&l1x&a&lAT4-Rocket &8$5": buyItem(p, "§2§lAT4-Rocket", null, 1, Material.POTATO_ITEM, 5); break;
								case "&2&l1x&a&lStinger-Rocket &8$20": buyItem(p, "§2§lStinger-Rocket", null, 1, Material.BAKED_POTATO, 20); break;
								case "&2&l10x&a&lGas-Kanister &8$50": buyItem(p, "§2§lGas-Kanister", null, 10, Material.CARROT_ITEM, 50); break;
								case "&a&lMedipack &8$3": buyItem(p, "§2§lMedipack", null, 1, Material.APPLE, 3); break;
								
								case "&a&lJetpack &8$130": buyWeapon(p, "Jetpack", 130); break;
								case "&a&lFlammenwerfer &8$100": buyWeapon(p, "Flammenwerfer", 100); break;
								
								case "&a&lLvl-1-Armor &8$50": buyArmor(p, "Lvl-1", 50); break;
								case "&a&lLvl-2-Armor &8$75": buyArmor(p, "Lvl-2", 75); break;
								case "&a&lLvl-3-Armor &8$100": buyArmor(p, "Lvl-3", 100); break;
								case "&a&lLvl-4-Armor &8$150": buyArmor(p, "Lvl-4", 150); break;
								
								case "&a&lLuftschlag &8$20": buyWeapon(p, "Luftschlag", 20); break;
								case "&a&lMine &8$6": buyWeapon(p, "Mine", 6); break;
								case "&a&lSensormine &8$8": buyWeapon(p, "Sensormine", 8); break;
								case "&a&lC4 &8$25": buyWeapon(p, "C4", 25); break;
								case "&a&lSplittergranate &8$12": buyWeapon(p, "Splittergranate", 12); break;
								case "&a&lBlendgranate &8$6": buyWeapon(p, "Blendgranate", 6); break;
								case "&a&lGranate &8$4": buyWeapon(p, "Granate", 4); break;
							}
						}
					}
				}
			}
			timeout.remove(p);
		}
	}
	
	private void buyWeapon(final Player p, String weaponname, int prize) {
		Stats stats = DataManager.getData(p).getStats();
		if (stats.getMoney() >= prize) {
			stats.editMoney(-prize);
			sbTitleAPI.reset(p);
			sbTitleAPI.sendTitle(p, "§2" + weaponname + " §aerhalten!");
			sbTitleAPI.sendSubTitle(p, "§7Kontostand: §a$" + stats.getMoney());
			csu.giveWeapon(p, weaponname, 1);
			p.updateInventory();
			GunpvpScoreboard.drawScoreBoard(p);
		} else goOutOfMoney(p, prize-stats.getMoney());
	}
	
	public static void buyArmor(final Player p, String type, int prize) {
		Stats stats = DataManager.getData(p).getStats();
		if (stats.getMoney() >= prize) {
			stats.editMoney(-prize);
			sbTitleAPI.reset(p);
			sbTitleAPI.sendTitle(p, "§2" + type + "-Armor §aerhalten!");
			sbTitleAPI.sendSubTitle(p, "§7Kontostand: §a$" + stats.getMoney());
			
			switch (type) {
			case "Lvl-1":
				p.getInventory().addItem(getItem(Material.LEATHER_BOOTS, 1, 0, "§2§lLvl-1-Armor", null));
				p.getInventory().addItem(getItem(Material.LEATHER_LEGGINGS, 1, 0, "§2§lLvl-1-Armor", null));
				p.getInventory().addItem(getItem(Material.LEATHER_CHESTPLATE, 1, 0, "§2§lLvl-1-Armor", null));
				p.getInventory().addItem(getItem(Material.LEATHER_HELMET, 1, 0, "§2§lLvl-1-Armor", null));
				break;
			case "Lvl-2":
				p.getInventory().addItem(getItem(Material.GOLD_BOOTS, 1, 0, "§2§lLvl-2-Armor", null));
				p.getInventory().addItem(getItem(Material.GOLD_LEGGINGS, 1, 0, "§2§lLvl-2-Armor", null));
				p.getInventory().addItem(getItem(Material.GOLD_CHESTPLATE, 1, 0, "§2§lLvl-2-Armor", null));
				p.getInventory().addItem(getItem(Material.GOLD_HELMET, 1, 0, "§2§lLvl-2-Armor", null));
				break;
			case "Lvl-3":
				p.getInventory().addItem(getItem(Material.IRON_BOOTS, 1, 0, "§2§lLvl-3-Armor", null));
				p.getInventory().addItem(getItem(Material.IRON_LEGGINGS, 1, 0, "§2§lLvl-3-Armor", null));
				p.getInventory().addItem(getItem(Material.IRON_CHESTPLATE, 1, 0, "§2§lLvl-3-Armor", null));
				p.getInventory().addItem(getItem(Material.IRON_HELMET, 1, 0, "§2§lLvl-3-Armor", null));
				break;
			case "Lvl-4":
				p.getInventory().addItem(getItem(Material.DIAMOND_BOOTS, 1, 0, "§2§lLvl-4-Armor", null));
				p.getInventory().addItem(getItem(Material.DIAMOND_LEGGINGS, 1, 0, "§2§lLvl-4-Armor", null));
				p.getInventory().addItem(getItem(Material.DIAMOND_CHESTPLATE, 1, 0, "§2§lLvl-4-Armor", null));
				p.getInventory().addItem(getItem(Material.DIAMOND_HELMET, 1, 0, "§2§lLvl-4-Armor", null));
				break;
			}
			
			p.updateInventory();
			GunpvpScoreboard.drawScoreBoard(p);
		} else goOutOfMoney(p, prize-stats.getMoney());
	}
	
	private static ItemStack getItem(Material mat, int amount, int data, String name, ArrayList<String> lore) {
		ItemStack item = new ItemStack(mat, amount, (short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private void buyItem(final Player p, String itemname, ArrayList<String> lore, int amount, Material type, int prize) {
		Stats stats = DataManager.getData(p).getStats();
		if (stats.getMoney() >= prize) {
			stats.editMoney(-prize);
			sbTitleAPI.reset(p);
			sbTitleAPI.sendTitle(p, "§2" + amount + "x" + itemname + " §aerhalten!");
			sbTitleAPI.sendSubTitle(p, "§7Kontostand: §a$" + stats.getMoney());
			ItemStack item = new ItemStack(type, amount, (byte) 0);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(itemname);
			meta.setLore(lore);
			item.setItemMeta(meta);
			p.getInventory().addItem(item);
			p.updateInventory();
			GunpvpScoreboard.drawScoreBoard(p);
		} else goOutOfMoney(p, prize-stats.getMoney());
	}
	
	private void unbuyableWeapon(Player p, String text) {
		p.sendMessage("§8[§2Gunpvp§8] §c" +  text);
	}
	
	private static void goOutOfMoney(Player p,int toless) {
		sbTitleAPI.reset(p);
		sbTitleAPI.sendTitle(p, "§4Zu wenig Geld");
		sbTitleAPI.sendSubTitle(p, "§cDu hast um §4" + toless + "§c zu wenig.");
	}
	
}
