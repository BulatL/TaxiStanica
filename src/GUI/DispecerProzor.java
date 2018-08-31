package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import enumeracija.TelefonskoOdeljenje;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class DispecerProzor extends JFrame {
	
	private TaxiSluzbaFunkcionalnosti taxiSluzba;
	private String                    username;
	
	private JMenuBar  				  mainMenu;
	private JMenu      				  voznjeMenu;
	private JMenuItem  				  voznjeItem;
	private JMenu                     musterijeMenu;
	private JMenuItem                 musterijeItem;
	private JMenu                     zaposleniMenu;
	private JMenuItem                 vozaciItem;
	private JMenuItem                 dispeceriItem;
	private JMenu                     automobiliMenu;
	private JMenuItem                 automobiliItem;
	private JMenu      				  informacijeMenu;
	private JMenuItem  				  informacijeItem;
	private JMenu      				  odjaviSeMenu;
	private JMenuItem  				  odjaviSeItem;
	private JButton                   btnZakaziVoznju;
	
	
	public DispecerProzor(TaxiSluzbaFunkcionalnosti taxiSluzba, String username){
		this.taxiSluzba   = taxiSluzba;
		this.username     = username;

		setTitle("Dobrodosli");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 350);
		setResizable(false);
		initGUI();
		initActions();

	}

	private void initGUI() {
		MigLayout mig = new MigLayout("align 50% 50%");
		setLayout(mig);
		
		mainMenu              = new JMenuBar();
		
		voznjeMenu            = new JMenu("Voznje");
		voznjeItem            = new JMenuItem("Voznje");
		voznjeMenu.add(voznjeItem);
		mainMenu.add(voznjeMenu);
		
		musterijeMenu        = new JMenu("Musterije");
		musterijeItem        = new JMenuItem("Musterije");
		musterijeMenu.add(musterijeItem);
		mainMenu.add(musterijeMenu);
		
		zaposleniMenu        = new JMenu("Zaposleni");
		vozaciItem           = new JMenuItem("Vozaci");
		dispeceriItem        = new JMenuItem("Dispeceri");
		zaposleniMenu.add(vozaciItem);
		zaposleniMenu.add(dispeceriItem);
		mainMenu.add(zaposleniMenu);
		
		automobiliMenu       = new JMenu("Automobili");
		automobiliItem       = new JMenuItem("Automobili");
		automobiliMenu.add(automobiliItem);
		mainMenu.add(automobiliMenu);
		
		informacijeMenu      = new JMenu("Informacije");
		informacijeItem      = new JMenuItem("Informacije");
		informacijeMenu.add(informacijeItem);
		mainMenu.add(informacijeMenu);
		
		odjaviSeMenu         = new JMenu("Odjavi se");
		odjaviSeItem         = new JMenuItem("Odjavi se");
		odjaviSeMenu.add(odjaviSeItem);
		mainMenu.add(odjaviSeMenu);
		
		btnZakaziVoznju      = new JButton("Zakazite novu voznju");
		add(btnZakaziVoznju);
		
		
		setJMenuBar(mainMenu);
	}
	
	private void initActions() {
		odjaviSeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor lp = new LoginProzor(DispecerProzor.this.taxiSluzba);
				lp.setVisible(true);
				
				DispecerProzor.this.dispose();
				DispecerProzor.this.setVisible(false);
				
			}
		});
		
		informacijeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformacijeTaxiStanice ifts = new InformacijeTaxiStanice(DispecerProzor.this.taxiSluzba, username);
				ifts.setVisible(true);
				
				//DispecerProzor.this.dispose();
				//DispecerProzor.this.setVisible(false);
				
			}
		});
		
		automobiliItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobiliTabela ap = new AutomobiliTabela(taxiSluzba);
				ap.setVisible(true);
				
				
			}
		});
		
		musterijeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeTabela mt = new MusterijeTabela(taxiSluzba);
				mt.setVisible(true);
				
				
				
			}
		});
		
		vozaciItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VozaciTabela vt = new VozaciTabela(taxiSluzba);
				vt.setVisible(true);
				
			}
		} );
		
		dispeceriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DispeceriTabela dt = new DispeceriTabela(taxiSluzba);
				dt.setVisible(true);
				
				
			}
		});
		
		vozaciItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnZakaziVoznju.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaDispecerFrame vdf = new VoznjaDispecerFrame(taxiSluzba , null, username);
				vdf.setVisible(true);
			}
		});
		
	}

	
}