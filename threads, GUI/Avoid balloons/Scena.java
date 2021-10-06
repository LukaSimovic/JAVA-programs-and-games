package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Scena extends Canvas implements Runnable {

	
	private Igra igra;
	private List<Balon> baloni = new LinkedList<>();
	private Igrac igrac;
	
	
	private Thread nit;
	private int dt=60;
	private boolean radi=false;
	
	
	
	public Scena(Igra igra) {
		super();
		this.igra = igra;
		
		requestFocus();
		setFocusable(true);
		
		//ako baguje, prebaci ovo za nit u kreni();
		nit = new Thread(this);
		nit.start();
		
		//kreni();
		osluskivac();
		
		
	}

	private void osluskivac() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: pomeriIgraca(false); break;
				case KeyEvent.VK_RIGHT: pomeriIgraca(true); break;

				}
			}
			
		});
		
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				synchronized(this) {
					while(!radi) {
						wait();
					}
				}
				
				
				Thread.sleep(dt);
				azuriraj();
				repaint();
				
				
			} catch (InterruptedException e) {
				break;
			}
		}

	}
	private void azuriraj() { 
		synchronized(this) {
			if(igrac==null) {
				int sirina=this.getWidth(), visina=this.getHeight();
				//System.out.println(sirina+" "+visina);
				//igrac = new Igrac(new Vektor(this.getWidth()/2, this.getHeight()-40), 30,this);
				igrac = new Igrac(new Vektor(sirina/2, visina-40), 30,this);
				if(igrac.centar.getX()==0)
					igrac=null;
				
				return;
			}
		}
		
		generisiBalon();
		protokVremena();
		proveraSudara();
		
	}

	private void proveraSudara() {
		if(igrac!=null) {
			for(int i=0; i<baloni.size(); i++) {
				if(igrac.sudar(baloni.get(i))) {
					zaustavi();
				}
			}
		}
		
	}

	private void protokVremena() {
		for(int i=0; i<baloni.size(); i++) {
			baloni.get(i).protekloVreme(dt/10);
		}
		
	}

	private void generisiBalon() {
		double rnd = Math.random();
		if(rnd<0.1) {
			double sir = Math.random()*this.getWidth();
			Balon b = new Balon(new Vektor(sir,0),Color.RED, 20, new Vektor(0,1),this);
			baloni.add(b);
		}
		
	}

	public synchronized void kreni() {
		baloni.clear(); igrac=null; 
		repaint();
		radi=true;
		notify();
		
	}
	public synchronized void stani() {
		radi=false;
	}
	public void zaustavi() {
		if(nit!=null)
			nit.interrupt();
	}
	
	

	@Override
	public void paint(Graphics g) {
		if(igrac!=null) {
			igrac.iscrtaj(this, g);
		}
		for(int i=0; i<baloni.size(); i++)
			baloni.get(i).iscrtaj(this,g);
	}

	public synchronized void izbaci(KruznaFigura f) {
		if(f instanceof Balon) {
			Balon b= (Balon) f;
			for(int i=0; i<baloni.size(); i++) {
				if(b.equals(baloni.get(i))) {
					baloni.remove(i);
					break;
				}
			}
		}
		//System.out.println(figure.size());
	}
	public synchronized void dodaj(KruznaFigura f) {
		if(f instanceof Balon) {
			Balon b = (Balon) f;
			baloni.add(b);
		}
		
	}
	
	public synchronized void pomeriIgraca(boolean b) {
		 
		if(b) {
			igrac.pomeri(5);
		}else {
			igrac.pomeri(-5);
		}
	}

}
