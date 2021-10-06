package pitanja;

public class PitanjeSaPonudjenimOdgovorima extends Pitanje {
	private String odgovori[];
	
	public PitanjeSaPonudjenimOdgovorima(String p, int po, double t, String[] o) {
		super(p,po,t);
		odgovori=o;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()+"\n");
		for(int i=0; i<odgovori.length; i++) {
			sb.append(i+1+". "+odgovori[i]+"\n");
		}
		return sb.toString();
	}
	
	
}
