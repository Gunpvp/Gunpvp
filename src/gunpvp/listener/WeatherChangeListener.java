package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener extends Listener {
	
	@EventHandler
	public void onDamage(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
}
