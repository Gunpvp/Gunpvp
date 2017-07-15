package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener extends Listener {
	
	@EventHandler
	public void onChange(SignChangeEvent e) {
		for (int i = 0;i<4;i++) {
			String s = e.getLine(i).replace("&", "§");
			e.setLine(i, s);
		}
	}
	
}
