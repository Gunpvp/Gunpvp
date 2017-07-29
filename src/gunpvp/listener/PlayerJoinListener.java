package gunpvp.listener;

import gunpvp.data.DataManager;
import gunpvp.data.PlayerData;
import gunpvp.inventories.Inventories;
import gunpvp.permissions.PermissionHandler;
import gunpvp.scoreboard.GunpvpScoreboard;
import gunpvp.util.Action;
import gunpvp.util.Console;
import gunpvp.util.Lobby;
import gunpvp.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import static gunpvp.permissions.PermissionHandler.getRankColor;
import static gunpvp.permissions.PermissionHandler.getRankEnum;

public class PlayerJoinListener extends Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		final Player p = event.getPlayer();
        event.setJoinMessage("");

		Timer.sync(new Action() {
			public void perform() {

                DataManager.add(p);

                PlayerData pd = DataManager.getData(p);

                PermissionHandler.addPlayer(p, pd.getRank().getRank());

                Console.info(getRankColor(getRankEnum(p.getUniqueId().toString())) +
                        p.getName() + "§a has joined to the server!");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("§a>> " + getRankColor(getRankEnum(p.getUniqueId().toString())) + p.getName());
                }

                Inventories.loadInventory(p);
				
				Lobby.reset(p);
				Lobby.send(p);
				Lobby.giveItems(p);
				
				GunpvpScoreboard.drawScoreBoard(p);
			}
		}, 0.1f);
		
	}
	
}
