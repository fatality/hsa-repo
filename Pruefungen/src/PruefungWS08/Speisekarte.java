package PruefungWS08;


public class Speisekarte {

	private Gericht[] gerichte;
	private int z = 0;
	
	public Speisekarte(int n) {
		gerichte = new Gericht[n];
	}
	
	public void addGericht(Gericht gericht) {
		for (int i = 0; i < gerichte.length; i++) {
			if (gerichte[i] == null) {
				gerichte[i] = gericht;
			}
		}
	}
	
	public Gericht getBilligstes(Essenstyp typ){
		Gericht bG = null , aG;
		
		for (int i = 0; i < z; i++) {
			if (gerichte[i].typ != typ)
				continue;
			aG = gerichte[i];
			
			if (bG == null) {
				bG = aG;
				continue;
			}
			
			if (aG.getPreis() < bG.getPreis()) {
				bG = aG;
			}
		}
		return bG;
	}
}
