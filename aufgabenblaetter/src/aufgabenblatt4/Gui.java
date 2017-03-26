
package aufgabenblatt4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Klasse zur Darstellung des Benutzerinterfaces
 * 
 * @author Nils Egge, Eduard Veit
 *
 */
public class Gui extends Application {

	private Stage primaryStage;
	private Pane pane;
	private TableView<Polygon> tabelle;
	private PolygonTabelle polygonTabelle;
	private TextArea area;
	private PolygonSkripting regAusdruck = new PolygonSkripting();

	/**
	 * Breite und Hoehe des Fensters
	 */
	private int breite, hoehe;

	private PolygonDarstellung polygonDarstellung;

	/**
	 * Programmstart
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStagePar) {
		primaryStage = primaryStagePar;
		primaryStage.setTitle("Zeicheneditor");
		pane = paneInit();
		primaryStage.setScene(new Scene(pane, breite, hoehe));
		PolygonModell modell = new PolygonModell(polygonDarstellung);
		polygonDarstellung.setModell(modell);
		polygonTabelle = new PolygonTabelle(this.tabelle, modell);
		polygonTabelle.initTabelle();
		primaryStage.show();
	}

	/**
	 * Methode zum erstellen der GUI
	 * 
	 * @return liefert die GUI als BorderPane zurueck
	 */
	private Pane paneInit() {
		BorderPane pane = new BorderPane();
		pane.setRight(polygonTabelle());
		pane.setCenter(zeichenflaeche());
		pane.setTop(befehlInput());
		return pane;
	}

	/**
	 * Methode zum erstellen der Befehlseingabe
	 * 
	 * @return liefert die Befehlseingabe als BorderPane zurueck
	 */
	private Pane befehlInput() {
		BorderPane pane = new BorderPane();
		area = new TextArea();
		area.setMaxHeight(80);
		area.setPromptText("Hier können Sie einen Befehl eingeben");
		area.setTooltip(new Tooltip("Um Punkte in den Zeicheneditor zu erstellen, gibt es zwei Moeglichkeiten:\n"
				+ "1. Moeglichkeit: Sie tippen mit der Maus in die Zeichenflaeche\n"
				+ "2. Moeglichkeit: Sie geben im Textfenster Befehle ein.\n" + "Befehle haben folgende Struktur:\n"
				+ "bewege -> XKoordinate , YKoordinate\n" + "Bsp.:   bewege -> 60.3, 200"));
		pane.setCenter(area);
		pane.setBottom(buttonPane());
		return pane;
	}

	/**
	 * Methode erstellt die Buttons fuer die Befehlseingabe
	 * 
	 * @return liefert die Buttons in einer HBox zureuck
	 */
	private Pane buttonPane() {
		HBox pane = new HBox();
		pane.setSpacing(5);

		Button befehl = new Button("Befehl ausffuehren!");
		befehl.setOnAction(event -> {
			String text = area.getText();
			try {
				polygonDarstellung.getModell().getAktuellesPolygon().addPunkt(regAusdruck.getX(text),
						regAusdruck.getY(text));
				area.clear();
			} catch (Exception e) {
				area.setText(e.getMessage());
			}
			
		});

		Button zeichnePolygon = new Button("Zeichne Polygon");
		zeichnePolygon.setOnAction(event -> {
			polygonDarstellung.getModell().neuesPolygonerstellen();
			polygonTabelle.refreshTabelle();
			area.clear();
		});
		
		Button verwerfeAktuellesPolygon = new Button("Verwerfe");
		verwerfeAktuellesPolygon.setOnAction(event -> {
			polygonDarstellung.getModell().verwerfePolygon();
			polygonTabelle.refreshTabelle();
			area.clear();
		});

		Button close = new Button("Exit");
		close.setOnAction(event -> {
			primaryStage.close();
		});
		pane.getChildren().addAll(befehl, zeichnePolygon, verwerfeAktuellesPolygon, close);
		return pane;
	}

	/**
	 * Methode zum erstellen der Zeichenflaeche
	 * 
	 * @return liefert die PolygonDarstellung als Borderpane zurueck
	 */
	private Pane zeichenflaeche() {
		BorderPane pane = new BorderPane();
		polygonDarstellung = new PolygonDarstellung(pane);
		return pane;
	}

	/**
	 * Methode erstellt eine Tabelle, die die gezeichneten Polygone anzeigt
	 * 
	 * @return liefert eine Tabelle als BorderPane zurueck
	 */
	private Pane polygonTabelle() {
		BorderPane pane = new BorderPane();
		tabelle = new TableView<Polygon>();
		pane.setCenter(tabelle);
		return pane;
	}
}