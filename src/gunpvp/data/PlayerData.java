package gunpvp.data;

public class PlayerData {
	
	private Stats stats;
	private Settings settings;
	private Classic classic;
	private AdventureRush adventurerush;
	private Chests chests;
    private Rank rank;

    protected PlayerData(Stats stats, Settings settings, Classic classic, AdventureRush adventurerush, Chests chests, Rank rank) {
        this.stats = stats;
		this.settings = settings;
		this.classic = classic;
		this.adventurerush = adventurerush;
		this.chests = chests;
        this.rank = rank;
    }

	public Stats getStats() {
		return stats;
	}

	public Settings getSettings() {
		return settings;
	}

	public Classic getClassic() {
		return classic;
	}

	public AdventureRush getAdventureRush() {
		return adventurerush;
	}

	public Chests getChests() {
		return chests;
	}

    public Rank getRank() {
        return rank;
    }

}
