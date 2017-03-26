/**
 * PMP2, WS 2015/16
 * Gruppe: Nils Eggebrecht, Eduard Veit
 * Aufgabe: Aufgabenblatt 3
 */

package aufgabenblatt3;

// kontinuierlich neue Lokführer erzeugen
// jeder Lokführer hat nur genau eine Aufgabe (z.B. 1x einfahren)
// Methoden einfahren() und ausfahren() -> mit wait() sichern

import java.util.Observable;
import java.util.Observer;

import aufgabenblatt3.Simulation;
import aufgabenblatt3.Zug;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * Klasse Frame extends Apllicants implements Observer beobachtet Klasse
 * Simulation und reagiert auf Veränderungen
 * 
 * @author Leon
 */
public class Frame extends Application implements Observer {
  /**
   * Main
   * 
   * @param args
   */
  public static void main(String[] args) {
	Application.launch();
  }


  private Zug[] gleise;

  private GridPane thisGridPane = new GridPane();

  public Frame() {
	Simulation obs = new Simulation(2);

	new Thread(obs).start();

  }

  /**
   * Fügt einer Pane eine 2D Zeichnung hinzu, die den Bahnhof represäntieren
   * soll.
   * 
   * @param bahnhof
   */
  private void addBf(Pane bahnhof) {
	Path path = new Path();
	MoveTo moveTo = new MoveTo();
	moveTo.setX(150);
	moveTo.setY(0);
	LineTo lineTo1 = new LineTo();
	lineTo1.setX(0);
	lineTo1.setY(0);
	LineTo lineTo2 = new LineTo();
	lineTo2.setX(0);
	lineTo2.setY(75);
	LineTo lineTo3 = new LineTo();
	lineTo3.setX(150);
	lineTo3.setY(75);
	path.getElements().add(moveTo);
	path.getElements().add(lineTo1);
	path.getElements().add(lineTo2);
	path.getElements().add(lineTo3);
	path.setStrokeType(StrokeType.CENTERED);
	path.setStrokeWidth(3);
	path.setStroke(Color.BLACK);
	bahnhof.getChildren().add(path);
  }

  /**
   * Fügt einer Pane eine 2D Zeichnung hinzu, die den Bahnhof repräsentieren
   * soll.
   * 
   * @param zug
   */
  private void addZug(Pane zug) {
	Path path = new Path();
	MoveTo moveTo = new MoveTo();
	moveTo.setX(140);
	moveTo.setY(10);
	LineTo lineTo1 = new LineTo();
	lineTo1.setX(10);
	lineTo1.setY(10);
	LineTo lineTo2 = new LineTo();
	lineTo2.setX(10);
	lineTo2.setY(65);
	LineTo lineTo3 = new LineTo();
	lineTo3.setX(140);
	lineTo3.setY(65);
	LineTo lineTo4 = new LineTo();
	lineTo4.setX(140);
	lineTo4.setY(10);
	path.getElements().add(moveTo);
	path.getElements().add(lineTo1);
	path.getElements().add(lineTo2);
	path.getElements().add(lineTo3);
	path.getElements().add(lineTo4);
	path.setStrokeType(StrokeType.CENTERED);
	path.setStrokeWidth(3);
	path.setStroke(Color.BLACK);
	zug.getChildren().add(path);
  }

  /**
   * @Override start Methode
   */
  @Override
  public void start(Stage primaryStage) {
	StackPane root = new StackPane();
	root.getChildren().add(thisGridPane);
	primaryStage.setTitle("Rangierbahnhof");
	primaryStage.setScene(new Scene(root, 180, 300));
	primaryStage.show();
  }

  /**
   * @Override update Methode aktualisiert thisGridPane und setzt die neuen Züge
   *           durch Methode setzeZugIf.
   */
  @Override
  public void update(Observable o, Object arg) {
	
	Platform.runLater(new Runnable() {
	  @Override
	  public void run() {
		thisGridPane.getChildren().clear();
		thisGridPane = zeichneBf(gleise, thisGridPane);
	  }
	});
  }

  /**
   * Zeichnet den Bahnhof und fügt der GridPane Zuege hinzu wenn der jeweilige
   * Zug[] index einer Instanz von der Klasse Zug entspricht.
   * 
   * @param gleise
   * @param gridpane
   * @return
   */
  private GridPane zeichneBf(Zug[] gleise, GridPane gridpane) {
	gridpane.setPadding(new Insets(5));
	gridpane.setHgap(5);
	gridpane.setVgap(5);
	Pane bahnhof = new Pane();
	Pane bahnhof1 = new Pane();
	Pane bahnhof2 = new Pane();
	addBf(bahnhof);
	addBf(bahnhof1);
	addBf(bahnhof2);
	gridpane.add(bahnhof, 0, 0);
	gridpane.add(bahnhof1, 0, 1);
	gridpane.add(bahnhof2, 0, 2);

	if (gleise[0] instanceof Zug) {
	  Pane zug = new Pane();
	  addZug(zug);
	  gridpane.add(zug, 0, 0);
	}
	if (gleise[1] instanceof Zug) {
	  Pane zug1 = new Pane();
	  addZug(zug1);
	  gridpane.add(zug1, 0, 1);
	}
	if (gleise[2] instanceof Zug) {
	  Pane zug2 = new Pane();
	  addZug(zug2);
	  gridpane.add(zug2, 0, 2);
	}
	return gridpane;
  }
}