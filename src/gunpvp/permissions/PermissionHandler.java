package gunpvp.permissions;

import gunpvp.data.Rank;
import org.bukkit.entity.Player;

import java.util.Hashtable;
import java.util.Map;

public class PermissionHandler {

    private static Map<String, Rank.RankEnum[]> permissions = new Hashtable<>();
    private static Map<String, Rank.RankEnum> playersAndRanks = new Hashtable<>();

    /**
     * adds an player with an rank to the playersAndRanks Hashtable
     *
     * @param p    player
     * @param rank Rank.RankEnum
     */
    public static void addPlayer(Player p, Rank.RankEnum rank) throws IllegalArgumentException {
        if (p != null && rank != null) {
            playersAndRanks.put(p.getUniqueId().toString(), rank);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * removes the Player p from the playersAndRanks hashtable
     *
     * @param p player
     */
    public static void removePlayer(Player p) throws IllegalArgumentException {
        if (p != null && playersAndRanks.containsKey(p.getUniqueId().toString())) {
            String uuid = p.getUniqueId().toString();
            playersAndRanks.remove(uuid);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * adds a new permission
     *
     * @param permissionName name of
     * @param allowedRanks   allowed Ranks
     */
    public static void addPermission(String permissionName, Rank.RankEnum[] allowedRanks)
            throws IllegalArgumentException {
        if (allowedRanks != null && permissionName != null) {
            permissions.put(permissionName, allowedRanks);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * checks if Player p is allowed to do that
     *
     * @param p              player
     * @param permissionName name of the Permission
     */
    public static boolean isPlayerAllowed(Player p, String permissionName) throws IllegalArgumentException {
        if (p != null && permissionName != null && !(permissionName.trim().equalsIgnoreCase(""))
                && playersAndRanks.containsKey(p.getUniqueId().toString()) && permissions.containsKey(permissionName)) {
            boolean allowed = false;
            for (Rank.RankEnum rank : permissions.get(permissionName)) {
                allowed |= (playersAndRanks.get(p.getUniqueId().toString()) == rank);
            }
            return allowed;
        } else {
            return false;
        }
    }

}
