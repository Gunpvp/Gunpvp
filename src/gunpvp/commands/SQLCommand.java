package gunpvp.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.bukkit.command.CommandSender;

import gunpvp.util.Console;
import gunpvp.util.Database;

public class SQLCommand extends Command {

	protected SQLCommand() {
		super("sql");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (sender.isOp()) {
			
			if (cmd.getName().toLowerCase().equals("sql")) {
				String statement = "";
				for (String part : args) statement = statement + " " + part;
				statement.trim();
				statement.toUpperCase();
				args[0] = args[0].toUpperCase();
				
				Connection con = Database.getConnection();
				if (con != null) {
					if (args[0].equals("SELECT")) {
						sender.sendMessage("§8[§bSQL§8] §7Trying to querr this command on DB:");
						sender.sendMessage("§8[§bSQL§8] §8" + statement);
						
						try {
							Console.sql(statement);
							ResultSet result = con.createStatement().executeQuery(statement);
							ResultSetMetaData meta = result.getMetaData();
							
							while (result.next()) {
								String entry = "§8[§bSQL§8]";
								for (int n = 0;n<meta.getColumnCount(); n++) {
									entry = entry + meta.getColumnLabel(n) + " ";
									switch (meta.getColumnTypeName(n)) {
									case "VARCHAR":
										entry = entry + result.getString(n);
										break;
									case "INT":
										entry = entry + result.getInt(n);
										break;
									default:
										entry = entry + meta.getColumnTypeName(n);
										break;
									}
									entry = entry + "|";
								}
								entry = entry.substring(0, entry.length()-1);
								sender.sendMessage(entry);
							}
							
							sender.sendMessage("§8[§bSQL§8] §bSuccesfully querried command!");
						} catch (SQLException e) {
							sender.sendMessage(getStackTrace(e));
						}
						
					} else {
						sender.sendMessage("§8[§bSQL§8] §7Trying to execute this command on DB:");
						sender.sendMessage("§8[§bSQL§8] §8" + statement);
						
						try {
							Console.sql(statement);
							con.createStatement().executeUpdate(statement);
							sender.sendMessage("§8[§bSQL§8] §bSuccesfully executed command!");
						} catch (SQLException e) {
							sender.sendMessage(getStackTrace(e));
						}
						
					}
				}
				
			}
			
		} else {
			sender.sendMessage("§8[§2Gunpvp§8] §cCommand nicht gefunden!");
		}
		
		return false;
	}
	
	public static String getStackTrace(Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
	
}
