package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Krug {
	
	protected Vektor centar;
	protected Color boja;
	protected double precnik;
	
	public Krug(Vektor centar, Color boja, double precnik) {
		super();
		this.centar = centar;
		this.boja = boja;
		this.precnik = precnik;
	}
	
	public static boolean preklapajuSe(Krug k1, Krug k2) {
		double rastCentara = rastojanje(k1.centar,k2.centar);
		
		if(k1.precnik/2+k2.precnik/2<rastCentara)
			return false;
		else
			return true;
	}
	
	private static double rastojanje(Vektor v1, Vektor v2) {
		return Math.sqrt(Math.pow(v1.getX()-v2.getX(), 2)+Math.pow(v1.getY()-v2.getY(), 2));
	}
	
	public void iscrtaj(Scena scena, Graphics g) {
		
		g.setColor(boja);
		
		g.fillOval((int)(centar.getX()-precnik/2), (int)(centar.getY()-precnik/2),(int) precnik, (int)precnik);
	}
	
}
