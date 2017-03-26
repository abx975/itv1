/**
 * Praktikum PM2, WS 15/16
 * Nico Grimm (nico.grimm@haw-hamburg.de)
 * Marco Colbow (marco.colbow@haw-hamburg.de)
 * Aufgabe 4
 */

package blatt4;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Klasse zum verwalten einer Liste mit Punkten fuer ein entsprechendes Polygon
 * 
 * @author grimmilenko
 *
 */
public class Polygon extends Observable {
	/**
	 * Innere Klasse zur Bestimmung der x- und y- Koordinate eines Punktes des
	 * Polygons
	 * 
	 * @author grimmilenko
	 *
	 */
	private class Punkt {
		private double xKoord;
		private double yKoord;

		/**
		 * Konstruktor
		 * 
		 * @param xKoord
		 *            x-Koordinate eines Punktes des Polygons
		 * @param yKoord
		 *            y-Koordinate eines Punktes des Polygons
		 */
		public Punkt(double xKoord, double yKoord) {
			this.xKoord = xKoord;
			this.yKoord = yKoord;
		}
	}

	/**
	 * Konstruktor
	 * 
	 * @param o
	 *            Einen Observer
	 */
	public Polygon(Observer o) {
		punkte = new ArrayList<Punkt>();
		addObserver(o);
	}

	/**
	 * Liste der Punkte
	 */
	private List<Punkt> punkte;

	private String info;

	/**
	 * Getter
	 * 
	 * @return Gibt die Liste der Punkte zurueck
	 */
	public List<Punkt> getListePunkte() {
		return punkte;
	}

	/**
	 * Setter
	 *
	 * @param x
	 *            x-Koordinate die der Punkt bekommen soll
	 * @param y
	 *            y-Koordinate die der Punkt bekommen soll
	 */
	public void setPunkt(double x, double y) {
		punkte.add(new Punkt(x, y));
		setChanged();
		notifyObservers();
	}

	/**
	 * Getter
	 * 
	 * @param index
	 *            Index der punkte-Liste
	 * @return Gibt die x-Koordinate an dem index zurueck
	 */
	public double getXAtIndex(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < punkte.size()) {
			return punkte.get(index).xKoord;
		} else {
			throw new IndexOutOfBoundsException("Der gesuchte Index ist nicht in der Liste");
		}
	}

	/**
	 * Getter
	 * 
	 * @param index
	 *            Index der punkte-Liste
	 * @return Gibt die y-Koordinate an dem index zurueck
	 */
	public double getYAtIndex(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < punkte.size()) {
			return punkte.get(index).yKoord;
		} else {
			throw new IndexOutOfBoundsException("Der gesuchte Index ist nicht in der Liste");
		}
	}

	@Override
	public String toString() {
		String punkteAnz = Integer.toString(punkte.size());
		if (punkte.size() > 1 || punkte.size() == 0) {
			punkteAnz += " Punkten";
		} else {
			punkteAnz += " Punkt";
		}
		info = "Polygon mit " + punkteAnz;
		return info;
	}

	/**
	 * Getter
	 * 
	 * @return Gibt die Info des Polygons zurueck
	 */
	public String getInfo() {
		return info;
	}

}