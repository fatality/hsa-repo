package PruefungWS08;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Speisekarte sk = new Speisekarte(2);
		Gericht g1 = new Gericht("g1",Essenstyp.HAUPTGERICHT,true,50);
		Gericht g2 = new Gericht("g2",Essenstyp.HAUPTGERICHT,true,40);

		sk.addGericht(g1);
		sk.addGericht(g2);
		
		System.out.println(sk.getBilligstes(g1.typ.toString()));
	}

}
