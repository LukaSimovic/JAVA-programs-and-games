package igra;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Basta extends Panel implements Runnable {
	
	private Rupa[][] matrica;
	private int kolicinaPovrca,dt,brKoraka;
	private Label kolicina;
	
	private boolean radi=false;
	private Thread nit;
	
	public Basta(int v, int k) {
		setBackground(new Color(14,185,0));
		matrica=new Rupa[v][k];
		setLayout(new GridLayout(v,k,20,20));
		for(int i=0; i<v; i++) {
			for(int j=0; j<k; j++) {
				matrica[i][j]=new Rupa(this);
				add(matrica[i][j]);
			}
		}
		kolicinaPovrca=100;
		nit=new Thread(this);
		nit.start();
		
		dodajOsluskivac();
	}
	
	private void dodajOsluskivac() {
		for(int i=0; i<matrica.length; i++) {
			for(int j=0; j<matrica[0].length; j++) {
				int x=i; int y=j;
				matrica[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if(nit.isAlive())
							matrica[x][y].zgazi();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if(nit.isAlive())
							matrica[x][y].zgazi();
					}

					@Override
					public void mouseDragged(MouseEvent e) {
						if(nit.isAlive())
							matrica[x][y].zgazi();
					}
					
				});
			}
		}
		
	}

	public void postaviLabelu(Label l) { kolicina=l;}
	private void azurirajLabelu() {
		if(kolicina==null) return;
		kolicina.setText("Povrce: "+kolicinaPovrca);
	}
	public void setDt(int dt) {
		this.dt=dt;
	}
	public int getBrKoraka() {
		return brKoraka;
	}
	public void setBrKoraka(int b) {
		brKoraka=b;
		for(int i=0; i<matrica.length; i++) {
			for(int j=0; j<matrica[0].length; j++) {
				matrica[i][j].setBrKoraka(b);
			}
		}
	}
	public void smanjiKolicinuPovrca() {
		kolicinaPovrca-=1;
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
				azurirajLabelu();
				repaint();
				
			} catch (InterruptedException e) {
				break;
			}
		}

	}

	private void azuriraj() {
		while(true) {
			int x=(int)(Math.random()*matrica.length);
			int y=(int)(Math.random()*matrica[0].length);
			if(matrica[x][y].slobodna()) {
				Krtica k = new Krtica(matrica[x][y]);
				matrica[x][y].stvori();
				matrica[x][y].kreni();
				matrica[x][y].setZivotinja(k);
				setDt((int)(dt-((double)dt)/100));
				break;
			}
		}
		
		if(kolicinaPovrca==0)
			zaustavi();
		
	}
	
	public synchronized void kreni() {
		repaint();
		radi=true;
		notify();
	}
	public void zaustavi() {
		nit.interrupt();
		for(int i=0; i<matrica.length; i++) {
			for(int j=0; j<matrica[0].length; j++)
				matrica[i][j].zaustavi();
		}
	}

}
