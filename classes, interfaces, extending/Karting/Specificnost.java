package karting;

public abstract class Specificnost {
	protected static int sID=0;
	protected int id=++sID;
	
	public int dohvatiId() { return id;}
	
	public abstract void ispoljiEfekat(Object o) throws GNeodgovarajuciObjekat;
	public abstract void ponistiEfekat(Object o) throws GNeodgovarajuciObjekat;
}
