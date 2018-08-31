package taxiSluzba;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GUI.LoginProzor;

public class TaxiSluzbaMain {

	public static void main(String[] args) throws ParseException {
		
		TaxiSluzbaFunkcionalnosti ts = new TaxiSluzbaFunkcionalnosti();
		ts.ucitajDispecere();
		ts.ucitajAutomobile();
		ts.ucitajMusterije();
		ts.ucitajVozace();
		ts.ucitajVoznjeApp();
		ts.ucitajTaxiSluzbu();
		ts.ucitajVoznjeDispecer();
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);
		System.out.println("Report Date: " + reportDate);
		
		
		LoginProzor lp = new LoginProzor(ts);
		lp.setVisible(true);

	}

}
