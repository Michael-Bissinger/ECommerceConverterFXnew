package ecommerce.converter;

import com.opencsv.exceptions.CsvValidationException;
import ecommerce.converter.generaltools.DimensionCalculator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ConvertMain {

    public static void start (String platform, String operation, File filepath, String format_final) throws IOException, CsvValidationException, ParseException {

        // ----------------- Ausgabe von Parametern -----------------
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");
        System.out.println("Endformat: [" + format_final + "]");

        // ----------------- Errechnen von Spalten und Reihen -----------------
        int rows = DimensionCalculator.getRows(filepath);
        int columns = DimensionCalculator.getColumns(filepath);

        // ----------------- Hauptklassen -----------------

        String [] [] daten_roh = DataExtractor.extractData(filepath, rows, columns);

        String [] [] daten_transformiert = DataTransformer.transformData(platform, operation, daten_roh, rows, columns);

        //String [] [] daten_geparsed = DataParser.parseData(platform, daten_transformiert);

        DataWriter.writeData(filepath, platform, format_final, daten_transformiert);

        // ----------------- Ende -----------------

        System.out.println("Konvertierung abgeschlossen!");


    }

}
