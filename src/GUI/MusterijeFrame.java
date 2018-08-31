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
import korisnici.Musterija;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class MusterijeFrame extends JFrame {
	
	private TaxiSluzbaFunkcionalnosti  taxiSluzba;
	private Musterija                  musterijaZaIzmenu;
	private String                     aplikacijaDa              = "DA";
	private String                     aplikacijaNe              = "NE";
	private String 					   polM                      = "MUSKI";
	private String                     polZ                      = "ZENSKI";

	private JLabel                     lblId                     = new JLabel("id");
	private JLabel                     lblIme                    = new JLabel("Ime");
	private JLabel                     lblPrezime                = new JLabel("Prezime");
	private JLabel                     lblJMBG                   = new JLabel("JMBG");
	private JLabel                     lblAdresa                 = new JLabel("Adresa");
	private JLabel                     lblPol                    = new JLabel("Pol");
	private JLabel                     lblBrojTelefona           = new JLabel("Broj telefona");
	private JLabel                     lblKorisnickoIme          = new JLabel("Korisnicko ime");
	private JLabel                     lblLozinka                = new JLabel("Lozinka");
	private JLabel                     lblAplikacija             = new JLabel("Aplikacija");
	private JTextField                 txtId                     = new JTextField(20);
	private JTextField                 txtIme                    = new JTextField(20);
	private JTextField                 txtPrezime                = new JTextField(20);
	private JTextField                 txtJMBG                   = new JTextField(20);
	private JTextField                 txtAdresa                 = new JTextField(20);
	private JComboBox<String>          cbPol                     = new JComboBox<String>();
	private JTextField                 txtBrojTelefona           = new JTextField(20);
	private JTextField                 txtKorisnickoIme          = new JTextField(20);
	private JPasswordField             pfLozinka                 = new JPasswordField(20);
	private JComboBox<String>          cbAplikacija              = new JComboBox<String>();
	private JButton                    btnDalje                  = new JButton("Dalje");
	private JButton                    btnNazad                  = new JButton("Nazad");
	
	public MusterijeFrame(TaxiSluzbaFunkcionalnosti taxiSLuzba, Musterija musterija){
		this.taxiSluzba        = taxiSLuzba;
		this.musterijaZaIzmenu = musterija;
		if(this.musterijaZaIzmenu != null){
			setTitle("Izmeni Musteriju ");
		}else {
			setTitle("Dodaj Musteriju");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {
		
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[][][][][][][][][]20[]");
		setLayout(mig);
		if(musterijaZaIzmenu != null){
		popuniPolja();
		txtKorisnickoIme.setEnabled(false);
		txtIme.setEnabled(false);
		txtPrezime.setEnabled(false);
		txtJMBG.setEnabled(false);
		cbAplikacija.setEnabled(false);
		cbPol.setEnabled(false);
		String pol                 = musterijaZaIzmenu.getPol().toString();
		cbPol.addItem(pol);
		if(musterijaZaIzmenu.isAplikacija() == true){
			cbAplikacija.addItem(aplikacijaDa);
		}else{
			cbAplikacija.addItem(aplikacijaNe);
		}
		}
		
		
		txtId.setText("Mora biti jedinstven!!!");
		add(lblId);
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
		if(musterijaZaIzmenu == null){
			cbPol.addItem(polM);
			cbPol.addItem(polZ);
			cbAplikacija.addItem(aplikacijaDa);
			cbAplikacija.addItem(aplikacijaNe);
		}
		add(cbPol);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblAplikacija);
		add(cbAplikacija);
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
				String korisnickoIme        = txtKorisnickoIme.getText().trim();
				String lozinka              = new String (pfLozinka.getPassword()).trim();
				int aplikacija              = cbAplikacija.getSelectedIndex();
				


				if (musterijaZaIzmenu != null){
					if(proveriUnosEdit()== true){
						Musterija musterija = (Musterija) musterijaZaIzmenu;		
						musterija.setId(id);
						musterija.setIme(ime);
						musterija.setPrezime(prezime);
						musterija.setJMBG(JMBG);
						musterija.setAdresa(adresa);
						if(pol == 0){
							Pol poll      = Pol.MUSKI;
							musterija.setPol(poll);
						}else{
							Pol poll      = Pol.ZENSKI;
							musterija.setPol(poll);
							}
						musterija.setBrojTelefona(brojTelefona);
						if(aplikacija == 0){
							Boolean aplikacijaa = true;
							musterija.setAplikacija(aplikacijaa);
						}else{
							Boolean aplikacijaa = false;
							musterija.setAplikacija(aplikacijaa);
							}
						musterija.setKorisnickoIme(korisnickoIme);
						musterija.setLozinka(lozinka);
					}
					
				}else {
					if(proveriUnosADD()== true){
						if(pol == 0 ){
							if(aplikacija == 0){
								Pol poll      = Pol.MUSKI;
								Boolean aplikacijaa = true;
								Musterija musterija = new Musterija(id, ime, prezime, JMBG, adresa, 
											poll, brojTelefona, korisnickoIme, lozinka, aplikacijaa);
								taxiSluzba.dodajMusteriju(musterija);
							}else{
								Pol poll      = Pol.MUSKI;
								Boolean aplikacijaa = false;
								Musterija musterija = new Musterija(id, ime, prezime, JMBG, adresa, 
										poll, brojTelefona, korisnickoIme, lozinka, aplikacijaa);
							taxiSluzba.dodajMusteriju(musterija);
							}
						
						
						
						}else{
							if(aplikacija == 0){
								Pol poll      = Pol.MUSKI;
								Boolean aplikacijaa = true;
								Musterija musterija = new Musterija(id, ime, prezime, JMBG, adresa, 
										poll, brojTelefona, korisnickoIme, lozinka, aplikacijaa);
							taxiSluzba.dodajMusteriju(musterija);
							}else{
								Pol poll      = Pol.MUSKI;
								Boolean aplikacijaa = false;
								Musterija musterija = new Musterija(id, ime, prezime, JMBG, adresa, 
										poll, brojTelefona, korisnickoIme, lozinka, aplikacijaa);
							taxiSluzba.dodajMusteriju(musterija);
							}

						}
					}
				}
				taxiSluzba.sacuvajMusterije();
				JOptionPane.showMessageDialog(null, "Uspesno ste dodali/izmenili musteriju",
						"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				MusterijeFrame.this.dispose();
				MusterijeFrame.this.setVisible(false);
				
			}
		});
		
		
	}
	
	public void popuniPolja(){
		txtId.setText(musterijaZaIzmenu.getId());
		txtIme.setText(musterijaZaIzmenu.getIme());
		txtPrezime.setText(musterijaZaIzmenu.getPrezime());
		txtJMBG.setText(musterijaZaIzmenu.getJMBG());
		txtAdresa.setText(musterijaZaIzmenu.getAdresa());
		txtKorisnickoIme.setText(musterijaZaIzmenu.getKorisnickoIme());
		pfLozinka.setText(musterijaZaIzmenu.getLozinka());
		txtBrojTelefona.setText(String.valueOf(musterijaZaIzmenu.getBrojTelefona()));
		
		
		
		
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
				txtAdresa.getText().trim().equals("")){
				return false;
			}
		
		String id             = txtId.getText().trim();
		String korisnickoIme  = txtKorisnickoIme.getText().trim();
		if(taxiSluzba.nadjiDispeceraPoKorisnickomImenu(korisnickoIme)!= null){
			JOptionPane.showMessageDialog(null, "Vec postoji musterija sa tim korisnickim imenom",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(taxiSluzba.nadjiDispecera(id)!= null){
			JOptionPane.showMessageDialog(null, "Vec postoji musterija sa tim idom",
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
				txtAdresa.getText().trim().equals("")){
				return false;
			}
			try {
				String sifra = new String(pfLozinka.getPassword()).trim();
				Integer.parseInt(txtBrojTelefona.getText().trim());
				txtAdresa.getText().trim();
			}catch (NumberFormatException nfe) {
				return false;
			}

		return true;
	}
}