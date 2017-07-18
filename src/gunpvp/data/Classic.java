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
	
	public void showClassic(Player p) {
		p.sendMessage("§7Gunner§8: "+(gunner>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
		p.sendMessage("§7Rambo§8: "+(rambo>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
		p.sendMessage("§7Pyro§8: "+(pyro>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
		p.sendMessage("§7Jugger§8: "+(jugger>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
		p.sendMessage("§7Healer§8: "+(healer>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
		p.sendMessage("§7Bomber§8: "+(bomber>0?"§a§lGEKAUFT":"§c§lGESPERRT"));
	}
	
}
