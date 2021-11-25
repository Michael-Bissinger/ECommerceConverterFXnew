package ecommerce.converter;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;

public class ConvertMain {


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

    public static void start (String platform, String operation, File filepath, String format_final) throws IOException, CsvValidationException {
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");
        System.out.println("Endformat: [" + format_final + "]");

        int rows = DimensionCalculator.getRows(platform, filepath);
        int columns = DimensionCalculator.getColumns(platform, filepath);





        String [] [] daten_roh = DataRecorder.loadData(platform, filepath, rows, columns);

        String [] [] daten_extrahiert = DataExtractor.extractData(platform, operation, daten_roh, rows, columns);

        DataParser.parseData(platform, daten_extrahiert);

        DataWriter.writeData(filepath, platform, format_final, daten_extrahiert); //TODO: Noch Daten aus DataParser nehmen statt daten_extrahiert




        System.out.println("Konvertierung abgeschlossen!");


    }

}
