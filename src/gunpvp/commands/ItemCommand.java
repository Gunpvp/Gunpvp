package gunpvp.commands;

import gunpvp.data.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static gunpvp.permissions.PermissionHandler.addPermissionDefinedByLowest;
import static gunpvp.permissions.PermissionHandler.isPlayerAllowed;

public class ItemCommand extends Command {

    private static final Rank.RankEnum LOWEST_RANK_ALLOWED = Rank.RankEnum.MODERATOR;
    private static final String PERMISSION_NAME = "ITEM_COMMAND_PERMISSION";

    ItemCommand() {
        super("item");
        addPermissionDefinedByLowest(PERMISSION_NAME, LOWEST_RANK_ALLOWED);
    }

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (cmd.getName().toLowerCase().equals("item")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
                if (isPlayerAllowed(player, PERMISSION_NAME)) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item != null) {
                        if (args.length >= 2) {
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(args[0].replace("&", "§").replace("_", " "));
							List<String> lore = new ArrayList<>();
							for (int i = 1;i<args.length;i++) lore.add(args[i].replace("&", "§").replace("_", " "));
							meta.setLore(lore);
							item.setItemMeta(meta);
						} else {
							player.sendMessage("§8[§2Gunpvp§8] §cMindestends zwei Parameter erforderlich!");
						}
					} else {
						player.sendMessage("§8[§2Gunpvp§8] §cDu musst ein Item in derHand haben!");
					}
				} else {
					sender.sendMessage("§8[§2Gunpvp§8] §cCommand nicht gefunden!");
				}
			}
		}
		
		return false;
	}
	
	

}
