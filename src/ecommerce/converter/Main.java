package ecommerce.converter;

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

    public static String FILEPATH = null; // Store filepath

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Scene scene = new Scene(new Label("Foo"));
        //Scene scene = new Scene(new GridPane(), 500, 500);



        // Controls


        final TextField directory_newfile = new TextField();


        Label lbl_platform = new Label("Plattform:");
        lbl_platform.setTextAlignment(TextAlignment.LEFT);
        Label lbl_newfile = new Label("Fertige Datei:");
        lbl_newfile.setTextAlignment(TextAlignment.LEFT);
        Label lbl_operation = new Label("Operation:");
        lbl_operation.setTextAlignment(TextAlignment.LEFT);


        // Drag and Drop

        Label label_rawfile_drop = new Label("Rohdatei hierher ziehen!");
        TextField dropped = new TextField("");
        dropped.setEditable(false);

        VBox dragTarget = new VBox();
        //dragTarget.setSpacing(10);
        //dragTarget.setPadding(new Insets(15,20, 10,10));
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
                    System.out.println("Filepath: " + FILEPATH);

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
                        "Amazon",
                        "Carrefour",
                        "Conrad",
                        "eBay",
                        "Manomano",
                        "Real"
                );
        final ComboBox platforms_box = new ComboBox(platformoptions);

        // "Gebühren" or "Full"-Choice
        ObservableList<String> operationoptions =
                FXCollections.observableArrayList(
                        "Nur Gebühren"
                );
        final ComboBox operation_box = new ComboBox(operationoptions);
        operation_box.getSelectionModel().selectFirst();



        // Create and setup GridPane

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Button btn = new Button();
        btn.setText("Starte Konvertierung!");

        // Add to GridPane

        //Drag and Drop
        grid.add(dragTarget, 0, 0, 1, 1);
        //grid.add(directory_rawfile, 1, 0, 1, 1);

        grid.add(lbl_platform, 0, 1, 1, 1);
        grid.add(platforms_box, 1, 1, 1, 1);

        grid.add(lbl_operation, 0, 2, 1, 1);
        grid.add(operation_box, 1, 2, 1, 1);

        grid.add(lbl_newfile, 0, 3, 1, 1);
        grid.add(directory_newfile, 1, 3, 1, 1);

        grid.add(btn, 1, 4, 1, 1);

        // ****** Scene setup ********
        Scene scene = new Scene(grid, 500, 500);

        primaryStage.setTitle("E-Commerce converter");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start program
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("---------------START------------------");

                if (platforms_box.getSelectionModel().getSelectedItem() != null & FILEPATH != null) {

                    if (FILEPATH != null) {


                    //String filepath = "abc"; // TODO: Get real filepath by Drag and Drop
                    ConvertMain.start(platforms_box.getSelectionModel().getSelectedItem().toString(), operation_box.getSelectionModel().getSelectedItem().toString(), FILEPATH);

                    } else {

                        System.out.println("Rohdatei muss ausgewählt werden!");

                    }
                } else {


                    if (FILEPATH == null) {


                        System.out.println("Rohdatei muss ausgewählt werden!");

                    }

                    if (platforms_box.getSelectionModel().getSelectedItem() == null) {


                        System.out.println("Plattform muss ausgewählt werden!");

                    }


                }



                //System.out.println(platforms_box.getSelectionModel().getSelectedItem());
                //System.out.println("Konvertierung startet!");

                System.out.println("----------------END-------------------");

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
