package karting;

public class Vozilo {
	private double maxV, ubrzanje, upravljivost;
	private String imeTakmicara;
	private double trV;
	
	public Vozilo(double m, double u, double up, String i) {
		maxV=m;
		ubrzanje=u;
		if(up<=0) upravljivost=0;
		else if(up>=1) upravljivost=1;
		else upravljivost=up;
		imeTakmicara=i;
		trV=0; 
	}

	public double dohvMaksBrzinu() {
		return maxV;
	}

	//ako je trenutna brzina veca od max, smanji je na max
	public void postMaksBrzinu(double maxV) {
		this.maxV = maxV;
		if(maxV<trV)
			trV=maxV;
	}

	public double dohvUbrzanje() {
		return ubrzanje;
	}

	public void postUbrzanje(double ubrzanje) {
		this.ubrzanje = ubrzanje;
	}

	public double dohvUpravljivost() {
		return upravljivost;
	}

	public void postUpravljivost(double upravljivost) {
		this.upravljivost = upravljivost;
	}

	public String dohvIme() {
		return imeTakmicara;
	}

	public void postIme(String imeTakmicara) {
		this.imeTakmicara = imeTakmicara;
	}

	public double dohvTrenBrzinu() {
		return trV;
	}

	//ako je nova trenutna veca od max, postavi je na max
	public void postTrenBrzinu(double trV) {
		if(maxV<trV)
			this.trV=maxV;
		else
			this.trV = trV;
	}
	
	//simulacija pomeranja (menja se trenutna brzina)
	public double pomeriVozilo(double vreme) {
		double s=0;
		double v0=trV;
		trV=v0+ubrzanje*vreme;
		if(trV>maxV) {
			trV=maxV;
			double tU=(maxV-v0)/ubrzanje;
			double tR=vreme-tU;
			s=v0*tU+ubrzanje*tU*tU/2+maxV*tR;
			return s;
		}
		else {
			s=v0*vreme+ubrzanje*vreme*vreme/2;
			return s;
		}
	}
	
	//samo predvidjanje pomeranja (ne menja se tr V)
	public double izracunajVreme(double s) {
		double t=0;
		double v0=trV;
		double trenV=Math.sqrt(v0*v0+2*ubrzanje*s);
		if(trenV>maxV) {
			trenV=maxV;
			double tU=(maxV-v0)/ubrzanje;
			double sU = v0*tU+ubrzanje*tU*tU/2;
			double sR=s-sU;
			double tR=sR/maxV;
			t=tR+tU;
		} else {
			t=(-v0+Math.sqrt(v0*v0+2*ubrzanje*s))/ubrzanje;
		}
		return t;
	}
	
	@Override
	public String toString() {
		return imeTakmicara+" ["+maxV+","+ubrzanje+","+upravljivost+"]";
	}
	
	
	
	
}
