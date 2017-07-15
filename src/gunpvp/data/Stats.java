package gunpvp.data;

import gunpvp.util.Database;

public class Stats {
	
	private String uuid;
	private String name;
	private int kills;
	private int deaths;
	private int money;
	
	public Stats(String uuid, String name, int kills, int deaths, int money) {
		this.uuid = uuid;
		this.name = name;
		this.kills = kills;
		this.deaths = deaths;
		this.money = money;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getMoney() {
		return money;
	}
	
	public void addKill() {
		kills++;
		Database.execute("UPDATE `GUNPVP_STATS` SET `KILLS` = "+(kills)+" WHERE `UUID` = '"+uuid+"'");
	}
	
	public void addDeath() {
		deaths++;
		Database.execute("UPDATE `GUNPVP_STATS` SET `DEATHS` = "+(deaths)+" WHERE `UUID` = '"+uuid+"'");
	}
	
	public void editMoney(int money) {
		this.money += money;
		Database.execute("UPDATE `GUNPVP_STATS` SET `MONEY` = "+(money)+" WHERE `UUID` = '"+uuid+"'");
	}
	
	public double getKD() {
		double kd = 0;
		if (deaths > 0) {
			kd = ((double) kills)/((double)deaths);
		} else {
			kd = kills;
		}
		kd = (double) Math.round(kd * 1000d) / 1000d;
		return kd;
	}
	
}
