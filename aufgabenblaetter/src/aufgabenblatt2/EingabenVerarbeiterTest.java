package aufgabenblatt2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * @author Eduard Veit, Nils Eggebrecht
 *
 */
public class EingabenVerarbeiterTest {

	String leerHinten = "EINGABE ";
	String Umlaute = "Äußeres ";
	String leerMitteVorne = " ein Haus";
	String langesWort = "Straßen-Feger";
	String[] eingabeArray = {null, leerHinten, Umlaute, null, langesWort, leerMitteVorne, null};
	List<String> eingabeList;
	EingabenVerarbeiter arbeiter;

	
	
	public EingabenVerarbeiterTest() {
		arbeiter = new EingabenVerarbeiter();
	}
	
	/**
	 * Test method for {@link aufgabenblatt2.EingabenVerarbeiter#verarbeiteEingaben(java.lang.String[])}.
	 */
	@Test
	public void testVerarbeiteEingaben() {
		eingabeList = arbeiter.verarbeiteEingaben(eingabeArray);
		assertTrue(eingabeList.size() == 4);
		assertTrue("Hinteres Leerzeichen wurde nicht entfernt.", eingabeList.get(0).equals("EINGABE"));
		assertTrue(eingabeList.get(2).equals("STRASSEN"));
		assertTrue("Umlauteersetzung", eingabeList.get(1).equals("AEUSSERE"));
	}
	
	



}
