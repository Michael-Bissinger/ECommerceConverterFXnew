package ecommerce.converter;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import ecommerce.converter.generaltools.LogCoordinator;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataRecorder {

    public static String [] [] loadData(String platform, File filepath_origin, int row_number, int columns_number) throws IOException, CsvValidationException {

        String [] [] rohdaten = new String [row_number] [columns_number];


        switch (platform) {
            case "Alltricks":

            case "Amazon":

                getOtherData(platform, filepath_origin);
                break;

            case "Carrefour":
                break;
            case "Conrad":
                break;
            case "Crowdfox":

            case "eBay":
                break;
            case "Manomano":

            case "MediaMarkt":

            case "Mercateo":

            case "Metro":

            case "Mivo":

            case "Otto":

            case "Real":
                // Get data from csv file
                rohdaten = getDataCSV(platform, filepath_origin, row_number, columns_number);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }

        System.out.println("+++++++++++");
        System.out.println("Rohdaten importiert");
        System.out.println("+++++++++++");

        return rohdaten;
    }




    static void getOtherData(String platform, File filepath_origin) throws IOException, CsvValidationException {


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


    static String [] [] getDataCSV(String platform, File filepath_origin, int row_number, int columns_number) throws IOException, CsvValidationException {

        // *********** USE CSV-Reader Software **************
        //https://www.baeldung.com/opencsv
        //https://www.youtube.com/watch?v=ZyjT2qYE4d4

        // Ergebnis
        String [] [] CSV_Daten = new String [row_number] [columns_number];

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));

        String[] nextline;


        LogCoordinator.writeLog("ROHDATEN: Einlesen Beginn!");
        Integer current_line = 0;
        while ((nextline = reader.readNext()) != null) {
            System.out.println("Aktuell bei Reihe: " + current_line);
            LogCoordinator.writeLog(("ROHDATEN: Einlesen von Reihe: " + current_line));

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


// ******************** USE ALTERNATIVE ****************
//https://www.youtube.com/watch?v=-Aud0cDh-J8

//String line = "";



//Scanner scan = new Scanner("C:\\Users\\Michael\\Desktop\\Musik\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a - Kopie.csv");
//        Scanner scan = new Scanner("C:\\Users\\michael.bissinger\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv");
//
//
//        String linex;
//
//        while(scan.hasNextLine()) {
//            linex = scan.nextLine();
//            System.out.println(linex);
//        }
//
//        scan.close();
//

//        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michael\\Desktop\\Musik\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a - Kopie.csv"))) {
//
//            //try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michael\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"))) {
//
//            System.out.println("File gefunden!");
//            if ((line = br.readLine()) == null) {
//                System.out.println("Ist null");
//
//
//
//            }
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//
//
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File nicht da");
//        }
//
//
//
//