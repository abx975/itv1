
package blatt4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Klasse zur Darstellung des Benutzerinterfaces
 * 
 * @author grimmilenko
 *
 */
public class Gui extends Application {

	private Stage primaryStage;
	private Pane pane;
	private TableView<Polygon> tabelle;
	private PolygonTabelle polyTabelle;
	private TextArea area;
	private PolygonSkripting regAusdruck = new PolygonSkripting();

	/**
	 * Breite und Hoehe der Anwendung
	 */
	private int width, height;

	private PolygonDarstellung polygonDarstellung;

	/**
	 * Programmstartpunkt
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Default-Konstruktor mit fester Fenstergroesse
	 */
	public Gui() {
		height = 600;
		width = 1000;
	}

	@Override
	public void start(Stage primaryStagePar) {
		primaryStage = primaryStagePar;
		primaryStage.setTitle("Polygon-Zeicheneditor");
		pane = paneInit();
		primaryStage.setScene(new Scene(pane, width, height));
		PolygonModell modell = new PolygonModell(polygonDarstellung);
		polygonDarstellung.setModell(modell);
		polyTabelle = new PolygonTabelle(this.tabelle, modell);
		polyTabelle.initTabelle();
		primaryStage.show();

		// Beispiel Polygon
		Polygon polygonBsp = modell.getPolygon();
		polygonBsp.setPunkt(25, 25);
		polygonBsp.setPunkt(150.5, 75.5);
		polygonBsp.setPunkt(50, 50);
		modell.bearbeitungZuEnde();
		polyTabelle.refreshTabelle();
	}

	/**
	 * Getter
	 * 
	 * @return Gibt die Pane zurueck
	 */
	public Pane getPane() {
		return pane;
	}

	/**
	 * Methode zum erstellen der GUI
	 * 
	 * @return Gibt die GUI als BorderPane zurueck
	 */
	private Pane paneInit() {
		BorderPane pane = new BorderPane();
		pane.setLeft(polygonTabelle());
		pane.setCenter(zeichenflaeche());
		pane.setBottom(befehlInput());
		return pane;
	}

	/**
	 * Methode zum erstellen der Befehlseingabe
	 * 
	 * @return Gibt die Befehlseingabe als BorderPane zurueck
	 */
	private Pane befehlInput() {
		BorderPane pane = new BorderPane();
		area = new TextArea();
		area.setMaxHeight(50);
		pane.setCenter(area);
		pane.setBottom(befehlButtons());
		return pane;
	}

	/**
	 * Methode zum erstellen der Buttons fuer die Befehlseingabe
	 * 
	 * @return Gibt die Buttons in einer HBox zureuck
	 */
	private Pane befehlButtons() {
		HBox pane = new HBox();
		pane.setSpacing(5);
		Button hilfe = new Button("?");
		hilfe.setTooltip(new Tooltip(
				"Um Punkte zu einem Polygon hinzuzufuegen, koennen Sie entweder mit der Maus in die Zeichenflaeche\n"
						+ "auf die gewuenschte Koordinate klicken oder mit Hilfe des Textfensters Befehle eingeben.\n"
						+ "Befehle haben folgende Struktur:\n"
						+ "bewege -> Koordinate 1, Koordinate 2\n"
						+ "Bsp.:   bewege -> 52.01, 1.456   oder   bewege -> 54,123.45"));
		Button befehl = new Button("Fuehre aus!");
		hilfe.setOnMouseEntered(event -> hilfe.setEffect(new DropShadow()));
		hilfe.setOnMouseExited(event -> hilfe.setEffect(null));
		befehl.setOnAction(event -> {
			String text = area.getText();
			try {
				polygonDarstellung
						.getModell()
						.getPolygon()
						.setPunkt(regAusdruck.getX(text),
								regAusdruck.getY(text));
				area.clear();
			} catch (Exception e) {
				area.setText(e.getMessage()
						+ " --- Bitte einen gueltigen Befehl eingeben!\n"
						+ "-->> Siehe \"?\" fuer gueltige Befehle! <<--");
			}
		});
		befehl.setOnMouseEntered(event -> befehl.setEffect(new DropShadow()));
		befehl.setOnMouseExited(event -> befehl.setEffect(null));
		Button fertig = new Button("Fertig");
		fertig.setOnAction(event -> {
			polygonDarstellung.getModell().bearbeitungZuEnde();
			polyTabelle.refreshTabelle();
			area.clear();
		});
		fertig.setOnMouseEntered(event -> fertig.setEffect(new DropShadow()));
		fertig.setOnMouseExited(event -> fertig.setEffect(null));
		Button close = new Button("Exit");
		close.setOnAction(event -> {
			primaryStage.close();
		});
		close.setOnMouseEntered(event -> close.setEffect(new DropShadow()));
		close.setOnMouseExited(event -> close.setEffect(null));
		pane.getChildren().addAll(befehl, hilfe, fertig, close);
		return pane;
	}

	/**
	 * Methode zum erstellen der PolygonDarstellung
	 * 
	 * @return Gibt die PolygonDarstellung zurueck
	 */
	private Pane zeichenflaeche() {
		BorderPane pane = new BorderPane();
		polygonDarstellung = new PolygonDarstellung(pane);
		return pane;
	}

	/**
	 * Methode zum erstellen einer Tabelle, die die gezeichneten Polygone
	 * anzeigt
	 * 
	 * @return Gibt eine Tabelle als BorderPane zurueck
	 */
	private Pane polygonTabelle() {
		BorderPane pane = new BorderPane();
		tabelle = new TableView<Polygon>();
		pane.setCenter(tabelle);
		return pane;
	}
}