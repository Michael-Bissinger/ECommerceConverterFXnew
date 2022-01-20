package ecommerce.converter;

import ecommerce.converter.generaltools.LogCoordinator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.File;

public class Main extends Application{

    public static String FILEPATH = null;
    // Speichert Dateinamen von Drag and Drop

    @Override
    public void start(Stage primaryStage)  {

        // Controls
        Label lbl_platform = new Label("Plattform:");
        lbl_platform.setTextAlignment(TextAlignment.LEFT);
        Label lbl_finalformat = new Label("Endformat:");
        lbl_finalformat.setTextAlignment(TextAlignment.LEFT);
        Label lbl_operation = new Label("Operation:");
        lbl_operation.setTextAlignment(TextAlignment.LEFT);

        // Drag and Drop
        Label label_rawfile_drop = new Label("Rohdatei hierher ziehen (nur eine!)");
        TextField dropped = new TextField("");
        dropped.setEditable(false);

        VBox dragTarget = new VBox();
        dragTarget.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");

        dragTarget.getChildren().addAll(label_rawfile_drop,dropped);
        dragTarget.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragTarget
                        && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {

                    File f = new File(db.getFiles().toString());
                    f.getName();
                    dropped.setText("[" + f.getName());

                    FILEPATH = db.getFiles().toString();

                    // ***** Letzten und ersten Buchstaben entfernen
                    // Ersten Buchstaben entfernen
                    FILEPATH = FILEPATH.substring(1);
                    // Letzten Buchstaben entfernen
                    FILEPATH = FILEPATH.substring(0, FILEPATH.length() - 1);
                    // Pfad für Verarbetung in Java anpassen
                    FILEPATH = FILEPATH.replace("\\", "/");

                    System.out.println("Dateipfad: " + FILEPATH);

                    // Farbe wird grün, nachdem Datei dragged/dropped wurde
                    dragTarget.setStyle("-fx-padding: 10;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 5;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: green;");

                    success = true;
                }
                // Meldung, dass der String für Dateilocation übertragen wurde
                event.setDropCompleted(success);

                event.consume();
            }
        });

        // Platform-Wahl
        ObservableList<String> platformoptions =
                FXCollections.observableArrayList(
                        "Real",
                        "Conrad",
                        "Alltricks"
                );
        final ComboBox platforms_box = new ComboBox(platformoptions);
        platforms_box.getSelectionModel().selectFirst(); // TODO: After testing remove

        // Aktuell lassen sich nur Gebühren verarbeiten, später mehr
        ObservableList<String> operationoptions =
                FXCollections.observableArrayList(
                        "Nur Gebühren"
                );
        final ComboBox operation_box = new ComboBox(operationoptions);
        operation_box.getSelectionModel().selectFirst();

        // Endformat: Aktuell geht nur "Maske (ASCII)"
        ObservableList<String> formatoptions =
                FXCollections.observableArrayList(
                        "Maske (ASCII)"
                );
        final ComboBox format_box = new ComboBox(formatoptions);
        format_box.getSelectionModel().selectFirst();

        // Setup GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Button btn_start = new Button();
        btn_start.setText("Starte Konvertierung!");

        Button btn_log = new Button();
        btn_log.setText("Log");

        // Hinzufügen zu GridPane
        // Legende:
        // i: Links nach rechts (column)
        // i2: Oben, unten (row)
        // i3: wie viele Felder nutzt es rechts (column) aus
        // i4: wie viele Felder nutzt es nach unten (row) aus

        //Drag and Drop
        grid.add(dragTarget, 0, 0, 2, 2);

        grid.add(lbl_platform, 0, 2);
        grid.add(platforms_box, 1, 2);

        grid.add(lbl_operation, 0, 3);
        grid.add(operation_box, 1, 3);

        grid.add(lbl_finalformat, 0, 4);
        grid.add(format_box, 1, 4);

        grid.add(btn_start, 1, 5);
        grid.add(btn_log, 0, 7);

        // ****** Scene setup ********
        Scene scene = new Scene(grid, 280, 310);

        primaryStage.setTitle("E-Commerce converter");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Programm starten
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (LogCoordinator.ACTIVITY_LOG == true) {
                    LogCoordinator.deleteLog();
                }
                System.out.println("---------------START------------------");

                if (platforms_box.getSelectionModel().getSelectedItem() != null
                        & FILEPATH != null
                        & format_box.getSelectionModel().getSelectedItem() != null ) {

                    File rawfile = new File(FILEPATH);

                        ConvertMain.start(platforms_box.getSelectionModel().getSelectedItem().toString(),
                                operation_box.getSelectionModel().getSelectedItem().toString(),
                                rawfile,
                                format_box.getSelectionModel().getSelectedItem().toString());
                } else {

                    if (FILEPATH == null) {
                        System.out.println("Rohdatei muss ausgewählt werden!");
                    }

                    if (platforms_box.getSelectionModel().getSelectedItem() == null) {
                        System.out.println("Plattform muss ausgewählt werden!");
                    }

                    if (format_box.getSelectionModel().getSelectedItem() == null) {
                        System.out.println("Endformat muss ausgewählt werden!");
                    }
                }

                System.out.println("----------------ENDE-------------------");
            }
        });

        btn_log.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                LogCoordinator.openLog();
            }
        });
    }
}