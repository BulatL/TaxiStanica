package enumeracija;

public enum VrstaAutomobila {

	PutnickiAautomobil,
	Kombi;

	public static VrstaAutomobila valueOf(int a) {
		switch (a) {
		case 1:
				return PutnickiAautomobil;
		default:
				return Kombi;
		}
	}
	public static int toInt(VrstaAutomobila va) {
		switch (va) {
		case PutnickiAautomobil:
				return 1;
		default:
				return 2;
	}
}
}