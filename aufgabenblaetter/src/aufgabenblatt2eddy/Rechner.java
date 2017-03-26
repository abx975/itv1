/**
 * 
 */
package aufgabenblatt2eddy;

import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * @author Eddy
 *
 */
public class Rechner {

	Map<Operation,BinaryOperator<Double>> operationen;
	BinaryOperator<Double> plusOperator;
	BinaryOperator<Double> minusOperator;
	BinaryOperator<Double> multiOperator;
	BinaryOperator<Double> diviOperator;
	
	/**
	 * 
	 */
	public Rechner() {
		plusOperator = (z1, z2) -> (z1 + z2);
		minusOperator = (z1, z2) -> (z1 - z2);
		multiOperator = (z1, z2) -> (z1 * z2);
		diviOperator = (z1, z2) -> (z1 / z2);
		operationen.put(Operation.ADDITION, plusOperator);
		operationen.put(Operation.SUBTRAKTION, minusOperator);
		operationen.put(Operation.MULTIPLIKATION, multiOperator);
		operationen.put(Operation.DIVISTION, diviOperator);
	}
	
	public double berechne(Operation op, double zahl1, double zahl2) {
		return operationen.get(op).apply(zahl1, zahl2);
	}
	
	

}
