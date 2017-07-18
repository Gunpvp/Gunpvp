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

import de.ShortByte.sbTitleAPI.sbTitleAPI;

public class ArcadeGrind implements Arcade {
	
	private CSUtility csu = new CSUtility();
	private static int beforebeforelast = new Random().nextInt(10);
	private static int beforelast = new Random().nextInt(10);
	private static int last = new Random().nextInt(10);
	private static int r = new Random().nextInt(10);
	private static final int arcade = 1;
	
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
		sbTitleAPI.sendActionBar(p, "§e§lStandard-Equip erhalten!");
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
		
		World w = Bukkit.getWorld("ArcadeGrind");
		Location loc0 = new Location(w, -49.5, 15.5, -33.5, 180, 0);
		Location loc1 = new Location(w, -1.5, 17.5, 16.5, 132, 0);
		Location loc2 = new Location(w, -94.5, 17.5, 47.5, 160, 0);
		Location loc3 = new Location(w, -143.5, 17.5, -6.5, 140, 0);
		Location loc4 = new Location(w, -131.5, 15.5, -48.5, -180, 0);
		Location loc5 = new Location(w, -9.5, 14.5, -66.5, -110, -5);
		Location loc6 = new Location(w, 67.5, 14.5, -64.5, 65, -5);
		Location loc7 = new Location(w, -60.5, 13.5, -14.5, 150, -4);
		Location loc8 = new Location(w, -139.5, 17.5, 9.5, 105, 0);
		Location loc9 = new Location(w, -106.5, 18.5, -36.5, 35, 0);
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