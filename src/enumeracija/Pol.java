package enumeracija;

public enum Pol {
		MUSKI,
		ZENSKI;
	
		public static Pol valueOf(int a) {
			switch (a) {
			case 1:
					return MUSKI;
			default:
					return ZENSKI;
			}
		}
		public static int toInt(Pol pol) {
			switch (pol) {
			case MUSKI:
					return 1;
			default:
					return 2;
		}
	}
}