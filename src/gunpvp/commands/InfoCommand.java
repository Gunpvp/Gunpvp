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
						p.sendMessage("§8§l§m==========================================");
						p.sendMessage("§2§lInfo-Command:");
						p.sendMessage("");
						p.sendMessage("§7/info 'kategorie'");
						p.sendMessage("");
						p.sendMessage("&8Kategorien:");
						p.sendMessage("§7- Arcade");
						p.sendMessage("§7- Classic");
						p.sendMessage("§7- Arcade");
						p.sendMessage("§7- Arcade");
						p.sendMessage("§8§l§m==========================================");
					} else {
						if (args.length == 1) {
							switch (args[0].toLowerCase()) {
							case "armor":
								p.sendMessage("§8§l§m==========================================");
								p.sendMessage("§2§lInfo zu: §aArmor");
								p.sendMessage("§8");
								p.sendMessage("§7Jeder Armor nutzt sich (je nach Art) verschieden");
								p.sendMessage("§7schnell ab. Er wird nicht gedropt wenn man den");
								p.sendMessage("§7Armor trägt. Ausgenommen Zombie-Modus hier wird");
								p.sendMessage("§7sie immer gedroppt damit deine Mitspieler sie");
								p.sendMessage("§7benutzen können falls du sterben solltest.");
								p.sendMessage("§8§l§m==========================================");
								break;
								
							case "arcade":
								p.sendMessage("§8§l§m==========================================");
								p.sendMessage("§2§lInfo zu: §aArcade");
								p.sendMessage("§8");
								p.sendMessage("§7Is gaunz cool.");
								p.sendMessage("§8§l§m==========================================");
								break;
								
							case "classic":
								p.sendMessage("§8§l§m==========================================");
								p.sendMessage("§2§lInfo zu: §aClassic");
								p.sendMessage("§8");
								p.sendMessage("§7Is gaunz cool.");
								p.sendMessage("§8§l§m==========================================");
								break;
								
							case "zombie":
								p.sendMessage("§8§l§m==========================================");
								p.sendMessage("§2§lInfo zu: §aZombie");
								p.sendMessage("§8");
								p.sendMessage("§7...");
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
