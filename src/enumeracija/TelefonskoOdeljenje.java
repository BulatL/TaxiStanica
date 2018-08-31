package enumeracija;

public enum TelefonskoOdeljenje {

	Prijem,
	Reklamacija;

	public static TelefonskoOdeljenje valueOf(int a) {
		switch (a) {
		case 1:
				return Prijem;
		default:
				return Reklamacija;
		}
	}
	public static int toInt(TelefonskoOdeljenje to) {
		switch (to) {
		case Prijem:
				return 1;
		default:
				return 2;
		}
	}
	
}