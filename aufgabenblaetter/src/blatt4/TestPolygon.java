/**
 * Praktikum PM2, WS 15/16
 *
 * Aufgabe 4
 */

package blatt4;

import static org.junit.Assert.*;

import java.util.Observer;

import org.junit.Test;

/**
 * Test-Klasse zur Ueberpruefung der Funktionalitaeten der Polygon-Klasse
 * 
 * @author grimmilenko
 *
 */
public class TestPolygon {

	@Test
	public void testGetXAtIndex() {
		Polygon test = new Polygon((Observer) this);
		test.setPunkt(1, 2); // index 0
		test.setPunkt(-25, 1.264); // index 1
		test.setPunkt(0.2, -2.745); // index 2
		test.setPunkt(0, 0); // index 3
		test.setPunkt(16, 16); // index 4

		assertEquals(1, test.getXAtIndex(0), 0e-10);
		assertEquals(-25, test.getXAtIndex(1), 0e-10);
		assertEquals(0.2, test.getXAtIndex(2), 0e-10);
		assertEquals(0, test.getXAtIndex(3), 0e-10);
		assertEquals(16, test.getXAtIndex(4), 0e-10);

		try {
			test.getXAtIndex(-5);
			assertTrue("Fehler! Keine Exception wurde geworfen", false);
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			test.getXAtIndex(25);
			assertTrue("Fehler! Keine Exception wurde geworfen", false);
		} catch (IndexOutOfBoundsException e) {

		}
	}

	@Test
	public void testGetYAtIndex() {
		Polygon test = new Polygon((Observer) this);
		test.setPunkt(1, 2); // index 0
		test.setPunkt(-25, 1.264); // index 1
		test.setPunkt(0.2, -2.745); // index 2
		test.setPunkt(0, 0); // index 3
		test.setPunkt(16, 16); // index 4

		assertEquals(2, test.getYAtIndex(0), 0e-10);
		assertEquals(1.264, test.getYAtIndex(1), 0e-10);
		assertEquals(-2.745, test.getYAtIndex(2), 0e-10);
		assertEquals(0, test.getYAtIndex(3), 0e-10);
		assertEquals(16, test.getYAtIndex(4), 0e-10);

		try {
			test.getYAtIndex(-5);
			assertTrue("Fehler! Keine Exception wurde geworfen", false);
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			test.getYAtIndex(25);
			assertTrue("Fehler! Keine Exception wurde geworfen", false);
		} catch (IndexOutOfBoundsException e) {

		}
	}
}