package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Tenk extends Figura implements Runnable {
	
	private Thread nit;
	private int dt=500;
	private boolean radi=true;

	public Tenk(Polje polje) {
		super(polje);
		
		nit = new Thread(this);
		nit.start();
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				synchronized(this) {
					while(!radi)
						wait();
				}
				
				
				Thread.sleep(dt);
				azuriraj();
				polje.repaint();
			} catch (InterruptedException e) {
				break;
			}
		}

	}
	private void azuriraj() {
		int smer=(int)(Math.random()*4);
		switch(smer) {
		case 0: polje.repaint();
			if(polje.dohvatiPolje(-1, 0)!=null) {
				Polje p = polje.dohvatiPolje(-1, 0);
				if(p.dozvoljeno(this))
					pomeri(p);
			}
			break;
		case 1: polje.repaint();
		if(polje.dohvatiPolje(1, 0)!=null) {
			Polje p = polje.dohvatiPolje(1, 0);
			if(p.dozvoljeno(this))
				pomeri(p);
		}
		break;
		case 2: polje.repaint();
		if(polje.dohvatiPolje(0, -1)!=null) {
			Polje p = polje.dohvatiPolje(0, -1);
			if(p.dozvoljeno(this))
				pomeri(p);
		}
		break;
		case 3: polje.repaint();
		if(polje.dohvatiPolje(0, 1)!=null) {
			Polje p = polje.dohvatiPolje(0, 1);
			if(p.dozvoljeno(this))
				pomeri(p);
		}
		break;
		}
		
	}

	public synchronized void kreni() {
		radi=true;
		notify();
	}
	public void zaustavi() {
		if(nit!=null)
			nit.interrupt();
	}

	@Override
	public void iscrtaj(Graphics g) {
		g.setColor(Color.BLACK);
		int sirina=polje.getWidth();
		int visina=polje.getHeight();
		
		g.drawLine(0, 0, sirina, visina);
		g.drawLine(0, visina, sirina, 0);

	}

}
