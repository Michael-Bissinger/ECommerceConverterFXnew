package ecommerce.converter;

import ecommerce.converter.generaltools.DimensionCalculator;
import java.io.File;

public class ConvertMain {

    public static void start (String platform, String operation, File filepath, String format_final) {

        // ----------------- Ausgabe von Parametern -----------------
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");
        System.out.println("Endformat: [" + format_final + "]");

        // ----------------- Errechnen von Spalten und Reihen -----------------
        int rows = DimensionCalculator.getRows(filepath);
        int columns = DimensionCalculator.getColumns(filepath);

        // ----------------- Hauptklassen -----------------

        // *********** Extract ***********
        String [] [] daten_roh = DataExtractor.extractData(filepath, rows, columns);

        // ********** Transform **********
        String [] [] daten_transformiert = DataTransformer.transformData(platform, operation, daten_roh, rows, columns);

        // ************ Load *************
        DataWriter.writeData(filepath, format_final, daten_transformiert);

        // ----------------- Ende -----------------

        System.out.println("Konvertierung abgeschlossen!");
    }
}
