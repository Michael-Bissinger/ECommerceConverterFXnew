package ecommerce.converter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

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

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }



        System.out.println("Data is loaded!");

    }

    static void copyFile(String filepath_origin) {

        // TODO: Original file is copyed to workspace-folder
        System.out.println("Copy original file to workspace!");

        //https://stackoverflow.com/questions/16433915/how-to-copy-file-from-one-location-to-another-location
        //https://www.youtube.com/watch?v=sgGGjisdNPA


        File f_origin = new File(filepath_origin);
        // File f_new = new File("\\out\\ressources\\test.csv");
        //File f_new = new File("\\D:\\Users\\Michael\\Desktop\\Studium\\Studium Wirtschaftsinformatik\\Semester\\Masterarbeit\\Arbeit\\Design\\E-CommerceConverter\\out\\ressources\\test.csv");


        //Files.copy(f_origin, f_new, StandardCopyOption.REPLACE_EXISTING);


        //Files.copy( f_origin.toPath(), f_new.toPath() );



        // TODO: Rename new file to orginal
    }


    static void getDataCSV(String platform, File filepath_origin) throws IOException, CsvValidationException {

        //https://www.baeldung.com/opencsv
        //https://www.youtube.com/watch?v=ZyjT2qYE4d4

        //CSVReader reader = new CSVReader(new FileReader("C:/Users/Michael/Downloads/report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Desktop\\Michael\\Programmieren\\_Projekte\\File Renamer Helbling Media App\\resources\\Excel-sheets\\Test-sheet\\MediaApp_Keyboard Accompaniment.xlsx"));
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Michael\\Desktop\\Michael\\Programmieren\\_Projekte\\File Renamer Helbling Media App\\resources\\Excel-sheets\\Test-sheet\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        //CSVReader reader = new CSVReader(new FileReader("resources\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));
        CSVReader reader = new CSVReader(new FileReader("report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"));



        //CSVReader reader = new CSVReader(new FileReader(filepath_origin.getName()));

//        try {
//            //CSVReader reader = new CSVReader(new FileReader(filepath_origin));
//            CSVReader reader = new CSVReader(new FileReader("Mask_ASCII_test.csv"));
//
//        String[] nextline;
//        while ((nextline = reader.readNext()) != null) {
//            if(nextline != null){
//                System.out.println(Arrays.toString(nextline));
//
//            }
//            reader.close();
//            System.out.println("Einlesen der Daten durchgeführt!");
//
//
//        }
//        }
//       catch (Exception e)
//        {
//            System.out.println("FEHLER: Fehler beim Einlesen!");
//        }



        switch (platform) {
            case "Real":





                break;



            default:
                System.out.println("Plattform kann nicht gelesen werden");
                break;
        }



    }

}
