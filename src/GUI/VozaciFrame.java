package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import automobil.Automobil;
import enumeracija.Pol;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class VozaciFrame extends JFrame{

	
	private TaxiSluzbaFunkcionalnosti  taxiSluzba;
	private Vozac                      vozacZaIzmenu;
	private String 					   polM                      = "MUSKI";
	private String                     polZ                      = "ZENSKI";

	private JLabel                     lblid                     = new JLabel("id");
	private JLabel                     lblIme                    = new JLabel("Ime");
	private JLabel                     lblPrezime                = new JLabel("Prezime");
	private JLabel                     lblJMBG                   = new JLabel("JMBG");
	private JLabel                     lblAdresa                 = new JLabel("Adresa");
	private JLabel                     lblPol                    = new JLabel("Pol");
	private JLabel                     lblBrojTelefona           = new JLabel("Broj telefona");
	private JLabel                     lblPlata                  = new JLabel("Plata");
	private JLabel                     lblBrClanskeKarte         = new JLabel("Broj clanske karte");
	private JLabel                     lblKorisnickoIme          = new JLabel("Korisnicko ime");
	private JLabel                     lblLozinka                = new JLabel("Lozinka");
	private JLabel                     lblAutomobil              = new JLabel("Automobil");
	private JTextField                 txtId                     = new JTextField(20);
	private JTextField                 txtIme                    = new JTextField(20);
	private JTextField                 txtPrezime                = new JTextField(20);
	private JTextField                 txtJMBG                   = new JTextField(20);
	private JTextField                 txtAdresa                 = new JTextField(20);
	private JComboBox<String>          cbPol                     = new JComboBox<String>();
	private JTextField                 txtBrojTelefona           = new JTextField(20);
	private JTextField                 txtPlata                  = new JTextField(20);
	private JTextField                 txtBrClanskeKarte         = new JTextField(20);
	private JTextField                 txtKorisnickoIme          = new JTextField(20);
	private JPasswordField             pfLozinka                 = new JPasswordField(20);
	private JComboBox<String>          cbAutomobil               = new JComboBox<String>();
	private JButton                    btnDalje                  = new JButton("Dalje");
	private JButton                    btnNazad                  = new JButton("Nazad");
	
	public VozaciFrame(TaxiSluzbaFunkcionalnosti taxiSLuzba, Vozac vozac){
		this.taxiSluzba        = taxiSLuzba;
		this.vozacZaIzmenu     = vozac;
		if(this.vozacZaIzmenu != null){
			setTitle("Izmeni dispecera ");
		}else {
			setTitle("Dodaj dispecera");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {
		
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][][]20[]");
		setLayout(mig);
		if(vozacZaIzmenu != null){
		popuniPolja();
		txtKorisnickoIme.setEnabled(false);
		txtIme.setEnabled(false);
		txtPrezime.setEnabled(false);
		txtJMBG.setEnabled(false);
		cbPol.setEnabled(false);
		String pol                 = vozacZaIzmenu.getPol().toString();
		cbPol.addItem(pol);
		cbPol.setEnabled(false);
		try{
				
			
			if(vozacZaIzmenu.getAutomobil().getBrTaxiVozila()==null){
				cbAutomobil.setEnabled(true);
			}else{
				String auto                = vozacZaIzmenu.getAutomobil().getBrTaxiVozila();
				cbAutomobil.setSelectedItem(auto);
			}
		
		}catch(Exception e){
			
		}
		
		}
		try{
			for (int i = 0; i < taxiSluzba.automobiliBezVozacaLista().size(); i++) {
				Automobil auto = taxiSluzba.automobiliBezVozacaLista().get(i);
				cbAutomobil.addItem(auto.getBrTaxiVozila());
			}
		}catch(Exception e){
				
			}
		
		

		add(lblid);
		add(txtId);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		if(vozacZaIzmenu == null){
			cbPol.addItem(polM);
			cbPol.addItem(polZ);
			
		}
		add(cbPol);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblPlata);
		add(txtPlata);
		add(lblBrClanskeKarte);
		add(txtBrClanskeKarte);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblAutomobil);
		add(cbAutomobil);
		add(new JLabel());
		add(btnDalje,"split 2");
		add(btnNazad);
	}

	private void initActions() {
		btnDalje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id                   = txtId.getText().trim();
				String ime                  = txtIme.getText().trim(); 
				String prezime              = txtPrezime.getText().trim();
				String JMBG                 = txtJMBG.getText().trim();
				String adresa               = txtAdresa.getText().trim();
				String pol                  = cbPol.getSelectedItem().toString();
				int brojTelefona            = Integer.parseInt(txtBrojTelefona.getText().trim());
				double plata                = Double.parseDouble(txtPlata.getText().trim());
				int brClanskeKarte          = Integer.parseInt(txtBrClanskeKarte.getText().trim());
				String korisnickoIme        = txtKorisnickoIme.getText().trim();
				String lozinka              = new String (pfLozinka.getPassword()).trim();
				
				
				
				


				if (vozacZaIzmenu != null){
					if(proveriUnosEdit()){
						Vozac vozac = (Vozac) vozacZaIzmenu;		
						vozac.setId(id);
						vozac.setIme(ime);
						vozac.setPrezime(prezime);
						vozac.setJMBG(JMBG);
						vozac.setAdresa(adresa);
						if(pol == "MUSKI"){
							Pol poll      = Pol.MUSKI;
							vozac.setPol(poll);
						}else{
							Pol poll      = Pol.ZENSKI;
							vozac.setPol(poll);
							}
						vozac.setBrojTelefona(brojTelefona);
						vozac.setPlata(plata);
						vozac.setBrClanskeKarte(brClanskeKarte);
						vozac.setKorisnickoIme(korisnickoIme);
						vozac.setLozinka(lozinka);
						try{
							Automobil automobil =(Automobil)  taxiSluzba.nadjiAutomobil(cbAutomobil.getSelectedItem().toString());
							if(automobil == null){
								vozac.setAutomobil(null);
							}else{
								vozac.setAutomobil(automobil);
							}
							
							}catch(Exception e1){
								
							}
						
					}
					
				}else {
					if(proveriUnosADD()){
						if(pol == "MUSKI" ){
								Pol poll      = Pol.MUSKI;
								try{
									Automobil automobil =(Automobil)  taxiSluzba.nadjiAutomobil(cbAutomobil.getSelectedItem().toString());
									if(automobil == null){
										automobil = null;
										Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, poll, brojTelefona, 
												plata, brClanskeKarte, korisnickoIme, lozinka, automobil);
										taxiSluzba.dodajVozaca(vozac);
									}else{
										Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, poll, brojTelefona, 
												plata, brClanskeKarte, korisnickoIme, lozinka, automobil);
										taxiSluzba.dodajVozaca(vozac);
									}
									
									}catch(Exception e1){
										
									}
						}else{
								Pol poll      = Pol.ZENSKI;
								try{
									Automobil automobil =(Automobil)  taxiSluzba.nadjiAutomobil(cbAutomobil.getSelectedItem().toString());
									if(automobil == null){
										automobil = null;
										Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, poll, brojTelefona, 
												plata, brClanskeKarte, korisnickoIme, lozinka, automobil);
										taxiSluzba.dodajVozaca(vozac);
									}else{
										Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, poll, brojTelefona, 
												plata, brClanskeKarte, korisnickoIme, lozinka, automobil);
										taxiSluzba.dodajVozaca(vozac);
									}
									
									}catch(Exception e1){
										
									}
							}
						
						
						
						
							}

						}
					
				taxiSluzba.sacuvajVozace();
				JOptionPane.showMessageDialog(null, "Uspesno ste dodali vozaca",
						"informacija", JOptionPane.INFORMATION_MESSAGE);
				VozaciFrame.this.dispose();
				VozaciFrame.this.setVisible(false);
				
				
			}
		});
		
	}
	
	public void popuniPolja(){
		txtId.setText(vozacZaIzmenu.getId());
		txtIme.setText(vozacZaIzmenu.getIme());
		txtPrezime.setText(vozacZaIzmenu.getPrezime());
		txtJMBG.setText(vozacZaIzmenu.getJMBG());
		txtAdresa.setText(vozacZaIzmenu.getAdresa());
		txtKorisnickoIme.setText(vozacZaIzmenu.getKorisnickoIme());
		pfLozinka.setText(vozacZaIzmenu.getLozinka());
		txtBrojTelefona.setText(String.valueOf(vozacZaIzmenu.getBrojTelefona()));
		txtBrClanskeKarte.setText(String.valueOf(vozacZaIzmenu.getBrClanskeKarte()));
		txtPlata.setText(String.valueOf(vozacZaIzmenu.getPlata()));
		
		
		
	}
	
	private boolean proveriUnosADD(){
		do{
		String ps= new String (pfLozinka.getPassword()).trim();
		if (txtId.getText().trim().equals("") || 
				txtIme.getText().trim().equals("")||
				txtPrezime.getText().trim().equals("")||
				txtJMBG.getText().trim().equals("")||
				txtKorisnickoIme.getText().trim().equals("")||
				ps.equals("")||
				txtBrClanskeKarte.getText().trim().equals("")||
				txtBrojTelefona.getText().trim().equals("")||
				txtPlata.getText().trim().equals("")||
				txtAdresa.getText().trim().equals("")){
				return false;
			}
			try {
				txtId.getText().trim(); 
						txtIme.getText().trim();
						txtPrezime.getText().trim();
						txtJMBG.getText().trim();
						txtKorisnickoIme.getText().trim();
						String sifra = new String(pfLozinka.getPassword()).trim();
						Integer.parseInt(txtBrojTelefona.getText().trim());
						Integer.parseInt(txtBrClanskeKarte.getText().trim());
						Double.parseDouble(txtPlata.getText().trim());
						txtAdresa.getText().trim();
				
				
			}catch (NumberFormatException nfe) {
				return false;
			}
			String id             = txtId.getText().trim();
			int brClanskeKarte    = Integer.parseInt(txtBrClanskeKarte.getText().trim());
			if(taxiSluzba.nadjiVozacaPoClanskoj(brClanskeKarte)!= null){
				JOptionPane.showMessageDialog(null, "Vec postoji vozac sa tim brojem clanske karte",
						"Greska", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			if(taxiSluzba.nadjiVozaca(id)!= null){
				JOptionPane.showMessageDialog(null, "Vec postoji vozac sa tim idom",
						"Greska", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		return true;
	}while(false);
	}
	
	private boolean proveriUnosEdit(){
		do{
		String ps= new String (pfLozinka.getPassword()).trim();
		if (ps.equals("")||
				txtBrClanskeKarte.getText().trim().equals("")||
				txtBrojTelefona.getText().trim().equals("")||
				txtPlata.getText().trim().equals("")||
				txtAdresa.getText().trim().equals("")){
				return false;
			}
			try {
				String sifra = new String(pfLozinka.getPassword()).trim();
				Integer.parseInt(txtBrClanskeKarte.getText().trim());
				Integer.parseInt(txtBrojTelefona.getText().trim());
				Double.parseDouble(txtPlata.getText().trim());
				txtAdresa.getText().trim();
				return true;
			}catch (NumberFormatException nfe) {
				return false;
			}

		
	
	}while(false);
	}
}