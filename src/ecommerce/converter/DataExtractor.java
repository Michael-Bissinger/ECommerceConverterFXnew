package ecommerce.converter;

import ecommerce.converter.platformextractor.ExtractorConrad;
import ecommerce.converter.platformextractor.ExtractorReal;

import java.io.File;

public class DataExtractor {

    public static String[][] extractData(String platform, String operation, String[][] daten, int rows, int columns) {

        String [] [] daten_final = new String[rows-1][7];

        switch (platform) {
            case "Alltricks":

                break;
            case "Conrad":
                daten_final = ExtractorConrad.extractConradData(operation, daten, rows, columns);

                break;

            case "Real":

                daten_final = ExtractorReal.extractRealData(operation, daten, rows, columns);

                break;

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }


        return daten_final;
    }



}
