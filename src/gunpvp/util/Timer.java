package gunpvp.util;

import gunpvp.Gunpvp;

public class Timer {
	
	public static void delay(Action action, float seconds) {
		Gunpvp.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Gunpvp.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(seconds*20));
	}
	
	public static int sync(Action action, float seconds) {
		return Gunpvp.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Gunpvp.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(seconds*20));
	}
	
	public static int repeat(Action action, float offset, float interval) {
		return Gunpvp.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(Gunpvp.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(offset*20), (int)(interval*20));
	}
	
	public static void cancel(int task) {
		Gunpvp.getPlugin().getServer().getScheduler().cancelTask(task);
	}
	
}
