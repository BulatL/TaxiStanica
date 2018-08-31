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
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class MusterijeTabela extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private JToolBar                  mainToolbar;

	private JButton 		 		  btnAdd;
	private JButton 				  btnEdit;
	private JButton 				  btnRefresh;
	private JButton 				  btnDelete;
	private JTable 					  musterijeTabela;
	
	public MusterijeTabela(TaxiSluzbaFunkcionalnosti taxiStanic){
		this.taxiStanica = taxiStanic;
		setTitle("Musterije");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		
	}

	private void initGUI() {
		/*
		ImageIcon addIcon      = new ImageIcon(getClass().getResource("/slike/add.png"));
		ImageIcon editIcon     = new ImageIcon(getClass().getResource("/slike/edit.png"));
		ImageIcon refreshIcon  = new ImageIcon(getClass().getResource("/slike/refreshh.png"));
		ImageIcon deleteIcon   = new ImageIcon(getClass().getResource("/slike/remove.png"));
		btnAdd                 = new JButton(addIcon);
		btnEdit                = new JButton(editIcon);
		btnRefresh             = new JButton(refreshIcon);
		btnDelete              = new JButton(deleteIcon);
		*/
		btnAdd                 = new JButton("Add");
		btnEdit                = new JButton("Edit");
		btnRefresh             = new JButton("Refresh");
		btnDelete              = new JButton("Delete");
		
		
		mainToolbar = new JToolBar();
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnRefresh);
		mainToolbar.add(btnDelete);
		add(mainToolbar,BorderLayout.NORTH);
		
		
		ArrayList<Musterija> musterijeLista = new ArrayList<Musterija>();
		for (int i = 0; i < taxiStanica.getMusterije().size(); i++) {
			Musterija musterije = taxiStanica.getMusterije().get(i);
			musterijeLista.add(musterije);
		}
		
		
		String [] zaglavlje = new String[] {"Id","Ime", "Prezime","JMBG"," Adresa", 	
									"Pol", "Broj telefona", "Korisnicko ime", "Lozinka","Aplikacija" };
		
		Object[][] podaci = new Object[musterijeLista.size()][zaglavlje.length];
		for (int v = 0; v < musterijeLista.size(); v++) {
			Musterija musterije = musterijeLista.get(v);
			
			
			podaci[v][0] = musterije.getId();
			podaci[v][1] = musterije.getIme();
			podaci[v][2] = musterije.getPrezime();
			podaci[v][3] = musterije.getJMBG();
			podaci[v][4] = musterije.getAdresa();
			podaci[v][5] = musterije.getPol();
			podaci[v][6] = musterije.getBrojTelefona();
			podaci[v][7] = musterije.getKorisnickoIme();
			podaci[v][8] = musterije.getLozinka();
			if(musterije.isAplikacija() == true){
				podaci[v][9] = "Ima";
			}else{
			podaci[v][9] = "Nema";
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		musterijeTabela = new JTable(model);
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(musterijeTabela);
		add(tableScroll,BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeFrame mf = new MusterijeFrame(taxiStanica, null);
				mf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Musterija musterija = nadjiSelektovanuMusteriju();
				if(musterija !=null){
					MusterijeFrame mf = new MusterijeFrame(taxiStanica, musterija);
					mf.setVisible(true);
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Musterija musterija = nadjiSelektovanuMusteriju();
				if(musterija !=null){
					taxiStanica.obrisiMusteriju(musterija);
					taxiStanica.sacuvajMusterije();
					JOptionPane.showMessageDialog(null, "Uspesno ste obrisali dispecera",
							"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				taxiStanica.ucitajDispecere();
				MusterijeTabela.this.dispose();
				MusterijeTabela.this.setVisible(false);
				
				MusterijeTabela mt = new MusterijeTabela(taxiStanica);
				mt.setVisible(true);
				
				
			}
		});
		
	}
	
	private Musterija nadjiSelektovanuMusteriju(){
		int red = musterijeTabela.getSelectedRow();
		if(red == -1){
			JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) musterijeTabela.getModel();
			String korisnickoIme = model.getValueAt(red, 7).toString();
			Musterija m = taxiStanica.nadjiMusterijuKorisnickoIme(korisnickoIme);
			if( m ==null){
				JOptionPane.showMessageDialog(null, "Musterija nije pronadjen",
						"Greska", JOptionPane.WARNING_MESSAGE);
			}
			return m;
		}
	}
}