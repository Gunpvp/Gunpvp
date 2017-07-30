package gunpvp.data;

import gunpvp.util.Console;

public class Rank {

    public enum RankEnum {SPIELER, DONATOR, MODERATOR, DEVELOPER, OWNER}

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

    public String getRankColor() {
        switch (rank) {
            case SPIELER:
                return "§a";
            case DONATOR:
                return "§b";
            case MODERATOR:
                return "§5";
            case DEVELOPER:
                return "§6";
            case OWNER:
                return "§c";
        }
        return "";
    }
}
