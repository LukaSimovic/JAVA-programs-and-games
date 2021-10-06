package igra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	private Scena scena= new Scena(this);
	
	
	public Igra() {
		super("Baloni");
		setBounds(600,200,500,500);
		scena.kreni();
		add(scena, BorderLayout.CENTER);
		//scena.setSize(this.getWidth()-14, this.getHeight()-37);
		//postavi na dimenzije igre ukoliko se igrac ne bude iscrtavao,
		//tj. ako scena.getw i geth vracaju 0 kada se igrac pravi, inace obrisi
		//ako postavljam ove dimenzije, moze se izbrisati if nakon pravljenja objekta igraca
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				scena.zaustavi();
			}
			
		});
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Igra();
	}

}
