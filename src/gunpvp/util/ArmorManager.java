package gunpvp.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorManager {

    public static ItemStack[] getArmorItemStackArr(int level) {
        ItemStack[] armor = new ItemStack[4];
        switch (level) {
		case 1:
            armor[0] = Items.generate("�2�lLvl-1-Armor", Material.LEATHER_BOOTS);
            armor[1] = Items.generate("�2�lLvl-1-Armor", Material.LEATHER_LEGGINGS);
            armor[2] = Items.generate("�2�lLvl-1-Armor", Material.LEATHER_CHESTPLATE);
            armor[3] = Items.generate("�2�lLvl-1-Armor", Material.LEATHER_HELMET);
            break;
		case 2:
            armor[0] = Items.generate("�2�lLvl-2-Armor", Material.GOLD_BOOTS);
            armor[1] = Items.generate("�2�lLvl-2-Armor", Material.GOLD_LEGGINGS);
            armor[2] = Items.generate("�2�lLvl-2-Armor", Material.GOLD_CHESTPLATE);
            armor[3] = Items.generate("�2�lLvl-2-Armor", Material.GOLD_HELMET);
            break;
		case 3:
            armor[0] = Items.generate("�2�lLvl-3-Armor", Material.IRON_BOOTS);
            armor[1] = Items.generate("�2�lLvl-3-Armor", Material.IRON_LEGGINGS);
            armor[2] = Items.generate("�2�lLvl-3-Armor", Material.IRON_CHESTPLATE);
            armor[3] = Items.generate("�2�lLvl-3-Armor", Material.IRON_HELMET);
            break;
		case 4:
            armor[0] = Items.generate("�2�lLvl-4-Armor", Material.DIAMOND_BOOTS);
            armor[1] = Items.generate("�2�lLvl-4-Armor", Material.DIAMOND_LEGGINGS);
            armor[2] = Items.generate("�2�lLvl-4-Armor", Material.DIAMOND_CHESTPLATE);
            armor[3] = Items.generate("�2�lLvl-4-Armor", Material.DIAMOND_HELMET);
            break;
		}
        return armor;
    }

    /**
     * @param armorType
     * @return
     */
    public static ItemStack[] getArmorItemStackArrByMaterial(String armorType) {
        int level = 1;
        String armorString = armorType.toString().toUpperCase();
        if (armorString.contains("LEATHER")) {
            level = 1;
        } else if (armorString.contains("GOLD")) {
            level = 2;
        } else if (armorString.contains("IRON")) {
            level = 3;
        } else if (armorString.contains("DIAMOND")) {
            level = 4;
        }
        return getArmorItemStackArr(level);
    }

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
