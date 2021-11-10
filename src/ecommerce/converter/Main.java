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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Scene scene = new Scene(new Label("Foo"));
        //Scene scene = new Scene(new GridPane(), 500, 500);


        // Drag and Drop
        //TODO: https://stackoverflow.com/questions/32534113/javafx-drag-and-drop-a-file-into-a-program


        // Controls
        final TextField directory_rawfile = new TextField();

        final TextField directory_newfile = new TextField();

        Label lbl_rawfile = new Label("Rohdaten:");
        lbl_rawfile.setTextAlignment(TextAlignment.LEFT);
        Label lbl_platform = new Label("Plattform:");
        lbl_platform.setTextAlignment(TextAlignment.LEFT);
        Label lbl_newfile = new Label("Fertige Datei:");
        lbl_newfile.setTextAlignment(TextAlignment.LEFT);
        Label lbl_operation = new Label("Operation:");
        lbl_operation.setTextAlignment(TextAlignment.LEFT);


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

        grid.add(lbl_rawfile, 0, 0, 1, 1);
        grid.add(directory_rawfile, 1, 0, 1, 1);

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

        // Start programm
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (platforms_box.getSelectionModel().getSelectedItem() != null) {

                    String filepath = "abc"; // TODO: Get real filepath by Drag and Drop
                    ConvertMain.start(platforms_box.getSelectionModel().getSelectedItem().toString(), operation_box.getSelectionModel().getSelectedItem().toString(), filepath);


                } else {

                    System.out.println("Plattform muss ausgewählt werden!");
                }

                //System.out.println(platforms_box.getSelectionModel().getSelectedItem());
                //System.out.println("Konvertierung startet!");

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
