package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Dispecer;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;
import voznja.VoznjaDispecer;

public class VoznjaDispecerFrame extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti taxiStanica;
    private VoznjaDispecer			  izmenaVoznjeDispecer;	
    private String                    username;

    
	 private JLabel      	  	lblTekst;
	 private JLabel     	  	lblAdresaPolaska;
	 private JTextField  	  	txtAdresaPolaska;
	 private JLabel      	  	lblbrTelefona;
	 private JTextField  	  	txtbrTelefona;
	 private JLabel           	lblVozac;
	 private JComboBox<String>	cbVozac;
	 private JButton     	  	btnNazad;
	 private JButton     	  	btnDalje;
	
	 public VoznjaDispecerFrame(TaxiSluzbaFunkcionalnosti taxiStanica, VoznjaDispecer izmenaVoznjeDispecer, 
			 										String username){
			this.taxiStanica          = taxiStanica;
			this.izmenaVoznjeDispecer = izmenaVoznjeDispecer;
			this.username             = username;
			setTitle("Voznje");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			initGUI();
			initActions();
			pack();
			setResizable(false);
		}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]","[]20[][][]20[]");
		setLayout(mig);
		
		lblTekst           = new JLabel("Zakazite voznju");
		lblAdresaPolaska   = new JLabel("Unesite adresu");
		txtAdresaPolaska   = new JTextField(25);
		lblbrTelefona      = new JLabel("Broj telefona");
		txtbrTelefona      = new JTextField(25);
		lblVozac           = new JLabel("Izaberite vozaca");

		btnDalje           = new JButton("Dalje");
		btnNazad           = new JButton("Nazad");
		
		if (izmenaVoznjeDispecer != null) {
			popuniPolja();
		}
		
		cbVozac         = new JComboBox<String>();
		for (Vozac vozac : taxiStanica.getVozaci()){
			cbVozac.addItem(vozac.getKorisnickoIme());
		}
		
		add(new JLabel());
		add(lblTekst);
		add(lblAdresaPolaska);
		add(txtAdresaPolaska);
		add(lblbrTelefona);
		add(txtbrTelefona);
		add(lblVozac);
		add(cbVozac);
		add(new JLabel());
		add(btnDalje,"split 2");
		add(btnNazad);
		
		if(izmenaVoznjeDispecer != null){
			txtAdresaPolaska.setEnabled(true);
			txtbrTelefona.setEnabled(true);
			btnDalje.setEnabled(true);
			btnNazad.setEnabled(true);
		}
		
	}

	private void initActions() {
		
		btnDalje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dispecer dispecer = taxiStanica.nadjiDispeceraPoKorisnickomImenu(username);
				String adresaPolaska  = txtAdresaPolaska.getText().trim();
				int brTelefona        = Integer.parseInt(txtbrTelefona.getText().trim());
				Vozac vozac =(Vozac) taxiStanica.nadjiVozacaPoKorisnickomImenu(cbVozac.getSelectedItem().toString());
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				Date vremeDate = Calendar.getInstance().getTime();        
				String vremePolaska = df.format(vremeDate); 

				if(izmenaVoznjeDispecer != null){
					if(proveriUnosVoznjeDispecer()){
						VoznjaDispecer voznjaDispecer = (VoznjaDispecer) izmenaVoznjeDispecer;
						voznjaDispecer.setVremePolaska(vremePolaska);
						voznjaDispecer.setAdresaPolaska(adresaPolaska);
						voznjaDispecer.setBrMusterije(brTelefona);;
						voznjaDispecer.setDispecer(dispecer);
						voznjaDispecer.setVozac(vozac);
						
					}
				}else {
					
					VoznjaDispecer voznjaDispecer = new VoznjaDispecer(vremePolaska, adresaPolaska, 
							brTelefona, vozac, dispecer);
					taxiStanica.dodajVoznjuDispecer(voznjaDispecer);
				
					
				}
				taxiStanica.sacuvajVoznjeDispecer();
				JOptionPane.showMessageDialog(null, "Uspesno ste narucili/izmenili voznju",
						"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				VoznjaDispecerFrame.this.dispose();
				VoznjaDispecerFrame.this.setVisible(false);
				
			}
		});
		
		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VoznjaDispecerFrame.this.dispose();
				VoznjaDispecerFrame.this.setVisible(false);
			}
		});
		
	}
	
	private Boolean proveriUnosVoznjeDispecer(){
		if(txtAdresaPolaska.getText().trim().equals("")||
				txtbrTelefona.getText().trim().equals("")){
				return false;
		}
		try {
			txtAdresaPolaska.getText().trim();
			Integer.parseInt(txtbrTelefona.getText().trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	private void popuniPolja(){
		txtAdresaPolaska.setText(izmenaVoznjeDispecer.getAdresaPolaska());
		txtbrTelefona.setText(String.valueOf(izmenaVoznjeDispecer.getBrMusterije()));
		
	}
	
	
}