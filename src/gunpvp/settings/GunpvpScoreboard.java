package gunpvp.settings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import gunpvp.data.DataManager;
import gunpvp.data.Stats;
import gunpvp.scoreboard.ScoreboardType;
import gunpvp.util.Database;

public class GunpvpScoreboard {
	
	private static ScoreboardType st = ScoreboardType.TOP10;
	@SuppressWarnings("unused")
	private static Hashtable<String, Double> top10 = new Hashtable<String, Double>();
	private static int time = 0;
	
	public synchronized static void drawScoreBoard(Player p) {
		synchronized (st) {
			switch (st) {
			case STATS:
				Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective obj = scoreboard.registerNewObjective("...", "...");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
				obj.setDisplayName("§8[§2§lSTATS§8]");
				
				Stats stats = DataManager.getData(p).getStats();
				
				Score s4 = obj.getScore("§7Money§8: §a$" + stats.getMoney());
				s4.setScore(4);
				Score s3 = obj.getScore("§7Kills§8: §a" + stats.getKills());
				s3.setScore(3);
				Score s2 = obj.getScore("§7Deaths§8: §a" + stats.getDeaths());
				s2.setScore(2);
				Score s1 = obj.getScore("§7KDR§8: §a" + stats.getKD());
				s1.setScore(1);
				Score s0 = obj.getScore("§7Ping§8: §a" + ((CraftPlayer) p).getHandle().ping + "ms");
				s0.setScore(0);
				p.setScoreboard(scoreboard);
				break;
			case TOP10:
				Scoreboard sbtop10 = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective objtop10 = sbtop10.registerNewObjective("....", "....");
				objtop10.setDisplaySlot(DisplaySlot.SIDEBAR);
				objtop10.setDisplayName("§8[§2§lRANKED§8]");
				
				Top10Data top10 = getRankedPlayers();
				
				int count = 12;
				
				for (int n = 0;n < 10;n++) {
					
					if (top10.getNames()[n]==null) {
						count--;
						continue;
					}
					
					String spaces = "";
					for (int x=0;x<18-top10.getNames()[n].length();x++) spaces = spaces + " ";
					
					Score score = objtop10.getScore("§7"+top10.getNames()[n]+"§8: "+spaces+ "§a" +String.format("%.3f", top10.getKDS()[n]));
					score.setScore(count-n);
					
				}
				
				Score score1 = objtop10.getScore("§2§l§m==================");
				score1.setScore(1);
				Score score2 = objtop10.getScore("§7min. 100 Kills erforderlich");
				score2.setScore(0);
				
				p.setScoreboard(sbtop10);
				break;		
			}
		}
	}
	
	public synchronized static void updateZombieBoard() {
//		for (Player all : Bukkit.getOnlinePlayers()) {
//			if (all.getWorld() == Bukkit.getWorld("ZombieForest")) {
//				Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
//				Objective obj = scoreboard.registerNewObjective("...", "...");
//				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
//				switch(all.getWorld().getName().toLowerCase()) {
//				case "zombieforest":
//					ZombieMap map = ZombieController.getZombieInfos()[0];
//					obj.setDisplayName("§8[§2§lWAVE§8§l: §7§l" + map.getWave() + "§8]");
//					if (map != null) {
//						if (map.getKillList() != null) {
//							for (Player current : map.getKillList().keySet()) {
//								Score score = obj.getScore("§2§l" + current.getName());
//								score.setScore(map.getKillList().get(current));
//							}
//						}
//					}
//					break;
//				}
//				all.setScoreboard(scoreboard);
//			}
//		}
	}
	public synchronized static void update() {
		time++;
		if (time == 15) {
			time = 0;
			synchronized (st) {
				switch (st) {
				case STATS: st = ScoreboardType.TOP10; break;
				case TOP10: st = ScoreboardType.STATS; break;
				}
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.getWorld() != Bukkit.getWorld("ZombieForest")) {
					drawScoreBoard(all);
				}
			}
		}
	}
	
	private static Top10Data getRankedPlayers() {
		
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
	
	private static void shiftArray(int n, double[] kds, String[] names) {
		for (int x=1;x<10-n;x++) {
			kds[10-x] = kds[9-x];
			names[10-x] = names[9-x];
		}
	}
	
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
	
}