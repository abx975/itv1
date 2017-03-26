/**
 * Praktikum PM2, WS 15/16
 *
 * Aufgabe 4
 */

package blatt4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasse zur Darstellung der erlaubten Befehle
 * 
 * @author grimmilenko
 *
 */
public class PolygonSkripting {
	private final Pattern pattern;
	private Matcher matcher;

	/**
	 * Konstruktor
	 */
	public PolygonSkripting() {
		pattern = Pattern.compile("(bewege)\\s->" + "\\s(0|-?[0-9]*\\.?[0-9]*),\\s?(0|-?[0-9]*\\.?[0-9]*)");
		matcher = null;
	}

	/**
	 * Methode ueberprueft, ob der eingebene Befehl gueltig ist
	 * 
	 * @param eingabeBefehl
	 *            Der eingegebene Befehl
	 * @return liefert einen Boolean für die Gueltigkeit des Befehls zurueck
	 */
	public boolean istGueltigerBefehl(String eingabeBefehl) {
		matcher = pattern.matcher(eingabeBefehl);
		return matcher.matches();
	}

	/**
	 * Methode, sucht aus dem Eingabe String die X-Koordinate
	 * 
	 * @param eingabeBefehl
	 *            Eingabe-String
	 * @return X-Koordinate aus dem Eingabe-String
	 * @throws Exception
	 *             Bei ungueltigem Befehl wird eine Exception geworfen
	 */
	public double getX(String eingabeBefehl) throws Exception {
		if (istGueltigerBefehl(eingabeBefehl)) {
			return new Double(matcher.group(2));
		} else {
			throw new Exception("Befehl ungueltig!");
		}
	}

	/**
	 * Methode, sucht aus dem Eingabe String die Y-Koordinate
	 * 
	 * @param eingabeBefehl
	 *            Eingabe-String
	 * @return Y-Koordinate aus dem Eingabe-String
	 * @throws Exception
	 *             Bei ungueltigem Befehl wird eine Exception geworfen
	 */
	public double getY(String eingabeBefehl) throws Exception {
		if (istGueltigerBefehl(eingabeBefehl)) {
			return new Double(matcher.group(3));
		} else {
			throw new Exception("Befehl ungueltig!");
		}
	}

}