package gunpvp.arcade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import gunpvp.listener.Listener;

public class ArcadeClickListener extends Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld() == Bukkit.getWorld("Gunpvp")) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.WALL_SIGN) {
					ArcadeSignUpdater.update();
					Sign s = (Sign) e.getClickedBlock().getState();
					Arcade arc = null;
					switch (s.getLine(1)) {
					case "Grind": arc = new ArcadeGrind(); break;
					case "Raid": arc = new ArcadeRaid(); break;
					case "Carrier": arc = new ArcadeCarrier(); break;
					}
					if (arc != null) {
						arc.teleport(p);
						arc.equip(p);
						arc.deleteObject();
					}
				}
			}
		}
	}
}
