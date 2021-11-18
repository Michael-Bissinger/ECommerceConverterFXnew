package ecommerce.converter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;

public class DataRecorder {

    public static void loadData (String platform, File filepath_origin) throws IOException, CsvValidationException {


        //DataRecorder.copyFile(filepath_origin);

        switch (platform) {
            case "Alltricks":

            case "Amazon":

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

                // Read data from csv file
                getDataCSV(platform, filepath_origin);
                getOtherData(platform, filepath_origin);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }



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


    static void getDataCSV(String platform, File filepath_origin) throws IOException, CsvValidationException {



        // *********** USE CSV-Reader Software **************
        //https://www.baeldung.com/opencsv
        //https://www.youtube.com/watch?v=ZyjT2qYE4d4

        //CSVReader reader = new CSVReader(new FileReader("C:/Users/Michael/Downloads/report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Desktop\\Michael\\Programmieren\\_Projekte\\File Renamer Helbling Media App\\resources\\Excel-sheets\\Test-sheet\\MediaApp_Keyboard Accompaniment.xlsx"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Desktop\\Michael\\Programmieren\\_Projekte\\File Renamer Helbling Media App\\resources\\Excel-sheets\\Test-sheet\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("resources\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));



        //CSVReader reader = new CSVReader(new FileReader(filepath_origin.getName()));


        // ++++++ CSV-Reader +++++++


        try {
            //CSVReader reader = new CSVReader(new FileReader(filepath_origin));
            //CSVReader reader = new CSVReader(new FileReader("report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));

        String[] nextline;

        // Ergebnis
        // String [] [] Daten = new String [] [];

        // count columns and rows
        int row_number = getCSVRows(filepath_origin);
        int columns_number = getCSVColumns(filepath_origin);

        // Ergebnis
        String [] [] Daten = new String [columns_number] [row_number];

        while ((nextline = reader.readNext()) != null) {


            if(nextline != null){
                System.out.println("CSV-Reader: " +Arrays.toString(nextline));


           }


        }

            reader.close();
            System.out.println("Einlesen der Daten durchgeführt!");

        }
       catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen!");
        }


        // ******************** USE ALTERNATIVE ****************
        //https://www.youtube.com/watch?v=-Aud0cDh-J8

        String line = "";



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
   }

    static int getCSVRows(File filepath_origin) {
    int rows = 0;

        try {
            //CSVReader reader = new CSVReader(new FileReader(filepath_origin));
            //CSVReader reader = new CSVReader(new FileReader("report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));

            String[] nextline;



        while ((nextline = reader.readNext()) != null) {


            if(nextline != null){
                System.out.println("CSV-Reader: " +Arrays.toString(nextline));
                rows++;
                System.out.println(rows + " Reihen gezählt");

            }


        }

        reader.close();
        System.out.println("Anzahl Reihen: " + rows);

    }
       catch (Exception e)
    {
        System.out.println("FEHLER: Fehler beim Einlesen!");
    }

        System.out.println(" Rohdatei enthält " + rows + " Zeilen.");
    return rows;
    }

    static int getCSVColumns(File filepath_origin) {
        int columns = 1;

        return columns;
    }


    }
