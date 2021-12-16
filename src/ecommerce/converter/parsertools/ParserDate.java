package ecommerce.converter.parsertools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserDate {


    public static String[][] reformatDate(String[][] daten, String datum_format) throws ParseException {


        //https://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string

        for (int row_pointer = 0; row_pointer < daten.length; row_pointer++) {
            String[] row_aktuell = new String[daten.length];

            String date_new = new String();
            System.out.print("Datum (original), Position " + row_pointer + " ist: " + daten[row_pointer][5]);

            //https://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string
            Date date_aktuell = new SimpleDateFormat(datum_format).parse(daten[row_pointer][5]);
            //date_new = daten[row2_pointer][5]
            date_new = new SimpleDateFormat("ddMM").format(date_aktuell);


            daten[row_pointer][5] = date_new; // Datum ist an 5. Position, also hier einfügen

            System.out.println("I Datum (neu), Position " + row_pointer + " ist: " + daten[row_pointer][5]);

        }


        System.out.println("reformatDate");

        return daten;
    }
}

