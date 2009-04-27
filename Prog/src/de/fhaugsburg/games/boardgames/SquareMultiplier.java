//package de.fhaugsburg.games.boardgames;
//
//public enum SquareMultiplier 
//{
	// Anlegung der Konstanten
//	WORDMULTIPLIER(2,3), LETTERMULTIPLIER(2,3);
	
	// Datenfeld
//	private int twice;
//	private int thirce;
	
	
	// Konstruktor
//	SquareMultiplier(int var, int var1)
//	{
//		twice = var;
//		thirce = var1;
//	}

	
	//In den Methoden muss noch ein if-Konstrukt implementiert werden, damit entweder die 2 oder die 3 zurückgegeben wird
//	public int getWordMultiplier()
//	{
//		return thirce;
//	}
	
//	public int getLetterMultiplier()
//	{
//		return twice;
//	}
//	
//}
//}

package de.fhaugsburg.games.boardgames;

public enum SquareMultiplier 
{
	// Anlegung der Konstanten
	WordMultiplier_2(2), WordMultiplier_3(3), LetterMultiplier_2(2), LetterMultiplier_3(3), Dummy(1);

	private int multiplier;
	
	SquareMultiplier(int multiplier)
	{
		this.multiplier = multiplier;
	}
	
	public int getMultiplier()
	{
		return multiplier;
	}
}
