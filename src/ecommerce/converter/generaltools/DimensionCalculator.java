package ecommerce.converter.generaltools;

import com.opencsv.CSVReader;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class DimensionCalculator {

    public static int getRows (File filepath) {

        int rows = 0;

        // Dateiformat erkennen
        String dateiformat = FilenameUtils.getExtension(String.valueOf(filepath));

        switch (dateiformat) {
            case "csv" -> rows = DimensionCalculator.getCSVRows(filepath);
            default -> System.out.println("FEHLER: Dateiformat kann nicht verarbeitet werden");
        }

        return rows;
    }

    public static int getColumns (File filepath) {

        int columns = 0;

        // Dateiformat erkennen
        String dateiformat = FilenameUtils.getExtension(String.valueOf(filepath));

        switch (dateiformat) {
            case "csv" -> columns = DimensionCalculator.getCSVColumns(filepath);
            default -> System.out.println("FEHLER: Dateiformat kann nicht verarbeitet werden");
        }

        return columns;
    }

    private static int getCSVRows(File filepath) {

        int rows = 0;

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {

                if(nextline != null){
                    rows++; // Reihen werden hochgezählt
                }
            }

            reader.close();

        }
        catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen der Zeilen!");
        }
        System.out.println("-------------------");
        System.out.println("Rohdatei enthält " + rows + " Reihen (rows).");
        System.out.println("-------------------");

        return rows; // Die finale Anzahl an Reihen wird zurückgegeben
    }

    private static int getCSVColumns(File filepath) {

        int columns = 0;

        try {
            CSVReader reader2 = new CSVReader(new FileReader(filepath));
            String[] nextline_columns;

            while ((nextline_columns = reader2.readNext()) != null) {
                // Anzahl Spalten wird hochgezählt, bis keine weitere mehr kommt

                String[] elements_rows = Arrays.toString(nextline_columns).split(";");
                columns = elements_rows.length;
            }

            reader2.close();
            System.out.println("Einlesen der Daten durchgeführt!");

        }
        catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen in getCSVRows!");
        }

        System.out.println("-------------------");
        System.out.println("Rohdatei enthält " + columns + " Spalten (columns).");
        System.out.println("-------------------");

        return columns;
    }
}