package gunpvp;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static gunpvp.permissions.PermissionHandler.getRankColor;
import static gunpvp.permissions.PermissionHandler.getRankEnum;

public class Titles {

	public static void sendTitle(Player p, String msg, String sub) {
		p.sendTitle(msg, sub, 10, 10, 40);
	}
	
	public static void sendBar(Player p, String msg) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer
				
				.a("{\"text\": \"" + msg + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}

	public static void clear(Player p) {
        p.sendTitle("§a", getRankColor(getRankEnum(p.getUniqueId().toString())), 2, 2, 10);
        p.resetTitle();
	}
	
}
