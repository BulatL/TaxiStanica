package voznja;

import java.util.Date;

import korisnici.Musterija;
import korisnici.Vozac;

public class Voznja {

	private String vremePolaska;	
	private String adresaPolaska;
	//private Musterija musterija;
	private Vozac vozac;
	
	
	public Voznja(String vremePolaska, String adresaPolaska, Vozac vozac){
		this.vremePolaska   = vremePolaska;
		this.adresaPolaska  = adresaPolaska;
		//this.musterija      = musterija;
		this.vozac          = vozac;
	}

	public Voznja (){
		this.vremePolaska   = "";
		this.adresaPolaska  = "";
		//this.musterija      = new Musterija();
		this.vozac          = new Vozac();
		
	}

	/*public static Date stringUDatum(String datum){
		try{
			DateFormat vremeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			return vremeFormat.parse(datum);
		}catch (ParseException pe){
			pe.printStackTrace();
		}
		return null;
	}*/
	
	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getAdresaPolaska() {
		return adresaPolaska;
	}

	public void setAdresaPolaska(String adresaPolaska) {
		this.adresaPolaska = adresaPolaska;
	}

	/*public Musterija getMusterija() {
		return musterija;
	}

	public void setMusterija(Musterija musterija) {
		this.musterija = musterija;
	}*/

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	
}