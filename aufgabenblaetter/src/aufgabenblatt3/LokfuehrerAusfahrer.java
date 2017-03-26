/**
 * 
 */
package aufgabenblatt3;

/**
 * Klasse repraesentiert den LokfuehrerAusfahrer
 * 
 * @author Eduard Veit, Nils Eggebrecht
 */
public class LokfuehrerAusfahrer extends Thread implements Lokfuehrer {

	Rangierbahnhof bahnhof;
	int gleis;

	public LokfuehrerAusfahrer(Rangierbahnhof bahnhof, int gleis) {
		this.bahnhof = bahnhof;
		this.gleis = gleis;
	}

	@Override
	public void run() {
		fahren(gleis);
		System.err.println("Zug von Gleis " + gleis + " ausgefahren.");
	}

	@Override
	public void fahren(int gleis) {
		bahnhof.ausfahren(gleis);
	}

}
