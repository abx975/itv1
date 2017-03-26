package aufgabenblatt2;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eddy + Nils
 *
 */
public class Rennauto2 extends Thread {

	int vMeterProSekunde;
	int streckeMeter;
	int wagennummer;

	public Rennauto2(int geschwindigkeit, int streckenlaenge, int wagennummer) {
		this.vMeterProSekunde = geschwindigkeit;
		this.streckeMeter = streckenlaenge;
		this.wagennummer = wagennummer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < streckeMeter + 1; i = i + vMeterProSekunde) {
			try {
				Thread.sleep(800 + (int) (Math.random() * 400));

				double renndauerSekunden = ((double) ((System.currentTimeMillis() - start)) / 1000);
				System.err.format(
						"Wagen: " + getWagennummer() + "\t Zeit: " + renndauerSekunden + "\t Strecke: " + i + "m\n");
			} catch (InterruptedException e) {
				System.err.println(e.getStackTrace());

				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {

		Rennauto2 rennautoThread1 = new Rennauto2(1, 10, 1);
		Rennauto2 rennautoThread2 = new Rennauto2(1, 10, 2);
		Rennauto2 rennautoThread3 = new Rennauto2(1, 10, 3);

		List<Rennauto2> threads = new ArrayList<Rennauto2>();

		threads.add(rennautoThread1);
		threads.add(rennautoThread2);
		threads.add(rennautoThread3);

		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}
	}

	public int getWagennummer() {
		return wagennummer;

	}
}