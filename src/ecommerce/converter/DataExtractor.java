package ecommerce.converter;

import com.opencsv.*;
import ecommerce.converter.generaltools.LogCoordinator;


import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Arrays;

public class DataExtractor {

    public static String [] [] extractData(File filepath, int rows, int columns) {

        String [] [] rohdaten = new String [rows] [columns];

        // Dateiformat erkennen
        String dateiformat = FilenameUtils.getExtension(String.valueOf(filepath));

        switch (dateiformat) {
            case "csv" -> rohdaten = getDataCSV(filepath, rows, columns);
            default -> System.out.println("FEHLER: Dateiformat kann nicht verarbeitet werden");
        }

        System.out.println("+++++++++++");
        System.out.println("Rohdaten importiert");
        System.out.println("+++++++++++");

        return rohdaten;
    }

    private static String [] [] getDataCSV(File filepath_origin, int rows, int columns) {

        String [] [] CSV_Daten = new String [rows] [columns]; // Finales Ergebnis

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));

        String[] nextline;

        LogCoordinator.writeLog("ROHDATEN: Einlesen Beginn!");
        Integer current_line = 0;
        while ((nextline = reader.readNext()) != null) {
            System.out.println("Aktuell bei Reihe: " + current_line);
            //LogCoordinator.writeLog(("ROHDATEN: Einlesen von Reihe: " + current_line));

            if(nextline != null){
                System.out.println("CSV-Reader: " + Arrays.toString(nextline));

                String[] elements = Arrays.toString(nextline).split(";");
                System.out.println("Anzahl Reihen: " + elements.length);

                for(int row_data = 0; row_data < elements.length; row_data++)
                {
                    //System.out.println("Speichere Array-Itemnummer :" + elements[row_data]);
                    if (row_data == 0) {
                        CSV_Daten[current_line] [row_data] = elements[row_data].substring(1); // Delete "[" at first one
                    } else if ((row_data+1 == elements.length)) {
                        System.out.println("Ende entdeckt!");
                        CSV_Daten[current_line] [row_data] = elements[row_data].substring(0, elements[row_data].length() - 1); // Delete "]" at last one
                    }
                    else {
                        CSV_Daten[current_line] [row_data] = elements[row_data];
                    }
                    System.out.println("Speichere Array-Itemnummer: " + current_line + "/" + row_data + " gespeichert: " + CSV_Daten[current_line][row_data]);
                }
           }
            current_line++;
            System.out.println("+++++++++++++++++++++++");

        }
        reader.close();
        System.out.println("Einlesen der Daten durchgeführt!");

        }
       catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen in getDataCSV!");
            LogCoordinator.writeLog("ROHDATEN: FEHLER beim Einlesen!");
        }

        LogCoordinator.writeLog("ROHDATEN: Einlesen abgeschlossen!");
        return CSV_Daten;

    }
}


