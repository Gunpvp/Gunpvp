package gunpvp.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gunpvp.adventurerush.AdventureRushSpawner;

public class ARCommand extends Command {

	protected ARCommand() {
		super("ar");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (cmd.getName().equals("ar")) {
			
			if (args.length == 1) {
				
				if (sender instanceof Player) {
					
					Player p = (Player) sender;
					
					Location loc = new Location(p.getWorld(), 0.5f+((int)p.getLocation().getX()), 0.5f+((int)p.getLocation().getY()), 0.5f+((int)p.getLocation().getZ()));
					
					AdventureRushSpawner.addSpawner(loc, args[0].toUpperCase());
					
				}
				
			}
			
		}
		
		return false;
	}

}
