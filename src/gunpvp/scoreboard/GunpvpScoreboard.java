package gunpvp.scoreboard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import gunpvp.data.DataManager;
import gunpvp.data.Stats;
import gunpvp.util.Database;

public class GunpvpScoreboard {
	
	private static ScoreboardType st = ScoreboardType.TOP10;
	private static Top10Data top10;
	
	private static int time;
	private static int count;
	
	private static Scoreboard scoreboard;
	private static Objective objective;
	private static Score score;
	
	public synchronized static void drawScoreBoard(Player p) {
		synchronized (st) {
			switch (st) {
			
			/**
			 * creates scoreboard that shows your stats
			 */
			case STATS:
				
				initScoreboard("§8[§2§lSTATS§8]");
				
				Stats stats = DataManager.getData(p).getStats();
				
				addElement(4, "§7Money§8: §a$" + stats.getMoney());
				addElement(3, "§7Kills§8: §a" + stats.getKills());
				addElement(2, "§7Deaths§8: §a" + stats.getDeaths());
				addElement(1, "§7KDR§8: §a" + stats.getKD());
				addElement(0, "§7Ping§8: §a" + ((CraftPlayer) p).getHandle().ping + "ms");
				p.setScoreboard(scoreboard);
				
				break;
				
			/**
			 * creates scoreboard that shows your stats
			 */
			case TOP10:
				
				initScoreboard("§8[§2§lRANKED§8]");
				
				count = 12;
				if (top10==null) top10 = clacTop10();
				
				for (int n = 0;n < 10;n++) {
					
					if (top10.getNames()[n]==null) {
						count--;
						continue;
					}

					String spaces = "";
					for (int x=0;x<18-top10.getNames()[n].length();x++) spaces = spaces + " ";
					
					addElement(count-n, "§7"+top10.getNames()[n]+"§8: "+spaces+ "§a" +String.format("%.3f", top10.getKDS()[n]));
					
				}
				
				addElement(1, "§2§l§m==================");
				addElement(0, "§7min. 100 Kills erforderlich");
				
				p.setScoreboard(scoreboard);
				break;		
			}
		}
	}
	
	public synchronized static void update() {
		if (time++ == 15) {
			time = 0;
			/**
			 * try to change scoreboard type
			 */
			synchronized (st) {
				st = st == ScoreboardType.STATS ? ScoreboardType.TOP10 : ScoreboardType.STATS;
			}
			/**
			 * calc top10 board
			 */
			top10 = clacTop10();
			
			/**
			 * bind scoreboard to everybody who is online
			 */
			for (Player all : Bukkit.getOnlinePlayers()) drawScoreBoard(all);
		}
	}
	
	/**
	 * 
	 * calculates data about top10
	 * 
	 * @return Top10Data
	 */
	private static Top10Data clacTop10() {
		
		double[] kds = new double[10];
		String[] names = new String[10];
		
		try {

			ResultSet result = Database.query("SELECT * FROM `GUNPVP_STATS`");
			while (result.next()) {
				
				int kills = result.getInt("KILLS");
				int deaths = result.getInt("DEATHS");
				String name = result.getString("NAME");
				if (kills > 100 && deaths > 0) {
					
					double kd = (kills*1.0) / (deaths*1.0);

					for (int n = 0;n < 10;n++) {
						if (kds[n] < kd) {
							shiftArray(n, kds, names);
							kds[n] = kd;
							names[n] = name;
							break;
						}
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Top10Data(names, kds);
		
	}
	
	/**
	 * 
	 * shifts array backwards
	 * is needed for sorting top10 array
	 * 
	 * @param n
	 * @param kds
	 * @param names
	 */
	private static void shiftArray(int n, double[] kds, String[] names) {
		for (int x=1;x<10-n;x++) {
			kds[10-x] = kds[9-x];
			names[10-x] = names[9-x];
		}
	}
	
	/**
	 * represents data set of top10
	 * stores name + kd (10 times)
	 */
	private static class Top10Data {
		
		private String[] names;
		private double[] kds;
		
		public Top10Data(String[] names, double[] kds) {
			this.names = names;
			this.kds = kds;
		}

		public String[] getNames() {
			return names;
		}

		public double[] getKDS() {
			return kds;
		}
		
	}
	
	/**
	 * recreates scoreboard
	 */
	public static void initScoreboard(String name) {
		
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("NULL", "NULL");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(name);
		
	}
	
	/**
	 * adds element to scoreboard
	 */
	public static void addElement(int slot, String text) {
		
		score = objective.getScore(text);
		score.setScore(slot);
		
	}
	
}