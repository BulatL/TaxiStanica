package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Musterija;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;
import voznja.VoznjaAplikacija;



public class VoznjaAplikacijaFrame extends JFrame {

	 private TaxiSluzbaFunkcionalnosti taxiStanica;
     private VoznjaAplikacija          voznjaApp;	
     private String                    username;

	 private JLabel      	  lblTekst;
	 private JLabel     	  lblAdresaPolaska;
	 private JTextField  	  txtAdresaPolaska;
	 private JLabel      	  lblNapomena;
	 private JTextField  	  txtNapomena;
	 private JLabel           lblKordinate;
	 private JTextField       txtKordinateX;
	 private JTextField       txtKordinateY;
	 private JButton     	  btnNazad;
	 private JButton     	  btnDalje;
	 private JButton          btnKordinate;
	
	 public VoznjaAplikacijaFrame(TaxiSluzbaFunkcionalnosti taxiStanica, VoznjaAplikacija izmenaVoznjeApp, String username){
			this.taxiStanica       = taxiStanica;
			this.voznjaApp         = izmenaVoznjeApp;
			this.username          = username;
			setTitle("Voznje");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			initGUI();
			initActions();
			pack();
			setResizable(false);
		}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]","[]20[][][][]20[]");
		setLayout(mig);
		
		lblTekst           = new JLabel("Zakazite voznju");
		lblAdresaPolaska   = new JLabel("Vasa adresa");
		txtAdresaPolaska   = new JTextField(25);
		lblNapomena        = new JLabel("Napomena");
		txtNapomena        = new JTextField(25);
		lblKordinate       = new JLabel("Unesite kordinate");
		txtKordinateX      = new JTextField("Kordinate X",15);
		txtKordinateY      = new JTextField("Kordinate Y",15);
		btnDalje           = new JButton("Dalje");
		btnNazad           = new JButton("Nazad");
		btnKordinate       = new JButton("Ispisi kordinate");
		
		if (voznjaApp != null) {
			popuniPolja();
		}
		
		add(new JLabel());
		add(lblTekst);
		add(lblAdresaPolaska);
		add(txtAdresaPolaska);
		add(lblNapomena);
		add(txtNapomena);
		add(lblKordinate);
		add(txtKordinateX,"split 2");
		add(txtKordinateY);
		add(new JLabel());
		add(btnKordinate);
		add(new JLabel());
		add(btnDalje,"split 2");
		add(btnNazad);
		
		if(voznjaApp != null){
			txtAdresaPolaska.setEnabled(true);
			txtNapomena.setEnabled(true);
			btnDalje.setEnabled(true);
			btnNazad.setEnabled(true);
		}
		
	}

	private void initActions() {
		
		btnKordinate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double koordinateX    = (Math.random() *900 +100);
				Double koordinateY    = (Math.random() *900 +100);
				int kordinateX        = koordinateX.intValue();
				int kordinateY        = koordinateY.intValue();
				txtKordinateX.setText(String.valueOf(kordinateX));
				txtKordinateY.setText(String.valueOf(kordinateY));
				
			}
		});
		
		btnDalje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Musterija musterija   = taxiStanica.nadjiMusterijuKorisnickoIme(username);
				
				String adresaPolaska  = txtAdresaPolaska.getText().trim();
				String napomena       = txtNapomena.getText().trim();
				/*Double koordinateX    = (Math.random() *900 +100);
				Double koordinateY    = (Math.random() *900 +100);
				int kordinateX        = koordinateX.intValue();
				int kordinateY        = koordinateY.intValue();*/
				int kordinateX        = Integer.parseInt(txtKordinateX.getText().trim());
				int kordinateY        = Integer.parseInt(txtKordinateY.getText().trim());
				if(voznjaApp != null){
					if(proveriUnosVoznjeApp()){
						VoznjaAplikacija voznjaAplikacija = (VoznjaAplikacija) voznjaApp;
						voznjaAplikacija.setAdresaPolaska(adresaPolaska);
						voznjaAplikacija.setNapomena(napomena);
						voznjaAplikacija.setKordinateX(kordinateX);
						voznjaAplikacija.setKordinateY(kordinateY);
						
					}
				}else {
					DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
					Date vremeDate = Calendar.getInstance().getTime();        
					String StringVreme = df.format(vremeDate); 
					
					VoznjaAplikacija voznjaAplikacija = new VoznjaAplikacija(StringVreme, adresaPolaska, musterija, 
							null, napomena, kordinateX, kordinateY);
					taxiStanica.dodajVoznjuApp(voznjaAplikacija);
				
					
				}
				taxiStanica.sacuvajVoznjeApp();
				JOptionPane.showMessageDialog(null, "Uspesno ste narucili/izmenili voznju",
						"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				VoznjaAplikacijaFrame.this.dispose();
				VoznjaAplikacijaFrame.this.setVisible(false);
				
			}
		});
		
		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VoznjaAplikacijaFrame.this.dispose();
				VoznjaAplikacijaFrame.this.setVisible(false);
			}
		});
		
	}
	
	private Boolean proveriUnosVoznjeApp(){
		if(txtAdresaPolaska.getText().trim().equals("")||
				txtNapomena.getText().trim().equals("")||
				txtKordinateX.getText().trim().equals("")||
				txtKordinateY.getText().trim().equals("")){
				return false;
		}
		try {
			txtAdresaPolaska.getText().trim();
			txtNapomena.getText().trim();
			Integer.parseInt(txtKordinateX.getText().trim());
			Integer.parseInt(txtKordinateY.getText().trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	private void popuniPolja(){
		txtAdresaPolaska.setText(voznjaApp.getAdresaPolaska());
		txtNapomena.setText(voznjaApp.getNapomena());
		txtKordinateX.setText(String.valueOf(voznjaApp.getKordinateX()));
		txtKordinateY.setText(String.valueOf(voznjaApp.getKordinateY()));
	}
	
	
	
}