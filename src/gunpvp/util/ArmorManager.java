package gunpvp.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorManager {
	
	public static void damageArmor(PlayerInventory inv) {
		for (ItemStack item : inv) {
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
		
		for (ItemStack item : inv.getArmorContents()) {
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
		
	}
	
}
