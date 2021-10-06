package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import igra.Igra.Rezim;

public class Igra extends Frame {
	
	private Mreza mreza=new Mreza(this);
	private Label novcica = new Label("Novcica: "),
			poena = new Label("Poena: 0"),
			podloga = new Label("Podloga: ");
	private TextField brN=new TextField("15",1);
	private Button pocni = new Button("Pocni");
	private Checkbox podloge[]=new Checkbox[2];
	
	public enum Rezim{IZMENA,IGRANJE};
	Rezim izabraniRezim;
	boolean odigrano=false;
	boolean uToku=false;
	
	
	public Igra() {
		super("Igra");
		setBounds(500,200,550,550);
		
		
		
		add(mreza,BorderLayout.CENTER);
		
		add(istocni(), BorderLayout.EAST);
		add(juzni(), BorderLayout.SOUTH);
		dodajMeni();
		
		dodajOsluskivac();
		
		setVisible(true);
	}

	private void dodajOsluskivac() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				for(int i=0; i<mreza.getTenkovi().size(); i++)
					mreza.getTenkovi().get(i).zaustavi();
				mreza.zaustavi();
				dispose();
			}
			
		});
		
		
	}

	private Panel istocni() {
		Panel istok = new Panel(new BorderLayout());
		istok.add(podloga, BorderLayout.WEST);
		CheckboxGroup grupa = new CheckboxGroup();
		podloge[0] = new Checkbox("Trava     ", grupa, true);
		podloge[0].setBackground(Color.GREEN);
		podloge[1] = new Checkbox("Zid       ", grupa, false);
		podloge[1].setBackground(Color.LIGHT_GRAY);
		
		Panel izbor = new Panel(new GridLayout(0,1));
		izbor.add(podloge[0]); izbor.add(podloge[1]);
		istok.add(izbor, BorderLayout.EAST);
		return istok;
	}

	private Panel juzni() {
		Panel jug = new Panel();
		jug.add(novcica);
		jug.add(brN);
		jug.add(poena);
		mreza.postaviLabelu(poena);
		jug.add(pocni);
		pocni.addActionListener(ae->{
			if(izabraniRezim==Rezim.IGRANJE && odigrano==false) {
				uToku=true;
				mreza.kreni(Integer.parseInt(brN.getText()));
				mreza.requestFocus();
				mreza.setFocusable(true);
			}
		});
		return jug;
	}

	private void dodajMeni() {
		MenuBar bar = new MenuBar();
		setMenuBar(bar);
		
		Menu rezim = new Menu("Rezim");
		bar.add(rezim);
		
		MenuItem izmena = new MenuItem("Rezim izmena");
		rezim.add(izmena);
		izmena.addActionListener(ae->{
			if(izabraniRezim==Rezim.IGRANJE && uToku==true) {
				mreza.zaustavi();
				return;
			}
			izabraniRezim=Rezim.IZMENA;
		});
		MenuItem igranje = new MenuItem("Rezim igranje");
		rezim.add(igranje);
		igranje.addActionListener(ae->{
			izabraniRezim=Rezim.IGRANJE;
		});
		mreza.postaviCb(podloge[0], podloge[1]);
		
		
	}
	
	public static void main(String[] args) {
		new Igra();
	}
}
