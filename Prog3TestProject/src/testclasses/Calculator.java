/**
 * Addiert zwei Zahlen miteinander und hinterlegt eine geheime (private) Zahl
 * 
 * @author fatality
 * @date 2009-10-22
 * @license GNU General Public License v3
 * 
 * http://www.gironimo.org/
 */

package testclasses;

public class Calculator {

    public int zahl1;
    public int zahl2;
    public int ergebnis;
    private int geheim;

    /**
     * Konstruktor
     * 
     * @param zahl1
     * @param zahl2
     * @param geheim
     */
    public Calculator(int zahl1, int zahl2, int geheim) {
	this.zahl1 = zahl1;
	this.zahl2 = zahl2;
	this.geheim = geheim;
    }

    /**
     * Einfache Methode zum addieren zweier Zahlen
     * 
     * @return ergebnis
     */
    public int addInt() {
	ergebnis = zahl1 + zahl2;
	return ergebnis;
    }

    @SuppressWarnings("unused")
    private void setGeheim(int geheim) {
	this.geheim = geheim;
    }

    /**
     * Liefert die geheime Zahl zurück
     * 
     * @return geheim
     */
    @SuppressWarnings("unused")
    private int getGeheim() {
	return geheim;
    }

    /**
     * Ausgabe auf dem Bildschirm als String
     * 
     * @return s
     */
    public String toString() {
	String s = "Ihre Eingabe: " + zahl1 + " + " + zahl2 + " = " + ergebnis;
	return s;
    }

    /**
     * Main Methode übergibt die Parameter und startet die Addiermethode, diese
     * werden dann zurück gegeben
     * 
     * @param args
     */
    public static void main(String[] args) {
	Calculator cl = new Calculator(10, 2, 0);
	cl.addInt();
	System.out.println(cl.toString());
    }

}