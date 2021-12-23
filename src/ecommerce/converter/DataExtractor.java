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




    static void getOtherData(File filepath_origin) throws IOException {


        //BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\IdeaProjects\\IOTest\\testdatei.csv"));
        System.out.println("Diesen Pfad nehemn: " + filepath_origin.getAbsolutePath());
        //BufferedReader csvReader_bf = new BufferedReader(new FileReader(filepath_origin.getAbsolutePath()));
        //geht       //BufferedReader csvReader_bf = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\IdeaProjects\\IOTest\\testdatei.csv"));
        //geht         //BufferedReader csvReader_bf = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\Downloads\\report_booking_gmu_7d986ae27a0e5a822e5fae1e398e36264ca68a4d22e60367b7a6e25457de8b9b.csv"));

        // ++++++++++++ GEHT ++++++++++++
        BufferedReader csvReader_bf = new BufferedReader(new FileReader(filepath_origin));


        String row;
        while ((row = csvReader_bf.readLine()) != null) {

            System.out.println("Buffered reader: " + csvReader_bf.readLine());
            //String[] data = row.split(",");
            // do something with the data
        }
        csvReader_bf.close();



    }


    private static String [] [] getDataCSV(File filepath_origin, int rows, int columns) {

        // *********** USE CSV-Reader Software **************
        //https://www.baeldung.com/opencsv
        //https://www.youtube.com/watch?v=ZyjT2qYE4d4

        // Ergebnis
        String [] [] CSV_Daten = new String [rows] [columns];

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

                //https://www.java67.com/2017/09/how-to-convert-comma-separated-string-to-ArrayList-in-java-example.html
                String[] elements = Arrays.toString(nextline).split(";");
                System.out.println("Anzahl Reihen: " + elements.length);

                for(int row_data = 0; row_data < elements.length; row_data++)
                {

                    //System.out.println("Speichere Array-Itemnummer :" + elements[row_data]);
                    if (row_data == 0) {
                        //System.out.println("Erste Zeile");
                        CSV_Daten[current_line] [row_data] = elements[row_data].substring(1); // Delete "[" at first one
                    } else if ((row_data+1 == elements.length)) {
                        System.out.println("Ende entdeckt!");
                        CSV_Daten[current_line] [row_data] = elements[row_data].substring(0, elements[row_data].length() - 1); // Delete "]" at last one
                    }
                    else {
                        //Daten[current_line] [row_data] = Arrays.toString(nextline);
                        CSV_Daten[current_line] [row_data] = elements[row_data];
                    }
                    System.out.println("Speichere Array-Itemnummer: " + current_line + "/" + row_data + " gespeichert: " + CSV_Daten[current_line][row_data]);

                }


           }
            //System.out.println("Das wurde in Position " + current_line + " gespeichert: " + Daten[current_line][0]);
            current_line++;
            System.out.println("+++++++++++++++++++++++");

        }

            reader.close();
            System.out.println("Einlesen der Daten durchgeführt!");

        }
       catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen in getDataCSV!");
            LogCoordinator.writeLog("ROHDATEN: FEHLER bei Einlesen!");
        }

        LogCoordinator.writeLog("ROHDATEN: Einlesen abgeschlossen!");
        return CSV_Daten;

    }

    }


