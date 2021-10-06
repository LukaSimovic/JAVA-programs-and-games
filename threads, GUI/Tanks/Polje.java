package igra;

import java.awt.Canvas;
import java.awt.Graphics;

public abstract class Polje extends Canvas {
	
	protected Mreza mreza;

	public Polje(Mreza mreza) {
		super();
		this.mreza = mreza;
	}

	public Mreza getMreza() {
		return mreza;
	}
	
	public int[] odrediPoziciju() {
		int vr,kol;
		for(int i=0; i<mreza.getMatrica().length; i++) {
			for(int j=0; j<mreza.getMatrica().length; j++) {
				if(mreza.getMatrica()[i][j].equals(this)) {
					vr=i; kol=j;
					return new int[] {vr,kol};
				}
			}
		}
		return null;
	}
	
	public Polje dohvatiPolje(int pomVr,int pomKol) {
		int[] tren = odrediPoziciju();
		int vr = tren[0],kol=tren[1];
		if(vr+pomVr>=mreza.getMatrica().length || vr+pomVr<0 ||
				kol+pomKol>=mreza.getMatrica().length || kol+pomKol<0)
			return null;
		return mreza.getMatrica()[vr+pomVr][kol+pomKol];
	}
	
	public abstract boolean dozvoljeno(Figura f);

	@Override
	public abstract void paint(Graphics g);
	
	
}
