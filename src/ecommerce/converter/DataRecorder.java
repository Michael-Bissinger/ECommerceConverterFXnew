package ecommerce.converter;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DataRecorder {

    public static void loadData (String platform, String filepath_origin) {


        DataRecorder.copyFile(filepath_origin);

        switch (platform) {
            case "Alltricks":

            case "Amazon":

                // Convert txt-file to csv OR Get data directly from txt

                // Get data from csv



                break;
            case "Carrefour":
                break;
            case "Conrad":
                break;
            case "Crowdfox":

            case "eBay":
                break;
            case "Manomano":

                getDataCSV(platform);

                // TODO: Get data from .csv-file

                // TODO:

            case "MediaMarkt":

            case "Mercateo":

            case "Metro":

            case "Mivo":

            case "Otto":

            case "Real":

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


        File f_origin = new File(filepath_origin);
        // File f_new = new File("\\out\\ressources\\test.csv");
        //File f_new = new File("\\D:\\Users\\Michael\\Desktop\\Studium\\Studium Wirtschaftsinformatik\\Semester\\Masterarbeit\\Arbeit\\Design\\E-CommerceConverter\\out\\ressources\\test.csv");


        //Files.copy(f_origin, f_new, StandardCopyOption.REPLACE_EXISTING);


        //Files.copy( f_origin.toPath(), f_new.toPath() );



        // TODO: Rename new file to orginal
    }


    static void getDataCSV(String platform) {

        //https://www.baeldung.com/opencsv



        switch (platform) {
            case "Manomano":





                break;



            default:
                System.out.println("Plattform kann nicht gelesen werden");
                break;
        }



    }

}
