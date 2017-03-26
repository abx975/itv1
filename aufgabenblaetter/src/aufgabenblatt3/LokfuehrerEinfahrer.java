package aufgabenblatt3;

/**
 * Klasse repraesentiert den LokfuehrerEinfahrer
 * 
 * @author Eduard Veit, Nils Eggebrecht
 */
public class LokfuehrerEinfahrer extends Thread implements Lokfuehrer{

	Rangierbahnhof bahnhof;
	int gleis;

	public LokfuehrerEinfahrer(Rangierbahnhof bahnhof, int gleis) {
		this.bahnhof = bahnhof;
		this.gleis = gleis;
	}

	@Override
	public void run() {
		bahnhof.einfahren(new Zug(), gleis);
		System.err.println("Neuer Zug auf Gleis " + gleis + " eingefahren.");
	}

	@Override
	public void fahren(int gleis) {
		bahnhof.einfahren(new Zug(), gleis);
	}
}