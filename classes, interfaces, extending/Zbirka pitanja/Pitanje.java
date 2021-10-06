package pitanja;

public class Pitanje implements Cloneable{
	private String tekst;
	private int poeni;
	private double tezina;
	
	public Pitanje(String p, int po, double t) {
		tekst=p;
		poeni=po;
		if(t<=1)
			tezina=1;
		else if(t>=10)
			tezina=10;
		else
			tezina=t;
	}
	
	private static int sID=0;
	private int id=++sID;

	public String getTekst() {
		return tekst;
	}
	public int getPoeni() {
		return poeni;
	}
	public double getTezina() {
		return tezina;
	}
	@Override
	public Pitanje clone()  {
		Pitanje p = null;
			try {
				p = (Pitanje)super.clone();
				return p;
			} catch (CloneNotSupportedException e) {}
		return null;
	}
	@Override
	public String toString() {
		return "Pitanje"+id+":"+tekst;
	}
	
	
	
	
	
	
}
