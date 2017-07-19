package gunpvp.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import gunpvp.Gunpvp;
import gunpvp.listener.Listener;

public class Medipack extends Listener {
	
	private ArrayList<Player> timeout = new ArrayList<Player>();
	
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (e.getItem() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getInventory().getItemInMainHand() != null) {
					if (p.getInventory().getItemInMainHand().getType() == Material.APPLE) {
						if (!timeout.contains(p)) {
							timeout.add(p);
							if (p.getInventory().getItemInMainHand().hasItemMeta()) {
								if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§2§lMedipack")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
									for (PotionEffect pe : p.getActivePotionEffects()) {
										if (pe.getType() == PotionEffectType.POISON) p.removePotionEffect(pe.getType());
									}
									if (p.getInventory().getItemInMainHand().getAmount() == 1) {
										Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
											@Override
											public void run() {
												p.getInventory().setItemInMainHand(null);
												p.updateInventory();
											}
										}, 5L);
									} else {
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										p.updateInventory();
									}
								}
							}
						}
						Bukkit.getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
							@Override
							public void run() {
								if (timeout.contains(p)) {
									timeout.remove(p);
								}
							}
						}, 10L);
					}
				}
			}
		}
	}
	
}
