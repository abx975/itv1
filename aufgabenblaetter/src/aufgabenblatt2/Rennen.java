package aufgabenblatt2;

import java.util.ArrayList;
import java.util.List;

public class Rennen {

	private static List<Rennauto> rennautoListe;

	public static void main(String[] args) {
		rennautoListe = new ArrayList<Rennauto>();
		for (int i = 0; i < 10; i++) {
			rennautoListe.add(new Rennauto(1, 10, i));
		}
	    for (int i = 0; i < rennautoListe.size(); i++) {
	        rennautoListe.get(i).start();
	    }
	    Rennabbrecher abbrecher = new Rennabbrecher();
	    abbrecher.start();
//	    try {
//			Thread.sleep(12000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	    rennautoListe.forEach(x -> {
	    	try {
	    		x.join();
			} catch (InterruptedException e) {
				;;
			}
	    	
	    	});

	    abbrecher.interrupt();
	    System.out.println("Rennen ist zu Ende.\nErgebnisse:");
	    rennautoListe.sort(null);
	    rennautoListe.forEach(x -> x.toPrintStream());

	}

	public static List<Rennauto> getRennautoListe() {
		return rennautoListe;
	}

}
