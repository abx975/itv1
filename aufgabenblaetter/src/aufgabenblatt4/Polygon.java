package aufgabenblatt4;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import aufgabenblatt4.Punkt;

/**
 * Klasse repräsentiert die Polygone
 * 
 * @author Nils Egge, Eduard Veit
 *
 */
public class Polygon extends Observable {
	private List<Punkt> polygon;
	private String info;

	public Polygon() {
		this.polygon = new LinkedList<Punkt>();
	}

	public Polygon(Observer obser) {
		polygon = new LinkedList<Punkt>();
		addObserver(obser);
	}

	/**
	 * Fügt neuen Punkt hinzu.
	 *
	 * @param x
	 *            x-Koordinate die der Punkt bekommen soll
	 * @param y
	 *            y-Koordinate die der Punkt bekommen soll
	 */

	public void addPunkt(double x, double y) {
		polygon.add(new Punkt(x, y));
		setChanged();
		notifyObservers();
	}

	/**
	 * Getter
	 * 
	 * @return liefert die Liste der Punkte zurueck
	 */
	public List<Punkt> getPolygon() {
		return polygon;
	}

	/**
	 * Getter
	 * 
	 * @param index
	 *            Index der punkte-Liste
	 * @return liefert die x-Koordinate an dem index zurueck
	 */
	public double getXAtIndex(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < polygon.size()) {
			return polygon.get(index).getX();
		} else {
			throw new IndexOutOfBoundsException("Der gesuchte Index ist nicht in der Liste");
		}
	}

	/**
	 * Getter
	 * 
	 * @param index
	 *            Index der punkte-Liste
	 * @return liefert die y-Koordinate an dem index zurueck
	 */
	public double getYAtIndex(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < polygon.size()) {
			return polygon.get(index).getY();
		} else {
			throw new IndexOutOfBoundsException("Der gesuchte Index ist nicht in der Liste");
		}
	}

	@Override
	public String toString() {
		String AnzahlPunkte = Integer.toString(polygon.size());
		if (polygon.size() > 1 || polygon.size() == 0) {
			AnzahlPunkte += " Punkten";
		} else {
			AnzahlPunkte += " Punkt";
		}
		info = "Polygon mit " + AnzahlPunkte;
		return info;
	}

	/**
	 * Getter
	 * 
	 * @return liefert die Informationen des Polygons zurueck
	 */
	public String getInfo() {
		return info;
	}

}