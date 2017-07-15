package gunpvp.commands;

import org.bukkit.command.CommandExecutor;

import gunpvp.Gunpvp;

public abstract class Command implements CommandExecutor {
	
	protected Command(String cmd) {
		Gunpvp.getPlugin().getCommand(cmd).setExecutor(this);
	}
	
}
