package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Trava extends Polje {

	public Trava(Mreza mreza) {
		super(mreza);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dozvoljeno(Figura f) {
		return true;
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.GREEN);

	}

}
