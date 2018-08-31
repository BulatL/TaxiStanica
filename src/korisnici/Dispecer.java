package korisnici;
import enumeracija.Pol;
import enumeracija.TelefonskoOdeljenje;

public class Dispecer extends Korisnici {
	
	private double plata;
	private TelefonskoOdeljenje telefonskaOdeljenje;
	private String id;
	
//	public enum TelefonskaOdeljenja{Prijem, reklamacija}
	
	


	public Dispecer( String id, String ime, String prezime, String JMBG, String adresa, Pol pol, int brojTelefona,
					 double plata, TelefonskoOdeljenje telefonskaOdeljenje,
					 String korisnickoIme, String lozinka) {
		
		super( ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka);
		this.id                  = id;
		this.plata               = plata;
		this.telefonskaOdeljenje = telefonskaOdeljenje;
	}

	
	public Dispecer (){
		super();
		this.id                  = "";
		this.plata               = 0.0;
		this.telefonskaOdeljenje = TelefonskoOdeljenje.Prijem;
	}

	public double getPlata() {
		return plata;
	}


	public void setPlata(double plata) {
		this.plata = plata;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public TelefonskoOdeljenje getTelefonskaOdeljenje() {
		return telefonskaOdeljenje;
	}


	public void setTelefonskaOdeljenje(TelefonskoOdeljenje telefonskaOdeljenje) {
		this.telefonskaOdeljenje = telefonskaOdeljenje;
	}
	
	
	
}
