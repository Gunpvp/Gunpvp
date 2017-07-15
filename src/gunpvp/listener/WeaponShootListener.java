package gunpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

import com.shampaggon.crackshot.events.WeaponShootEvent;

public class WeaponShootListener extends Listener {

	@EventHandler
	public void onShot(WeaponShootEvent e) {
		if (e.getPlayer().getWorld() == Bukkit.getWorld("Gunpvp")) {
			e.getProjectile().remove();
		}
	}
	
}
