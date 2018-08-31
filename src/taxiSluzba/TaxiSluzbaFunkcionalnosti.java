package taxiSluzba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import automobil.Automobil;
import enumeracija.Pol;
import enumeracija.TelefonskoOdeljenje;
import enumeracija.VrstaAutomobila;
import korisnici.Dispecer;
import korisnici.Musterija;
import korisnici.Vozac;
import voznja.VoznjaAplikacija;
import voznja.VoznjaDispecer;



public class TaxiSluzbaFunkcionalnosti {
	
	private ArrayList<Musterija> 		musterije;
	private ArrayList<Vozac>     		vozaci;
	private ArrayList<Dispecer>  		dispeceri;
	private ArrayList<Automobil> 		automobili;
	private ArrayList<Automobil>        trenutniAuto;
	private ArrayList<VoznjaAplikacija> voznjaAplikacijaUcitano;
	private ArrayList<VoznjaDispecer>   voznjeDispecerUcitano;
	private ArrayList<TaxiSluzba>       taxiSluzba;
 	private ArrayList<Automobil>        automobiliBezVozacaLista;
	
	public TaxiSluzbaFunkcionalnosti(){
		this.musterije    	 		  = new ArrayList<Musterija>();
		this.vozaci       	  		  = new ArrayList<Vozac>();
		this.dispeceri    	  		  = new ArrayList<Dispecer>();
		this.automobili   	  		  = new ArrayList<Automobil>();
		this.trenutniAuto 	 		  = new ArrayList<Automobil>();
		this.voznjaAplikacijaUcitano  = new ArrayList<VoznjaAplikacija>();
		this.taxiSluzba               = new ArrayList<TaxiSluzba>();
		this.voznjeDispecerUcitano    = new ArrayList<VoznjaDispecer>();
		this.automobiliBezVozacaLista = new ArrayList<Automobil>();
	}
//---------------------------------------------------------------------------------------------------------------------	
	public void ucitajTaxiSluzbu(){
		try {
			File dispeceriFile=new File("src/txt/taxiSluzba.txt");
			BufferedReader reader = new BufferedReader(new FileReader(dispeceriFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit   = line.split("\\|");
				int PIB          = Integer.parseInt(lineSplit[0]);
				String naziv     = lineSplit[1];
				String adresa    = lineSplit[2];
				
				TaxiSluzba ts = new TaxiSluzba(PIB, naziv, adresa);
				taxiSluzba.add(ts);
				
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Greska prilikom citanja iz fajla taxi sluzba",
						"Greska",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public void sacuvajTaxiSluzba() {
		try {
			File taxiSluzbaFile = new File("src/txt/taxiSluzba.txt");
			String content = "";
			for (TaxiSluzba taxiSluzbaa : taxiSluzba ) {
				content += taxiSluzbaa.getPIB() + "|" +
						taxiSluzbaa.getNaziv() + "|" +
						taxiSluzbaa.getAdresa()+ "\n";
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(taxiSluzbaFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dodajTaxiSluzbaInformacije(TaxiSluzba taxiSluzba){
		this.taxiSluzba.add(taxiSluzba);
	}
	
	

	
	public ArrayList<TaxiSluzba> getTaxiSluzba() {
		return taxiSluzba;
	}

	public void setTaxiSluzba(ArrayList<TaxiSluzba> taxiSluzba) {
		this.taxiSluzba = taxiSluzba;
	}

//----------------------------------------------------------------------------------------------------------------------	
	public void ucitajDispecere(){
		try{
			File dispeceriFile=new File("src/txt/dispeceri.txt");
			BufferedReader reader = new BufferedReader(new FileReader(dispeceriFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit   = line.split("\\|");
				String id            = lineSplit[0];
				String ime           = lineSplit[1];
				String prezime       = lineSplit[2];
				String JMBG          = lineSplit[3];
				String adresa        = lineSplit[4];
				Pol pol              = Pol.valueOf(lineSplit[5]);
				int brojTelefona     = Integer.parseInt(lineSplit[6]);
				Double plata         = Double.parseDouble(lineSplit[7]);
				TelefonskoOdeljenje to = TelefonskoOdeljenje.valueOf(lineSplit[8]);
				String korisnickoIme = lineSplit[9];
				String lozinka       = lineSplit[10];
				Dispecer dispecer = new Dispecer(id, ime, prezime, JMBG, adresa, pol, 
						brojTelefona, plata, to, korisnickoIme, lozinka);
				dispeceri.add(dispecer);
				
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla dispeceri",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void sacuvajDispecere() {
		try {
			File dispeceriFile = new File("src/txt/dispeceri.txt");
			String content = "";
			for (Dispecer dispecer : dispeceri) {
				
				content += dispecer.getId() + "|" +
						dispecer.getIme() + "|" +
						dispecer.getPrezime()+ "|" +
						dispecer.getJMBG() + "|" +
						dispecer.getAdresa() + "|" +
						dispecer.getPol() + "|" + 
						dispecer.getBrojTelefona() + "|" +
						dispecer.getPlata() + "|" +
						dispecer.getTelefonskaOdeljenje() + "|" +
						dispecer.getKorisnickoIme() + "|" +
						dispecer.getLozinka()  +"\n";
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(dispeceriFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  boolean loginDispecer(String korisnickoIme, String lozinka){
		for(Dispecer dispecer: dispeceri){
			if(dispecer.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)&& 
					dispecer.getLozinka().equals(lozinka)){
				return true;
				
			}
		}
		return false;
	}
	
	public  Dispecer nadjiDispecera(String id){
		for (Dispecer dispecer : dispeceri) {
			if(dispecer.getId().equals(id)){
				return dispecer;
				
			}
		}
		return null;
	}
	
	public  Dispecer nadjiDispeceraPoKorisnickomImenu(String korisnickoIme){
		for (Dispecer dispecer : dispeceri) {
			if(dispecer.getKorisnickoIme().equals(korisnickoIme)){
				return dispecer;
				
			}
		}
		return null;
	}
	public void dodajDispecera(Dispecer dispecer) {
		this.dispeceri.add(dispecer);
	}
	
	public void obrisiDispecera(Dispecer dispecer) {
		this.dispeceri.remove(dispecer);
	}

	public void Dispecera(Dispecer dispecer) {
		this.dispeceri.remove(dispecer);
	}
	
	public ArrayList<Dispecer> getDispeceri() {
		return dispeceri;
	}



	public void setDispeceri(ArrayList<Dispecer> dispeceri) {
		this.dispeceri = dispeceri;
	}
	
//------------------------------------------------------------------------------------------------------------------		
	
	public void ucitajMusterije(){
		try{
			File musterijeFile=new File("src/txt/musterije.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit   = line.split("\\|");
				String id            = lineSplit[0];
				String ime           = lineSplit[1];
				String prezime       = lineSplit[2];
				String JMBG          = lineSplit[3];
				String adresa        = lineSplit[4];
				Pol pol              = Pol.valueOf(lineSplit[5]);
				int brojTelefona     = Integer.parseInt(lineSplit[6]);
				String korisnickoIme = lineSplit[7];
				String lozinka       = lineSplit[8];
				Boolean aplikacija   =Boolean.parseBoolean(lineSplit[9]) ;
				Musterija musterija = new Musterija(id, ime, prezime, JMBG, adresa, pol, brojTelefona, 
													korisnickoIme, lozinka, aplikacija);
				musterije.add(musterija);
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla musterije",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void sacuvajMusterije() {
		try {
			File musterijeFile = new File("src/txt/musterije.txt");
			String content = "";
			for (Musterija musterija : musterije) {
				content += musterija.getId() + "|" +
						musterija.getIme() + "|" +
						musterija.getPrezime()+ "|" +
						musterija.getJMBG() + "|" +
						musterija.getAdresa() + "|" +
						musterija.getPol() + "|" + 
						musterija.getBrojTelefona() + "|" +
						musterija.getKorisnickoIme() + "|" +
						musterija.getLozinka() + "|" +
						musterija.isAplikacija() +"\n";
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(musterijeFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  boolean loginMusterija(String korisnickoIme, String lozinka){
		for(Musterija musterija: musterije){
			if(musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)&& 
					musterija.getLozinka().equals(lozinka)){
				return true;
				
			}
		}
		return false;
	}
	
	public  Musterija nadjiMusteriju(String id){
		for (Musterija musterija : musterije) {
			if(musterija.getId().equals(id)){
				return musterija;
				
			}
		}
		return null;
	}
	
	public  Musterija nadjiMusterijuKorisnickoIme(String korisnickoIme){
		for (Musterija musterija : musterije) {
			if(musterija.getKorisnickoIme().equals(korisnickoIme)){
				return musterija;
				
			}
		}
		return null;
	}
	
	public void dodajMusteriju(Musterija musterija) {
		this.musterije.add(musterija);
	}

	public void obrisiMusteriju(Musterija musterija) {
		this.musterije.remove(musterija);
	}
	
	public void setMusterije(ArrayList<Musterija> musterije) {
		this.musterije = musterije;
	}



	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}
//----------------------------------------------------------------------------------------------------------------
	

	public void ucitajVozace(){
		try{

			File vozacFile=new File("src/txt/vozaci.txt");
			BufferedReader reader = new BufferedReader(new FileReader(vozacFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit       = line.split("\\|");
				String id                = lineSplit[0];
				String ime               = lineSplit[1];
				String prezime           = lineSplit[2];
				String JMBG              = lineSplit[3];
				String adresa            = lineSplit[4];
				Pol pol                  = Pol.valueOf(lineSplit[5]);
				int brojTelefona         = Integer.parseInt(lineSplit[6]);
				double plata             = Double.parseDouble(lineSplit[7]);
				int brClanskeKarte       = Integer.parseInt(lineSplit[8]);
				String korisnickoIme 	 = lineSplit[9];
				String lozinka       	 = lineSplit[10];
				String idAutomobila      = lineSplit[11];
				if(idAutomobila.equals("null")){
					Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, pol, brojTelefona, 
							plata, brClanskeKarte, korisnickoIme, lozinka, null);
					vozaci.add(vozac);
					
				}else {
					
					Automobil automobilUcitano = nadjiAutomobil(idAutomobila);
					
							Vozac vozac = new Vozac(id, ime, prezime, JMBG, adresa, pol, brojTelefona, 
									plata, brClanskeKarte, korisnickoIme, lozinka, automobilUcitano);
							vozaci.add(vozac);
				}
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla vozaci",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void sacuvajVozace() {
		try {
			File vozaciFile = new File("src/txt/vozaci.txt");
			String content = "";
			for (Vozac vozac : vozaci ) {
				try{ 
					if(vozac.getAutomobil() == null){
				content += vozac.getId() + "|" +
						vozac.getIme() + "|" +
						vozac.getPrezime()+ "|" +
						vozac.getJMBG() + "|" +
						vozac.getAdresa() + "|" +
						vozac.getPol() + "|" + 
						vozac.getBrojTelefona() + "|" +
						vozac.getPlata() + "|" +
						vozac.getBrClanskeKarte() + "|" +
						vozac.getKorisnickoIme() + "|" +
						vozac.getLozinka() + "|" +
						"null" +"\n";
					}else
						content += vozac.getId() + "|" +
						vozac.getIme() + "|" +
						vozac.getPrezime()+ "|" +
						vozac.getJMBG() + "|" +
						vozac.getAdresa() + "|" +
						vozac.getPol() + "|" + 
						vozac.getBrojTelefona() + "|" +
						vozac.getPlata() + "|" +
						vozac.getBrClanskeKarte() + "|" +
						vozac.getKorisnickoIme() + "|" +
						vozac.getLozinka() + "|" +
						vozac.getAutomobil().getBrTaxiVozila() +"\n";
				}catch (Exception e) {
					
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(vozaciFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  boolean loginVozac(String korisnickoIme, String lozinka){
		for(Vozac vozac: vozaci){
			if(vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)&& 
					vozac.getLozinka().equals(lozinka)){
				return true;
				
			}
		}
		return false;
	}
	
	public  Vozac nadjiVozaca(String id){
		for (Vozac vozac : vozaci) {
			if(vozac.getId().equals(id)){
				return vozac;
				
			}
		}
		return null;
	}
	
	public  Vozac nadjiVozacaPoClanskoj(int clanska){
		for (Vozac vozac : vozaci) {
			if(vozac.getBrClanskeKarte() == clanska){
				return vozac;
				
			}
		}
		return null;
	}
	
	public  Vozac nadjiVozacaPoKorisnickomImenu(String korisnickoIme){
		for (Vozac vozac : vozaci) {
			if(vozac.getKorisnickoIme().equals(korisnickoIme)){
				return vozac;
				
			}
		}
		return null;
	}
	
	public void dodajVozaca(Vozac vozac) {
		this.vozaci.add(vozac);
	}

	public void obrisiVozaca(Vozac vozac) {
		this.vozaci.remove(vozac);
	}
	
	public ArrayList<Vozac> getVozaci() {
		return vozaci;
	}



	public void setVozaci(ArrayList<Vozac> vozaci) {
		this.vozaci = vozaci;
	}
//---------------------------------------------------------------------------------------------------------------------
	
	public void ucitajAutomobile (){
		try{
			File musterijeFile=new File("src/txt/automobili.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit              = line.split("\\|");
				String proizvodjac              = lineSplit[0];
				String model                    = lineSplit[1];
				int godinaProizvodnje           = Integer.parseInt(lineSplit[2]);
				int brRegistarskeOznake         = Integer.parseInt(lineSplit[3]);
				String brTaxiVozila             = lineSplit[4];
				VrstaAutomobila vrstaAutomobila = VrstaAutomobila.valueOf(lineSplit[5]);
				
				Automobil auto = new Automobil(proizvodjac, model, godinaProizvodnje, 
								brRegistarskeOznake, brTaxiVozila , vrstaAutomobila);
				automobili.add(auto);
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla automobili",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public void sacuvajAutomobile() {
		try {
			File automobiliFile = new File("src/txt/automobili.txt");
			String content = "";
			for (Automobil automobil : automobili) {
				content += automobil.getProizvodjac() + "|" +
						automobil.getModel() + "|" +
						automobil.getGodinaProizvodnje()+ "|" +
						automobil.getBrRegistarskeOznake() + "|" +
						automobil.getBrTaxiVozila() + "|" +
						automobil.getVrstaAutomobila() +  "\n";
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(automobiliFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Automobil nadjiAutomobil(String brTaxiVozila){
		for (Automobil automobil : automobili) {
			if(automobil.getBrTaxiVozila().equals(brTaxiVozila)){
				return automobil;
				
			}
		}
		return null;
	}
	
	public Automobil nadjiAutomobilZaVozaca(String brTaxiVozila){
		for (Automobil automobil : automobili) {
			if(automobil.getBrTaxiVozila().equals(brTaxiVozila)){
				return automobil;
				
			}
		}
		return null;
	}

	public ArrayList<Automobil> automobiliBezVozacaLista(){
		ArrayList<Automobil> autoLista = new ArrayList<Automobil>();
		for(Automobil auto : automobili){
			autoLista.add(new Automobil(auto));
		}
		for (Vozac vozac : getVozaci()){
			if(vozac.getAutomobil() != null){
				for(Automobil auto : autoLista){
					if(vozac.getAutomobil().getBrTaxiVozila().equals(auto.getBrTaxiVozila())){
						autoLista.remove(auto);
						break;
					}
				}
			}
		}
		return automobiliBezVozacaLista = autoLista;
	}
	
	

	
	public void dodajAutomobil(Automobil automobil) {
		this.automobili.add(automobil);
	}

	public void obrisiAutomobil(Automobil automobil) {
		this.automobili.remove(automobil);
	}
	
	public ArrayList<Automobil> getAutomobiliBezVozacaLista() {
		return automobiliBezVozacaLista;
	}
	public void setAutomobiliBezVozacaLista(ArrayList<Automobil> automobiliBezVozacaLista) {
		this.automobiliBezVozacaLista = automobiliBezVozacaLista;
	}
	
	
	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}



	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}


	
//-------------------------------------------------------------------------------------------------------------------	
	
	public void ucitajVoznjeApp () throws ParseException{
		try{
			File musterijeFile=new File("src/txt/voznjaApp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit       = line.split("\\|");
				String datum             = lineSplit[0];
				String adresaPolaska     = lineSplit[1];
				String musterija         = lineSplit[2];
				String vozac             = lineSplit[3];
				String napomena          = lineSplit[4];
				int kordinateX           = Integer.parseInt(lineSplit[5].split(",")[0]);
				int kordinateY           = Integer.parseInt(lineSplit[5].split(",")[1]);
				Musterija musterijaUcitano = nadjiMusteriju(musterija);
				if(vozac.equals("null")){
					VoznjaAplikacija voznjaApp = new VoznjaAplikacija(datum, adresaPolaska, musterijaUcitano, 
							null, napomena, kordinateX, kordinateY);
					voznjaAplikacijaUcitano.add(voznjaApp);
				}else{
					Vozac vozacUcitano = nadjiVozaca(vozac);
				VoznjaAplikacija voznjaApp = new VoznjaAplikacija(datum, adresaPolaska, musterijaUcitano, 
									vozacUcitano, napomena, kordinateX, kordinateY);
				voznjaAplikacijaUcitano.add(voznjaApp);
				}
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla voznjaApp",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public void sacuvajVoznjeApp() {
		try {
			File voznjeAppFile = new File("src/txt/voznjaApp.txt");
			String content = "";
			for (VoznjaAplikacija voznjaApp : voznjaAplikacijaUcitano ) {
				
				try {
					if(voznjaApp.getVozac() == null){
					content += voznjaApp.getVremePolaska() + "|" +
							voznjaApp.getAdresaPolaska() + "|" +
							voznjaApp.getMusterija().getId()+ "|" +
							"null" + "|" +
							voznjaApp.getNapomena() + "|" +
							voznjaApp.getKordinateX() + "," + 
							voznjaApp.getKordinateY() + "\n";
					}else{
						
						content += voznjaApp.getVremePolaska() + "|" +
								voznjaApp.getAdresaPolaska() + "|" +
								voznjaApp.getMusterija().getId()+ "|" +
								voznjaApp.getVozac().getId() + "|" +
								voznjaApp.getNapomena() + "|" +
								voznjaApp.getKordinateX() + "," + 
								voznjaApp.getKordinateY() + "\n";
					}
						
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(voznjeAppFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public  VoznjaAplikacija nadjiVoznjuApp(String datum){
		for (VoznjaAplikacija voznjaAplikacija : voznjaAplikacijaUcitano) {
			if(voznjaAplikacija.getVremePolaska().equals(datum)){
				return voznjaAplikacija;
				
			}
		}
		return null;
	}
	
	
	public void dodajVoznjuApp(VoznjaAplikacija voznjaApp) {
		this.voznjaAplikacijaUcitano.add(voznjaApp);
	}

	public void obrisiVoznjuApp(VoznjaAplikacija voznjaApp) {
		this.voznjaAplikacijaUcitano.remove(voznjaApp);
	}
	

	public ArrayList<VoznjaAplikacija> getVoznjaAplikacijaUcitano() {
		return voznjaAplikacijaUcitano;
	}



	public void setVoznjaAplikacijaUcitano(ArrayList<VoznjaAplikacija> voznjaAplikacijaUcitano) {
		this.voznjaAplikacijaUcitano = voznjaAplikacijaUcitano;
	}



//-------------------------------------------------------------------------------------------------------------------
	

	public void ucitajVoznjeDispecer () throws ParseException{
		try{
			File musterijeFile=new File("src/txt/voznjaDispecer.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line = null;
			while((line = reader.readLine())!=null){
				String[] lineSplit         = line.split("\\|");
				String datum               = lineSplit[0];
				String adresaPolaska       = lineSplit[1];
				int musterija              = Integer.parseInt(lineSplit[2]);
				String vozac               = lineSplit[3];
				String dispecer            = lineSplit[4];
				Dispecer dispecerUcitano   = nadjiDispecera(dispecer);
				Vozac vozacUcitano = nadjiVozaca(vozac);
				
					VoznjaDispecer voznjaDispecer = new VoznjaDispecer(datum, adresaPolaska, 
							musterija, vozacUcitano, dispecerUcitano);
					voznjeDispecerUcitano.add(voznjaDispecer);
				
			}
			reader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Greska prilikom citanja iz fajla voznjaDispecer",
					"Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public void sacuvajVoznjeDispecer() {
		try {
			File voznjeDispecerFile = new File("src/txt/voznjaDispecer.txt");
			String content = "";
			for (VoznjaDispecer voznjaDispecer : voznjeDispecerUcitano ) {
				content += voznjaDispecer.getVremePolaska() + "|" +
						voznjaDispecer.getAdresaPolaska() + "|" +
						voznjaDispecer.getBrMusterije()+ "|" +
						voznjaDispecer.getVozac().getId() + "|" +
						voznjaDispecer.getDispecer().getId() + "\n";
			}
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(voznjeDispecerFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  VoznjaDispecer nadjiVoznjuDispecer(String datum){
		for (VoznjaDispecer voznjaDispecer : voznjeDispecerUcitano) {
			if(voznjaDispecer.getVremePolaska().equals(datum)){
				return voznjaDispecer;
				
			}
		}
		return null;
	}
	
	public void dodajVoznjuDispecer(VoznjaDispecer voznjaDispecer) {
		this.voznjeDispecerUcitano.add(voznjaDispecer);
	}

	public void obrisiVoznjuDispecer(VoznjaDispecer voznjaDispecer) {
		this.voznjeDispecerUcitano.remove(voznjaDispecer);
	}
	
	
	public ArrayList<VoznjaDispecer> getVoznjeDispecerUcitano() {
		return voznjeDispecerUcitano;
	}
	public void setVoznjeDispecerUcitano(ArrayList<VoznjaDispecer> voznjeDispecerUcitano) {
		this.voznjeDispecerUcitano = voznjeDispecerUcitano;
	}
	
//-------------------------------------------------------------------------------------------------------------------	
	public ArrayList<Automobil> getTrenutniAuto() {
		return trenutniAuto;
	}


	public void setTrenutniAuto(ArrayList<Automobil> trenutniAuto) {
		this.trenutniAuto = trenutniAuto;
	}
	
	



	
	
}

