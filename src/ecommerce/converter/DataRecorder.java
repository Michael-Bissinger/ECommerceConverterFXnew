package ecommerce.converter;

import com.opencsv.CSVReader;

public class DataRecorder {

    public static void loadData (String platform, String filepath_original) {


        DataRecorder.copyFile(filepath_original);

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

    static void copyFile(String filepath_original) {

        // TODO: Original file is copyed to workspace-folder
        System.out.println("Copy original file to workspace!");


        // TODO: Rename new file to orginal
    }


    static void getDataCSV(String platform) {

        //https://www.baeldung.com/opencsv



        switch (platform) {
            case "Manomano":

                CSVReader csvReader



                break;



            default:
                System.out.println("Plattform kann nicht gelesen werden");
                break;
        }



    }

}
