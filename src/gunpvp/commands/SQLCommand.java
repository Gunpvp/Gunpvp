package gunpvp.commands;

import gunpvp.data.Rank;
import gunpvp.util.Console;
import gunpvp.util.Database;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static gunpvp.permissions.PermissionHandler.addPermissionDefinedByLowest;
import static gunpvp.permissions.PermissionHandler.isPlayerAllowed;

public class SQLCommand extends Command {

    private static final Rank.RankEnum LOWEST_RANK_ALLOWED = Rank.RankEnum.DEVELOPER;
    private static final String PERMISSION_NAME = "SQL_COMMAND_PERMISSION";

    SQLCommand() {
        super("sql");
        addPermissionDefinedByLowest(PERMISSION_NAME, LOWEST_RANK_ALLOWED);
    }

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {

        if (sender instanceof Player && isPlayerAllowed((Player) sender, PERMISSION_NAME)) {

            if (cmd.getName().toLowerCase().equals("sql")) {
                String statement = "";
                for (String part : args) statement += " " + part;
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
                                    entry += meta.getColumnLabel(n) + " ";
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

    private static String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
	
}
