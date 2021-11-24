package ecommerce.converter;

import ecommerce.converter.platformextractor.ExtractorReal;

import java.io.File;

public class DataExtractor {

    public static String[][] extractData(String platform, String operation, String[][] daten, int rows, int columns) {

        String [] [] daten_final = new String[rows-1][7];

        switch (platform) {
            case "Alltricks":

            case "Amazon":

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

                daten_final = ExtractorReal.extractRealData(operation, daten, rows, columns);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }


        return daten_final;
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


}
