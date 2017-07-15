package gunpvp.arcade;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shampaggon.crackshot.CSUtility;

public class ArcadeKillItemGetter {
	
	private static CSUtility csu = new CSUtility();
	
	public static void giveKillPresent(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		switch (new Random().nextInt(200)) {
		case 0: case 15: caseL96(p); break; case 1: case 16: caseBarret(p); break; case 2: case 17: caseDragunov(p); break; 
		case 3: case 18: caseGL1(p); break; case 4: case 19: caseW1200(p); break; case 5: case 20: caseR870(p); break;
		case 6: case 21: caseRPK(p); break; case 7: case 22: caseG3(p); break; case 8: case 23: caseSG550(p); break;
		case 9: case 24: caseColt45(p); break; case 10: case 25: caseDesertEagle(p); break; case 11: case 26: caseUSP(p); break;
		case 12: case 27: caseM16(p); break; case 13: case 28: caseL86(p); break; case 14: case 29:
			switch (new Random().nextInt(3)) {
			case 0: caseRPG(p); break;
			case 1: caseAT4(p); break;
			case 2: caseStinger(p); break;
			}
			break;
		default:
			p.sendMessage("§aKillbonus §2>>> §7128xAmmo erhalten");
			p.getInventory().addItem(ammo);
			p.getInventory().addItem(ammo);
			break;
		case 30: case 31: caseGranate(p); break;
		case 32: case 33: caseSplittergranate(p); break;
		case 34: case 35: caseBlendgranate(p); break;
		case 36: case 37: caseLuftschlag(p); break;
		case 38: case 39: caseMinen(p); break;
		case 40: case 41: caseC4(p); break;
		}
	}
	
	private static void caseL96(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7L96 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "L96", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseBarret(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7Barret erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "Barret", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseDragunov(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7Dragunov erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "Dragunov", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseGL1(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7GL1 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "GL1", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseW1200(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7W1200 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "W1200", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseR870(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7R870 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "R870", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseRPK(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7RPK erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "RPK", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseG3(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7G3 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "G3", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseSG550(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7SG550 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "SG550", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseColt45(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7Colt45 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "Colt45", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseDesertEagle(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7DesertEagle erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "DesertEagle", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseUSP(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7USP erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "USP", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseM16(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7M16 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "M16", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseL86(Player p) {
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7L86 erhalten");
		p.sendMessage("§aKillbonus §2>>> §764xAmmo erhalten");
		csu.giveWeapon(p, "L86", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseRPG(Player p) {
		ItemStack ammo = new ItemStack(Material.PUMPKIN_SEEDS, 5, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lRPG-Rocket");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7RPG-Launcher erhalten");
		p.sendMessage("§aKillbonus §2>>> §75xRPG-Ammo erhalten");
		csu.giveWeapon(p, "RPG", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseAT4(Player p) {
		ItemStack ammo = new ItemStack(Material.POTATO_ITEM, 15, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAT4-Rocket");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7AT4-Launcher erhalten");
		p.sendMessage("§aKillbonus §2>>> §715xAT4-Ammo erhalten");
		csu.giveWeapon(p, "AT4", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseStinger(Player p) {
		ItemStack ammo = new ItemStack(Material.BAKED_POTATO, 3, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lStinger-Rocket");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.sendMessage("§aKillbonus §2>>> §7Stinger-Launcher erhalten");
		p.sendMessage("§aKillbonus §2>>> §73xStinger-Ammo erhalten");
		csu.giveWeapon(p, "Stinger", 1);
		p.getInventory().addItem(ammo);
		p.updateInventory();
	}
	
	private static void caseGranate(Player p) {
		p.sendMessage("§aKillbonus §2>>> §78xGranaten erhalten");
		csu.giveWeapon(p, "Granate", 8);
		p.updateInventory();
	}
	
	private static void caseSplittergranate(Player p) {
		p.sendMessage("§aKillbonus §2>>> §72xSplittergranaten erhalten");
		csu.giveWeapon(p, "Splittergranate", 2);
		p.updateInventory();
	}
	
	private static void caseBlendgranate(Player p) {
		p.sendMessage("§aKillbonus §2>>> §75xBlendgranaten erhalten");
		csu.giveWeapon(p, "Blendgranate", 5);
		p.updateInventory();
	}
	
	private static void caseLuftschlag(Player p) {
		p.sendMessage("§aKillbonus §2>>> §7Luftschlag erhalten");
		csu.giveWeapon(p, "Luftschlag", 1);
		p.updateInventory();
	}
	
	private static void caseMinen(Player p) {
		p.sendMessage("§aKillbonus §2>>> §74xMinen erhalten");
		p.sendMessage("§aKillbonus §2>>> §72xSensorminen erhalten");
		csu.giveWeapon(p, "Mine", 4);
		csu.giveWeapon(p, "Sensormine", 2);
		p.updateInventory();
	}
	
	private static void caseC4(Player p) {
		p.sendMessage("§aKillbonus §2>>> §7C4 erhalten");
		csu.giveWeapon(p, "C4", 1);
		p.updateInventory();
	}
	
}
