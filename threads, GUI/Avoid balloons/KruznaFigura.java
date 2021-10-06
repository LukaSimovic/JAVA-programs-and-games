package igra;

import java.awt.Color;

public class KruznaFigura extends Krug {
	
	protected Vektor brzina;
	protected Scena scena;
	public KruznaFigura(Vektor centar, Color boja, double precnik, Vektor brzina, Scena scena) {
		super(centar, boja, precnik);
		this.brzina = brzina;
		this.scena = scena;
	}

	public void protekloVreme(double vreme) {
		centar.saberi(brzina.pomnozi(vreme));
		
		if(centar.getY()>scena.getHeight() || centar.getX()<0 || centar.getX()>scena.getWidth()) {
			scena.izbaci(this);
		}
	}
	
	public boolean sudar(KruznaFigura f) {
		return Krug.preklapajuSe(this, f);
	}

}
