package gunpvp.commands;

import gunpvp.util.Console;

public class Commands {
	
	public static void init() {
		
		new PingCommand();
		new InfoCommand();
		new ShopCommand();
		new SpawnCommand();
		
		Console.info("loaded commands!");
	}
	
}
