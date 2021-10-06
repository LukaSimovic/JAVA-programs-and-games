package raspored;

public abstract class Sadrzaj {
	protected String naziv;
	protected Vreme trajanje, pocetno;
	public Sadrzaj(String naziv, Vreme trajanje) throws GVreme {
		this.naziv = naziv;
		this.trajanje = trajanje;
		pocetno = new Vreme();
	}
	
	public void pomeri(Vreme pomeraj) throws GVreme {
		pocetno = Vreme.saberi(pocetno, pomeraj);
	}
	
	public Vreme preklapaSe(Sadrzaj s) throws GVreme { //obicno preklapanje jednog sadrzaja sa drugim (oba imaju jedno ponavljanje)
		Vreme krajnje = Vreme.saberi(pocetno, trajanje);
		Vreme skrajnje = Vreme.saberi(s.pocetno, s.trajanje);
		
		if(pocetno.dohvatiVremeUMinutima()<=s.pocetno.dohvatiVremeUMinutima()) {
			int brojac = pocetno.dohvatiVremeUMinutima();
			while(brojac<krajnje.dohvatiVremeUMinutima()) {
				if(brojac==s.pocetno.dohvatiVremeUMinutima())
					return s.pocetno;
				else
					brojac+=15;
			}
			return null;
		}
		else {
			int brojac = s.pocetno.dohvatiVremeUMinutima();
			while(brojac<skrajnje.dohvatiVremeUMinutima()) {
				if(brojac==pocetno.dohvatiVremeUMinutima())
					return pocetno;
				else
					brojac+=15;
			}
			return null;
		}
	}

	public String dohvNaziv() {
		return naziv;
	}

	public Vreme dohvTrajanje() {
		return trajanje;
	}

	public Vreme dohvPocetak() {
		return pocetno;
	}
	
	public void setPocetno(Vreme pocetno) {
		this.pocetno=pocetno;
	}
	
	public abstract char vrsta();

	@Override
	public String toString() {
		try {
			return vrsta()+ ", "+naziv+" | "+pocetno+" - "+(Vreme.saberi(pocetno, trajanje));
		} catch (GVreme e) {return null;}
		
	}
	
	
	
	
	
	
	
}
