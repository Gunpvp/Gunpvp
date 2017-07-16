package gunpvp.listener;

import gunpvp.arcade.ArcadeClickListener;
import gunpvp.arcade.ArcadeDeathListener;
import gunpvp.classic.ClassicClickListener;
import gunpvp.classic.ClassicDeathListener;
import gunpvp.enderchest.EnderchestMaster;
import gunpvp.items.Medipack;
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
		
		new ClassicDeathListener();
		new ClassicClickListener();
		
		new SettingsPanel();
		new Medipack();
		
		new DeathListener();
		
		new ShopListener();
		new InteractListener();
		new EnderchestMaster();
		
		Console.info("started listeners!");
		
	}
	
}
