package gunpvp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommand extends Command {

	protected ItemCommand() {
		super("item");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (cmd.getName().toLowerCase().equals("item")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
			}
		}
		
		return false;
	}
	
	

}
