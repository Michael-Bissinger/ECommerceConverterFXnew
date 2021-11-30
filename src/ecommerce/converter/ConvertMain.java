package ecommerce.converter;

import com.opencsv.exceptions.CsvValidationException;
import ecommerce.converter.generaltools.LogCoordinator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ConvertMain {

    // Neu: Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
//    String[] header = new String[]{"Umsatz", "Soll-Haben", "Kontonummer", "Gegenkonto", "BU-Schlüssel", "Belegdatum", "Belegfeld 1", "Belegfeld 2", "Buchungstext", "Festschreibung"};
    private static String[] DATENMODELL = {
            "Umsatz",
            "Soll-Haben",
            "Kontonummer",
            "Gegenkonto",
            "BU-Schlüssel",
            "Belegfeld 1",
            "Belegfeld 2",
            "Buchungstext",
            "Festschreibung"}; // Datenmodell

    public static void start (String platform, String operation, File filepath, String format_final) throws IOException, CsvValidationException, ParseException {
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");
        System.out.println("Endformat: [" + format_final + "]");

        int rows = DimensionCalculator.getRows(platform, filepath);
        int columns = DimensionCalculator.getColumns(platform, filepath);


        LogCoordinator.writeLog("Hallo1");
        LogCoordinator.writeLog("Hallo2");



        String [] [] daten_roh = DataRecorder.loadData(platform, filepath, rows, columns);

        String [] [] daten_extrahiert = DataExtractor.extractData(platform, operation, daten_roh, rows, columns);

        String [] [] daten_geparsed = DataParser.parseData(platform, daten_extrahiert);

        DataWriter.writeData(filepath, platform, format_final, daten_geparsed);




        System.out.println("Konvertierung abgeschlossen!");


    }

}
