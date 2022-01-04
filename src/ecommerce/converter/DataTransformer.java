package ecommerce.converter;

import ecommerce.converter.platformtransformer.TransformerAlltricks;
import ecommerce.converter.platformtransformer.TransformerConrad;
import ecommerce.converter.platformtransformer.TransformerReal;

public class DataTransformer {

    public static String[][] transformData(String platform, String operation, String[][] daten, int rows, int columns) {

        String [] [] daten_final = new String[rows-1][10];

        switch (platform) {
            case "Alltricks" -> daten_final = TransformerAlltricks.transformData(operation, daten, rows, columns);
            case "Conrad" -> daten_final = TransformerConrad.transformData(operation, daten, rows, columns);
            case "Real" -> daten_final = TransformerReal.transformData(operation, daten, rows, columns);

            default -> System.out.println("FEHLER: Dateiformat kann nicht verarbeitet werden");
        }

        return daten_final;
    }

}