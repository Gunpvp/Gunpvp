package gunpvp.listener;

import gunpvp.adventurerush.AdventureRushMoveListener;
import gunpvp.adventurerush.AdventureRushSignListener;
import gunpvp.adventurerush.AdventureRushZombieDeathListener;
import gunpvp.arcade.ArcadeClickListener;
import gunpvp.arcade.ArcadeDeathListener;
import gunpvp.chestlottery.LuckyPack;
import gunpvp.classic.ClassicClickListener;
import gunpvp.classic.ClassicDeathListener;
import gunpvp.enderchest.EnderchestMaster;
import gunpvp.items.Medipack;
import gunpvp.settings.SettingsPanel;
import gunpvp.util.Console;
import gunpvp.zombie.ZombieClickListener;

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
		new LeaveDecayListener();
		new PlayerChangeHandListener();
		new SignChangeListener();
		new WeaponShootListener();
		new ExplosionPrimeListener();
		new ProjectileHitListener();
		new ArcadeDeathListener();
		new ArcadeClickListener();
		
		new ClassicDeathListener();
		new ClassicClickListener();
		
		new SettingsPanel();
		new Medipack();
		
		new AdventureRushSignListener();
		new AdventureRushMoveListener();
		new AdventureRushZombieDeathListener();

		new LuckyPack();
		
		new DeathListener();
		
		new ShopListener();
		new InteractListener();
		new EnderchestMaster();
		
		new ZombieClickListener();
		
		Console.info("started listeners!");
		
	}
	
}
