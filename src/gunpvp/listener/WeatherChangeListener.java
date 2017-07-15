package gunpvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener {
	
	@EventHandler
	public void onDamage(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
}
