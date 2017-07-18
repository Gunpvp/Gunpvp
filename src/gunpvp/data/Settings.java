package gunpvp.data;

import org.bukkit.entity.Player;

import gunpvp.util.Database;

public class Settings {
	
	private boolean auto;
	private boolean chat;
	private boolean info;
	
	public Settings(boolean auto, boolean chat, boolean info) {
		this.auto = auto;
		this.chat = chat;
		this.info = info;
	}

	public boolean hasAutoEnabled() {
		return auto;
	}

	public boolean hasChatEnabled() {
		return chat;
	}

	public boolean hasInfoEnabled() {
		return info;
	}
	
	public void flipAuto() {
		auto = !auto;
		Database.execute("UPDATE GUNPVP_SETTINGS SET `AUTO`= '"+auto+"'");
	}
	
	public void flipChat() {
		chat = !chat;
		Database.execute("UPDATE GUNPVP_SETTINGS SET `CHAT`= '"+chat+"'");
	}
	
	public void flipInfo() {
		info = !info;
		Database.execute("UPDATE GUNPVP_SETTINGS SET `INFO`= '"+info+"'");
	}
	
	public void showSettings(Player p) {
		p.sendMessage("§7Autorespawn§8: "+(auto?"§a§lEIN":"§c§lAUS"));
		p.sendMessage("§7Chat§8: "+(chat?"§a§lEIN":"§c§lAUS"));
		p.sendMessage("§7Infotexte§8: "+(info?"§a§lEIN":"§c§lAUS"));
	}
	
}
