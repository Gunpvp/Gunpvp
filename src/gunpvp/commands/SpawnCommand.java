package gunpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import gunpvp.util.Lobby;

public class SpawnCommand extends Command {
	
	protected SpawnCommand() {
		super("spawn");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getGameMode() != GameMode.SPECTATOR) {
				if (cmd.getName().equals("lobby") ||
						cmd.getName().equals("main") ||
						cmd.getName().equals("hub") ||
						cmd.getName().equals("l") ||
						cmd.getName().equals("spawn") ||
						cmd.getName().equals("s")) {
					if (p.getWorld()!=Bukkit.getWorld("Gunpvp")) {
						if (p.getWorld() != Bukkit.getWorld("ZombieForest")) {
							
							if (p.getHealth() > p.getMaxHealth() * (3f/4f)) {
								
								for (Entity en : p.getNearbyEntities(25, 25, 25)) {
									if (en instanceof Player) {
										if ((Player) en != p) {
											p.sendMessage("§8[§2Gunpvp§8] §cJemand ist in deiner Nähe! Es darf niemand");
											p.sendMessage("§8[§2Gunpvp§8] §cim Umkreis von 25 Blöcken um dich sein!");
											return false;
										}
									}
								}
								Lobby.reset(p);
								Lobby.giveItems(p);
								p.sendMessage("§8[§2Gunpvp§8] §aDu wurdest zum Spawn teleportiert!");
								
							} else {
								p.sendMessage("§c§lUm zum Spawn zu gelangen benötigst du mindstens 3/4 deiner Leben!");
								
							}
						} else {
							p.sendMessage("§8[§2Gunpvp§8] §cDu kannst dich nicht aus dem Zombie-Modus teleportieren!");
						}
					} else {
						Lobby.reset(p);
						Lobby.giveItems(p);
						p.sendMessage("§8[§2Gunpvp§8] §aDu wurdest zum Spawn teleportiert!");
					}
				}
			}
		}
		return true;
	}
	
}
