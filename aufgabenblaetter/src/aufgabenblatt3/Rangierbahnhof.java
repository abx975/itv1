package aufgabenblatt3;

/**
 * Klasse repraesentiert den Rangierbahnhof
 * 
 * @author Eduard Veit, Nils Eggebrecht
 */
public class Rangierbahnhof {

	private Zug[] zugArray;

	public Rangierbahnhof() {
		this(3);
	}

	public Rangierbahnhof(int anzahlGleise) {
		this.zugArray = new Zug[anzahlGleise];
	}

	/**
	 * synchronized Methode zum einfahren eines Zuges von einem Gleis
	 * 
	 * @param gleis
	 */
	public synchronized void einfahren(Zug zug, int gleis) {
		while (zugArray[gleis] != null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		zugArray[gleis] = zug;
		notifyAll();
	}

	/**
	 * synchronized Methode zum ausfahren eines Zuges von einem Gleis
	 * 
	 * @param gleis
	 */
	public synchronized void ausfahren(int gleis) {
		while (zugArray[gleis] == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		zugArray[gleis] = null;
		notifyAll();
	}

	public int anzahlGleise() {
		return zugArray.length;
	}

	public Zug[] getGleise() {
		return zugArray;
	}
}