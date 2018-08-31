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

import korisnici.Musterija;
import korisnici.Vozac;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;
import voznja.VoznjaAplikacija;


public class MusterijeVoznjeTabela extends JFrame {

	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private String                    username;
	private JToolBar                  mainToolbar;
	private JButton 				  btnEdit;
	private JButton 				  btnRefresh;
	private JButton 			  	  btnDelete;
	private JTable 					  voznjeTabela;

	
	public MusterijeVoznjeTabela(TaxiSluzbaFunkcionalnosti taxiStanica, String username){
		this.taxiStanica = taxiStanica;
		this.username    = username;
		setTitle("Voznje");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
	}

	private void initGUI() {
		
		mainToolbar = new JToolBar();
		btnEdit                = new JButton("Edit");
		btnRefresh             = new JButton("Refresh");
		btnDelete              = new JButton("Delete");
		

		mainToolbar.add(btnEdit);
		mainToolbar.add(btnRefresh);
		mainToolbar.add(btnDelete);
		add(mainToolbar,BorderLayout.NORTH);
		
		ArrayList<VoznjaAplikacija> voznjeApp = new ArrayList<VoznjaAplikacija>();
		for (int i = 0; i < taxiStanica.getVoznjaAplikacijaUcitano().size(); i++) {
			VoznjaAplikacija va =taxiStanica.getVoznjaAplikacijaUcitano().get(i);
			Musterija m = taxiStanica.nadjiMusteriju(va.getMusterija().getId());
			if(m.getKorisnickoIme().equals(username)){
				voznjeApp.add(va);
			}
		}
		
		String [] zaglavlje = new String[] {"Datum i vreme polaska", "Adresa polaska","Musterija", 
													" Vozac", "Kordinate", "Napomena" };
		
		Object[][] podaci = new Object[voznjeApp.size()][zaglavlje.length];
		for (int v = 0; v < voznjeApp.size(); v++) {
			VoznjaAplikacija voznjaAplikacija = voznjeApp.get(v);
			Musterija musterija = taxiStanica.nadjiMusteriju(voznjaAplikacija.getMusterija().getId());
			try{
			Vozac vozac = taxiStanica.nadjiVozaca(voznjaAplikacija.getVozac().getId());
			podaci[v][3]= vozac.getIme();
			}catch (Exception e){
				podaci[v][3]= "None";
			}
			//DateFormat datumFormat   = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			//String datumIVreme = datumFormat.format(voznjaAplikacija.getVremePolaska());
			podaci[v][0] = voznjaAplikacija.getVremePolaska();
			podaci[v][1] = voznjaAplikacija.getAdresaPolaska();
			podaci[v][2] = musterija.getIme();
			String kordinatex= Integer.toString(voznjaAplikacija.getKordinateX());
			String kordinatey= Integer.toString(voznjaAplikacija.getKordinateY());
			String kordinate = kordinatex+","+kordinatey;
			
			podaci[v][4] = kordinate;
			podaci[v][5] = voznjaAplikacija.getNapomena();
		}
		
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		voznjeTabela = new JTable(model);
		voznjeTabela.setRowSelectionAllowed(true);
		voznjeTabela.setColumnSelectionAllowed(false);
		voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		voznjeTabela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(voznjeTabela);
		add(tableScroll,BorderLayout.CENTER);
	}

	private void initActions() {
		
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaAplikacija va = nadjiSelektovanuVoznju();
				if(va!=null ){
					if(va.getVozac() == null){
					VoznjaAplikacijaFrame vaf = new VoznjaAplikacijaFrame(taxiStanica, va ,username);
					vaf.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Ne mozete izmeniti zavrsenu voznju",
								"greska", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaAplikacija voznjaApp = nadjiSelektovanuVoznju();
				if( voznjaApp != null){
					taxiStanica.obrisiVoznjuApp(voznjaApp);
					taxiStanica.sacuvajVoznjeApp();
					JOptionPane.showMessageDialog(null, "Uspesno ste obrisali voznju",
							"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
	}
	
	private VoznjaAplikacija nadjiSelektovanuVoznju (){
		int red = voznjeTabela.getSelectedRow();
		if(red == -1){
			JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) voznjeTabela.getModel();
			String vreme = model.getValueAt(red, 0).toString();
			//DateFormat datumFormat   = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			//String datumIVreme = datumFormat.format(vreme);
			VoznjaAplikacija va = taxiStanica.nadjiVoznjuApp(vreme);
			if( va ==null){
				JOptionPane.showMessageDialog(null, "Voznja nije pronadjena",
						"Greska", JOptionPane.WARNING_MESSAGE);
			}
			return va;
		}
	}
	
	
}