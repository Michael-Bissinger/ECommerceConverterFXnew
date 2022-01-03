package ecommerce.converter.transformtools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformerDate {

    private static String DATEV_DATUM = "ddMM";

    public static String[][] reformatDate(String[][] daten, String datum_format) {


        //https://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string

        for (int row_pointer = 0; row_pointer < daten.length; row_pointer++) {

            String date_new;
            System.out.print("Datum (original), Position " + row_pointer + " ist: " + daten[row_pointer][5]);

            //https://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string
            Date date_aktuell = null;
            try {
                date_aktuell = new SimpleDateFormat(datum_format).parse(daten[row_pointer][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            date_new = new SimpleDateFormat(DATEV_DATUM).format(date_aktuell);

            // Datum kommt in finalen Daten an 5. Position, also dort neuen Wert einfügen
            daten[row_pointer][5] = date_new;

            System.out.println("I Datum (neu), Position " + row_pointer + " ist: " + daten[row_pointer][5]);

        }


        System.out.println("reformatDate");

        return daten;
    }
}

