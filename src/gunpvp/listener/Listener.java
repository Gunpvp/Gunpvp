package gunpvp.listener;

import gunpvp.Gunpvp;

public class Listener implements org.bukkit.event.Listener{
	
	public Listener() {
		Gunpvp.getPlugin().getServer().getPluginManager().registerEvents(this, Gunpvp.getPlugin());
	}
	
}
