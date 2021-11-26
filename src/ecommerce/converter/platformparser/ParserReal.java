package ecommerce.converter.platformparser;

import ecommerce.converter.parsertools.ParserDate;

import java.text.ParseException;

public class ParserReal {

    private static String DATUM_FORMAT = "yyyy-MM-dd HH:mm:ss"; // Datumsformat von "Real"

    public static String[][] parseRealData(String[][] daten) throws ParseException {


        daten = ParserDate.reformatDate(daten, DATUM_FORMAT);

        return daten;

    }
}