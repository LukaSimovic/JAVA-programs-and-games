package raspored;

public class Ponavljajuci extends Sadrzaj {
	
	private Vreme perioda;
	
	public Ponavljajuci(String n, Vreme poc, Vreme p) throws GVreme {
		super(n,poc);
		perioda=p;
	}
	
	

	public Vreme getPerioda() {
		return perioda;
	}
	
	public Vreme poslednjePocetno() throws GVreme {
		Vreme pp=pocetno;
		while(Vreme.saberi(pp, trajanje).dohvatiVremeUMinutima()<Vreme.saberi(Vreme.saberi(pp, trajanje), perioda).dohvatiVremeUMinutima())
			pp=Vreme.saberi(pp, perioda);
		return pp;
	}
	

	@Override
	public Vreme preklapaSe(Sadrzaj s) throws GVreme {
		if(s instanceof Ponavljajuci) { //svako ponavljanje sa svakim ponavljanjem (oba sadrzaja su ponavljajuci)
			Ponavljajuci p1=this;
			Ponavljajuci p2 = (Ponavljajuci) s;
			if(p1.equals(p2))
				return p1.pocetno;
			Vreme pp1=p1.pocetno;
			Vreme pp2=p2.pocetno;
			boolean p1tece=true;
			boolean p2tece=true;
			while(p1tece) {
				pp2=p2.pocetno;
				p2tece=true;
				while(p2tece) {
					Vreme krajnje = Vreme.saberi(pp1, p1.trajanje);
					Vreme skrajnje = Vreme.saberi(pp2, p2.trajanje);
					if(pp1.dohvatiVremeUMinutima()<=pp2.dohvatiVremeUMinutima()) {
						int brojac = pp1.dohvatiVremeUMinutima();
						while(brojac<krajnje.dohvatiVremeUMinutima()) {
							if(brojac==pp2.dohvatiVremeUMinutima())
								return pp2;
							else
								brojac+=15;
						}
					}
					else {
						int brojac = pp2.dohvatiVremeUMinutima();
						while(brojac<skrajnje.dohvatiVremeUMinutima()) {
							if(brojac==pp1.dohvatiVremeUMinutima())
								return pp1;
							else
								brojac+=15;
						}
					}
					if(pp2.dohvatiVremeUMinutima()!=p2.poslednjePocetno().dohvatiVremeUMinutima())
						pp2=Vreme.saberi(pp2, p2.perioda);
					else
						p2tece=false;
				}
				if(pp1.dohvatiVremeUMinutima()!=p1.poslednjePocetno().dohvatiVremeUMinutima())
					pp1=Vreme.saberi(pp1, p1.perioda);
				else
					p1tece=false;
			}
		}
		else { //svako ponavljanje this sa jednim ponavljanjem drugog sadrzaja s 
			Ponavljajuci p1=this;
			Vreme pp1=p1.pocetno;
			Vreme pp2 = s.pocetno;
			boolean p1tece=true;
			while(p1tece) {
				Vreme krajnje = Vreme.saberi(pp1, p1.trajanje);
				Vreme skrajnje = Vreme.saberi(pp2, s.trajanje);
				if(pp1.dohvatiVremeUMinutima()<=pp2.dohvatiVremeUMinutima()) {
					int brojac = pp1.dohvatiVremeUMinutima();
					while(brojac<krajnje.dohvatiVremeUMinutima()) {
						if(brojac==pp2.dohvatiVremeUMinutima())
							return pp2;
						else
							brojac+=15;
					}
				}
				else {
					int brojac = pp2.dohvatiVremeUMinutima();
					while(brojac<skrajnje.dohvatiVremeUMinutima()) {
						if(brojac==pp1.dohvatiVremeUMinutima())
							return pp1;
						else
							brojac+=15;
					}
				}
				if(pp1.dohvatiVremeUMinutima()!=p1.poslednjePocetno().dohvatiVremeUMinutima())
					pp1=Vreme.saberi(pp1, p1.perioda);
				else
					p1tece=false;
			}
		}
		return null;
	}



	@Override
	public char vrsta() {
		return 'P';
	}



	@Override
	public String toString() {
		return super.toString()+ "  T"+perioda;
	}
	
	

}
