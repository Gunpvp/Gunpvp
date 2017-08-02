package gunpvp.zombie;

import java.util.List;

public class Arenas {
	
	private static List<Arena> arenas;
	
	public static void init() {
		
	}
	
	public static Arena getArena(String name) {
		for (Arena arena : arenas) if (arena.getName().equals(name)) return arena;
		return null;
	}
	
}
