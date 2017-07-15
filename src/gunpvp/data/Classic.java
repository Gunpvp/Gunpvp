package gunpvp.data;

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
	
}
