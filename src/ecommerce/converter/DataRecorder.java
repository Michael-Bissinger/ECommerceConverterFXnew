package ecommerce.converter;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataRecorder {

    public static void loadData (String platform, File filepath_origin) throws IOException, CsvValidationException {


        //DataRecorder.copyFile(filepath_origin);

        switch (platform) {
            case "Alltricks":

            case "Amazon":

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

                // Read data from csv file
                getDataCSV(platform, filepath_origin);
                getOtherData(platform, filepath_origin);

                break;

            case "Rakuten":

            case "Saturn":

            case "Völkner":

            default:
                System.out.println("Plattform ist nicht verfügbar");
                break;
        }



    }




    static void getOtherData(String platform, File filepath_origin) throws IOException, CsvValidationException {


        //BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\IdeaProjects\\IOTest\\testdatei.csv"));
        System.out.println("Diesen Pfad nehemn: " + filepath_origin.getAbsolutePath());
        //BufferedReader csvReader_bf = new BufferedReader(new FileReader(filepath_origin.getAbsolutePath()));
        //geht       //BufferedReader csvReader_bf = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\IdeaProjects\\IOTest\\testdatei.csv"));
        //geht         //BufferedReader csvReader_bf = new BufferedReader(new FileReader("C:\\Users\\michael.bissinger\\Downloads\\report_booking_gmu_7d986ae27a0e5a822e5fae1e398e36264ca68a4d22e60367b7a6e25457de8b9b.csv"));

        // ++++++++++++ GEHT ++++++++++++
        BufferedReader csvReader_bf = new BufferedReader(new FileReader(filepath_origin));


        String row;
        while ((row = csvReader_bf.readLine()) != null) {

            System.out.println("Buffered reader: " + csvReader_bf.readLine());
            //String[] data = row.split(",");
            // do something with the data
        }
        csvReader_bf.close();



    }


    static void getDataCSV(String platform, File filepath_origin) throws IOException, CsvValidationException {

        // *********** USE CSV-Reader Software **************
        //https://www.baeldung.com/opencsv
        //https://www.youtube.com/watch?v=ZyjT2qYE4d4

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));

        String[] nextline;

        // count columns and rows
        int row_number = getCSVRows(filepath_origin);
        int columns_number = getCSVColumns(filepath_origin);

        // Ergebnis
        String [] [] Daten = new String [columns_number] [row_number];

        Integer current_line = 0;
        while ((nextline = reader.readNext()) != null) {
            System.out.println("Aktuell bei Reihe: " + current_line);

            if(nextline != null){
                System.out.println("CSV-Reader: " + Arrays.toString(nextline));

                //https://www.java67.com/2017/09/how-to-convert-comma-separated-string-to-ArrayList-in-java-example.html
                String[] elements = Arrays.toString(nextline).split(";");
                System.out.println("Anzahl Reihen: " + elements.length);

                for(int row_data = 0; row_data < elements.length; row_data++)
                {

                    //System.out.println("Speichere Array-Itemnummer :" + elements[row_data]);
                    if (row_data == 0) {
                        //System.out.println("Erste Zeile");
                        Daten[current_line] [row_data] = elements[row_data].substring(1); // Delete "[" at first one
                    } else if ((row_data+1 == elements.length)) {
                        System.out.println("Ende entdeckt!");
                        Daten[current_line] [row_data] = elements[row_data].substring(0, elements[row_data].length() - 1); // Delete "]" at last one
                    }
                    else {
                        //Daten[current_line] [row_data] = Arrays.toString(nextline);
                        Daten[current_line] [row_data] = elements[row_data];
                    }
                    System.out.println("Speichere Array-Itemnummer: " + current_line + "/" + row_data + " gespeichert: " + Daten[current_line][row_data]);


                }

                //Daten[current_line] [0] = Arrays.toString(nextline);

           }
            //System.out.println("Das wurde in Position " + current_line + " gespeichert: " + Daten[current_line][0]);
            current_line++;
            System.out.println("+++++++++++++++++++++++");


        }

            reader.close();
            System.out.println("Einlesen der Daten durchgeführt!");

        }
       catch (Exception e)
        {
            System.out.println("FEHLER: Fehler beim Einlesen in getDataCSV!");
        }


        //https://zetcode.com/java/opencsv/

//        //var fileName = "src/main/resources/numbers.csv";
//        var fileName = filepath_origin;
//        Path myPath = Paths.get(String.valueOf(fileName));
//
//        CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
//
//        try (var br = Files.newBufferedReader(myPath,  StandardCharsets.UTF_8);
//             var reader = new CSVReaderBuilder(br).withCSVParser(parser)
//                     .build()) {
//
//            //List<String[]> rows = reader.readAll();
//            List<String[]> rows = reader.readNext();
//
//
//
//            for (String[] row : rows) {
//
//                for (String e : row) {
//                    System.out.println("Reihe:" + e);
//                    System.out.format("%s ", e);
//                }
//
//                System.out.println();
//           }
//        } catch (CsvException e) {
//            e.printStackTrace();
//        }


    }

    static int getCSVColumns(File filepath_origin) {
    int columns = 0;

        try {
            CSVReader reader = new CSVReader(new FileReader(filepath_origin));
            String[] nextline;

        while ((nextline = reader.readNext()) != null) {

            if(nextline != null){
                //System.out.println("CSV-Reader: " +Arrays.toString(nextline));
                columns++;
                //System.out.println(rows + " Reihen gezählt");
            }
        }

        reader.close();

    }
       catch (Exception e)
    {
        System.out.println("FEHLER: Fehler beim Einlesen der Zeoöem!");
    }
        System.out.println("-------------------");
        System.out.println("Rohdatei enthält " + columns + " Zeilen.");
        System.out.println("-------------------");
    return columns;
    }

    static int getCSVRows(File filepath_origin) {
        int columns = 1;
        int rows = 1;


        String[] nextline;




        //    List<String[]> lines = reader.readAll();


        try {
            CSVReader reader2 = new CSVReader(new FileReader(filepath_origin));
            String[] nextline_rows;


        while ((nextline_rows = reader2.readNext()) != null) {


            //if(nextline_rows != null){

                //https://www.java67.com/2017/09/how-to-convert-comma-separated-string-to-ArrayList-in-java-example.html
                String[] elements_rows = Arrays.toString(nextline_rows).split(";");
                //System.out.println("String Einzelelement:" + elements[1]);
                //System.out.println("Anzahl Reihen: " + elements_rows.length);
                rows = elements_rows.length;




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
        System.out.println("Rohdatei enthält " + rows + " Reihen.");
        System.out.println("-------------------");

        return rows;
    }


    }


// ******************** USE ALTERNATIVE ****************
//https://www.youtube.com/watch?v=-Aud0cDh-J8

//String line = "";



//Scanner scan = new Scanner("C:\\Users\\Michael\\Desktop\\Musik\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a - Kopie.csv");
//        Scanner scan = new Scanner("C:\\Users\\michael.bissinger\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv");
//
//
//        String linex;
//
//        while(scan.hasNextLine()) {
//            linex = scan.nextLine();
//            System.out.println(linex);
//        }
//
//        scan.close();
//

//        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michael\\Desktop\\Musik\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a - Kopie.csv"))) {
//
//            //try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michael\\Downloads\\report_booking_gmu_a2760808c5c831e24673062dffc0e2516dd1a3f5600bdea140a51202eb02769a.csv"))) {
//
//            System.out.println("File gefunden!");
//            if ((line = br.readLine()) == null) {
//                System.out.println("Ist null");
//
//
//
//            }
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//
//
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File nicht da");
//        }
//
//
//
//