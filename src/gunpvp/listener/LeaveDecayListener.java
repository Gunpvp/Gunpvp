package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeaveDecayListener extends Listener {
	
	@EventHandler
	public void onDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

}
