package karting;

import java.util.LinkedList;
import java.util.List;

public class Krivina extends Specificnost implements Cloneable{
	
	private double maxBrzina;
	public Krivina(double m) { maxBrzina=m;}
	
	public double dohvMaksBrzinu() { return maxBrzina;}
	
	//pomocna klasa i pomocna lista za cuvanje max brzina prilikom ulaska vozila u krivinu
	class Elem{
		String ime;
		double max;
		Elem(Vozilo v){
			ime = v.dohvIme();
			max = v.dohvMaksBrzinu();
		}
	}
	List<Elem> pomLista=new LinkedList<>();

	@Override
	public void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if(!(o instanceof Vozilo)) throw new GNeodgovarajuciObjekat();
		Vozilo vozilo = (Vozilo) o;
		double m = maxBrzina*vozilo.dohvUpravljivost();
		
		
		pomLista.add(new Elem(vozilo));
		if(m<vozilo.dohvMaksBrzinu()) {
			vozilo.postMaksBrzinu(m);
		}
	}

	@Override
	public void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat {
		if(!(o instanceof Vozilo)) throw new GNeodgovarajuciObjekat();
		Vozilo vozilo = (Vozilo) o;
		for(int i=0; i<pomLista.size(); i++) {
			if(pomLista.get(i).ime==vozilo.dohvIme()) {
				vozilo.postMaksBrzinu(pomLista.get(i).max);
				pomLista.remove(i);
				break;
			}
		}
	}

	@Override
	public Krivina clone(){
		Krivina k=null;
		try {
			k=(Krivina)super.clone();
			k.id=++sID;
		}catch(CloneNotSupportedException e) {}
		return k;
	}

	@Override
	public String toString() {
		return "K"+maxBrzina;
	}
	
	

}
