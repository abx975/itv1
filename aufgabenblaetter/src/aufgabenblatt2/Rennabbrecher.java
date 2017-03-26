package aufgabenblatt2;

public class Rennabbrecher extends Thread {
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		boolean abbruch = false;
		try {
			while (!abbruch) {
				Thread.sleep(1000);
				if (Math.random() * 10 < 1) {
					Rennen.getRennautoListe().forEach(x -> x.interrupt());
					System.err.println("Rennabbruch!");
					abbruch = true;
				}
			}
		} catch (InterruptedException e) {
			interrupt();
		}
	}
}