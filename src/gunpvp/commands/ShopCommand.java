package gunpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends Command {
	
	protected ShopCommand() {
		super("shop");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getGameMode() != GameMode.SPECTATOR) {
				if (cmd.getName().equalsIgnoreCase("shop") || cmd.getName().equalsIgnoreCase("buy")) {
					if (p.getWorld()==Bukkit.getWorld("Gunpvp")) {
						p.sendMessage("§8[§2Gunpvp§8] §aDu wurdest zum Shop teleportiert!");
						p.teleport(new Location(Bukkit.getWorld("Gunpvp"), -22.0, 151.3, 1, 90, 0));
					} else {
						p.sendMessage("§8[§2Gunpvp§8] §cDu musst dich in der Lobby befinden,");
						p.sendMessage("§8[§2Gunpvp§8] §cum zum Shop zu gelangen. Tippe /lobby");
					}
				}
			}
		}
		return false;
	}
	
}
