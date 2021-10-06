package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Novcic extends Figura {

	public Novcic(Polje polje) {
		super(polje);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void iscrtaj(Graphics g) {
		g.setColor(Color.YELLOW);
		int sirina=polje.getWidth();
		int visina=polje.getHeight();
		
		g.fillOval(sirina/4, visina/4, sirina/2, visina/2);

	}

}
