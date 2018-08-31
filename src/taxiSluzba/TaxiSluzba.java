package taxiSluzba;

public class TaxiSluzba {

	private int PIB;
	private String naziv;
	private String adresa;
	
	
	
	public TaxiSluzba(int PIB, String naziv, String adresa) {
		super();
		this.PIB    = PIB;
		this.naziv  = naziv;
		this.adresa = adresa;
	}
	
	public TaxiSluzba(){
		this.PIB    = 0;
		this.naziv  = "";
		this.adresa = "";
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int pIB) {
		PIB = pIB;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	
}