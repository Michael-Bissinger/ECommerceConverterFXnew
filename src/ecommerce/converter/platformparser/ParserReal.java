package ecommerce.converter.platformparser;

import ecommerce.converter.parsertools.ParserDate;


public class ParserReal {

    private static String DATUM_FORMAT = "yyyy-MM-dd HH:mm:ss"; // Datumsformat von "Real"

    public static String[][] parseRealData(String[][] daten) {


        daten = ParserDate.reformatDate(daten, DATUM_FORMAT);

        //daten = ParserFix.writeFixation(daten);





        return daten;

    }
}
