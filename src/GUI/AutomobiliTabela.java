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
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class AutomobiliTabela extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private JToolBar                  mainToolbar;

	private JButton 		 		  btnAdd;
	private JButton 				  btnEdit;
	private JButton 				  btnRefresh;
	private JButton 				  btnDelete;
	private JTable 					  AutomobiliTabela;
	
	public AutomobiliTabela(TaxiSluzbaFunkcionalnosti taxiStanica){
		this.taxiStanica = taxiStanica;
		setTitle("Automobili");
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
		
		tabelaAutomobili();
		
	}
	
	private void initActions() {
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobiliFrame af = new AutomobiliFrame(taxiStanica, null);
				af.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Automobil auto = nadjiSelektovaniAutomobil();
				if(auto !=null){
					AutomobiliFrame af = new AutomobiliFrame(taxiStanica, auto);
					af.setVisible(true);
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Automobil auto = nadjiSelektovaniAutomobil();
				if(auto !=null){
					taxiStanica.obrisiAutomobil(auto);
					taxiStanica.sacuvajAutomobile();
				}
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				taxiStanica.ucitajAutomobile();
				AutomobiliTabela.this.dispose();
				AutomobiliTabela.this.setVisible(false);
				
				AutomobiliTabela at = new AutomobiliTabela(taxiStanica);
				at.setVisible(true);
				
				
			}
		});
		
		
	}
	
	
	private JTable tabelaAutomobili(){
		ArrayList<Automobil> automobilLista = new ArrayList<Automobil>();
		for (int i = 0; i < taxiStanica.getAutomobili().size(); i++) {
			Automobil automobili = taxiStanica.getAutomobili().get(i);
			automobilLista.add(automobili);
		}
		
		
		String [] zaglavlje = new String[] {"Proizvodjac", "Model","Godina Proizvodnje", 
											" Broj registarske oznake", "Broj taxi vozila", "Vrsta automobila" };
		
		Object[][] podaci = new Object[automobilLista.size()][zaglavlje.length];
		for (int v = 0; v < automobilLista.size(); v++) {
			Automobil automobili = automobilLista.get(v);
			
			podaci[v][0] = automobili.getProizvodjac();
			podaci[v][1] = automobili.getModel();
			podaci[v][2] = automobili.getGodinaProizvodnje();
			podaci[v][3] = automobili.getBrRegistarskeOznake();
			podaci[v][4] = automobili.getBrTaxiVozila();
			podaci[v][5] = automobili.getVrstaAutomobila();
		}
		
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		AutomobiliTabela = new JTable(model);
		AutomobiliTabela.setRowSelectionAllowed(true);
		AutomobiliTabela.setColumnSelectionAllowed(false);
		AutomobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AutomobiliTabela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(AutomobiliTabela);
		add(tableScroll,BorderLayout.CENTER);
		
	return AutomobiliTabela;
	}
	
	
	private Automobil nadjiSelektovaniAutomobil (){
		int red = AutomobiliTabela.getSelectedRow();
		if(red == -1){
			JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) AutomobiliTabela.getModel();
			String brTaxiVozila = model.getValueAt(red, 4).toString();
			Automobil a = taxiStanica.nadjiAutomobil(brTaxiVozila);
			if( a ==null){
				JOptionPane.showMessageDialog(null, "Automobil nije pronadjen",
						"Greska", JOptionPane.WARNING_MESSAGE);
			}
			return a;
		}
	}


}