package gunpvp.commands;

import gunpvp.data.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static gunpvp.permissions.PermissionHandler.addPermissionDefinedByLowest;
import static gunpvp.permissions.PermissionHandler.isPlayerAllowed;

public class RankChangeCommand extends Command {

    private static final Rank.RankEnum LOWEST_ALLOWED_RANK = Rank.RankEnum.DEVELOPER;
    private static final String PERMISSION_NAME = "RANK_CHANGE_PERMISSION";

    RankChangeCommand() {
        super("rank");
        addPermissionDefinedByLowest(PERMISSION_NAME, LOWEST_ALLOWED_RANK);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof Player && isPlayerAllowed((Player) commandSender, PERMISSION_NAME)) {
            commandSender.sendMessage("test");
        }
        return false;
    }

}
