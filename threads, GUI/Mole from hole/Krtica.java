package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	public Krtica(Rupa rupa) {
		super(rupa);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ispoljiEfekatUdarene() {
		rupa.zaustavi();
		rupa.repaint();

	}

	@Override
	public void ispoljiEfekatPobegle() {
		rupa.getBasta().smanjiKolicinuPovrca();
		//rupa.ukradiPovrce();
	}

	@Override
	public void iscrtaj(int k, int b, Graphics g) {
		int sirina=(int)(((double)k/(double)b)*rupa.getWidth());
		int visina=(int)(((double)k/(double)b)*rupa.getHeight());
		
		int sirRupe=rupa.getWidth();
		int visRupe=rupa.getHeight();
		
		g.setColor(Color.DARK_GRAY);
		g.fillOval(sirRupe/2-sirina/2, visRupe/2-visina/2, sirina, visina);
	}

}
