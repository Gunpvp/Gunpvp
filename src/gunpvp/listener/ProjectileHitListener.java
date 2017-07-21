package gunpvp.listener;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import com.shampaggon.crackshot.events.WeaponHitBlockEvent;

import gunpvp.util.Action;
import gunpvp.util.Timer;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.PacketPlayOutBlockBreakAnimation;

public class ProjectileHitListener extends Listener {
	
	private Vector<HittedBlock> blocks = new Vector<>();
	
	@EventHandler
	public void BreakAnimation(WeaponHitBlockEvent e) {
		
		synchronized (blocks) {
			blocks.add(new HittedBlock(new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ()), 5)));
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				for (HittedBlock block : blocks) {
					((CraftPlayer)p).getHandle().playerConnection.sendPacket(block.getPacket());
				}
			}
		}
		
	}
	
	private class HittedBlock {
		
		private PacketPlayOutBlockBreakAnimation packet;
		private static final float TIME = 3f * 20;
		
		public HittedBlock(PacketPlayOutBlockBreakAnimation packet) {
			this.packet = packet;
			Timer.delay(new Action() {
				public void perform() {
					synchronized (blocks) {
						blocks.remove(this);
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
							for (HittedBlock block : blocks) {
								((CraftPlayer)p).getHandle().playerConnection.sendPacket(block.getPacket());
							}
						}
					}
				}
			}, TIME);
		}

		public PacketPlayOutBlockBreakAnimation getPacket() {
			return packet;
		}
		
	}
 
	
}
