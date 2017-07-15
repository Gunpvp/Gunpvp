package gunpvp.util;

import gunpvp.Gunpvp;

public class Console {
	
	private static final String INFO = "§8[§aINFO§8] §7";
	private static final String WARN = "§8[§6INFO§8] §7";
	private static final String ERROR = "§8[§4ERROR§8] §7";
	private static final String SQL = "§8[§bSQL§8] §7";
	
	public static void info(String msg) {
		Gunpvp.getPlugin().getServer().getConsoleSender().sendMessage(INFO+msg);
	}
	
	public static void warn(String msg) {
		Gunpvp.getPlugin().getServer().getConsoleSender().sendMessage(WARN+msg);
	}
	
	public static void error(String msg) {
		Gunpvp.getPlugin().getServer().getConsoleSender().sendMessage(ERROR+msg);
	}

	public static void sql(String msg) {
		Gunpvp.getPlugin().getServer().getConsoleSender().sendMessage(SQL+msg);
	}
	
}
