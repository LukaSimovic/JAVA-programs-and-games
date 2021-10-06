package raspored;

import java.util.LinkedList;
import java.util.List;

public class Sema {
	private Vreme pocetak,kraj;
	private String naziv;
	//private int brojSadrzaja;
	
	private List<Sadrzaj> lista = new LinkedList<>();
	
	public Sema(String n, Vreme p, Vreme k) {
		naziv=n; pocetak=p; kraj=k;
	}
	
	public Sema(String n) throws GVreme {
		this(n,new Vreme(8,0), new Vreme(22,0));
	}
	
	public int dohvBrSadrzaja() {
		return lista.size();
	}
	
	public void dodaj(Sadrzaj s) throws GDodaj, GVreme {
		if(kraj.dohvatiVremeUMinutima()<Vreme.saberi(s.pocetno, s.trajanje).dohvatiVremeUMinutima())
			throw new GDodaj();
		if(s instanceof Ponavljajuci) {
			Ponavljajuci p = (Ponavljajuci) s;
			
			while(p.pocetno.dohvatiVremeUMinutima()<kraj.dohvatiVremeUMinutima()) {
				boolean moze=true;
				for(Sadrzaj tmp : lista) {
					if(p.preklapaSe(tmp)!=null) {
						moze=false;
						break;
					}
				}
				if(moze && p.pocetno.dohvatiVremeUMinutima()>=pocetak.dohvatiVremeUMinutima()) {
					int i;
					for(i=0; i<lista.size(); i++) {
						if(p.pocetno.dohvatiVremeUMinutima()<lista.get(i).pocetno.dohvatiVremeUMinutima())
							break;
					}
					lista.add(i, p);
					return;
				}
				else {
					if(p.pocetno.dohvatiVremeUMinutima()<pocetak.dohvatiVremeUMinutima())
						p.pocetno=pocetak;
					else
						p.pocetno=Vreme.saberi(p.pocetno, new Vreme(0,15));
				}
				
			}
			throw new GDodaj();
		}
		//dodavanje nekog drugog sadrzaja potpuno isto, samo nije p nego neko drugo slovo
	}
	
	public double zauzetost() throws GVreme {
		double z=0;
		double cela = kraj.dohvatiVremeUMinutima()-pocetak.dohvatiVremeUMinutima();
		for(Sadrzaj tmp : lista) {
			if(tmp instanceof Ponavljajuci) {
				Ponavljajuci p = (Ponavljajuci) tmp;
				Vreme vr = p.pocetno;
				boolean biloPosl=false;
				while(!biloPosl) {
					if(Vreme.saberi(vr, p.trajanje).dohvatiVremeUMinutima()<kraj.dohvatiVremeUMinutima())
						z+=p.trajanje.dohvatiVremeUMinutima();
					
					if(vr.dohvatiVremeUMinutima()!=p.poslednjePocetno().dohvatiVremeUMinutima())
						vr=Vreme.saberi(vr, p.getPerioda());
					else
						biloPosl=true;
				}
			}else { //slucaj da neki drugi sadrzaj ima samo jedno ponavljanje u listi
				z+=tmp.trajanje.dohvatiVremeUMinutima();
			}
		}
		double proc = z/cela*100;
		return proc;
	}
	
	public Sadrzaj dohvSadrzaj(int id) throws GIndeks {
		if(id<0 || id>=lista.size()) throw new GIndeks();
		return lista.get(id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv+" : "+pocetak+" - "+kraj+"\n");
		for(Sadrzaj s : lista)
			sb.append(s).append("\n");
		return sb.toString();
	}
	
	
}
