package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import enumeracija.TelefonskoOdeljenje;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class LoginProzor extends JFrame {
	
	JLabel lblLogIn;
	JLabel lblIzaberi;
	JRadioButton rbMusterija;
	JRadioButton rbVozac;
	JRadioButton rbDispecer;
	JLabel lblKorisnickoIme;
	JTextField txtKorisnickoIme;
	JLabel lblLozinka;
	JPasswordField pfLozinka;
	JButton btnDalje;
	JButton btnNazad;
	TaxiSluzbaFunkcionalnosti taxiStanica;
	

	

	public LoginProzor (TaxiSluzbaFunkcionalnosti taxiStanica){
		this.taxiStanica= taxiStanica;
		setTitle("Taxi Sluzba ");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
		setResizable(false);
		
		
	}
	
	public void initGUI(){
		
		MigLayout mig = new MigLayout("wrap 2", "[][]","[]20[][][]20[]");
		setLayout(mig);
		
		lblLogIn = new JLabel("Molimo vas ulogujte se kako bi nastavili dalje");
		lblIzaberi = new JLabel("Vi ste");
		rbMusterija = new JRadioButton("Musterija");
		rbVozac = new JRadioButton("Vozac");
		rbDispecer = new JRadioButton("Dispecer");
		lblKorisnickoIme = new JLabel("Korisnicko ime ");
		txtKorisnickoIme = new JTextField(15);
		lblLozinka = new JLabel("Lozinka ");
		pfLozinka = new JPasswordField(15);
		btnDalje = new JButton("Dalje");
		btnNazad = new JButton("Nazad");
		
		add(lblLogIn, "span 2");
		add(new JLabel());
		add(rbMusterija);
		add(lblIzaberi);
		add(rbVozac);
		add(new JLabel());
		add(rbDispecer);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnDalje, "split 2");
		add(btnNazad);
		ButtonGroup bG = new ButtonGroup();
		bG.add(rbMusterija);
		bG.add(rbDispecer);
		bG.add(rbVozac);
		rbMusterija.setSelected(true);
		
		
	}
	public void initActions(){
		
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni","Potvrda",
							JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION){
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
					}
				}
			});
			
			btnNazad.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginProzor.this.dispose();
					LoginProzor.this.setVisible(false);
					
				}
			});
			
			btnDalje.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String username = txtKorisnickoIme.getText().trim();
					String password = new String(pfLozinka.getPassword()).trim();
					if (username.equals("") || password.equals("")){
						JOptionPane.showMessageDialog(null, "Morate uneti sve podatke za prijavu!",
								"Greska",
								JOptionPane.WARNING_MESSAGE);
						
					}else {
						if (rbMusterija.isSelected()) {
								if(LoginProzor.this.taxiStanica.loginMusterija(username, password)){
									Boolean aplikacija =taxiStanica.nadjiMusterijuKorisnickoIme(username).isAplikacija();
									LoginProzor.this.dispose();
									LoginProzor.this.setVisible(false);
									
									MusterijaProzor kp = new MusterijaProzor(LoginProzor.this.taxiStanica, username, aplikacija);
									kp.setVisible(true);
							}	else {
									JOptionPane.showMessageDialog(null,
											"Neispravni login podaci",
											"Greska",
											JOptionPane.ERROR_MESSAGE);
							}
						
						}else if (rbVozac.isSelected()) {
								if(LoginProzor.this.taxiStanica.loginVozac(username, password)){
									LoginProzor.this.dispose();
									LoginProzor.this.setVisible(false);
									
									VozacProzor vp = new VozacProzor(LoginProzor.this.taxiStanica, username);
									vp.setVisible(true);
							}	else {
									JOptionPane.showMessageDialog(null,
											"Neispravni login podaci",
											"Greska",
											JOptionPane.ERROR_MESSAGE);
							}
						}else {
								if(LoginProzor.this.taxiStanica.loginDispecer(username, password)){
									LoginProzor.this.dispose();
									LoginProzor.this.setVisible(false);
									
									
									DispecerProzor dp = new DispecerProzor(LoginProzor.this.taxiStanica, username);
									dp.setVisible(true);
							}	else {
									JOptionPane.showMessageDialog(null,
											"Neispravni login podaci",
											"Greska",
											JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			});
	
	
	}

}
