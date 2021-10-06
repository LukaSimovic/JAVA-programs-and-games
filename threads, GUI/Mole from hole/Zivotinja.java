package igra;

import java.awt.Graphics;

public abstract class Zivotinja {
	
	protected Rupa rupa;
	
	

	public Zivotinja(Rupa rupa) {
		super();
		this.rupa = rupa;
	}

	public abstract void ispoljiEfekatUdarene();
	public abstract void ispoljiEfekatPobegle();
	
	public abstract void iscrtaj(int k, int b, Graphics g);

	

}
