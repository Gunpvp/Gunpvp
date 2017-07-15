package gunpvp.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.ShortByte.sbTitleAPI.sbTitleAPI;

public class SpawnCommand extends Command {
	
	protected SpawnCommand() {
		super("spawn");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getGameMode() != GameMode.SPECTATOR) {
				if (cmd.getName().equals("lobby") ||
						cmd.getName().equals("main") ||
						cmd.getName().equals("hub") ||
						cmd.getName().equals("l") ||
						cmd.getName().equals("spawn") ||
						cmd.getName().equals("s")) {
					if (p.getWorld()!=Bukkit.getWorld("Gunpvp")) {
						if (p.getWorld() != Bukkit.getWorld("ZombieForest")) {
							if (p.getName()==p.getName()) { // TODO ANTICHEAT
								ArrayList<Player> list = new ArrayList<Player>();
								for (Entity en : p.getNearbyEntities(25, 25, 25)) {
									if (en instanceof Player) {
										if ((Player) en != p) {
											list.add(p);
										}
									}
								}
								if (list.size()==0) {
									p.getInventory().setItem(7, null);
									p.getInventory().setItem(8, null);
									ItemStack item1 = new ItemStack(Material.IRON_INGOT, 1, (byte) 0);
									ItemMeta meta1 = item1.getItemMeta();
									meta1.setDisplayName("§b§lEinstellungen");
									meta1.setLore(null);
									item1.setItemMeta(meta1);
									p.getInventory().setItem(7, item1);
									p.updateInventory();
									ItemStack item2 = new ItemStack(Material.COMPASS, 1, (byte) 0);
									ItemMeta meta2 = item2.getItemMeta();
									meta2.setDisplayName("§b§lSpielmodus wählen");
									meta2.setLore(null);
									item2.setItemMeta(meta2);
									p.getInventory().setItem(8, item2);
									p.updateInventory();
									p.teleport(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 151.5, 0.5, 0, 0));
									p.sendMessage("§8[§2Gunpvp§8] §aDu wurdest zum Spawn teleportiert!");
								} else {
									p.sendMessage("§8[§2Gunpvp§8] §cJemand ist in deiner Nähe! Es darf niemand");
									p.sendMessage("§8[§2Gunpvp§8] §cim Umkreis von 25 Blöcken um dich sein!");
								}
							} else {
								sbTitleAPI.sendActionBar(p, "§c§lDu musst noch warten, da du gerade Schaden erlitten hast!");
								
							}
						} else {
							p.sendMessage("§8[§2Gunpvp§8] §cDu kannst dich nicht aus");
							p.sendMessage("§8[§2Gunpvp§8] §cdem Zombie-Modus teleportieren!");
						}
					} else {
						p.teleport(new Location(Bukkit.getWorld("Gunpvp"), 0.5, 151.5, 0.5, 0, 0));
						p.sendMessage("§8[§2Gunpvp§8] §aDu wurdest zum Spawn teleportiert!");
					}
				}
			}
		}
		return true;
	}
	
}
