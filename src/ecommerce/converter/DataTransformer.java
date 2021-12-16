package ecommerce.converter;

import ecommerce.converter.platformtransformer.TransformerConrad;
import ecommerce.converter.platformtransformer.TransformerReal;

public class DataTransformer {

    public static String[][] transformData(String platform, String operation, String[][] daten, int rows, int columns) {

        String [] [] daten_final = new String[rows-1][7];

        switch (platform) {
            case "Alltricks":

                break;
            case "Conrad":
                daten_final = TransformerConrad.extractConradData(operation, daten, rows, columns);

                break;

            case "Real":

                daten_final = TransformerReal.extractRealData(operation, daten, rows, columns);

                break;

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }


        return daten_final;
    }



}
