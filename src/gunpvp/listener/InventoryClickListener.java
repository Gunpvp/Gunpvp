package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener extends Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() instanceof ItemStack) {
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lEinstellungen") ||
						e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lSpielmodus wählen") ||
						e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lZurück zur Lobby") ||
						e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lLucky Packs") ) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
