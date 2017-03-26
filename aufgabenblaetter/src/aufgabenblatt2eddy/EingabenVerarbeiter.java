/**
 * 
 */
package aufgabenblatt2eddy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Eddy
 *
 */
public class EingabenVerarbeiter {

	public List<String> verarbeiteEingaben(String[] eingabenArray) {
		List<String> result = new ArrayList<String>();
		List<String> eingabenListe = Arrays.asList(eingabenArray);
		Predicate<String> notNull = s -> s != null;
		
		eingabenListe.stream()
			.filter(notNull)
			.map(String::trim)
			.map(String::toUpperCase)
			.map(string -> string.replace("Ä", "AE"))
			.map(string -> string.replace("Ö", "OE"))
			.map(string -> string.replace("Ü", "UE"))
			.map(string -> string.replace("ß", "SS"))
			.map(string -> {
				if (string.length() > 8) {
					string = string.substring(0, 8);
				} return string;
			})
			.forEach(string -> result.add(string));
		return result;
	}

}
