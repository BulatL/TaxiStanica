package korisnici;
import enumeracija.Pol;

public class Korisnici {
	//private String id;
	private String ime;
	private String prezime;
	private String JMBG;
	private String adresa;
	private Pol pol;
	private int brojTelefona;
	private String korisnickoIme;
	private String lozinka;
	
	
	//public enum Pol{ Muski, Zenski}
	

	
	public Korisnici( String ime, String prezime, String JMBG, String adresa, Pol pol, int brojTelefona, String korisnickoIme, String lozinka) {
		super();
		//this.id               = id;
		this.ime              = ime;
		this.prezime          = prezime;
		this.JMBG             = JMBG;
		this.adresa           = adresa;
		this.pol              = pol;
		this.brojTelefona     = brojTelefona;
		this.korisnickoIme	  = korisnickoIme;
		this.lozinka  		  = lozinka;
	}
	
	public Korisnici(){
		//this.id              = "";
		this.ime             = "";
		this.prezime         = "";
		this.JMBG            = "";
		this.adresa          = "";
		this.pol             = Pol.ZENSKI;
		this.brojTelefona    = 0;
	}
		

/*
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
*/
	public void setPol(Pol pol) {
		this.pol = pol;
	}


	public Pol getPol() {
		return pol;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJMBG() {
		return JMBG;
	}


	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}




	public int getBrojTelefona() {
		return brojTelefona;
	}


	public void setBrojTelefona(int brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
	
}
