package gunpvp.listener;

import gunpvp.arcade.ArcadeClickListener;
import gunpvp.arcade.ArcadeDeathListener;
import gunpvp.settings.SettingsPanel;
import gunpvp.util.Console;

public class Listeners {
	
	public static void init() {
		
		new BlockBreakListener();
		new BlockPlaceListener();
		new EntityDamageListener();
		new FoodLevelChangeListener();
		new InventoryClickListener();
		new PlayerJoinListener();
		new PlayerQuitListener();
		new PlayerDropItemListener();
		new PlayerItemDamageListener();
		new WeatherChangeListener();
		new ChatListener();
		new SignChangeListener();
		new WeaponShootListener();
		
		new ArcadeDeathListener();
		new ArcadeClickListener();
		
		new SettingsPanel();
		
		Console.info("started listeners!");
		
	}
	
}
