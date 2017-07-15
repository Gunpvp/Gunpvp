package gunpvp.data;

import org.bukkit.entity.Player;

import gunpvp.util.Database;

public class Classic {
	
	private int gunner;
	private int rambo;
	private int pyro;
	private int jugger;
	private int healer;
	private int bomber;
	
	public Classic(int gunner, int rambo, int pyro, int jugger, int healer, int bomber) {
		this.gunner = gunner;
		this.rambo = rambo;
		this.pyro = pyro;
		this.jugger = jugger;
		this.healer = healer;
		this.bomber = bomber;
	}

	public int getGunner() {
		return gunner;
	}

	public int getRambo() {
		return rambo;
	}

	public int getPyro() {
		return pyro;
	}

	public int getJugger() {
		return jugger;
	}

	public int getHealer() {
		return healer;
	}

	public int getBomber() {
		return bomber;
	}

	public static void enableKit(String kit, Player player) {
		DataManager.remove(player);
		Database.execute("UPDATE `GUNPVP_CLASSIC` SET `"+kit.toUpperCase()+"` = 1 WHERE `UUID` = '"+player.getUniqueId().toString()+"'");
		DataManager.add(player);
	}
	
}
