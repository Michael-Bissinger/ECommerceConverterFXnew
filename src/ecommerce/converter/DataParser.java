package ecommerce.converter;

import ecommerce.converter.platformparser.ParserConrad;
import ecommerce.converter.platformparser.ParserReal;

import java.text.ParseException;

public class DataParser {

    public static String[][] parseData(String platform, String [][] daten) throws ParseException {


        System.out.println("DataParser aufgerufen");

        switch (platform) {
            case "Alltricks":

                break;
            case "Conrad":
                daten = ParserConrad.parseConradData(daten);
                break;

            case "Real":

                daten = ParserReal.parseRealData(daten);

                break;

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }


        return daten;



    }


    }
