package aufgabenblatt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Eddy
 *
 */
public class EingabeEddy {

	String[] eingabe = { "Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };

	public List<String> verarbeite() {
		List<String> result = new ArrayList<String>();
		List<String> eingaben = Arrays.asList(eingabe);
		Predicate<String> notNull = s -> s == null;
		eingaben.stream().filter(notNull).filter(string -> string.length() <= 8).map(String::trim)
				.map(String::toUpperCase).map(string -> string.replace("Ä", "AE"))
				.map(string -> string.replace("Ö", "OE")).map(string -> string.replace("Ü", "UE"))
				.map(string -> string.replace("ß", "SS")).forEach(string -> result.add(string));
		return result;

	}


}