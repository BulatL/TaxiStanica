package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class InformacijeTaxiStanice extends JFrame{
	
	private TaxiSluzbaFunkcionalnosti TaxiSluzba;
	private taxiSluzba.TaxiSluzba     taxiSluzbaPopuniPolja;
	private String                    username;

	
	private JLabel                    lblTaxiSluzba;
	private JLabel                    lblPIB;
	private JTextField                txtPIB;
	private JLabel                    lblNaziv;
	private JTextField                txtNaziv;
	private JLabel                    lblAdresa;
	private JTextField                txtAdresa;
	private JButton                   btnDalje;
	private JButton                   btnNazad;
	
	public InformacijeTaxiStanice(TaxiSluzbaFunkcionalnosti TaxiSluzba, String username){
		this.TaxiSluzba = TaxiSluzba;
		this.username   = username;
		setTitle("Informacije o taxi stanici");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	
	}


	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]","[]20[][][]20[]");
		setLayout(mig);
		
		lblTaxiSluzba = new JLabel("Infroamcije o taxi sluzbi");
		lblPIB        = new JLabel("PIB");
		txtPIB        = new JTextField(20);
		lblNaziv      = new JLabel("Naizv");
		txtNaziv      = new JTextField(20);
		lblAdresa     = new JLabel("Adresa");
		txtAdresa     = new JTextField(20);
		btnDalje      = new JButton("Dalje");
		btnNazad      = new JButton("Nazad");
		
		
		add(lblTaxiSluzba,"span 2");
		add(lblPIB);
		add(txtPIB);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(new JLabel());
		add(btnDalje,"split 2");
		add(btnNazad);

	
		for (int i = 0; i < TaxiSluzba.getTaxiSluzba().size(); i++) {
			taxiSluzba.TaxiSluzba infromacije = TaxiSluzba.getTaxiSluzba().get(i);
	
			int pib       = infromacije.getPIB();
			String naziv  = infromacije.getNaziv();
			String adresa = infromacije.getAdresa();
			
			txtPIB.setText(String.valueOf(pib));
			txtNaziv.setText(naziv);
			txtAdresa.setText(adresa);
			
		}
		if(TaxiSluzba.nadjiDispeceraPoKorisnickomImenu(username) != null){
			txtPIB.setEnabled(true);
			txtNaziv.setEnabled(true);
			txtAdresa.setEnabled(true);
			btnDalje.setEnabled(true);
		}else{
			txtPIB.setEnabled(false);
			txtNaziv.setEnabled(false);
			txtAdresa.setEnabled(false);
			btnDalje.setEnabled(false);
		}
	}


	private void initActions() {


		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformacijeTaxiStanice.this.dispose();
				InformacijeTaxiStanice.this.setVisible(false);
				
			}
		});
		
		btnDalje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pib        = Integer.parseInt(txtPIB.getText().trim());
				String naziv   = txtNaziv.getText().trim();
				String adresa  = txtAdresa.getText().trim();
				if(proveriUnos()){
					taxiSluzba.TaxiSluzba taxiInformacije = new taxiSluzba.TaxiSluzba(pib, naziv, adresa);
					TaxiSluzba.dodajTaxiSluzbaInformacije(taxiInformacije);
				}
				TaxiSluzba.sacuvajTaxiSluzba();
				InformacijeTaxiStanice.this.dispose();
				InformacijeTaxiStanice.this.setVisible(false);
			}
		});
		
	}
	
	
	private boolean proveriUnos(){
		if (txtPIB.getText().trim().equals("") || 
				txtNaziv.getText().trim().equals("")||
				txtAdresa.getText().trim().equals("")){
				return false;
			}
			try {
				Integer.parseInt(txtPIB.getText().trim());
				txtNaziv.getText().trim();
				txtAdresa.getText().trim();

			}catch (NumberFormatException nfe) {
				return false;
			}
		return true;
	}
}

