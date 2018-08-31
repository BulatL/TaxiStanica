package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import korisnici.Vozac;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;
import voznja.Voznja;
import voznja.VoznjaAplikacija;
import voznja.VoznjaDispecer;

public class VozacVoznjeTabela extends JFrame {

	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private String                    username;
	private String                    korisnickoImeTrenutno;


	private JTable 					  VoznjeTabela;
	
	public VozacVoznjeTabela(TaxiSluzbaFunkcionalnosti taxiStanica, String username){
		this.taxiStanica = taxiStanica;
		this.username    = username;
		setTitle("Automobili");
		setSize(1100, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		
	}



	private void initGUI() {
		
		
		ArrayList<Voznja> voznjeLista = new ArrayList<Voznja>();
		/*voznjeLista.addAll(taxiStanica.getVoznjeDispecerUcitano());
		voznjeLista.addAll(taxiStanica.getVoznjaAplikacijaUcitano());
		
		*/
		for (int i = 0; i < taxiStanica.getVoznjaAplikacijaUcitano().size(); i++) {
			VoznjaAplikacija voznjaApp = taxiStanica.getVoznjaAplikacijaUcitano().get(i);
			String prosledjenoIme = username;
			
			try {
				Vozac vozac = taxiStanica.nadjiVozaca(voznjaApp.getVozac().getId());
				korisnickoImeTrenutno =vozac.getKorisnickoIme();
				if(korisnickoImeTrenutno.equals(prosledjenoIme)){
					voznjeLista.add(voznjaApp);
					korisnickoImeTrenutno = vozac.getKorisnickoIme();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		for (int i = 0; i < taxiStanica.getVoznjeDispecerUcitano().size(); i++) {
			VoznjaDispecer voznjeDispecer = taxiStanica.getVoznjeDispecerUcitano().get(i);
			String prosledjenoIme = username;
			
			try {
				Vozac vozac = taxiStanica.nadjiVozaca(voznjeDispecer.getVozac().getId());
				korisnickoImeTrenutno =vozac.getKorisnickoIme();
				if(korisnickoImeTrenutno.equals(prosledjenoIme)){
				voznjeLista.add(voznjeDispecer);
				korisnickoImeTrenutno = vozac.getKorisnickoIme();
			}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
	}
		
		
		
		
		String [] zaglavlje = new String[] {"Datum i vreme polaska", "Adresa polaska", 
									" Vozac", "Kordinate", "Napomena","Musterija","Dispecer" };
		
		Object[][] podaci = new Object[voznjeLista.size()][zaglavlje.length];
		for (int v = 0; v < voznjeLista.size(); v++) {
			Voznja voznja = voznjeLista.get(v);
			Vozac vozac = taxiStanica.nadjiVozacaPoKorisnickomImenu(korisnickoImeTrenutno);
		
			//DateFormat datumFormat   = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			//String datumIVreme = datumFormat.format(voznja.getVremePolaska());
			try{
			if(korisnickoImeTrenutno.equals(voznja.getVozac().getKorisnickoIme())){
			podaci[v][0] = voznja.getVremePolaska();
			podaci[v][1] = voznja.getAdresaPolaska();
			String ime   = vozac.getIme();
			String prezime = vozac.getPrezime();
			String vozacTabela = ime +" "+ prezime;
			podaci[v][2] = vozacTabela;
			if(voznja instanceof VoznjaAplikacija){
				VoznjaAplikacija voznjaAplikacija = (VoznjaAplikacija) voznja;
				String kordinatex = Integer.toString(voznjaAplikacija.getKordinateX());
				String kordinatey = Integer.toString(voznjaAplikacija.getKordinateY());
				String kordinate  = kordinatex+","+kordinatey;
				
				podaci[v][3] = kordinate;
				podaci[v][4] = ((VoznjaAplikacija) voznja).getNapomena();
				podaci[v][5] = voznjaAplikacija.getMusterija().getKorisnickoIme();
			}if(voznja instanceof VoznjaDispecer){
				VoznjaDispecer voznjaDispecer = (VoznjaDispecer) voznja;
				podaci[v][5] = voznjaDispecer.getBrMusterije();
				podaci[v][6] = voznjaDispecer.getDispecer().getKorisnickoIme();
			}
						
		}
		}catch(Exception e){
			
		}
		}
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		VoznjeTabela = new JTable(model);
		VoznjeTabela.setRowSelectionAllowed(true);
		VoznjeTabela.setColumnSelectionAllowed(false);
		VoznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		VoznjeTabela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(VoznjeTabela);
		add(tableScroll,BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		// TODO Auto-generated method stub
		
	}
	
}