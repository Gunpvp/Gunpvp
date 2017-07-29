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

    public void editRank(RankEnum rankEnum) {
        this.rank = rankEnum;
        Database.execute("UPDATE 'GUNPVP_PLAYER_RANKS' SET 'RANK' = '" + this.rank + "' WHERE `UUID` = '" + this.uuid + "'");
    }

}
