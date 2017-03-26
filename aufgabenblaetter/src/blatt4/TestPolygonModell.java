/**
 * Praktikum PM2, WS 15/16
 * Nico Grimm (nico.grimm@haw-hamburg.de)
 * Marco Colbow (marco.colbow@haw-hamburg.de)
 * Aufgabe 4
 */

package blatt4;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test-Klasse zur Ueberpruefung der Funktionalitaeten der PolygonModell-Klasse
 * 
 * @author grimmilenko
 *
 */
public class TestPolygonModell {

	@Test
	public void testBearbeitungZuEnde() {
		PolygonModell test = new PolygonModell(new PolygonModell());
		test.getPolygon().setPunkt(1, 2);

		Polygon akt = test.getPolygon();

		test.bearbeitungZuEnde();
		assertFalse(test.getListePolygone().isEmpty());
		assertEquals(akt, test.getListePolygone().get(0));

		Polygon neu = test.getPolygon();
		assertNotEquals(akt, neu);
	}

}