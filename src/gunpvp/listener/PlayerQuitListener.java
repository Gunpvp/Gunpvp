package gunpvp.listener;

import gunpvp.classic.ClassicItems;
import gunpvp.data.DataManager;
import gunpvp.inventories.Inventories;
import gunpvp.permissions.PermissionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§c<< §7" + e.getPlayer().getName());
		DataManager.remove(e.getPlayer());
		ClassicItems.removeFromClassic(e.getPlayer());
		Inventories.saveInventory(e.getPlayer());
        PermissionHandler.removePlayer(e.getPlayer());
    }
	
}
