package automobil;

import java.awt.PageAttributes.OriginType;

import enumeracija.VrstaAutomobila;
import korisnici.Vozac;

public class Automobil {
	
	private String model;
	private String proizvodjac;
	private int godinaProizvodnje;
	private int brRegistarskeOznake;
	private String brTaxiVozila;
	private VrstaAutomobila vrstaAutomobila;
	
	
	
	public Automobil(String proizvodjac, String model, int godinaProizvodnje, int brRegistarskeOznake,
			String brTaxiVozila, VrstaAutomobila vrstaAutomobila) {
		super();
		this.proizvodjac         = proizvodjac;
		this.model               = model;
		this.godinaProizvodnje   = godinaProizvodnje;
		this.brRegistarskeOznake = brRegistarskeOznake;
		this.brTaxiVozila        = brTaxiVozila;
		this.vrstaAutomobila     = vrstaAutomobila;
		
	}
	
	public Automobil(){
		super();
		this.proizvodjac         = "";
		this.model               = "";
		this.godinaProizvodnje   = 0; 
		this.brRegistarskeOznake = 0;
		this.brTaxiVozila        = "";
		this.vrstaAutomobila     = vrstaAutomobila.PutnickiAautomobil;
	}
	
	public Automobil(Automobil original){
		super();
		this.proizvodjac         = original.proizvodjac;
		this.model               = original.model;
		this.godinaProizvodnje   = original.godinaProizvodnje; 
		this.brRegistarskeOznake = original.brRegistarskeOznake;
		this.brTaxiVozila        = original.brTaxiVozila;
		this.vrstaAutomobila     = original.vrstaAutomobila;
	
	
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public int getBrRegistarskeOznake() {
		return brRegistarskeOznake;
	}

	public void setBrRegistarskeOznake(int brRegistarskeOznake) {
		this.brRegistarskeOznake = brRegistarskeOznake;
	}

	public String getBrTaxiVozila() {
		return brTaxiVozila;
	}

	public void setBrTaxiVozila(String brTaxiVozila) {
		this.brTaxiVozila = brTaxiVozila;
	}

	public VrstaAutomobila getVrstaAutomobila() {
		return vrstaAutomobila;
	}

	public void setVrstaAutomobila(VrstaAutomobila vrstaAutomobila) {
		this.vrstaAutomobila = vrstaAutomobila;
	}


	
}