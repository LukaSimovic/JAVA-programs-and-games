package igra;

import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import igra.Igra.Rezim;

public class Mreza extends Panel implements Runnable {
	
	private Polje[][] matrica;
	private Igra igra;
	
	private int poeni=0;
	private Label lpoeni;
	private int brNovcica;
	
	private Checkbox tr,zd;
	
	private Igrac igrac;
	private List<Novcic> novcici = new LinkedList<>();
	private List<Tenk> tenkovi = new LinkedList<>();
	
	private boolean radi=false;
	private int dt=40;
	private Thread nit;
	
	public Mreza(int dim, Igra igra) {
		matrica = new Polje[dim][dim];
		this.igra=igra;
		
		setLayout(new GridLayout(dim,dim));
		for(int i=0; i<dim; i++) {
			for(int j=0; j<dim; j++) {
				double rnd=Math.random();
				if(rnd<0.8)
					matrica[i][j]=new Trava(this);
				else
					matrica[i][j]=new Zid(this);
				add(matrica[i][j]);
			}
		}
		
		requestFocus();
		setFocusable(true);
		
		osluskivaci();
		
		nit=new Thread(this);
		nit.start();
	}
	public Mreza(Igra i) {
		this(17,i);
	}
	
	public void postaviLabelu(Label l) {
		lpoeni=l;
	}
	public void postaviCb(Checkbox t, Checkbox z) {
		tr=t; zd=z;
	}
	public void azurirajLabelu() {
		if(lpoeni==null || igrac==null)
			return;
		lpoeni.setText("Poena:  "+poeni);
	}
	private void osluskivaci() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(nit.isAlive()) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_A:
						if(igrac.polje.dohvatiPolje(0, -1)!=null) {
							igrac.polje.repaint();
							Polje p = igrac.polje.dohvatiPolje(0, -1);
							if(p.dozvoljeno(igrac))
								igrac.pomeri(p);
						}
						break;
					case KeyEvent.VK_S:
						if(igrac.polje.dohvatiPolje(1, 0)!=null) {
							igrac.polje.repaint();
							Polje p = igrac.polje.dohvatiPolje(1, 0);
							if(p.dozvoljeno(igrac))
								igrac.pomeri(p);
						}
						break;
					case KeyEvent.VK_D:
						if(igrac.polje.dohvatiPolje(0, 1)!=null) {
							igrac.polje.repaint();
							Polje p = igrac.polje.dohvatiPolje(0, 1);
							if(p.dozvoljeno(igrac))
								igrac.pomeri(p);
						}
						break;
					case KeyEvent.VK_W:
						if(igrac.polje.dohvatiPolje(-1, 0)!=null) {
							igrac.polje.repaint();
							Polje p = igrac.polje.dohvatiPolje(-1,0);
							if(p.dozvoljeno(igrac))
								igrac.pomeri(p);
						}
						break;
					}
					
				}
			}
			
		});
		
		for(int i=0; i<matrica.length; i++) {
			for(int j=0; j<matrica.length; j++) {
				int x=i; int y=j;
				Mreza m=this;
				matrica[i][j].addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						requestFocus();
						if(igra.izabraniRezim==Rezim.IZMENA && igra.odigrano==false && igra.uToku==false) {
							remove(x*matrica.length+y);
							if(tr.getState()) {
								matrica[x][y]=new Trava(m);
								add(matrica[x][y], x*matrica.length+y);
								revalidate();
								matrica[x][y].repaint();
							}else if(zd.getState()){
								matrica[x][y]=new Zid(m);
								add(matrica[x][y], x*matrica.length+y);
								revalidate();
								matrica[x][y].repaint();
							}
							dodajNoviOsluskivac(x,y);
						}
					}

					
					
				});
			}
		}
		
		
			
		
	}
	
	private void dodajNoviOsluskivac(int x, int y) {
		Mreza m=this;
		matrica[x][y].addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				requestFocus();
				if(igra.izabraniRezim==Rezim.IZMENA && igra.odigrano==false && igra.uToku==false) {
					remove(x*matrica.length+y);
					if(tr.getState()) {
						matrica[x][y]=new Trava(m);
						add(matrica[x][y], x*matrica.length+y);
						revalidate();
						matrica[x][y].repaint();
					}else if(zd.getState()) {
						matrica[x][y]=new Zid(m);
						add(matrica[x][y], x*matrica.length+y);
						revalidate();
						matrica[x][y].repaint();
					}
					dodajNoviOsluskivac(x,y);
				}
			}
			
		});
		
	}
	

	public Polje[][] getMatrica() {
		return matrica;
	}
	public Igrac getIgrac() {
		return igrac;
	}
	public List<Novcic> getNovcici() {
		return novcici;
	}
	public List<Tenk> getTenkovi() {
		return tenkovi;
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
		if(igrac==null) {
			postaviIgraca();
			postaviNovcice();
			postaviTenkove();
		}
		
		//tenk jede igraca?
		int j=0;
		while(j<tenkovi.size()) {
			if(tenkovi.get(j).polje==igrac.polje) {
				igrac=null;
				zaustavi();
				return;
			}
			j++;
		}
		
		//igrac jede novcic?
		int i=0;
		while(i<novcici.size()) {
			if(novcici.get(i).polje==igrac.polje) {
				Polje p = novcici.get(i).polje;
				novcici.remove(i);
				p.repaint();
				poeni++;
			}
			i++;
		}
		
		//pojedeni svi novcici?
		if(novcici.size()==0) {
			System.out.println("POBEDA");
			novcici.clear();
			zaustavi();
			repaint();
		}
		
	}
	public synchronized void kreni(int x) {
		brNovcica=x;
		
		while(!tenkovi.isEmpty()) {
			for(int i=0; i<tenkovi.size(); i++) {
				Polje p = tenkovi.get(i).polje;
				tenkovi.get(i).zaustavi();
				tenkovi.remove(i);
				p.repaint();
			}
		}
		if(igrac!=null) {
			Polje p = igrac.polje;
			igrac=null;
			p.repaint();
		}
		while(!novcici.isEmpty()) {
			for(int i=0; i<novcici.size(); i++) {
				Polje p=novcici.get(i).polje;
				novcici.remove(i);
				p.repaint();
			}
		}
		
		poeni=0;
		repaint();
		azurirajLabelu();
		radi=true;
		notify();
	}
	public void zaustavi() {
		igra.odigrano=true;
		igra.uToku=false;
		if(nit!=null)
			nit.interrupt();
		for(int i=0; i<tenkovi.size(); i++) {
			Polje p = tenkovi.get(i).polje;
			tenkovi.get(i).zaustavi();
			p.repaint();
		}
		repaint();
	}
	
	
	
	
	private void postaviIgraca() {
		do {
			int x=(int)(Math.random()*matrica.length);
			int y=(int)(Math.random()*matrica.length);
			Igrac i=null;
			if(matrica[x][y].dozvoljeno(i)) {
				igrac=new Igrac(matrica[x][y]);
				break;
			}
		}while(true);	
	}
	private void postaviNovcice() {
		
		for(int i=0; i<brNovcica; i++) {
			do {
				int x=(int)(Math.random()*matrica.length);
				int y=(int)(Math.random()*matrica.length);
				Novcic n=new Novcic(matrica[x][y]);
				if(matrica[x][y].dozvoljeno(n) && !novcici.contains(n) && !novcici.contains(igrac)) {
					novcici.add(n);
					break;
				}
			}while(true);	
		}
	}
	private void postaviTenkove() {
		for(int i=0; i<brNovcica/3; i++) {
			do {
				int x=(int)(Math.random()*matrica.length);
				int y=(int)(Math.random()*matrica.length);
				Tenk t=null;
				if(matrica[x][y].dozvoljeno(t)) {
					t=new Tenk(matrica[x][y]);
					tenkovi.add(t);
					break;
				}
			}while(true);	
		}
	}
	
	
	
	
	@Override
	public void paint(Graphics g) {
		
		if(igrac!=null) {
			g=igrac.polje.getGraphics();
			igrac.iscrtaj(g);
		}
		
		for(Novcic n : novcici) {
			g=n.polje.getGraphics();
			n.iscrtaj(g);
		}
		
		for(Tenk t: tenkovi) {
			g=t.polje.getGraphics();
			t.iscrtaj(g);
		}
	}
	
	

}
