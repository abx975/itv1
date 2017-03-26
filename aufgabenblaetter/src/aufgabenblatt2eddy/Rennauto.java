package aufgabenblatt2eddy;

import java.io.PrintStream;

/**
 * 
 */

/**
 * @author Eddy
 *
 */
public class Rennauto extends Thread implements Comparable<Rennauto>{

	private double vMeterProSekunde;
	private double streckeMeter;
	private int wagennummer;
	private double renndauerSekunden = 0;
	
	/**
	 * 
	 */
	public Rennauto(int wagennummer) {
		this(1,10,wagennummer);
	}
	
	public Rennauto(double geschwindigkeit, double streckenlaenge, int wagennummer) {
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
		for (double i = 1; i < streckeMeter+1; i = i + vMeterProSekunde) {
			try {
				Thread.sleep( 800 + (int)(Math.random()*400) );
				System.out.printf("Wagen "+wagennummer+": %.1f / %.1f \n", i, streckeMeter);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
		renndauerSekunden = (((double)(System.currentTimeMillis() - start)) / 1000);
	}

	public double getRenndauerSekunden() {
		return renndauerSekunden;
	}
	
	public int getWagennummer() {
		return wagennummer;
	}
	
	@Override
	public int compareTo(Rennauto other) {
		if (getRenndauerSekunden() > other.getRenndauerSekunden()) {
			return 1;
		} 
		else if (getRenndauerSekunden() < other.getRenndauerSekunden()) {
			return -1;
		} 
		else {
			return 0;
		}
	}
	

	public PrintStream toPrintStream() {
		
		return System.out.printf("Wagen "+wagennummer+": %.2f Sekunden \n",renndauerSekunden);
	}

}
