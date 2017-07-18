package gunpvp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gunpvp.data.DataManager;

public class MoneyCommand extends Command {

	protected MoneyCommand() {
		super("money");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (cmd.getName().toLowerCase().equals("money") || cmd.getName().toLowerCase().equals("m") || cmd.getName().toLowerCase().equals("cash")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage("§8[§2Gunpvp§8] §7Kontostand§8: §a" + DataManager.getData(p).getStats().getMoney());
			}
		}
		
		return false;
	}

}
