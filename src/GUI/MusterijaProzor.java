package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class MusterijaProzor extends JFrame {
	private TaxiSluzbaFunkcionalnosti taxiSluzba;
	private String                    username;
	private Boolean                   aplikacija;
	
	private JMenuBar  				  mainMenu;
	private JMenu     			 	  voznjeMenu;
	private JMenuItem  				  voznjeItem;
	private JButton    				  btnzakaziVoznju;
	private JMenu      				  informacijeMenu;
	private JMenuItem  				  informacijeItem;
	private JMenu     				  odjaviSeMenu;
	private JMenuItem  				  odjaviSeItem;
	
	
	
	public MusterijaProzor(TaxiSluzbaFunkcionalnosti taxiSluzba, String username, Boolean aplikacija){
	this.taxiSluzba = taxiSluzba;
	this.username   = username;
	this.aplikacija = aplikacija;
	setTitle("Dobrodosli");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setSize(400, 350);
	setResizable(false);
	initGUI();
	initActions();
	//pack();
	}


	private void initGUI() {
		MigLayout mig = new MigLayout("align 50% 50%");
		setLayout(mig);
		
		mainMenu = new JMenuBar();
		
		voznjeMenu = new JMenu("Voznje");
		voznjeItem = new JMenuItem("Prikazi voznje");
		voznjeMenu.add(voznjeItem);
		
		informacijeMenu = new JMenu("Informacije");
		informacijeItem = new JMenuItem("Infromacije o taksi sluzbi");
		informacijeMenu.add(informacijeItem);
		
		odjaviSeMenu    = new JMenu("Odjavi se");
		odjaviSeItem    = new JMenuItem("Odjavi se");
		odjaviSeMenu.add(odjaviSeItem);
		
		mainMenu.add(voznjeMenu);
		mainMenu.add(informacijeMenu);
		mainMenu.add(odjaviSeMenu);
		
		setJMenuBar(mainMenu);
		
		btnzakaziVoznju = new JButton("Zakazi voznju preko aplikacije");
		
		add(btnzakaziVoznju,"wrap");
		if(aplikacija == false){
			btnzakaziVoznju.setEnabled(false);
			add(new JLabel("Nemate aplikaciju"));
		}
		add(new JLabel(),"wrap");
		add(new JLabel(),"wrap");
	}

	private void initActions() {
		
		btnzakaziVoznju.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VoznjaAplikacijaFrame va = new VoznjaAplikacijaFrame(taxiSluzba, null, username);
				va.setVisible(true);
				
				//KorisnikProzor.this.dispose();
				//KorisnikProzor.this.setVisible(false);
				
			}
		});
		
		voznjeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MusterijeVoznjeTabela kpv = new MusterijeVoznjeTabela(MusterijaProzor.this.taxiSluzba,username);
				kpv.setVisible(true);
				
				//KorisnikProzor.this.dispose();
				//KorisnikProzor.this.setVisible(false);
			}
		});
		
		odjaviSeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor lp = new LoginProzor(MusterijaProzor.this.taxiSluzba);
				lp.setVisible(true);
				
				MusterijaProzor.this.dispose();
				MusterijaProzor.this.setVisible(false);
				
			}
		});
		
		informacijeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformacijeTaxiStanice ifts = new InformacijeTaxiStanice(MusterijaProzor.this.taxiSluzba, username);
				ifts.setVisible(true);
				
				//KorisnikProzor.this.dispose();
				//KorisnikProzor.this.setVisible(false);
				
			}
		});
		
	}


	
}