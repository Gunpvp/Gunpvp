package gunpvp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gunpvp.data.DataManager;

public class StatsCommand extends Command {

	protected StatsCommand() {
		super("stats");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (cmd.getName().toLowerCase().equals("stats")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				DataManager.getData(p).getStats().showStats(p);
			}
		}
		
		return false;
	}

}
