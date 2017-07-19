package gunpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gunpvp.data.DataManager;
import gunpvp.util.Database;

public class DatabaseCommand extends Command {

	protected DatabaseCommand() {
		super("database");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		name = name.toLowerCase();
		
		if (sender.isOp()) {
			if (cmd.getName().equals("database") || cmd.getName().equals("db")) {
				
				if (args.length==2 || args.length==3) {
					
					if (args[0].equals("show")) {
						Player p = args.length==3?Bukkit.getPlayer(args[2]):((Player)sender);
						if (p == null) {
							sender.sendMessage("§8[§2Gunpvp§8] §cSpieler wurde nicht gefunden!");
							return false;
						}
						switch (args[1].toUpperCase()) {
						case "STATS":
							DataManager.getData(p).getStats().showStats(p);
							break;
						case "SETTINGS":
							DataManager.getData(p).getSettings().showSettings(p);
							break;
						case "CLASSIC":
							DataManager.getData(p).getClassic().showClassic(p);
							break;
						default:
							sender.sendMessage("§8[§2Gunpvp§8] §cUnbekannte Tabelle: " + args[1]);
							break;
						}
					} else {
						showUsage(sender);
					}
					
				} else if (args.length==5) {
					
					if (args[0].equals("modify")) {
						Player p = args.length==3?Bukkit.getPlayer(args[2]):((Player)sender);
						if (p == null) {
							sender.sendMessage("§8[§2Gunpvp§8] §cSpieler wurde nicht gefunden!");
							return false;
						}
						switch (args[1].toUpperCase()) {
						case "STATS":
							Database.execute("UPDATE `GUNPVP_STATS` SET `"+args[3].toUpperCase()+"` = " + args[4] + " WHERE `UUID` = '"+p.getUniqueId().toString()+"'");
							break;
						case "SETTINGS":
							Database.execute("UPDATE `GUNPVP_SETTINGS` SET `"+args[3].toUpperCase()+"` = " + args[4] + " WHERE `UUID` = '"+p.getUniqueId().toString()+"'");
							break;
						case "CLASSIC":
							Database.execute("UPDATE `GUNPVP_CLASSIC` SET `"+args[3].toUpperCase()+"` = " + args[4] + " WHERE `UUID` = '"+p.getUniqueId().toString()+"'");
							break;
						default:
							sender.sendMessage("§8[§2Gunpvp§8] §cUnbekannte Tabelle: " + args[1]);
							break;
						}
						DataManager.remove(p);
						DataManager.add(p);
					} else {
						showUsage(sender);
					}
					
				} else {
					showUsage(sender);
				}
				
			}
		} else {
			sender.sendMessage("§8[§2Gunpvp§8] §cCommand nicht gefunden!");
		}
		
		return false;
	}
		
		private void showUsage(CommandSender sender) {
			sender.sendMessage("§8[§2Gunpvp§8] §cVerwendung: /database show 'table' 'user'");
			sender.sendMessage("§8[§2Gunpvp§8] §cVerwendung: /database modify 'table' 'user' 'column' 'value'");
		}

}
