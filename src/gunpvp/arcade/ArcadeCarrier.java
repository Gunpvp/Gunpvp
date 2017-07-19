package gunpvp.arcade;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shampaggon.crackshot.CSUtility;

import gunpvp.Titles;

public class ArcadeCarrier implements Arcade {
	
	private CSUtility csu = new CSUtility();
	private static int beforebeforelast = new Random().nextInt(10);
	private static int beforelast = new Random().nextInt(10);
	private static int last = new Random().nextInt(10);
	private static int r = new Random().nextInt(10);
	private static final int arcade = 3;
	
	@Override
	public int getArcade() {
		return arcade;
	}
	
	@Override
	public void equip(Player p) {
		p.getInventory().setItem(6,null);
		p.updateInventory();
		csu.giveWeapon(p, "AK-74", 1);
		csu.giveWeapon(p, "Granate", 2);
		ItemStack ammo = new ItemStack(Material.SEEDS, 64, (byte) 0);
		ItemMeta meta = ammo.getItemMeta();
		meta.setDisplayName("§2§lAmmo");
		meta.setLore(null);
		ammo.setItemMeta(meta);
		p.getInventory().addItem(ammo);
		p.getInventory().addItem(ammo);
		csu.giveWeapon(p, "Granate", 2);
		Titles.sendBar(p, "§e§lStandard-Equip erhalten!");
		p.getInventory().setItem(7, null);
		p.getInventory().setItem(8, null);
		ItemStack item1 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
		ItemMeta meta1 = item1.getItemMeta();
		meta1.setDisplayName("§b§lEinstellungen");
		meta1.setLore(null);
		item1.setItemMeta(meta1);
		p.getInventory().setItem(7, item1);
		p.updateInventory();
		ItemStack item2 = new ItemStack(Material.BED, 1, (byte) 0);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName("§b§lZurück zur Lobby");
		meta2.setLore(null);
		item2.setItemMeta(meta2);
		p.getInventory().setItem(8, item2);
		p.updateInventory();
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 100));
	}
	
	@Override
	public void teleport(Player p) {
		
		do {
			r = new Random().nextInt(10);
		} while(r == last  || r == beforelast || r == beforebeforelast);
		beforebeforelast = beforelast;
		beforelast = last;
		last = r;
		
		World w = Bukkit.getWorld("ArcadeCarrier");
		Location loc0 = new Location(w, -998.5, 70.5, -943.5, 180f, -4.5f);
		Location loc1 = new Location(w, -1003.5, 76.5, -937.5, 160f, 2.5f);
		Location loc2 = new Location(w, -1021.5, 48.5, -963.5, 180f, 0f);
		Location loc3 = new Location(w, -1025.5, 48.5, -998.5, 160f, 1f);
		Location loc4 = new Location(w, -959.5, 48.5, -983.5, 130f, 13.5f);
		Location loc5 = new Location(w, -948.5, 48.5, -951.5, 110f, -5.5f);
		Location loc6 = new Location(w, -967.5, 45.5, -937.5, 150f, -13.5f);
		Location loc7 = new Location(w, -989.5, 48.5, -975.5, 60f, 2f);
		Location loc8 = new Location(w, -1018.5, 49.5, -955.5, 100f, 2.5f);
		Location loc9 = new Location(w, -1053.5, 46.5, -950.5, 80f, 0f);
		switch(r) {
		case 0: p.teleport(loc0); break;
		case 1: p.teleport(loc1); break;
		case 2: p.teleport(loc2); break;
		case 3: p.teleport(loc3); break;
		case 4: p.teleport(loc4); break;
		case 5: p.teleport(loc5); break;
		case 6: p.teleport(loc6); break;
		case 7: p.teleport(loc7); break;
		case 8: p.teleport(loc8); break;
		case 9: p.teleport(loc9); break;
		default: p.teleport(loc1); break;
		}
	}
	
	@Override
	public void deleteObject() {
		csu = null;
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
