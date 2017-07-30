package gunpvp.listener;

import gunpvp.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static gunpvp.permissions.PermissionHandler.getRankColor;
import static gunpvp.permissions.PermissionHandler.getRankEnum;

public class ChatListener extends Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		if (DataManager.getData(e.getPlayer()).getSettings().hasChatEnabled()) {
			String world = "";
			switch (e.getPlayer().getWorld().getName()) {
			case "Gunpvp": world = "Lobby"; break;
			case "ArcadeGrind": world = "Arcade-Grind"; break;
			case "ArcadeRaid": world = "Arcade-Raid"; break;
			case "ArcadeCarrier": world = "Arcade-Carrier"; break;
			case "ClassicBayview": world = "Classic-Bayview"; break;
			case "ClassicStudio": world = "Classic-Studio"; break;
			case "ClassicMeltdown": world = "Classic-Meltdown"; break;
			case "ZombieForest": world = "Zombie-Forest"; break;
			default: world = "Gunpvp"; break;
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (DataManager.getData(all).getSettings().hasChatEnabled()) {
					all.sendMessage("§8[" + getRankColor(getRankEnum(e.getPlayer().getUniqueId().toString())) + world + "§8] > §c" +
							getRankColor(getRankEnum(e.getPlayer().getUniqueId().toString())) +
                            e.getPlayer().getName() + "§8 : §7" + e.getMessage());
                }
            }
		} else e.getPlayer().sendMessage("§8[§2Gunpvp§8] §cDie CHAT-Funktion ist deaktiviert.");
	}
	
}
