package gunpvp.permissions;

import gunpvp.data.Rank;
import gunpvp.util.Database;
import org.bukkit.entity.Player;

import java.util.Hashtable;
import java.util.Map;

import static gunpvp.data.Rank.RankEnum.*;

public class PermissionHandler {

    private static Map<String, Rank.RankEnum[]> permissions = new Hashtable<>();
    private static Map<String, Rank.RankEnum> playersAndRanks = new Hashtable<>();
    private static final Rank.RankEnum[] rankOrderLowToHigh = {SPIELER, DONATOR, MODERATOR, DEVELOPER, OWNER};

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

    public static void addPermissionDefinedByLowest(String permissionName, Rank.RankEnum allowedRankPlusHigher)
            throws IllegalArgumentException {
        boolean foundLowestRankForPermission = false;
        Rank.RankEnum[] rankEnums = new Rank.RankEnum[5];
        int startNumber = 0;
        for (int i = 0; i < rankOrderLowToHigh.length; i++) {
            if (rankOrderLowToHigh[i] == allowedRankPlusHigher) {
                foundLowestRankForPermission = true;
                rankEnums = new Rank.RankEnum[rankOrderLowToHigh.length - i];
            }
            if (foundLowestRankForPermission) {
                rankEnums[startNumber] = rankOrderLowToHigh[i];
                startNumber++;
            }
        }
        permissions.put(permissionName, rankEnums);
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

    /**
     * @param rankEnum enum of PlayerRank
     * @return color code as String or "" if rankEnum not valid
     */
    public static String getRankColor(Rank.RankEnum rankEnum) {
        switch (rankEnum) {
            case SPIELER:
                return "§a";
            case DONATOR:
                return "§b";
            case MODERATOR:
                return "§c";
            case DEVELOPER:
                return "§6";
            case OWNER:
                return "§5";
        }
        return "";
    }

    /**
     * get the rank of the player
     *
     * @param uuid uuid of player
     * @return RankEnum containing the players rank or null if player doesn't exist in that HashTable
     */
    public static Rank.RankEnum getRankEnum(String uuid) {
        if (uuid != null && (!uuid.trim().equalsIgnoreCase(""))) {
            return playersAndRanks.get(uuid);
        } else {
            return null;
        }
    }

    /**
     * changes the Rank of the player
     *
     * @param p        player
     * @param rankEnum rank to change to
     * @return true if rank was changed or false if player isn't in the HashTable
     */
    public static boolean changePlayerRank(Player p, Rank.RankEnum rankEnum) {
        if (playersAndRanks.containsKey(p.getUniqueId().toString())) {
            Database.execute("UPDATE 'GUNPVP_PLAYER_RANKS' SET 'RANK' = '" + rankEnum + "' WHERE UUID = '" +
                    p.getUniqueId().toString() + "'");
            playersAndRanks.replace(p.getUniqueId().toString(), rankEnum);
            return true;
        } else {
            return false;
        }
    }

}
