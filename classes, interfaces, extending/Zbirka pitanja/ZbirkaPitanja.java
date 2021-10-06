package pitanja;

import java.util.LinkedList;
import java.util.List;

public class ZbirkaPitanja {
	
	private List<Pitanje> lista = new LinkedList<>();
	
	public void dodaj(Pitanje p) {
		lista.add(p);
	}
	
	public Pitanje dohvati(int poz) throws GNemaPitanja {
		if(poz<0 || poz>=lista.size()) throw new GNemaPitanja();
		return lista.get(poz);
	}
	
	public int dohvBroj() {
		return lista.size();
	}
	
	public IteratorPitanja iterator() {
		return new IteratorPitanja(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Pitanje p : lista)
			sb.append(p+"\n");
		return sb.toString();
	}
	
	
}
