package ecommerce.converter.platformextractor;

import ecommerce.converter.extractortools.ItemPositionCoordinator;

import java.util.Arrays;

public class ExtractorReal {

    private static Integer KONTO_DEBITOR = 1469000; // Debitoren-Konto von "Real"
    private static Integer KONTO_KREDITOR = 7000130; // Kreditoren-Konto von "Real"

    private static String[] RELEVANTE_ITEMS = {
            "booking_date",
            "booking_text",
            "order_number",
            "amount",
            "fee_gross",
            "buyer.email",
            "billing.first_name",
            "billing.last_name"}; // Relevante Items zur Berechnung

    private static String[] GEBUEHRENARTEN = {
            "Bezahlung Zusatzleistungen",
            "Freigabe Verkaufserlös"}; // Mögliche Gebührenarten bei "Real"





    public static void extractRealData(String operation, String[][] daten_original, int rows, int columns) {

        //String [] [] daten_fertig = new String [columns_number] [row_number];
        //String[][] daten_final = new String[rows][columns];

        // Reihen: Umsatz, S/H, Debit-K, Kreditoren-K, Datum, Buchungstext 1, Buchungstext 2
        String[][] daten_final = new String[rows-1][7];


        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_original, columns, RELEVANTE_ITEMS);


        // Datum schreiben




        //bookingdate-position
        Integer position_datum = ItemPositionCoordinator.findRelevantPosition(positionen, RELEVANTE_ITEMS[0], RELEVANTE_ITEMS);

        System.out.println("Position von Datum ist: " + position_datum);

        // Schreibe Datum aus daten_original in 4. Reihe von daten_final
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][4] = daten_original[pointer_reihe][position_datum];
            System.out.println("Daten final abgespeichert (" + RELEVANTE_ITEMS[0] + ") Position: " + pointer_reihe + ": " + daten_final[pointer_reihe-1][4]);

            //for(int j=0; j<columns; j++) {
            //}
        }



        // Betragfeld schreiben
        switch (operation) {
            case "Nur Gebühren":

                daten_final = extractFees(daten_original, rows, columns, positionen);


                break;
            case "Nur Transaktionen":

                break;

            case "Transaktionen und Gebühren":

                break;


            default:
                System.out.println("Operation ist nicht verfügbar");
                break;
        }


    }




    private static String[][] extractFees(String[][] daten_original, int rows, int columns, String[][] positionen) {


        String[][] daten_final = new String[rows][columns];

        // Zeile einlesen
        //https://stackoverflow.com/questions/25798958/iterate-through-2-dimensional-array
        for(int i=1; i<rows; i++) { // Bei "i = 1" beginnen, damit oberste Zeile nicht mitgenommen wird

            String[] zeile_aktuell = new String[columns];


            for(int j=0; j<columns; j++) {
                //System.out.println("Values at arr["+i+"]["+j+"] is "+daten_original[i][j]);

                zeile_aktuell[j] = daten_original[i][j];

                //System.out.println("Über Zeile " + j +" iteriert");




            }
            System.out.println("-> Alle gesammelten Ergebnisse lauten: " + Arrays.toString(zeile_aktuell));

            // Bestimmen welche Art von Gebühr es ist
            String art_gebuehr = chooseFeeType(zeile_aktuell, positionen);

            // Zeile zur Berechnung von Gebühr an entsprechende Methode übergeben (jede Gebührenart hat Methode)

            switch (art_gebuehr) {
                case "Bezahlung Zusatzleistungen":

                    break;
                case "Freigabe Verkaufserlös":

                    break;

                default:
                    System.out.println("Gebührenart nicht gefunden!");
                    break;
            }

        }




        return daten_final;

    }

    private static String chooseFeeType(String[] datenzeile, String[][] positionen) {

        String gebuehrtyp = new String();
        String bookingtext = new String();


        String carName = "booking_text";// insert code here
        for (int i=0;i<positionen.length;i++) {
            if (positionen[i][0].equals(carName)) {

                System.out.println("Array-Position von: " + carName + ": " + i);

                bookingtext = datenzeile[i];

                break;
            }
        }


        System.out.println("bookingtext: " +bookingtext);


        // Check fee

        //https://stackoverflow.com/questions/5091057/how-to-find-a-whole-word-in-a-string-in-java


        //"Bezahlung Zusatzleistungen",
        //        "Freigabe Verkaufserlös"

        for (int i=0;i<GEBUEHRENARTEN.length;i++) {

            //System.out.println("Suche nach " + GEBUEHRENARTEN[i]);

            //https://stackoverflow.com/questions/5091057/how-to-find-a-whole-word-in-a-string-in-java
            if (bookingtext.contains(GEBUEHRENARTEN[i])) {
                //System.out.println("TREFFFFFFFFFFFFFER!");
                gebuehrtyp = GEBUEHRENARTEN[i];
            } else {
                //System.out.println("Kein Treffer");
            }

        }

        System.out.println("Gebührtyp final ist: " + gebuehrtyp);

        return gebuehrtyp;
    }


}
