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

import korisnici.Dispecer;
import taxiSluzba.TaxiSluzbaFunkcionalnosti;

public class DispeceriTabela extends JFrame {

	
	private TaxiSluzbaFunkcionalnosti taxiStanica;
	private JToolBar                  mainToolbar;

	private JButton 		 		  btnAdd;
	private JButton 				  btnEdit;
	private JButton 				  btnRefresh;
	private JButton 				  btnDelete;
	private JTable 					  DispeceriTabela;
	
	public DispeceriTabela(TaxiSluzbaFunkcionalnosti taxiStanic){
		this.taxiStanica = taxiStanic;
		setTitle("Dispeceri");
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
		
		
		ArrayList<Dispecer> dispeceriLista = new ArrayList<Dispecer>();
		for (int i = 0; i < taxiStanica.getDispeceri().size(); i++) {
			Dispecer dispeceri = taxiStanica.getDispeceri().get(i);
			dispeceriLista.add(dispeceri);
		}
		
		
		String [] zaglavlje = new String[] {"Id", "Ime", "Prezime","JMBG", 
											" Adresa", "Pol", "Broj telefona","Plata" ,"Telefonsko odeljenje"
											,"Korisnicko ime", "Lozinka" };
		
		Object[][] podaci = new Object[dispeceriLista.size()][zaglavlje.length];
		for (int v = 0; v < dispeceriLista.size(); v++) {
			Dispecer dispeceri = dispeceriLista.get(v);
			
			podaci[v][0] = dispeceri.getId();
			podaci[v][1] = dispeceri.getIme();
			podaci[v][2] = dispeceri.getPrezime();
			podaci[v][3] = dispeceri.getJMBG();
			podaci[v][4] = dispeceri.getAdresa();
			podaci[v][5] = dispeceri.getPol();
			podaci[v][6] = dispeceri.getBrojTelefona();
			podaci[v][7] = dispeceri.getPlata();
			podaci[v][8] = dispeceri.getTelefonskaOdeljenje();
			podaci[v][9] = dispeceri.getKorisnickoIme();
			podaci[v][10] = dispeceri.getLozinka();

		}
		
		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		DispeceriTabela = new JTable(model);
		DispeceriTabela.setRowSelectionAllowed(true);
		DispeceriTabela.setColumnSelectionAllowed(false);
		DispeceriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DispeceriTabela.setDefaultEditor(Object.class, null);
		JScrollPane tableScroll = new JScrollPane(DispeceriTabela);
		add(tableScroll,BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DispeceriFrame df = new DispeceriFrame(taxiStanica, null);
				df.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dispecer dispecer = nadjiSelektovanogDispecera();
				if(dispecer !=null){
					DispeceriFrame df = new DispeceriFrame(taxiStanica, dispecer);
					df.setVisible(true);
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dispecer dispecer = nadjiSelektovanogDispecera();
				if(dispecer !=null){
					taxiStanica.obrisiDispecera(dispecer);
					taxiStanica.sacuvajDispecere();
					JOptionPane.showMessageDialog(null, "Uspesno ste obrisali dispecera",
							"obavestenje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				taxiStanica.ucitajDispecere();
				DispeceriTabela.this.dispose();
				DispeceriTabela.this.setVisible(false);
				
				DispeceriTabela dt = new DispeceriTabela(taxiStanica);
				dt.setVisible(true);
				
				
			}
		});
		
	}
	
	private Dispecer nadjiSelektovanogDispecera(){
		int red = DispeceriTabela.getSelectedRow();
		if(red == -1){
			JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) DispeceriTabela.getModel();
			String korisnickoIme = model.getValueAt(red, 9).toString();
			Dispecer d = taxiStanica.nadjiDispeceraPoKorisnickomImenu(korisnickoIme);
			if( d ==null){
				JOptionPane.showMessageDialog(null, "Dispecer nije pronadjen",
						"Greska", JOptionPane.WARNING_MESSAGE);
			}
			return d;
		}
	}
}