/**
 * Praktikum PM2, WS 15/16
 * Aufgabe 4
 */

package aufgabenblatt4;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Klasse repraesentiert das PolygonModell
 * 
 * @author Nils Egge, Eduard Veit
 *
 */
public class PolygonModell extends Observable implements Observer {
	/**
	 * Liste der Polygone
	 */
	private List<Polygon> polygone = new ArrayList<Polygon>();
	/**
	 * aktuelles Polygon
	 */
	private Polygon aktuellesPolygon;

	/**
	 * Methode abschliesst die Bearbeitung eines Polygons ab und erstellt neues
	 * Polygon
	 */
	public void neuesPolygonerstellen() {
		aktuellesPolygon.toString();
		polygone.add(aktuellesPolygon);
		aktuellesPolygon = new Polygon();
		aktuellesPolygon.addObserver(this);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode verwirft aktuelles Polygon
	 */
	public void verwerfePolygon() {
		aktuellesPolygon = new Polygon();
		aktuellesPolygon.addObserver(this);
		setChanged();
		notifyObservers();
	}

	/**
	 * Kostruktor
	 */
	public PolygonModell(Observer o) {
		aktuellesPolygon = new Polygon(this);
		addObserver(o);
	}

	/**
	 * Getter
	 * 
	 * @return liefert das aktuelle zu bearbeitende Polygon zurueck
	 */
	public Polygon getAktuellesPolygon() {
		return aktuellesPolygon;
	}

	/**
	 * Getter
	 * 
	 * @return liefert die Liste der Polygone zurueck
	 */
	public List<Polygon> getListePolygone() {
		return polygone;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Anzahl der Polygone in der Liste: " + polygone.size() + ", aktuelles " + aktuellesPolygon.toString();
	}
	
}