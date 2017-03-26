package aufgabenblatt2eddy;

public class Rennabbrecher extends Thread {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!interrupted()) {
			try {
				Thread.sleep(1000);
				if (Math.random() * 10 < 1) {
					Rennen.getRennautoListe().forEach(x -> x.interrupt());
					System.err.println("Rennabbruch!");
				}
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
}
