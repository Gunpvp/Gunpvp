package gunpvp.adventurerush;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import gunpvp.Titles;
import gunpvp.data.DataManager;
import gunpvp.data.PlayerData;
import gunpvp.util.Action;
import gunpvp.util.Lobby;
import gunpvp.util.Timer;

public class AdventureRushDeathListener {

	public static void onDeath(Player p, Player k) {
		
		PlayerData data = DataManager.getData(p);
		
		if (data.getSettings().hasAutoEnabled()) {
			
			
			ARPlayer arp = AdventureRushMaster.getARP(p);
			
			Location world_loc = new Location(Bukkit.getWorld("AdventureRush"), 2000*(arp.getWorld()-1)+0.5, 100.5, 0.5, -90, 0);
			
			p.teleport(world_loc);
			Timer.sync(new Action() {
				public void perform() {
					Titles.sendTitle(p, "§cDu bist gestorben!", "§7Welt " + arp.getWorld() + "§8: §a" + String.format("%.2f", arp.getPercent()) + "%");
					p.setGameMode(GameMode.SURVIVAL);
					
					float highscore = data.getAdventureRush().getSteps() / 100f;
					if (arp.getWorld() < data.getAdventureRush().getWorld()) highscore = 100.00f;
					
					if (arp.getPercent() > highscore) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						Titles.sendTitle(p, "§aNeuer Highscore!", "§7Welt " + arp.getWorld() + "§8: §a" + String.format("%.2f", arp.getPercent()) + "%");
						data.getAdventureRush().setSteps(p, arp.getPercent());
					}
					
					AdventureRushMaster.removeARPlayer(arp);
					AdventureRushMaster.addARPlayer(p, arp.getWorld());
				}
			}, 0.05f);
			
			AdventureRushKits.giveKitToPlayer(p, AdventureRushKit.PUNCHER);
			
		} else {
			
			Timer.sync(new Action() {
				public void perform() {
					Lobby.reset(p);
					Lobby.giveItems(p);
				}
			}, 0.05f);
			
		}
		
	}

}
