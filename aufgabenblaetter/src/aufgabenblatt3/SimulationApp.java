package aufgabenblatt3;

import java.util.Observable;
import java.util.Observer;

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

public class SimulationApp extends Application implements Observer {

	private GridPane gridPane;
	private Simulation simulation;

	/**
	 * SimulationApp-Methode liefert eine Simulation und das Gridpane.
	 */
	public SimulationApp() {
		gridPane = new GridPane();
		simulation = new Simulation(3);
		simulation.addObserver(this);
		new Thread(simulation).start();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Start-Methode Window wird erstellt.
	 */
	@Override
	public void start(Stage window) throws Exception {
		StackPane root = new StackPane();
		root.getChildren().add(gridPane);
		Scene scene = new Scene(root);
		window.setTitle("Simulation: Bahnhof");
		window.centerOnScreen();
		window.setScene(scene);
		window.show();
	}

	private Zug[] gleise;

	/**
	 * Update-Methode aktualiesiert die Zeichnung
	 */
	@Override
	public void update(Observable observable, Object object) {
		gleise = ((Simulation) observable).getBahnhof().getGleise();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gridPane.getChildren().clear();
				gridPane = zeichneBf(gleise, gridPane);
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

	/**
	 * Fügt einer Pane eine Zeichnung hinzu, welche den Bahnhof represäntiert.
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
	 * Fügt einer Pane eine Zeichnung hinzu, welche den Zug repräsentiert.
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
		zug.getChildren().add(path);
	}

	/**
	 * zeichnet Bahnhof.
	 * 
	 * @return gridPane
	 */
	private GridPane zeichneBahnhof() {
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		Pane[] gleisePane = new Pane[simulation.getBahnhof().anzahlGleise()];
		for (int i = 0; i < simulation.getBahnhof().anzahlGleise(); i++) {
			Pane gleisPane = gleisePane[i];
			zeichneGleis(gleisPane);
			gridPane.add(gleisPane, 0, i);
		}
		for (int i = 0; i < simulation.getBahnhof().anzahlGleise(); i++) {
			if (simulation.getBahnhof().getGleise()[i] instanceof Zug) {
				Pane zugPane = new Pane();
				zeichneZug(zugPane);
				gridPane.add(zugPane, 0, i);
			}
		}
		return gridPane;
	}

	/**
	 * zeichnet Zug
	 * 
	 * @param zugPane
	 */
	private void zeichneZug(Pane zugPane) {
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
		path.setStrokeWidth(3);
		zugPane.getChildren().add(path);
	}

	/**
	 * zeichnet Gleis.
	 * 
	 * @param gleisPane
	 */
	private void zeichneGleis(Pane gleisPane) {
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
		gleisPane.getChildren().add(path);
	}
}