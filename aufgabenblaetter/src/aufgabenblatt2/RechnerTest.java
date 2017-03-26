package aufgabenblatt2;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import aufgabenblatt2.Operation;
import aufgabenblatt2.Rechner;

/**
 * Diese Klasse testet die Klasse Rechner.
 * 
 * @author Nils Eggebrecht, Eduard Veit
 * @version 1.0
 */
public class RechnerTest {
	double epsilion = 0.00000000001;
	Rechner rechner = new Rechner();

	DoubleDoubleZuDouble ddzdadd = (x, y) -> x + y;
	DoubleDoubleZuDouble ddzdsub = (x, y) -> x - y;
	DoubleDoubleZuDouble ddzdmul = (x, y) -> x * y;
	DoubleDoubleZuDouble ddzddiv = (x, y) -> {
		if ((Math.abs(0.0 + y) < epsilion) && (Math.abs(0.0 - y) < epsilion)) {
			throw new ArithmeticException();
		} else {
			return x / y;
		}
	};

	@Test
	public void testAddiereddzd() {
		assertTrue("3.3 + 3.2 muss 6.5 ergeben.", (Math.abs(ddzdadd.werteAus(3.3, 3.2) - 6.5) < epsilion));
		assertTrue("3.3 + (-3.2) muss 0.1 ergeben.", (Math.abs(ddzdadd.werteAus(3.3, -3.2) - 0.1) < epsilion));
		assertTrue("(-3.3) + (-3.2) muss 6.5 ergeben.", (Math.abs(ddzdadd.werteAus(-3.3, -3.2) + 6.5) < epsilion));
	}

	@Test
	public void testSubrahiereDdzdsub() {
		assertTrue("3.3 - 3.2 muss 0.1 ergeben.", (Math.abs(ddzdsub.werteAus(3.3, 3.2) - 0.1) < epsilion));
		assertTrue("3.3 - 0 muss 3.3 ergeben.", (Math.abs(ddzdsub.werteAus(3.3, 0.0) - 3.3) < epsilion));
		assertTrue("0.0 - 0.0 muss 0.0 ergeben.", (Math.abs(ddzdsub.werteAus(0.0, 0.0)) < epsilion));
		assertTrue("-5.0 - 3.0 muss -8 ergeben.", (Math.abs(ddzdsub.werteAus(-5.0, 3.0) + 8) < epsilion));
	}

	@Test
	public void testMultipliziereDdzdmul() {
		assertTrue("3.3 * 3.2 muss 10.56 ergeben.", (Math.abs(ddzdmul.werteAus(3.0, 3.0) - 9) < epsilion));
		assertTrue("3.3 * 0.0 muss 0.0 ergeben.", (Math.abs(ddzdmul.werteAus(3.3, 0.0)) < epsilion));
		assertTrue("0.0 * 3.3 muss 0.0 ergeben.", (Math.abs(ddzdmul.werteAus(0.0, 3.3)) < epsilion));
		assertTrue("2.0 * -5.0 muss 0.0 ergeben.", (Math.abs(ddzdmul.werteAus(2.0, -5.0) + 10) < epsilion));
	}

	@Test
	public void testDividiereDdzddiv() {
		assertTrue("3.3 / 3.2 muss 1.03125 ergeben.", (Math.abs(ddzddiv.werteAus(3.3, 3.2) - 1.03125) < epsilion));
	}

	@Test
	public void testNullsteleddzd() {

		assertTrue("2.0 * 3.0 + 1.0 muss 7.0 ergeben.",
				(Math.abs(ddzdadd.werteAus(1, (ddzdmul.werteAus(2.0, 3.0))) - 7.0) < epsilion));

		assertTrue("3.0 * 3.0 + 4.0 muss 13.0 ergeben.",
				(Math.abs(ddzdadd.werteAus(4.0, (ddzdmul.werteAus(3.0, 3.0))) - 13.0) < epsilion));

		assertTrue("0.0 * 3.0 + 4.0 muss 4.0 ergeben.",
				(Math.abs(ddzdadd.werteAus(4.0, (ddzdmul.werteAus(0.0, 3.0))) - 4.0) < epsilion));

	}

	@Test(expected = ArithmeticException.class)
	public void testDividiereDurch0Ddzddiv() {
		Assert.assertEquals("3.2 / 0.0 muss 1.03125 ergeben.", ddzddiv.werteAus(3.2, 0.0));
	}

	@Test
	public void testAddiere() {
		assertTrue("3.3 + 3.2 muss 6.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.ADDIERE, 3.3, 3.2) - 6.5) < epsilion));
		assertTrue("-3.3 + -3.2 muss 6.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.ADDIERE, -3.3, -3.2) + 6.5) < epsilion));
		assertTrue("3.3 + (-3.2) muss 6.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.ADDIERE, 3.3, -3.2) - 0.1) < epsilion));
	}

	@Test
	public void testSubtrahiere() {
		assertTrue("3.0 - 3.0  muss 1.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.SUBTRAHIERE, 3.0, 3.0)) < epsilion));
		assertTrue("1.0 - 2.0  muss -1.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.SUBTRAHIERE, 1.0, 2.0) + 1.0) < epsilion));
		assertTrue("-5.0 - -2.0  muss -3.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.SUBTRAHIERE, -5.0, -2.0) + 3.0) < epsilion));
		assertTrue("-5.0 - 2.0  muss 2.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.SUBTRAHIERE, -5.0, 2.0) + 7.0) < epsilion));
	}

	@Test
	public void testMultipliziere() {
		assertTrue("3.3 * 3.2 muss 10.56 ergeben.",
				(Math.abs(rechner.berechne(Operation.MULTIPLIZIERE, 3.3, 3.2) - 10.56) < epsilion));
		assertTrue("-2.0 * 3.0 muss 6.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.MULTIPLIZIERE, -2.0, 3.0) + 6.0) < epsilion));
		assertTrue("0.0 * 3.3 muss 0.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.MULTIPLIZIERE, 0.0, 3.3)) < epsilion));
		assertTrue("3.3 * 0.0 muss 0.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.MULTIPLIZIERE, 3.3, 0.0)) < epsilion));
	}

	@Test
	public void testDividiere() {
		assertTrue("3.0 / 3.0  muss 1.0 ergeben.",
				(Math.abs(rechner.berechne(Operation.DIVIDIERE, 3.0, 3.0) - 1.0) < epsilion));
		assertTrue("3.0 / 2.0  muss 1.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.DIVIDIERE, 3.0, 2.0) - 1.5) < epsilion));
		assertTrue("1.0 / 2.0  muss 0.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.DIVIDIERE, 1.0, 2.0) - 0.5) < epsilion));
		assertTrue("-5.0 / 2.0  muss 2.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.DIVIDIERE, -5.0, 2.0) + 2.5) < epsilion));
		assertTrue("5.0 / -2.0  muss 2.5 ergeben.",
				(Math.abs(rechner.berechne(Operation.DIVIDIERE, 5.0, -2.0) + 2.5) < epsilion));
	}

	@Test(expected = ArithmeticException.class)
	public void testDividiereDurch0() {
		Assert.assertEquals("3.0 / 0.0 muss rithmeticException werfen.",
				rechner.berechne(Operation.DIVIDIERE, 3.0, 0.0));
	}

	@Test
	public void testNullstele() {
		assertTrue("2.0 * 3.0 + 1.0 muss 7.0 ergeben.", (Math.abs(rechner.berechne(Operation.ADDIERE, 1,
				(rechner.berechne(Operation.MULTIPLIZIERE, 2.0, 3.0))) - 7) < epsilion));

	}
}