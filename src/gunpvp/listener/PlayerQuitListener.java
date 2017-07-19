package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import gunpvp.classic.ClassicItems;
import gunpvp.data.DataManager;
import gunpvp.inventories.Inventories;

public class PlayerQuitListener extends Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("�c<< �7" + e.getPlayer().getName());
		DataManager.remove(e.getPlayer());
		ClassicItems.removeFromClassic(e.getPlayer());
		if (!e.getPlayer().getWorld().getName().startsWith("Classic")) Inventories.saveInventory(e.getPlayer());
	}
	
}
