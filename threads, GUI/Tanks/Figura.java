package igra;

import java.awt.Graphics;

public abstract class Figura {
	
	protected Polje polje;

	public Figura(Polje polje) {
		super();
		this.polje = polje;
	}

	public Polje getPolje() {
		return polje;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figura other = (Figura) obj;
		return polje.equals(other.polje);
	}
	
	public void pomeri(Polje p) {
		polje=p;
	}
	
	public abstract void iscrtaj(Graphics g);
	
	
}
