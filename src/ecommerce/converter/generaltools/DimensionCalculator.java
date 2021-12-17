package ecommerce.converter.generaltools;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DimensionCalculator {

    public static int getColumns (String platform, File filepath_origin) {

        int columns = 0;

        switch (platform) {
            case "Alltricks" -> {columns = DimensionCalculator.getCSVColumns(filepath_origin);}
            case "Conrad" -> {columns = DimensionCalculator.getCSVColumns(filepath_origin);}
            case "Real" -> {columns = DimensionCalculator.getCSVColumns(filepath_origin);}
            default -> {System.out.println("Plattform ist nicht verfügbar");}
        }

        return columns;
    }


    public static int getRows (String platform, File filepath_origin) {

        int rows = 0;

        switch (platform) {
            case "Alltricks" -> {rows = DimensionCalculator.getCSVRows(filepath_origin);}
            case "Conrad" -> {rows = DimensionCalculator.getCSVRows(filepath_origin);}
            case "Real" -> {rows = DimensionCalculator.getCSVRows(filepath_origin);}
            default -> {System.out.println("Plattform ist nicht verfügbar");}
        }

        return rows;
    }



    private static int getCSVRows(File filepath_origin) {

        int rows = 0;

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));
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

    private static int getCSVColumns(File filepath_origin) {

        int columns = 1;

        try {
            CSVReader reader2 = new CSVReader(new FileReader(filepath_origin));
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
