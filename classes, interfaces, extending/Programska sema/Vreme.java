package raspored;

public class Vreme {
	private int sati,minuti;

	public Vreme(int sati, int minuti) throws GVreme {
		if(sati<0 || sati>23 || minuti<0 || minuti>59 || minuti%15!=0)
			throw new GVreme();
		this.sati = sati;
		this.minuti = minuti;
	}
	
	public Vreme() throws GVreme{
		this(0,0);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==this) return true;
		if(!(obj instanceof Vreme)) return false;
		return (minuti==((Vreme)obj).minuti && sati==((Vreme)obj).sati);
	}
	
	public static Vreme saberi(Vreme v1, Vreme v2) throws GVreme {
		int nm=(v1.minuti+v2.minuti)%60;
		int ns = (v1.sati+v2.sati+(v1.minuti+v2.minuti)/60)%24;
		return new Vreme(ns,nm);
	}
	
	public int dohvatiVremeUMinutima() {
		return sati*60+minuti;
	}

	@Override
	public String toString() {
		return "(" + sati + ":" + minuti + ")";
	}

}
