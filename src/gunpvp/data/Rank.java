package gunpvp.data;

import gunpvp.util.Console;
import gunpvp.util.Database;

public class Rank {

    public enum RankEnum {SPIELER, DONATOR, DEV, ADMIN}

    private String uuid;
    private RankEnum rank;

    public Rank(String uuid, RankEnum rankEnum) {
        this.uuid = uuid;
        this.rank = rankEnum;
        if (rankEnum != null) {
            Console.info(rank.toString());
        }
    }

    public RankEnum getRank() {
        return rank;
    }

    public String getColoredRank() {
        switch (rank) {
            case SPIELER:
                return "§2" + rank.toString();
            case DONATOR:
                return "§b" + rank.toString();
            case DEV:
                return "§6" + rank.toString();
            case ADMIN:
                return "§4" + rank.toString();
        }
        return "";
    }

    public void editRank(RankEnum rankEnum) {
        this.rank = rankEnum;
        Database.execute("UPDATE 'GUNPVP_PLAYER_RANKS' SET 'RANK' = '" + this.rank + "' WHERE `UUID` = '" + this.uuid + "'");
    }

}
