package gunpvp.settings;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import gunpvp.data.DataManager;
import gunpvp.data.Settings;
import gunpvp.listener.Listener;
import gunpvp.util.Items;

public class SettingsPanel extends Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand() != null) {
				if (p.getInventory().getItemInMainHand().getType() == Material.IRON_INGOT) {
					openSettingsInv(p);
				}
				if (p.getInventory().getItemInMainHand().getType() == Material.BED) {
					Bukkit.dispatchCommand(p, "s");
				}
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("Einstellungen")) {
			Player p = (Player) e.getWhoClicked();
			Settings settings = DataManager.getData(p).getSettings();
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER ||
						e.getCurrentItem().getType() == Material.ENDER_PEARL) {
					switch (e.getSlot()) {
					case 11: settings.flipAuto(); e.getView().close(); openSettingsInv(p); break;
					case 13: settings.flipChat(); e.getView().close(); openSettingsInv(p); break;
					case 15: settings.flipInfo(); e.getView().close(); openSettingsInv(p); break;
					}
				}
			}
		}
	}
	
	private void openSettingsInv(Player p) {
		 
		Inventory inv = Bukkit.createInventory(null, 27, "Einstellungen");
		
		Settings settings = DataManager.getData(p).getSettings();
		
		if (settings.hasAutoEnabled()) {
			inv.setItem(11, Items.generate("§7§lAutorespawn§8§l: §a§lEIN", Material.EYE_OF_ENDER, 1, 0));
		} else {
			inv.setItem(11, Items.generate("§7§lAutorespawn§8§l: §c§lAUS", Material.ENDER_PEARL, 1, 0));
		}
		
		if (settings.hasChatEnabled()) {
			inv.setItem(13, Items.generate("§7§lChat§8§l: §a§lEIN", Material.EYE_OF_ENDER, 1, 0));
		} else {
			inv.setItem(13, Items.generate("§7§lChat§8§l: §c§lAUS", Material.ENDER_PEARL, 1, 0));
		}
		
		if (settings.hasInfoEnabled()) {
			inv.setItem(15, Items.generate("§7§lInfotexte§8§l: §a§lEIN", Material.EYE_OF_ENDER, 1, 0));
		} else {
			inv.setItem(15, Items.generate("§7§lInfotexte§8§l: §c§lAUS", Material.ENDER_PEARL, 1, 0));
		}

		p.openInventory(inv);
	}
	
}
