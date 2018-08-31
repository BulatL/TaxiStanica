package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Automobil;
import korisnici.Vozac;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class VozaciTabela extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private JToolBar                  mainToolbar;

	private JButton 		 		  btnAdd;
	private JButton 				  btnEdit;
	private JButton 				  btnDelete;
	private JTable 					  vozaciTbela;
	
	public VozaciTabela(TaxiSluzbaFunkcionalnosti taxiStanica){
		this.taxiStanica = taxiStanica;
		setTitle("Vozaci");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		
	}

	private void initGUI() {
		
		btnAdd                 = new JButton("Add");
		btnEdit                = new JButton("Edit");
		btnDelete              = new JButton("Delete");
		
		
		mainToolbar = new JToolBar();
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar,BorderLayout.NORTH);
		
		
		ArrayList<Vozac> vozaciLista = new ArrayList<Vozac>();
		for (int i = 0; i < taxiStanica.getVozaci().size(); i++) {
			Vozac vozaci = taxiStanica.getVozaci().get(i);
			vozaciLista.add(vozaci);
		}
		
		
		String [] zaglavlje = new String[] {"Id","Ime", "Prezime","JMBG", 
											" Adresa", "Pol", "Broj telefona", "Plata", "Broj clanske karte",
											"Korisnicko ime", "Lozinka","Automobil" };
		
		Object[][] podaci = new Object[vozaciLista.size()][zaglavlje.length];
		for (int v = 0; v < vozaciLista.size(); v++) {
			Vozac vozaci = vozaciLista.get(v);
			
			try{
				Automobil automobil = taxiStanica.nadjiAutomobil(vozaci.getAutomobil().getBrTaxiVozila());
				String automobilProizvodjac = automobil.getProizvodjac();
				String automobilModel       = automobil.getModel();
				String auto                 = automobilProizvodjac + automobilModel;
				podaci[v][11]= auto;
				}catch (Exception e){
					podaci[v][11]= null;
				}
			
			podaci[v][0] = vozaci.getId();
			podaci[v][1] = vozaci.getIme();
			podaci[v][2] = vozaci.getPrezime();
			podaci[v][3] = vozaci.getJMBG();
			podaci[v][4] = vozaci.getAdresa();
			podaci[v][5] = vozaci.getPol();
			podaci[v][6] = vozaci.getBrojTelefona();
			podaci[v][7] = vozaci.getPlata();
			podaci[v][8] = vozaci.getBrClanskeKarte();
			podaci[v][9] = vozaci.getKorisnickoIme();
			podaci[v][10] = vozaci.getLozinka();

		}
		
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		vozaciTbela = new JTable(model);
		vozaciTbela.setRowSelectionAllowed(true);
		vozaciTbela.setColumnSelectionAllowed(false);
		vozaciTbela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vozaciTbela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(vozaciTbela);
		add(tableScroll,BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VozaciFrame sf = new VozaciFrame(taxiStanica, null);
				sf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vozac vozac = nadjiSelektovanogVozaca();
				if(vozac !=null){
					VozaciFrame sf = new VozaciFrame(taxiStanica, vozac);
					sf.setVisible(true);
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vozac vozac = nadjiSelektovanogVozaca();
				if(vozac !=null){
					taxiStanica.obrisiVozaca(vozac);
					taxiStanica.sacuvajVozace();
					JOptionPane.showMessageDialog(null, "Uspesno ste obrisali dispecera",
							"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		
	}
	
	private Vozac nadjiSelektovanogVozaca(){
		int red = vozaciTbela.getSelectedRow();
		if(red == -1){
			JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) vozaciTbela.getModel();
			String id = model.getValueAt(red, 0).toString();
			Vozac v = taxiStanica.nadjiVozaca(id);
			if( v ==null){
				JOptionPane.showMessageDialog(null, "Vozac nije pronadjen",
						"Greska", JOptionPane.WARNING_MESSAGE);
			}
			return v;
		}
	}
		
}