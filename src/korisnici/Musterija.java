package korisnici;
import enumeracija.Pol;

public class Musterija extends Korisnici {

	private boolean aplikacija;
	private String id;
	
	

	public Musterija( String id,String ime, String prezime, String JMBG, String adresa, Pol pol, int brojTelefona,
					  String korisnickoIme, String lozinka, boolean aplikacija) {
		
		super( ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka);
		this.id             = id;
		this.aplikacija     = aplikacija;
	}

	public Musterija (){
		super();
		this.aplikacija = false;
	}
	
	
	public boolean isAplikacija() {
		return aplikacija;
	}


	public void setAplikacija(boolean aplikacija) {
		this.aplikacija = aplikacija;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
