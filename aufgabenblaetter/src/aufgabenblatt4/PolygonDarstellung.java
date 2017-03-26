/**
 * Praktikum PM2, WS 15/16
 * 
 * Aufgabe 4
 */

package aufgabenblatt4;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Klasse zur Darstellung der Polygone
 * 
 * @author Nils Egge, Eduard Veit
 *
 */
public class PolygonDarstellung extends Canvas implements Observer {
	private GraphicsContext graphicsContext;
	private BorderPane root;
	private InvalidationListener listener;
	private PolygonModell modell;

	/**
	 * Kostruktor
	 * 
	 * @param pane
	 *            Die BorderPane auf der die PolygonDarstellung gezeichnet
	 *            werden soll
	 */
	public PolygonDarstellung(BorderPane pane) {

		graphicsContext = getGraphicsContext2D();
		graphicsContext.setFill(Color.WHITE);

		pane.getChildren().add(this);
		root = pane;
		listener = new InvalidationListener() {

			@Override
			public void invalidated(javafx.beans.Observable observable) {

				clear();
				zeichnePolygon(modell.getAktuellesPolygon());
				zeichneFertigePolygone();

				setWindow();
				if (modell != null) {
					zeichnePolygon(modell.getAktuellesPolygon());
				}

			}
		};

		root.widthProperty().addListener(listener);
		root.heightProperty().addListener(listener);

		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				Polygon aktuellesPolygon = modell.getAktuellesPolygon();
				aktuellesPolygon.addPunkt(mouseEvent.getX(), mouseEvent.getY());
				mouseEvent.consume();
			}
		});
	}

	/**
	 * Methode leert das Zeichenfenster
	 */
	public synchronized void clear() {
		graphicsContext.clearRect(0, 0, getWidth(), getHeight());
		graphicsContext.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Methode setzt die größe des Zeichenfensters
	 */
	public void setWindow() {
		setWidth(root.getLayoutBounds().getWidth());
		setHeight(root.getLayoutBounds().getHeight());
	}

	/**
	 * Methode zeichnet das Polygon
	 * 
	 * @param polygon
	 *            Das zu zeichnende Polygon
	 */
	public synchronized void zeichnePolygon(Polygon polygon) {
		polygonZeichnung(polygon, Color.ORANGE);
	}

	/**
	 * Methode erstellt die Zeichnung eines Polygons
	 * 
	 * @param polygon
	 *            Das zu zeichnende Polygon
	 */
	private synchronized void polygonZeichnung(Polygon polygon, Color color) {
		if (polygon != null) {
			graphicsContext.setStroke(color);

			for (int i = 0; i < polygon.getPolygon().size(); i++) {
				double x1 = polygon.getXAtIndex(i);
				double y1 = polygon.getYAtIndex(i);
				double x2;
				double y2;
				graphicsContext.strokeOval(x1 - 2, y1 - 2, 4, 4);
				if (i + 1 >= polygon.getPolygon().size()) {
					x2 = polygon.getXAtIndex(i);
					y2 = polygon.getYAtIndex(i);
				} else {
					x2 = polygon.getXAtIndex(i + 1);
					y2 = polygon.getYAtIndex(i + 1);
				}
				graphicsContext.strokeLine(x1, y1, x2, y2);
			}
		}
	}

	/**
	 * Methode setzt die Referenz des Modells
	 * 
	 * @param modell
	 *            Das Referenz-Modell
	 */
	public synchronized void setModell(PolygonModell modell) {
		this.modell = modell;
	}

	/**
	 * Getter
	 * 
	 * @return liefert das Modell zurueck
	 */
	public PolygonModell getModell() {
		return modell;
	}

	/**
	 * Methode zeichnet das aktuelle Polygon, wenn es fertig ist
	 */
	public void zeichneFertigePolygone() {

		for (int i = 0; i < modell.getListePolygone().size(); i++) {
			polygonZeichnung(modell.getListePolygone().get(i), Color.BLACK);
		}
	}
	

	@Override
	public void update(Observable o, Object arg) {
		clear();
		zeichnePolygon(modell.getAktuellesPolygon());
		zeichneFertigePolygone();
	}
}