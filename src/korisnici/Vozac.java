package korisnici;

import automobil.Automobil;
import enumeracija.Pol;

public class Vozac extends Korisnici {
	
	private double plata;
	private int brClanskeKarte;
	private Automobil automobil;
	private String id;
	
	
	
	
	public Vozac(String id, String ime, String prezime, String jMBG, String adresa, Pol pol, int brojTelefona,
				  double plata, int brClanskeKarte,
				  String korisnickoIme, String lozinka,Automobil automobil) {
		
		super( ime, prezime, jMBG, adresa, pol, brojTelefona,korisnickoIme, lozinka);
		this.id              = id;
		this.plata           = plata;
		this.brClanskeKarte  = brClanskeKarte;
		this.automobil       = automobil;
	}
	
	public Vozac (){
		super();
		this.plata           = 0;
		this.brClanskeKarte  = 0;
		this.automobil       = new Automobil();
	}


	public double getPlata() {
		return plata;
	}


	public void setPlata(double plata) {
		this.plata = plata;
	}


	public int getBrClanskeKarte() {
		return brClanskeKarte;
	}


	public void setBrClanskeKarte(int brClanskeKarte) {
		this.brClanskeKarte = brClanskeKarte;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}