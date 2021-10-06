package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Rupa extends Canvas implements Runnable {
	
	private Basta basta;
	private Zivotinja zivotinja=null;
	
	private Thread nit;
	private boolean radi=false;
	private boolean pokrenuta=false;
	private boolean slobodna=true;
	
	private int brKoraka;
	private int korak;
	
	
	public Rupa(Basta basta) {
		super();
		this.basta = basta;
		korak=1;
	}
	
	
	
	public Zivotinja getZivotinja() {
		return zivotinja;
	}
	public void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}



	public void zgazi() {
		if(zivotinja!=null)
			zivotinja.ispoljiEfekatUdarene();
	}
	
	public void setBrKoraka(int b) {
		brKoraka=b;
	}
	
	

	@Override
	public void paint(Graphics g) {
		setBackground(new Color(134,69,1));
		
		if(zivotinja!=null)
			zivotinja.iscrtaj(korak,brKoraka,g);
	}



	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				synchronized(this) {
					while(!radi)
						wait();
				}
				
				Thread.sleep(100);
				povecavajZivotinju();
				repaint();
				
				if(korak==brKoraka) {
					Thread.sleep(2000);
					zivotinja.ispoljiEfekatPobegle();
					zaustavi();
					repaint();
				}
			} catch (InterruptedException e) {
				break;
			}
		}

	}

	private void povecavajZivotinju() {
		if(korak<brKoraka)
			korak++;
		
	}
	
	public synchronized void stvori() {
		pokrenuta=true;
		slobodna=false;
		nit=new Thread(this);
		nit.start();
	}
	public synchronized void kreni() {
		zivotinja=null;
		korak=1;
		repaint();
		radi=true;
		notify();
	}
	public void zaustavi() {
		pokrenuta=false;
		slobodna=true;
		korak=0;
		if(nit!=null) {
			nit.interrupt();
			zivotinja=null;
		}
	}
	
	public synchronized boolean pokrenuta() {
		return pokrenuta;
	}
	public synchronized boolean slobodna() {
		return slobodna;
	}
	
	public Basta getBasta() {
		return basta;
	}
	/*
	public void ukradiPovrce() {
		basta.smanjiKolicinuPovrca();
	}
	*/

}
