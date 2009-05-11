package de.fhaugsburg.games.boardgames.scrabble.management;

public class TestRandomPool {
	
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		RandomPool randompool = new RandomPool();
	
		System.out.print("Sind noch Steine im Sack? ");
		if(randompool.empty() == false) {
			System.out.print("Ja");
		}
		System.out.println();
		System.out.println(randompool.toString());
		randompool.take();
		randompool.take();
		randompool.take();
		System.out.println(randompool.toString());
	}
	
}