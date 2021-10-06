package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor centar, double precnik,Scena scena) {
		super(centar, Color.GREEN, precnik, new Vektor(0,0), scena);
		// TODO Auto-generated constructor stub
	}
	
	public void pomeri(double pomeraj) {
		
		Vektor v = new Vektor(pomeraj,0);
		if(centar.getX()+precnik/2+v.getX()<=scena.getWidth() &&
				centar.getX()+v.getX()-precnik/2>=0) {
			centar.saberi(new Vektor(pomeraj,0));
		}
	}

	@Override
	public void iscrtaj(Scena scena, Graphics g) {
		
		g.setColor(boja);
		
		g.fillOval((int)(centar.getX()-precnik/2), (int)(centar.getY()-precnik/2),(int) precnik, (int)precnik);
		g.setColor(Color.BLUE);
		
		g.fillOval((int)(centar.getX()-precnik/4), (int)(centar.getY()-precnik/4),(int) precnik/2, (int)precnik/2);
	}
	
	

}
