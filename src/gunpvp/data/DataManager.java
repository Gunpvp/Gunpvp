package gunpvp.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import gunpvp.util.Console;
import gunpvp.util.Database;

public class DataManager {
	
	private static List<PlayerData> datas = new ArrayList<>();
	
	public static void add(Player p) {
		if (getData(p)==null) {

			ResultSet exists = Database.query("SELECT * FROM GUNPVP_STATS WHERE UUID= '" + p.getUniqueId().toString() + "'");
			try {
				/*
				 * player is already in database
				 */
				if (exists.next() && exists.getString("UUID") != null) {

					datas.add(getPlayerData(p));

				}
				/*
				 * player is not in database
				 */
				else {
						
					Database.execute("INSERT INTO GUNPVP_STATS(UUID,NAME,KILLS,DEATHS,MONEY) VALUES ('"+p.getUniqueId().toString()+"', '"+p.getName()+"', '0', '0', '500')");
					Database.execute("INSERT INTO GUNPVP_SETTINGS(UUID,AUTO,CHAT,INFO) VALUES ('"+p.getUniqueId().toString()+"', 'false', 'true', 'true')");
					Database.execute("INSERT INTO GUNPVP_CLASSIC(UUID,GUNNER,RAMBO,PYRO,JUGGER,HEALER,BOMBER) VALUES ('"+p.getUniqueId().toString()+"', '0', '0', '0', '0', '0', '0')");
					Database.execute("INSERT INTO GUNPVP_ADVENTURERUSH(UUID,WORLD,STEPS) VALUES ('"+p.getUniqueId().toString()+"', '0', '0')");
					Database.execute("INSERT INTO GUNPVP_CHESTS(UUID,NORMAL,RARE,SPECIAL,OP) VALUES ('"+p.getUniqueId().toString()+"',0,0,0,0)");

					Console.info("Created new data for player " + p.getName());

					datas.add(getPlayerData(p));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static PlayerData getPlayerData(Player p) {
		String uuid = p.getUniqueId().toString();
		
		Stats stats = new Stats(uuid,
				getString(p, "STATS", "NAME"),
				getInt(p, "STATS", "KILLS"),
				getInt(p, "STATS", "DEATHS"),
				getInt(p, "STATS", "MONEY"));
		
		Settings settings = new Settings(
				getBoolean(p, "SETTINGS", "AUTO"),
				getBoolean(p, "SETTINGS", "CHAT"),
				getBoolean(p, "SETTINGS", "INFO"));
		
		Classic classic = new Classic(
				getInt(p, "CLASSIC", "GUNNER"),
				getInt(p, "CLASSIC", "RAMBO"),
				getInt(p, "CLASSIC", "PYRO"),
				getInt(p, "CLASSIC", "JUGGER"),
				getInt(p, "CLASSIC", "HEALER"),
				getInt(p, "CLASSIC", "BOMBER"));
		
		AdventureRush adventurerush = new AdventureRush(
				getInt(p, "ADVENTURERUSH", "WORLD"),
				getInt(p, "ADVENTURERUSH", "STEPS"));

        Chests chests=new Chests(
                p.getUniqueId().toString(),
                getInt(p,"CHESTS","NORMAL"),
                getInt(p,"CHESTS","RARE"),
                getInt(p,"CHESTS","SPECIAL"),
                getInt(p,"CHESTS","OP")
        );

		Console.info("Players data was loaded!");
		return new PlayerData(stats, settings, classic, adventurerush, chests);
	}
	
	private static String getString(Player p, String database, String column) {
		try {
			ResultSet result = Database.query("SELECT "+column+" FROM GUNPVP_"+database+" WHERE UUID= '" + p.getUniqueId().toString() + "'");
			if((result.next())) return result.getString(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int getInt(Player p, String database, String column) {
		try {
			ResultSet result = Database.query("SELECT "+column+" FROM GUNPVP_"+database+" WHERE UUID= '" + p.getUniqueId().toString() + "'");
			if((result.next())) return result.getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private static boolean getBoolean(Player p, String database, String column) {
		try {
			ResultSet result = Database.query("SELECT "+column+" FROM GUNPVP_"+database+" WHERE UUID= '" + p.getUniqueId().toString() + "'");
			if((result.next())) return Boolean.parseBoolean(result.getString(column));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void remove(Player p) {
		datas.remove(getData(p));
	}
	
	public static PlayerData getData(Player p) {
		for (PlayerData data : datas) if (data.getStats().getUuid().equals(p.getUniqueId().toString())) return data;
		return null;
	}
	
}
