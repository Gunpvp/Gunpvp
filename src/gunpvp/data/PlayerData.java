package gunpvp.data;

public class PlayerData {
	
	private Stats stats;
	private Settings settings;
	private Classic classic;
	private AdventureRush adventurerush;
	private Chests chests;
	
	protected PlayerData(Stats stats, Settings settings, Classic classic, AdventureRush adventurerush, Chests chests) {
		this.stats = stats;
		this.settings = settings;
		this.classic = classic;
		this.adventurerush = adventurerush;
		this.chests = chests;
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
}
