package aufgabenblatt3;

import java.util.Observable;

public class Simulation extends Observable implements Runnable {

	private Rangierbahnhof bahnhof;

	/**
	 * Methode liefert den Bahnhof zurück
	 * 
	 * @return bahnhof
	 */
	public Rangierbahnhof getBahnhof() {
		return bahnhof;
	}

	/**
	 * Simulation enthaelt einen Rangierbahnhof
	 * 
	 * @param gleise
	 */
	public Simulation(int gleise) {
		bahnhof = new Rangierbahnhof(gleise);
	}

	public static void main(String[] args) {
		Thread simulThread = new Thread(new Simulation(3));
		simulThread.start();
	}

	/**
	 * erstellt alle 500ms einen Lockführer mit einer zufaelligen Aufgabe.
	 */
	@Override
	public void run() {
		Lokfuehrer fuehrer;
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}

			if (Math.random() < 0.5) {
				fuehrer = new LokfuehrerAusfahrer(bahnhof, (int) (Math.random() * bahnhof.anzahlGleise()));
			} else {
				fuehrer = new LokfuehrerEinfahrer(bahnhof, (int) (Math.random() * bahnhof.anzahlGleise()));
			}
			fuehrer.start();
			setChanged();
			notifyObservers();
		}
	}
}