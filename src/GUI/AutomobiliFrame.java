package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobil.Automobil;
import enumeracija.VrstaAutomobila;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class AutomobiliFrame extends JFrame {

	private TaxiSluzbaFunkcionalnosti  taxiSluzba;
	private Automobil                  automobilZaIzmenu;
	private String                     vrstaAutomobilaPutnicki   = "Putnicki";
	private String                     vrstaAutomobilaKombi      = "kombi";
	
	private JLabel                     lblProizvodjac            = new JLabel("Proizvodjac");
	private JLabel                     lblModel                  = new JLabel("Model");
	private JLabel                     lblGodinaProizvodnje      = new JLabel("Godina proizvodnje");
	private JLabel                     lblBrojRegistarskeOznake  = new JLabel("Broj registarske oznake");
	private JLabel                     lblBrojTaxiVozila         = new JLabel("Broj taxi vozila");
	private JLabel                     lblVrstaAutomobila        = new JLabel("Vrsta automobila");
	private JTextField                 txtProizvodjac            = new JTextField(20);
	private JTextField                 txtModel                  = new JTextField(20);
	private JTextField                 txtGodinaProizvodnje      = new JTextField(20);
	private JTextField                 txtBrojRegistarskeOznake  = new JTextField(20);
	private JTextField                 txtBrojTaxiVozila         = new JTextField(20);
	private JComboBox<String>          cbVrstaAutomobila         = new JComboBox<String>();
	private JButton                    btnDalje                  = new JButton("Dalje");
	private JButton                    btnNazad                  = new JButton("Nazad");
	
	public AutomobiliFrame(TaxiSluzbaFunkcionalnosti taxiSLuzba, Automobil automobil){
		this.taxiSluzba        = taxiSLuzba;
		this.automobilZaIzmenu = automobil;
		if(this.automobilZaIzmenu != null){
			setTitle("Izmeni automobil ");
		}else {
			setTitle("Dodaj automobbil");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {
		
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
		setLayout(mig);
		if(automobilZaIzmenu != null){
		popuniPolja();
		cbVrstaAutomobila.setEnabled(false);
		txtBrojTaxiVozila.setEnabled(false);
		String vrstaAutomobila =automobilZaIzmenu.getVrstaAutomobila().toString();
		cbVrstaAutomobila.addItem(vrstaAutomobila);
		}
		
		

		
		add(lblProizvodjac);
		add(txtProizvodjac);
		add(lblModel);
		add(txtModel);
		add(lblGodinaProizvodnje);
		add(txtGodinaProizvodnje);
		add(lblBrojRegistarskeOznake);
		add(txtBrojRegistarskeOznake);
		add(lblBrojTaxiVozila);
		add(txtBrojTaxiVozila);
		add(lblVrstaAutomobila);
		if(automobilZaIzmenu == null){
			cbVrstaAutomobila.addItem(vrstaAutomobilaPutnicki);
			cbVrstaAutomobila.addItem(vrstaAutomobilaKombi);
		}
		add(cbVrstaAutomobila);
		add(new JLabel());
		add(btnDalje,"split 2");
		add(btnNazad);
	}

	private void initActions() {
		btnDalje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String proizvodjac          = txtProizvodjac.getText().trim(); 
				String model                = txtModel.getText().trim();
				int godinaProizvodnje       = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
				int brojRegistarskeOznake   = Integer.parseInt(txtBrojRegistarskeOznake.getText().trim());		
				String brojTaxiVozila       = txtBrojTaxiVozila.getText().trim();
				int vrstaAutomobila         = cbVrstaAutomobila.getSelectedIndex();


				if (automobilZaIzmenu != null){
					if(proveriUnosEdit()){
						Automobil auto = (Automobil) automobilZaIzmenu;		
						auto.setProizvodjac(proizvodjac);
						auto.setModel(model);
						auto.setGodinaProizvodnje(godinaProizvodnje);
						auto.setBrRegistarskeOznake(brojRegistarskeOznake);
						auto.setBrTaxiVozila(brojTaxiVozila);
						if(vrstaAutomobila == 0){
							VrstaAutomobila vrstaA      = VrstaAutomobila.PutnickiAautomobil;
							auto.setVrstaAutomobila(vrstaA);
						}else{
							VrstaAutomobila vrstaA      = VrstaAutomobila.Kombi;
							auto.setVrstaAutomobila(vrstaA);
							}
					}
					
				}else {
					if(proveriUnosADD()){
						if(vrstaAutomobila == 0){
							VrstaAutomobila vrstaA      = VrstaAutomobila.PutnickiAautomobil;
						Automobil auto = new Automobil(proizvodjac, model, godinaProizvodnje, 
								brojRegistarskeOznake, brojTaxiVozila, vrstaA);
						taxiSluzba.dodajAutomobil(auto);
						
						}else{
							VrstaAutomobila vrstaA      = VrstaAutomobila.Kombi;
						Automobil auto = new Automobil(proizvodjac, model, godinaProizvodnje, 
								brojRegistarskeOznake, brojTaxiVozila, vrstaA);
						taxiSluzba.dodajAutomobil(auto);
						}
					}
				}
				taxiSluzba.sacuvajAutomobile();
				JOptionPane.showMessageDialog(null, "Uspesno ste dodali/izmenili automobil",
						"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				AutomobiliFrame.this.dispose();
				AutomobiliFrame.this.setVisible(false);
				
			}
		});
		
	}
	
	public void popuniPolja(){
		txtProizvodjac.setText(automobilZaIzmenu.getProizvodjac());
		txtModel.setText(automobilZaIzmenu.getModel());
		txtGodinaProizvodnje.setText(String.valueOf(automobilZaIzmenu.getGodinaProizvodnje()));
		txtBrojRegistarskeOznake.setText(String.valueOf(automobilZaIzmenu.getBrRegistarskeOznake()));
		txtBrojTaxiVozila.setText(automobilZaIzmenu.getBrTaxiVozila());
		
		
	}
	
	private boolean proveriUnosADD(){
		if (txtProizvodjac.getText().trim().equals("") || 
				txtModel.getText().trim().equals("")||
				txtGodinaProizvodnje.getText().trim().equals("")||
				txtBrojRegistarskeOznake.getText().trim().equals("")||
				txtBrojTaxiVozila.getText().trim().equals("")){
				return false;
			}
			try {
				txtProizvodjac.getText().trim();
				txtModel.getText().trim();
				Integer.parseInt(txtGodinaProizvodnje.getText().trim());
				Integer.parseInt(txtBrojRegistarskeOznake.getText().trim());
				Integer.parseInt(txtBrojTaxiVozila.getText().trim());
			}catch (NumberFormatException nfe) {
				return false;
			}

			String proveriBrojTaxiVozila =txtBrojTaxiVozila.getText().trim();
			if(taxiSluzba.nadjiAutomobil(proveriBrojTaxiVozila)!= null){
				JOptionPane.showMessageDialog(null, "Broj taxi vozila mora biti jedinstven, izaberite novi",
						"Greska", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		return true;
	}
	
	private boolean proveriUnosEdit(){
		
		if (txtProizvodjac.getText().trim().equals("") || 
				txtModel.getText().trim().equals("")||
				txtGodinaProizvodnje.getText().trim().equals("")||
				txtBrojRegistarskeOznake.getText().trim().equals("")){
				return false;
			}
			try {
				txtProizvodjac.getText().trim();
				txtModel.getText().trim();
				Integer.parseInt(txtGodinaProizvodnje.getText().trim());
				Integer.parseInt(txtBrojRegistarskeOznake.getText().trim());
			}catch (NumberFormatException nfe) {
				return false;
			}

		return true;
	}
	
	

}