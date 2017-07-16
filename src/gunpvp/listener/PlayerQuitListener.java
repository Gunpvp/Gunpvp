package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import gunpvp.data.DataManager;
import gunpvp.enderchest.EnderchestMaster;

public class PlayerQuitListener extends Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§c<< §7" + e.getPlayer().getName());
		DataManager.remove(e.getPlayer());
		
	}
	
}
