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

import enumeracija.Pol;
import enumeracija.TelefonskoOdeljenje;
import korisnici.Dispecer;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class DispeceriFrame extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti  taxiSluzba;
	private Dispecer                   dispecerZaIzmenu;
	private String                     telefonskoOdeljenjeP      = "Prijem";
	private String                     telefonskoOdeljenjeR      = "Reklamacija";
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
	private JLabel                     lblTelefonskoOdeljenje    = new JLabel("Telefonsko Odeljenje");
	private JLabel                     lblKorisnickoIme          = new JLabel("Korisnicko ime");
	private JLabel                     lblLozinka                = new JLabel("Lozinka");
	private JTextField                 txtId                     = new JTextField(20);
	private JTextField                 txtIme                    = new JTextField(20);
	private JTextField                 txtPrezime                = new JTextField(20);
	private JTextField                 txtJMBG                   = new JTextField(20);
	private JTextField                 txtAdresa                 = new JTextField(20);
	private JComboBox<String>          cbPol                     = new JComboBox<String>();
	private JTextField                 txtBrojTelefona           = new JTextField(20);
	private JTextField                 txtPlata                  = new JTextField(20);
	private JComboBox<String>          cbTelefonskoOdeljenje     = new JComboBox<String>();
	private JTextField                 txtKorisnickoIme          = new JTextField(20);
	private JPasswordField             pfLozinka                 = new JPasswordField(20);
	private JButton                    btnDalje                  = new JButton("Dalje");
	private JButton                    btnNazad                  = new JButton("Nazad");
	
	public DispeceriFrame(TaxiSluzbaFunkcionalnosti taxiSLuzba, Dispecer dispecer){
		this.taxiSluzba        = taxiSLuzba;
		this.dispecerZaIzmenu = dispecer;
		if(this.dispecerZaIzmenu != null){
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
		if(dispecerZaIzmenu != null){
		popuniPolja();
		txtKorisnickoIme.setEnabled(false);
		txtIme.setEnabled(false);
		txtPrezime.setEnabled(false);
		txtJMBG.setEnabled(false);
		cbTelefonskoOdeljenje.setEnabled(false);
		cbPol.setEnabled(false);
		String pol                 = dispecerZaIzmenu.getPol().toString();
		String telefonskoOdeljenje = dispecerZaIzmenu.getTelefonskaOdeljenje().toString();
		cbPol.addItem(pol);
		cbTelefonskoOdeljenje.addItem(telefonskoOdeljenje);
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
		if(dispecerZaIzmenu == null){
			cbPol.addItem(polM);
			cbPol.addItem(polZ);
			cbTelefonskoOdeljenje.addItem(telefonskoOdeljenjeP);
			cbTelefonskoOdeljenje.addItem(telefonskoOdeljenjeR);
		}
		add(cbPol);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblPlata);
		add(txtPlata);
		add(lblTelefonskoOdeljenje);
		add(cbTelefonskoOdeljenje);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
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
				int pol                     = cbPol.getSelectedIndex();
				int brojTelefona            = Integer.parseInt(txtBrojTelefona.getText().trim());
				double plata                = Double.parseDouble(txtPlata.getText().trim());
				int telefonskoOdeljenje     = cbTelefonskoOdeljenje.getSelectedIndex();
				String korisnickoIme        = txtKorisnickoIme.getText().trim();
				String lozinka              = new String (pfLozinka.getPassword()).trim();
				


				if (dispecerZaIzmenu != null){
					if(proveriUnosEdit()== true){
						Dispecer dispecer = (Dispecer) dispecerZaIzmenu;		
						dispecer.setId(id);
						dispecer.setIme(ime);
						dispecer.setPrezime(prezime);
						dispecer.setJMBG(JMBG);
						dispecer.setAdresa(adresa);
						if(pol == 0){
							Pol poll      = Pol.MUSKI;
							dispecer.setPol(poll);
						}else{
							Pol poll      = Pol.ZENSKI;
							dispecer.setPol(poll);
							}
						dispecer.setBrojTelefona(brojTelefona);
						dispecer.setPlata(plata);
						if(telefonskoOdeljenje == 0){
							TelefonskoOdeljenje to = TelefonskoOdeljenje.Prijem;
							dispecer.setTelefonskaOdeljenje(to);
						}else{
							TelefonskoOdeljenje to = TelefonskoOdeljenje.Reklamacija;
							dispecer.setTelefonskaOdeljenje(to);
							}
						dispecer.setKorisnickoIme(korisnickoIme);
						dispecer.setLozinka(lozinka);
					}
					
				}else {
					if(proveriUnosADD()== true){
						if(pol == 0 ){
							if(telefonskoOdeljenje == 0){
								Pol poll      = Pol.MUSKI;
								TelefonskoOdeljenje to = TelefonskoOdeljenje.Prijem;
								Dispecer dispecer = new Dispecer(id, ime, prezime, JMBG, adresa, poll, 
										brojTelefona, plata, to, korisnickoIme, lozinka);
								taxiSluzba.dodajDispecera(dispecer);
							}else{
								Pol poll      = Pol.MUSKI;
								TelefonskoOdeljenje to = TelefonskoOdeljenje.Reklamacija;
								Dispecer dispecer = new Dispecer(id, ime, prezime, JMBG, adresa, poll, 
										brojTelefona, plata, to, korisnickoIme, lozinka);
								taxiSluzba.dodajDispecera(dispecer);
							}
						
						
						
						}else{
							if(telefonskoOdeljenje == 0){
								Pol poll      = Pol.MUSKI;
								TelefonskoOdeljenje to = TelefonskoOdeljenje.Prijem;
								Dispecer dispecer = new Dispecer(id, ime, prezime, JMBG, adresa, poll, 
										brojTelefona, plata, to, korisnickoIme, lozinka);
								taxiSluzba.dodajDispecera(dispecer);
							}else{
								Pol poll      = Pol.MUSKI;
								TelefonskoOdeljenje to = TelefonskoOdeljenje.Reklamacija;
								Dispecer dispecer = new Dispecer(id, ime, prezime, JMBG, adresa, poll, 
										brojTelefona, plata, to, korisnickoIme, lozinka);
								taxiSluzba.dodajDispecera(dispecer);
							}

						}
					}
				}
				taxiSluzba.sacuvajDispecere();
				JOptionPane.showMessageDialog(null, "Uspesno ste dodali/izmenili dispecera",
						"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				DispeceriFrame.this.dispose();
				DispeceriFrame.this.setVisible(false);
				
			}
		});
		
		
	}
	
	public void popuniPolja(){
		txtId.setText(dispecerZaIzmenu.getId());
		txtIme.setText(dispecerZaIzmenu.getIme());
		txtPrezime.setText(dispecerZaIzmenu.getPrezime());
		txtJMBG.setText(dispecerZaIzmenu.getJMBG());
		txtAdresa.setText(dispecerZaIzmenu.getAdresa());
		txtKorisnickoIme.setText(dispecerZaIzmenu.getKorisnickoIme());
		pfLozinka.setText(dispecerZaIzmenu.getLozinka());
		txtBrojTelefona.setText(String.valueOf(dispecerZaIzmenu.getBrojTelefona()));
		txtPlata.setText(String.valueOf(dispecerZaIzmenu.getPlata()));
		
		
		
	}
	
	private boolean proveriUnosADD(){
		String ps= new String (pfLozinka.getPassword()).trim();
		if (txtId.getText().trim().equals("") || 
				txtIme.getText().trim().equals("")||
				txtPrezime.getText().trim().equals("")||
				txtJMBG.getText().trim().equals("")||
				txtKorisnickoIme.getText().trim().equals("")||
				ps.equals("")||
				txtBrojTelefona.getText().trim().equals("")||
				txtPlata.getText().trim().equals("")||
				txtAdresa.getText().trim().equals("")){
				return false;
			}
		
		String id             = txtId.getText().trim();
		String korisnickoIme  = txtKorisnickoIme.getText().trim();
		if(taxiSluzba.nadjiDispeceraPoKorisnickomImenu(korisnickoIme)!= null){
			JOptionPane.showMessageDialog(null, "Vec postoji dispecer sa tim korisnickim imenom",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(taxiSluzba.nadjiDispecera(id)!= null){
			JOptionPane.showMessageDialog(null, "Vec postoji dispecer sa tim idom",
					"Greska", JOptionPane.WARNING_MESSAGE);
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
						Double.parseDouble(txtPlata.getText().trim());
						txtAdresa.getText().trim();
				
				
			}catch (NumberFormatException nfe) {
				return false;
			}
		return true;
	}
	
	private boolean proveriUnosEdit(){
		String ps= new String (pfLozinka.getPassword()).trim();
		if (ps.equals("")||
				txtBrojTelefona.getText().trim().equals("")||
				txtPlata.getText().trim().equals("")||
				txtAdresa.getText().trim().equals("")){
				return false;
			}
			try {
				String sifra = new String(pfLozinka.getPassword()).trim();
				Integer.parseInt(txtBrojTelefona.getText().trim());
				Double.parseDouble(txtPlata.getText().trim());
				txtAdresa.getText().trim();
			}catch (NumberFormatException nfe) {
				return false;
			}

		return true;
	}
	
	
	
}