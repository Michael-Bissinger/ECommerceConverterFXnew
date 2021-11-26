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



}
