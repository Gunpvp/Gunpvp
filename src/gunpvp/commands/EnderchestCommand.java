package gunpvp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gunpvp.enderchest.EnderchestMaster;

public class EnderchestCommand extends Command {

	protected EnderchestCommand() {
		super("enderchest");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (name.equals("enderchest")) {
			if (sender instanceof Player) {
				
				Player p = (Player) sender;
				
				if (p.isOp()) {
					EnderchestMaster.openEnderchest(p);
				} else {
					p.sendMessage("§8[§2Gunpvp§8] §cDieser Befehl benötigt Adminstratorrechte!");
				}
				
			}
		}
		
		return false;
	}
	
}
