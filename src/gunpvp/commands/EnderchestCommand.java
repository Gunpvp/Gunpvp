package gunpvp.commands;

import gunpvp.data.Rank;
import gunpvp.enderchest.EnderchestMaster;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static gunpvp.permissions.PermissionHandler.addPermissionDefinedByLowest;
import static gunpvp.permissions.PermissionHandler.isPlayerAllowed;

public class EnderchestCommand extends Command {

    private static final Rank.RankEnum LOWEST_RANK_ALLOWED = Rank.RankEnum.MODERATOR;
    private static final String PERMISSION_NAME = "ENDERCHEST_COMMAND_PERMISSION";

	protected EnderchestCommand() {
		super("enderchest");
        addPermissionDefinedByLowest(PERMISSION_NAME, LOWEST_RANK_ALLOWED);
    }

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		
		if (name.equals("enderchest")) {
			if (sender instanceof Player) {
				
				Player p = (Player) sender;

                if (isPlayerAllowed(p, PERMISSION_NAME)) {
                    EnderchestMaster.openEnderchest(p);
				} else {
					p.sendMessage("§8[§2Gunpvp§8] §cDieser Befehl benötigt Adminstratorrechte!");
				}
				
			}
		}
		
		return false;
	}
	
}
