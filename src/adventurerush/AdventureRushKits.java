package adventurerush;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.shampaggon.crackshot.CSUtility;

public class AdventureRushKits {
	
	private static CSUtility csu = new CSUtility();
	
	public static void giveKitToPlayer(Player p, AdventureRushKit kit) {
		
		p.getInventory().clear();
		changeHealth(p, 20.0);
		
		switch (kit) {
		case AVENGER:
			changeHealth(p, 16);
			p.getInventory().addItem(csu.generateWeapon("L86"));
			p.getInventory().addItem(csu.generateWeapon("USP"));
			break;
		case PUNCHER:
			changeHealth(p, 40);
			p.getInventory().addItem(csu.generateWeapon("GL1"));
			p.getInventory().addItem(csu.generateWeapon("Colt45"));
			break;
		case VIPER:
			changeHealth(p, 24);
			p.getInventory().addItem(csu.generateWeapon("Dragunov"));
			p.getInventory().addItem(csu.generateWeapon("R870"));
			break;
		}
		
		p.updateInventory();
		
	}
	
	private static void changeHealth(Player p, double health) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		p.setHealth(health);
	}
	
}
