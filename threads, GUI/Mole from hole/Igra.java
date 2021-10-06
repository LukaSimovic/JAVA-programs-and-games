package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	private static Igra primerak=null;
	
	private Basta basta = new Basta(4,4);
	
	private Button kreni = new Button("Kreni");
	private Checkbox[] tezine = new Checkbox[3];
	private Label povrce=new Label("Povrce: 100"),
			tezina=new Label("Tezina:");
	
	private Igra() {
		super("Igra");
		setBounds(600,200,500,400);
		add(basta,BorderLayout.CENTER);
		add(istocni(), BorderLayout.EAST);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				basta.zaustavi();
			}
			
		});
		
		setVisible(true);
	}

	private Panel istocni() {
		Panel istokGore = new Panel(new GridLayout(0,1,0,5));
		istokGore.add(tezina);
		tezina.setFont(new Font(null, Font.BOLD, 18));
		tezina.setAlignment(Label.CENTER);
		CheckboxGroup grupa = new CheckboxGroup();
		tezine[0] = new Checkbox("Lako",grupa,false);
		tezine[1] = new Checkbox("Srednje",grupa,true);
		tezine[2] = new Checkbox("Tesko",grupa,false);
		istokGore.add(tezine[0]); istokGore.add(tezine[1]); istokGore.add(tezine[2]);
		istokGore.add(kreni);
		kreni.addActionListener(ae->{
			if(kreni.getLabel()=="Kreni") {
				kreni.setLabel("Stani");
				if(tezine[0].getState()) {
					basta.setDt(1000); basta.setBrKoraka(10);
				}
				else if(tezine[1].getState()) {
					basta.setDt(750); basta.setBrKoraka(8);
				}
				else if(tezine[2].getState()) {
					basta.setDt(500); basta.setBrKoraka(6);
				}
				basta.kreni();
			}else {
				kreni.setLabel("Kreni");
				basta.zaustavi();
			}
		});
		Panel istokDole=new Panel(new CardLayout());
		istokDole.add(povrce);
		povrce.setFont(new Font(null, Font.BOLD, 18));
		povrce.setAlignment(Label.CENTER);
		basta.postaviLabelu(povrce);
		Panel istok = new Panel(new GridLayout(0,1));
		istok.add(istokGore); istok.add(istokDole);
		return istok;
	}
	
	public static Igra getIgra() {
		if(primerak==null)
			primerak=new Igra();
		return primerak;
	}
	
	public static void main(String[] args) {
		new Igra();
	}
}
