package gunpvp.commands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand extends Command {

	protected PingCommand() {
		super("ping");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getGameMode() != GameMode.SPECTATOR) {
				if (cmd.getName().equalsIgnoreCase("ping") || cmd.getName().equalsIgnoreCase("p")) {
					if (args.length == 0) {
						p.sendMessage("§8[§2Gunpvp§8] §aPing§8: §7" + ((CraftPlayer) p).getHandle().ping);
					} else
						p.sendMessage("§8[§2Gunpvp§8] §cDu kannst nur den Ping von dir selbst sehen! - /ping");
					return true;
				}
			}
		}
		return false;
	}

}