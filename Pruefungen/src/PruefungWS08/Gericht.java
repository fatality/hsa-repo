package PruefungWS08;


public class Gericht {
	
	public final String name;
	public final Essenstyp typ;
	public final boolean fleischlos;
	private int preis;
	
	public Gericht(String name, Essenstyp typ, boolean fleischlos, int preis){
		
		this.name = name;
		this.typ = typ;
		this.fleischlos = fleischlos;
		this.preis = preis;
		
	}


	public void setPreis(int preis){
		this.preis = preis;	
	}
	
	public int getPreis(){
		return preis;
	}
	
	public String toString(){
		String s = "Name: " + name + "\nEssenstyp: " + typ.toString() + "\nFleischlos: "
		            + fleischlos + "\nPreis: " + preis + "cent";
		return s;
	}
}
