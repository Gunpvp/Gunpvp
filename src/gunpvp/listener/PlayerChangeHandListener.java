package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedMainHandEvent;

public class PlayerChangeHandListener extends Listener {
	
	@EventHandler
	public void onChangeHand(PlayerChangedMainHandEvent e) {
		e.getPlayer().sendMessage("Changed: " + e.getMainHand().toString());
	}
	
}
