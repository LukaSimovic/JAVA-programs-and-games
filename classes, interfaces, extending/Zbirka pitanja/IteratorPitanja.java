package pitanja;

public class IteratorPitanja {
	
	private ZbirkaPitanja zbirka;
	private Pitanje tek;
	private int br;
	
	public IteratorPitanja(ZbirkaPitanja z) {
		zbirka=z;
		try {
			tek=zbirka.dohvati(0);
		} catch (GNemaPitanja e) {}
	}
	
	public boolean postoji() {
		return tek!=null;
	}
	
	public Pitanje dohvati() throws GNemaPitanja {
		if(!postoji()) throw new GNemaPitanja();
		
		return tek;
	}
	
	public void sledece() throws GNemaPitanja {
		if(br+1==zbirka.dohvBroj()) 
			tek=null;
		tek=zbirka.dohvati(++br);
	}
}
