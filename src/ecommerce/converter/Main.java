package ecommerce.converter;

import com.opencsv.exceptions.CsvValidationException;
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
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main extends Application{

    public static String FILEPATH = null; // Store filepath from Drag and Drop

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Scene scene = new Scene(new Label("Foo"));
        //Scene scene = new Scene(new GridPane(), 500, 500);



        // Controls


        final TextField directory_newfile = new TextField();


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
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                String filepath_drag;

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    //dropped.setText(db.getFiles().toString());

                    File f = new File(db.getFiles().toString());
                    f.getName();
                    dropped.setText("[" + f.getName());

                    FILEPATH = db.getFiles().toString();
                    String filepathdb = db.getFiles().toString();

                    //Cut first and last letter
                    FILEPATH = FILEPATH.substring(1);                           // Remove first letter
                    FILEPATH = FILEPATH.substring(0, FILEPATH.length() - 1);    // Remove last letter
                    //FILEPATH = FILEPATH.replace("\\", "\\\\");     // Make useable for Java
                    FILEPATH = FILEPATH.replace("\\", "/");     // Make useable for Java

                    System.out.println("Dateipfad: " + FILEPATH);


                    // Set color on green after File is dragged/dropped
                    dragTarget.setStyle("-fx-padding: 10;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 5;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: green;");

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });




        // Platform-Choice
        ObservableList<String> platformoptions =
                FXCollections.observableArrayList(
                        "Conrad",
                        "Real",
                        "Alltricks"
                );
        final ComboBox platforms_box = new ComboBox(platformoptions);
        platforms_box.getSelectionModel().selectFirst(); // TODO: After testing remove

        // "Gebühren" or "Full"-Choice
        ObservableList<String> operationoptions =
                FXCollections.observableArrayList(
                        "Nur Gebühren"
                );
        final ComboBox operation_box = new ComboBox(operationoptions);
        operation_box.getSelectionModel().selectFirst();

        // Endformat: "DATEV-Format" or "ASCII-Mask" or"XML"
        ObservableList<String> formatoptions =
                FXCollections.observableArrayList(
                        "Maske (ASCII)",
                            "DATEV-Format (ASCII)",
                            "XML"
                );
        final ComboBox format_box = new ComboBox(formatoptions);
        format_box.getSelectionModel().selectFirst();



        // Create and setup GridPane

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Button btn_start = new Button();
        btn_start.setText("Starte Konvertierung!");

        Button btn_log = new Button();
        btn_log.setText("Log");

        // Add to GridPane
        // Legende:
        // i: Links nach rechts (column)
        // i2: Oben, unten (row)
        // i3: wie viele Felder nutzt es rechts (column) aus
        // i4: wie viele Felder nutzt es nach unten (row) aus



        //Drag and Drop
        grid.add(dragTarget, 0, 0, 2, 2);
        //grid.add(directory_rawfile, 1, 0, 1, 1);

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

        // HWS icon %TODO: Das HWS-Logo soll oben links stehen
        //Image image = new Image("/icons/HWS_logo.png");
        //Image image = new Image("HWS_logo.png");
        //primaryStage.getIcons().add(image);

        //primaryStage.getIcons().add(new Image("/HWS_logo.png"));

        primaryStage.setTitle("E-Commerce converter");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start program
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                LogCoordinator.deleteLog();

                System.out.println("---------------START------------------");

                if (platforms_box.getSelectionModel().getSelectedItem() != null
                        & FILEPATH != null
                        & format_box.getSelectionModel().getSelectedItem() != null ) {

                    File rawfile = new File(FILEPATH);

                    try {
                        ConvertMain.start(platforms_box.getSelectionModel().getSelectedItem().toString(),
                                operation_box.getSelectionModel().getSelectedItem().toString(),
                                rawfile,
                                format_box.getSelectionModel().getSelectedItem().toString());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvValidationException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

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



                //System.out.println(platforms_box.getSelectionModel().getSelectedItem());
                //System.out.println("Konvertierung startet!");

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


    static void restartSoftware() {

        // TODO: Delete files in Work-Directories
        System.out.println("Work-Directories are cleaned!");

        // Reset Platform-choice
        //platforms_box.

    }
}
