package ecommerce.converter;

import ecommerce.converter.platformextractor.ExtractorReal;
import ecommerce.converter.platformparser.ParserReal;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class DataParser {

    public static void parseData(String platform, String [][] daten) throws ParseException {


        System.out.println("DataParser aufgerufen");

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

                daten = ParserReal.parseRealData(daten);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }



    }


    }
