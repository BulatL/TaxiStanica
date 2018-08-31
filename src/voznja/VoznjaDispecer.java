package voznja;

import java.util.Date;

import korisnici.Dispecer;
import korisnici.Vozac;

public class VoznjaDispecer extends Voznja {

	private Dispecer dispecer;
	private int      brMusterije;

	public VoznjaDispecer(String vremePolaska, String adresaPolaska, int brMusterije,
				Vozac vozac, Dispecer dispecer) {
		super(vremePolaska, adresaPolaska, vozac);
		this.brMusterije = brMusterije;
		this.dispecer    = dispecer;
		
	}
	
	
	public VoznjaDispecer(){
		super();
		this.brMusterije  = 0;
		this.dispecer     = new Dispecer();
	}


	
	public Dispecer getDispecer() {
		return dispecer;
	}


	public void setDispecer(Dispecer dispecer) {
		this.dispecer = dispecer;
	}


	public int getBrMusterije() {
		return brMusterije;
	}


	public void setBrMusterije(int brMusterije) {
		this.brMusterije = brMusterije;
	}
	
}