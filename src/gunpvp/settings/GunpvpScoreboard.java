package gunpvp.settings;

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

public class GunpvpScoreboard {
	
	private static ScoreboardType st = ScoreboardType.STATS;
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
//				Scoreboard sbtop10 = Bukkit.getScoreboardManager().getNewScoreboard();
//				Objective objtop10 = sbtop10.registerNewObjective("....", "....");
//				objtop10.setDisplaySlot(DisplaySlot.SIDEBAR);
//				objtop10.setDisplayName("§8[§2§lRANKED§8]");
//				HashMap<String, Integer> values = getRankedPlayers();
//				for (String s : values.keySet()) {
//					Score score = objtop10.getScore(s);
//					score.setScore(values.get(s)+2);
//				}
//				Score score1 = objtop10.getScore("§2§l§m==================");
//				score1.setScore(1);
//				Score score2 = objtop10.getScore("§7min. 100 Kills erforderlich");
//				score2.setScore(0);
//				p.setScoreboard(sbtop10);
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
	
//	private static HashMap<String, Integer> getRankedPlayers() {
//		return null;
//		
//		
//		ResultSet result = Database.query("SELECT NAME, KD FROM GUNPVP_STATS ORDER BY KD");
//		
//		int amount = 10;
//		if (origindata.size() < 10) amount = origindata.size();
//		//Filter for best ten + Sort them
//		Dataline[] data = new Dataline[amount];
//		int line = amount-1;
//		while (line >= 0) {
//			//Get Current-Best
//			Dataline cbest = new Dataline(".", ".", "0", "1");
//			for (Dataline dl : origindata) {
//				if (dl.getKD() >= cbest.getKD()) cbest = dl;
//			}
//			//Insert+Remove from Oldlist
//			if (!cbest.getUUID().equalsIgnoreCase(".")) {
//				data[line] = cbest;
//				origindata.remove(cbest);
//			} else break;
//			line--;
//		}
//		//Convert to Hashmap
//		HashMap<String, Integer> values = new HashMap<String, Integer>();
//		for (int i = 0;i<amount;i++) {
//			String out = "§2" + (amount-i) + ". §7" + data[i].getName() + "§2§l | §a" + data[i].getKD();
//			values.put(out, i);
//		}
//		return values;
//	}
	
}
