package gunpvp.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	
	private static ItemStack stack;
	private static ItemMeta meta;
	private static List<String> lore;

	public static ItemStack generate(String name, Material type, int amount, int data, String... description) {
		stack = new ItemStack(type, amount, (byte) data);
		meta = stack.getItemMeta();
		lore = new ArrayList<>();
		for (int n=0;n < description.length;n++) lore.add(description[n]);
		meta.setDisplayName(name);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		return stack;
	}
	
	public static ItemStack generate(String name, Material type, int amount, String... description){
	    return generate(name,type,amount,0,description);
    }
	
	public static ItemStack generate(String name, Material type){
	    return generate(name,type,1,0);
    }
	
}
