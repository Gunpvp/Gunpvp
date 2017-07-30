package gunpvp.commands;

import gunpvp.util.Console;

public class Commands {
	
	public static void init() {
		
		new PingCommand();
		new InfoCommand();
		new ShopCommand();
		new SpawnCommand();
		new EnderchestCommand();
		new DatabaseCommand();
		new StatsCommand();
		new MoneyCommand();
		new ItemCommand();
		new SQLCommand();
		new LuckyPackCommand();
		new ARCommand();
        new RankChangeCommand();

        Console.info("loaded commands!");
	}
	
}
