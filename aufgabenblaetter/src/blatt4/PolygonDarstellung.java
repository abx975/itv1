/**
 * Praktikum PM2, WS 15/16
 * Nico Grimm (nico.grimm@haw-hamburg.de)
 * Marco Colbow (marco.colbow@haw-hamburg.de)
 * Aufgabe 4
 */

package blatt4;

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
 * @author grimmilenko
 *
 */
public class PolygonDarstellung extends Canvas implements Observer {
	private GraphicsContext gc;
	private BorderPane root;
	private InvalidationListener listener;
	private PolygonModell modell;

	/**
	 * Kostruktor
	 * 
	 * @param pane
	 *            Die StackPane auf der die PolygonDarstellung gezeichnet werden
	 *            soll
	 */
	public PolygonDarstellung(BorderPane pane) {
		super(750, 525);

		gc = getGraphicsContext2D();

		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.CHOCOLATE);

		pane.getChildren().add(this);
		root = pane;
		listener = new InvalidationListener() {

			@Override
			public void invalidated(javafx.beans.Observable observable) {
				
				clearScreen();
				zeichnePolygon(modell.getPolygon());
				zeichneFertigePolygone();
				
				setWindow();
				if (modell != null) {
					zeichnePolygon(modell.getPolygon());
				}

			}
		};

		root.widthProperty().addListener(listener);
		root.heightProperty().addListener(listener);

		addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Polygon aktuellesPolygon = modell.getPolygon();
						aktuellesPolygon.setPunkt(event.getX(), event.getY());
						event.consume();
					}
				});
	}

	/**
	 * Methode zum clearen des Zeichenfensters
	 */
	public synchronized void clearScreen() {
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Methode zum anpassen der Groesse des Zeichenfensters
	 */
	public void setWindow() {
		setWidth(root.getLayoutBounds().getWidth());
		setHeight(root.getLayoutBounds().getHeight());
	}

	/**
	 * Methode zum zeichnen eines Polygons
	 * 
	 * @param poly
	 *            Das zu zeichnende Polygon
	 */
	public synchronized void zeichnePolygon(Polygon poly) {
		polygonZeichnung(poly, Color.CHOCOLATE);
	}

	/**
	 * Methode zum erstellen der Zeichnung eines Polygons
	 * 
	 * @param poly
	 *            Das zu zeichnende Polygon
	 */
	private synchronized void polygonZeichnung(Polygon poly, Color color) {
		if (poly != null) {
			gc.setStroke(color);

			for (int i = 0; i < poly.getListePunkte().size(); i++) {
				double x1 = poly.getXAtIndex(i);
				double y1 = poly.getYAtIndex(i);
				double x2;
				double y2;
				gc.strokeOval(x1 - 2.5, y1 - 2.5, 5, 5);
				if (i + 1 >= poly.getListePunkte().size()) {
					x2 = poly.getXAtIndex(i);
					y2 = poly.getYAtIndex(i);
				} else {
					x2 = poly.getXAtIndex(i + 1);
					y2 = poly.getYAtIndex(i + 1);
				}
				gc.strokeLine(x1, y1, x2, y2);
			}
		}
	}

	/**
	 * Methode zum setten des Referenz-Modells
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
	 * @return Gibt das Modell zurueck
	 */
	public PolygonModell getModell() {
		return modell;
	}

	public void zeichneFertigePolygone() {

		for (int i = 0; i < modell.getListePolygone().size(); i++) {
			polygonZeichnung(modell.getListePolygone().get(i), Color.BLACK);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		clearScreen();
		zeichnePolygon(modell.getPolygon());
		zeichneFertigePolygone();
	}
}