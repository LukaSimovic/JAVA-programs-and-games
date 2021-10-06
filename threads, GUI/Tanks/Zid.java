package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Zid extends Polje {

	public Zid(Mreza mreza) {
		super(mreza);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dozvoljeno(Figura f) {
		
		return false;
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.LIGHT_GRAY);

	}

}
