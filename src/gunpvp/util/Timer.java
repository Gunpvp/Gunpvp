package gunpvp.util;

import gunpvp.Gunpvp;

public class Timer {
	
	public static void delay(Action action, float seconds) {
		Gunpvp.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(seconds*20));
	}
	
	public static void repeat(Action action, float offset, float interval) {
		Gunpvp.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(Gunpvp.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(offset*20), (int)(interval*20));
	}
	
}
