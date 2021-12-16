package ecommerce.converter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DimensionCalculator {

    public static int getColumns (String platform, File filepath_origin) throws IOException, CsvValidationException {

        int columns = 0;

        switch (platform) {
            case "Alltricks":

            case "Amazon":

                break;

            case "Carrefour":
                break;
            case "Conrad":

                columns = DimensionCalculator.getCSVColumns(filepath_origin);

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

                columns = DimensionCalculator.getCSVColumns(filepath_origin);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }

        return columns;
    }


    public static int getRows (String platform, File filepath_origin) throws IOException, CsvValidationException {

        int rows = 0;

        switch (platform) {
            case "Alltricks":

            case "Amazon":

                break;

            case "Carrefour":
                break;
            case "Conrad":

                rows = DimensionCalculator.getCSVRows(filepath_origin);

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

                rows = DimensionCalculator.getCSVRows(filepath_origin);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }

        return rows;
    }



    private static int getCSVRows(File filepath_origin) {
        int rows = 0;

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {

                if(nextline != null){
                    //System.out.println("CSV-Reader: " +Arrays.toString(nextline));
                    rows++;
                    //System.out.println(rows + " Reihen gezählt");
                }
            }

            reader.close();

        }
        catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen der Zeilen!");
        }
        System.out.println("-------------------");
        System.out.println("Rohdatei enthält " + rows + " Reihen (rows).");
        System.out.println("-------------------");
        return rows;
    }

    private static int getCSVColumns(File filepath_origin) {

        int columns = 1;

        try {
            CSVReader reader2 = new CSVReader(new FileReader(filepath_origin));
            String[] nextline_columns;


            while ((nextline_columns = reader2.readNext()) != null) {


                //if(nextline_rows != null){

                //https://www.java67.com/2017/09/how-to-convert-comma-separated-string-to-ArrayList-in-java-example.html
                String[] elements_rows = Arrays.toString(nextline_columns).split(";");
                //System.out.println("String Einzelelement:" + elements[1]);
                //System.out.println("Anzahl Reihen: " + elements_rows.length);
                columns = elements_rows.length;




                //}
                // System.out.println("Das wurde in Position " + current_line + " gespeichert: " + Daten[current_line][0]);



            }

            reader2.close();
            System.out.println("Einlesen der Daten durchgeführt!");

        }
        catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen in getCSVRows!");
        }



//        try {
//            CSVReader reader2 = new CSVReader(new FileReader(filepath_origin));
//            String[] nextline;

//            //https://stackoverflow.com/questions/18055889/extract-data-from-csv-file-and-put-to-2d-array-refactoring/18056210
//            List<String[]> lines = reader.readAll();
//            lines.toArray(new String[lines.size()][]);
//            //lines.size();
//            System.out.println("!!!!!!!!!!!!!!!ANZAHL: "+lines.size());


        // ******* //
//            String[] lines = reader.readNext();
//            System.out.println("!!!!!LINES: " + lines.toString());

        //String[] lines2 = reader.readNext();
        //          List<String[]> lines = reader2.readAll();
        //lines.toArray(new String[lines.size()][]);
        //System.out.println("!!!!!LINES: " + lines.get(0).toString());


        //List<String> elephantList = Arrays.asList(str.split(","));
        //List<String> elephantList = Arrays.asList(str.split(","));

        //String listString = String.join(", ", (CharSequence) lines);
        //System.out.println("!!!! LIST STRING: " + listString);


        //        String listString = String.join(", ", (CharSequence) lines);
        //        System.out.println(listString);


        // ******* //

//            System.out.println("Anzahl Comluns: " + reader2.readNext().length);
        //final long count = Arrays.stream(reader.readNext()).count();
        //final long linesRead = reader.getLinesRead();
//            System.out.println(reader2.readNext());

//            String[] columnitems = reader.readNext();
//            System.out.println("Anzahl Comluns: " + columnitems.length);


        //       }
        //       catch (Exception e)
        //       {
        //           System.out.println("FEHLER: Fehler beim Einlesen in getCSVColumns!");
        //       }


        //rows = 49;

        System.out.println("-------------------");
        System.out.println("Rohdatei enthält " + columns + " Spalten (columns).");
        System.out.println("-------------------");

        return columns;
    }



}
