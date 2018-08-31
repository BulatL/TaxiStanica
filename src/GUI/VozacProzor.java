package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class VozacProzor extends JFrame {

	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private String  				  username;
	private JButton     			  btnPrikazVoznji;
	private JButton     			  btnIzaberiteVoznju;
	private JMenuBar    			  mainMenu;
	private JMenu       			  taxiStanicaMenu;
	private JMenuItem   			  taxiStanicaItem;
	private JMenu      				  menuOdjaviSe;
	private JMenuItem  				  odjaviSeItem;
	
	
	public VozacProzor(TaxiSluzbaFunkcionalnosti taxiStanica, String username){
		this.taxiStanica = taxiStanica;
		this.username    = username;
		setTitle("Dobrodosli");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 350);
		setResizable(false);
		initGUI();
		iitActions();
		//pack();
		}


	private void initGUI() {
		MigLayout mig = new MigLayout("align 50% 50%");
		setLayout(mig);
		
		mainMenu = new JMenuBar();
		
		btnPrikazVoznji    = new JButton("Prikazi sve moje voznje");
		btnIzaberiteVoznju = new JButton("Preuzmi slobodnu voznju");
		
		taxiStanicaMenu = new JMenu("Taxi stanica");
		taxiStanicaItem = new JMenuItem("Infromacije");
		taxiStanicaMenu.add(taxiStanicaItem);
		mainMenu.add(taxiStanicaMenu);
		
		menuOdjaviSe    = new JMenu("Odjavi se");
		odjaviSeItem    = new JMenuItem("Odjavi se");
		menuOdjaviSe.add(odjaviSeItem);
	
		mainMenu.add(menuOdjaviSe);
		
		setJMenuBar(mainMenu);
		
		add(btnPrikazVoznji,"wrap");
		add(btnIzaberiteVoznju);
		
	}


	private void iitActions() {


		taxiStanicaItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformacijeTaxiStanice ifts = new InformacijeTaxiStanice(VozacProzor.this.taxiStanica, username);
				ifts.setVisible(true);
				
				//VozacProzor.this.dispose();
				//VozacProzor.this.setVisible(false);
			}
		});
		
		odjaviSeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor lp = new LoginProzor(VozacProzor.this.taxiStanica);
				lp.setVisible(true);
				
				VozacProzor.this.dispose();
				VozacProzor.this.setVisible(false);
				
			}
		});
		
		btnPrikazVoznji.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VozacVoznjeTabela vvt = new VozacVoznjeTabela(taxiStanica, username);
				vvt.setVisible(true);
				
				
			}
		});
		
		btnIzaberiteVoznju.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SlobodneVoznjeTabela svt = new SlobodneVoznjeTabela(taxiStanica, username);
				svt.setVisible(true);
				
				
				
			}
		});
		
	}
	
}