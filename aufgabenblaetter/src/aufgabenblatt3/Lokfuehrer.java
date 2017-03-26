package aufgabenblatt3;

/**
 * interface Lokfuehrer
 * 
 * @author Nils Eggebrecht, Eduard Veit
 *
 */
public interface Lokfuehrer {
	/**
	 * Methode zum Ein- und Ausfahren
	 * @param gleis
	 */
	public void fahren(int gleis);

	public void start();
}