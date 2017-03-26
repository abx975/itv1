package aufgabenblatt2;

import java.util.HashMap;

import java.util.function.BinaryOperator;
import aufgabenblatt2.Operation;

/**
 * Diese Klasse implementiert die vier Rechenarten.
 * 
 * @author Nils Eggebrecht, Eduard Veit
 * @version 1.0
 */
public class Rechner {

	private HashMap<Operation, BinaryOperator<Double>> operMap;
	double epsilion = 0.0000000001;

	/**
	 * Der Konstructor beinhaltet die 4 Rechenoperationen
	 */
	public Rechner() {
		operMap = new HashMap<Operation, BinaryOperator<Double>>();
		operMap.put(Operation.ADDIERE, (x, y) -> x + y);
		operMap.put(Operation.SUBTRAHIERE, (x, y) -> x - y);
		operMap.put(Operation.MULTIPLIZIERE, (x, y) -> x * y);
		operMap.put(Operation.DIVIDIERE, (x, y) -> {
			if ((Math.abs(0.0 + y) < epsilion) && (Math.abs(0.0 - y) < epsilion)) {
				throw new ArithmeticException();
			} else {
				return x / y;
			}
		});

	}

	/**
	 * 
	 * Die Methode fuehrt die Rechnung aus.
	 * 
	 * @param oper
	 * @param zahl1
	 * @param zahl2
	 * @return Ergebnis der Rechenoperation.
	 */
	public double berechne(Operation oper, double zahl1, double zahl2) {
		return operMap.get(oper).apply(zahl1, zahl2);
	}
}
