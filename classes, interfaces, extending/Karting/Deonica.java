package karting;

import java.util.LinkedList;
import java.util.List;


public class Deonica implements Cloneable{
	private double duzina;
	
	public Deonica(double d) {
		duzina=d;
	}
	
	public double dohvDuzinu() {
		return duzina;
	}
	
	private List<Specificnost> lista = new LinkedList<>();
	
	public void dodajSpecificnost(Specificnost s) {
		lista.add(s);
	}
	
	public int dohvBrSpec() {
		return lista.size();
	}
	
	public void izbaciSpecificnost(int id) {
		int br=0;
		Specificnost tek = lista.get(br);
		while(tek!=null && tek.dohvatiId()!=id) {
			tek=lista.get(++br);
		}
		if(tek!=null) {
			lista.remove(br);
		}
	}
	
	public Specificnost dohvSpecificnost(int poz) {
			return lista.get(poz);
	}

	@Override
	public Deonica clone(){
		Deonica d = null;
		
		try {
			d = (Deonica)super.clone();
			d.lista = new LinkedList<>();
			
			for(int i=0; i<lista.size(); i++) {
				Specificnost s = lista.get(i);
				if(s.getClass()==Krivina.class) {
					d.lista.add(((Krivina)s).clone());
				}
			}
		} catch (CloneNotSupportedException e) {}
		
		return d;
		
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("deonica("+duzina+"m)");
		for(int i=0; i<lista.size();i++)
			sb.append(lista.get(i));
		return sb.toString();
	}
	
	
	
	
	
	
	
}
