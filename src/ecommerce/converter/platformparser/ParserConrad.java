package ecommerce.converter.platformparser;

import ecommerce.converter.parsertools.ParserDate;
import ecommerce.converter.parsertools.ParserFix;

import java.text.ParseException;

public class ParserConrad {

    private static String DATUM_FORMAT = "dd.MM.yy - HH:mm:ss"; // Datumsformat von "Conrad" (01.08.19 - 15:20:53)

    public static String[][] parseConradData(String[][] daten) throws ParseException {

        daten = ParserDate.reformatDate(daten, DATUM_FORMAT);

        daten = ParserFix.writeFixation(daten);

        return daten;

    }
}
