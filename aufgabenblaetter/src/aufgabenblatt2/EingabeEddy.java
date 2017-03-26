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

	String[] eingabe = { "Eingabe ", "�u�eres ", null, "Strassen-Feger", " ein Haus" };

	public List<String> verarbeite() {
		List<String> result = new ArrayList<String>();
		List<String> eingaben = Arrays.asList(eingabe);
		Predicate<String> notNull = s -> s == null;
		eingaben.stream().filter(notNull).filter(string -> string.length() <= 8).map(String::trim)
				.map(String::toUpperCase).map(string -> string.replace("�", "AE"))
				.map(string -> string.replace("�", "OE")).map(string -> string.replace("�", "UE"))
				.map(string -> string.replace("�", "SS")).forEach(string -> result.add(string));
		return result;

	}


}