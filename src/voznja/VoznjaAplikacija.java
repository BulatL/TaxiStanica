package voznja;

import java.util.Date;

import korisnici.Musterija;
import korisnici.Vozac;

public class VoznjaAplikacija extends Voznja {

	private Musterija musterija;
	private String napomena;
	private int kordinateX;
	private int kordinateY;
	
	
	public VoznjaAplikacija(String vremePolaska, String adresaPolaska, Musterija musterija, 
				Vozac vozac, String napomena,int kordinateX,int kordinateY) {
		super(vremePolaska, adresaPolaska, vozac);
		this.musterija  = musterija;
		this.napomena   = napomena;
		this.kordinateX = kordinateX;
		this.kordinateY = kordinateY;
	}

	
	public VoznjaAplikacija(){
		super();
		this.musterija  = new Musterija();
		this.napomena   = "";
		this.kordinateX = 0;
		this.kordinateY = 0;
	}


	public String getNapomena() {
		return napomena;
	}


	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}


	public int getKordinateX() {
		return kordinateX;
	}


	public void setKordinateX(int kordinateX) {
		this.kordinateX = kordinateX;
	}


	public int getKordinateY() {
		return kordinateY;
	}


	public void setKordinateY(int kordinateY) {
		this.kordinateY = kordinateY;
	}


	public Musterija getMusterija() {
		return musterija;
	}


	public void setMusterija(Musterija musterija) {
		this.musterija = musterija;
	}

	
}