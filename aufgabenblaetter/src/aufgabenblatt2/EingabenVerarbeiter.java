/**
 * 
 */
package aufgabenblatt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Eduard Veit, Nils Eggebrecht
 *
 */
public class EingabenVerarbeiter {

	public List<String> verarbeiteEingaben(String[] eingabenArray) {

		/**
		 * Liste fuer die bearbeiteten Woerter
		 */
		List<String> result = new ArrayList<String>();
		/**
		 * Liste fuer die eingabe Woerter
		 */
		List<String> eingabenListe = Arrays.asList(eingabenArray);
		Predicate<String> notNull = s -> s != null;
		/**
		 * Stream erstellen, bearbeiten und in result Liste speichern.
		 */
		eingabenListe.stream()
				// Entfernen von null- Eingaben
				.filter(notNull)
				// Abschneiden der Leerzeichen am Anfang und Ende
				.map(String::trim)
				// Konvertierung von Klein- zu Großbuchstaben
				.map(String::toUpperCase)
				// Ersetzen Ä→AE, Ö→OE, Ü→UE, ß→SS
				.map(string -> string.replace("Ä", "AE"))
				.map(string -> string.replace("Ö", "OE"))
				.map(string -> string.replace("Ü", "UE"))
				.map(string -> string.replace("ß", "SS"))
				// Kuerzen des Strings auff maximal 8 Zeichen
				.map(string -> {
					if (string.length() > 8) {
						string = string.substring(0, 8);
					}
					return string;
				}).forEach(string -> result.add(string));
		return result;
	}
}
