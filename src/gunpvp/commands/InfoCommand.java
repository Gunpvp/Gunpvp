package gunpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends Command {
	
	protected InfoCommand() {
		super("info");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("info") || cmd.getName().equalsIgnoreCase("i")) {
				if (p.getWorld() == Bukkit.getWorld("Gunpvp")) {
					if (args.length == 0 || args.length >= 2) {
						p.sendMessage("§8[§2Gunpvp§8] §4Info-Command");
						p.sendMessage("§8[§2Gunpvp§8] §4/info 'mode'");
						p.sendMessage("§8[§2Gunpvp§8] §c - Arcade");
						p.sendMessage("§8[§2Gunpvp§8] §c - Classic");
						p.sendMessage("§8[§2Gunpvp§8] §c - TeamDeathMatch");
						p.sendMessage("§8[§2Gunpvp§8] §c - Zombie");
					} else {
						if (args.length == 1) {
							switch (args[0].toLowerCase()) {
							case "armor":
								p.sendMessage("§8§l§m==========================================");
								p.sendMessage("§2§lInfo zu: §aArmor");
								p.sendMessage("§8");
								p.sendMessage("§7Jeder Armor nutzt sich(je nach Art) verschieden");
								p.sendMessage("§7schnell ab. Er wird nicht gedropt wenn man den");
								p.sendMessage("§7Armor trägt. Ausgenommen Zombie-Modus hier wird");
								p.sendMessage("§7sie immer gedroppt damit deine Mitspieler sie");
								p.sendMessage("§7benutzen können falls du sterben solltest.");
								p.sendMessage("§8§l§m==========================================");
								break;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
}
