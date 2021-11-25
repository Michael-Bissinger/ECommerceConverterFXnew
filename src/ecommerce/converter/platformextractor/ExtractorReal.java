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





    public static String[][] extractRealData(String operation, String[][] daten_original, int rows, int columns) {

        //String [] [] daten_fertig = new String [columns_number] [row_number];
        //String[][] daten_final = new String[rows][columns];

        // Reihen: Umsatz, S/H, Debit-K, Kreditoren-K, Datum, Buchungstext 1, Buchungstext 2
        // Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        String[][] daten_final = new String[rows-1][9];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_original, columns, RELEVANTE_ITEMS);



        // ****************** DATUM ******************
        daten_final = getDate(daten_final, positionen, daten_original, rows);
        //daten_final = datum_daten;


        // ****************** DebitorenKonto ******************
        System.out.println("++++++++ DEBITORENKONTO ++++++++");
        // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.print("Daten final abgespeichert (" + "Debitorenkonto" + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][2] = KONTO_DEBITOR.toString();
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][2] + " - ");

        }
        System.out.println("Fertig mit Debitoren!");


        // ****************** Kreditorenkonto ******************
        System.out.println("++++++++ KREDITORENKONTO ++++++++");
        // Schreibe Kreditorenkonto in 4. Reihe (also Position 3) von daten_final
        System.out.print("Daten final abgespeichert (" + "Debitorenkonto" + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][3] = KONTO_KREDITOR.toString();
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][3] + " - ");

        }
        System.out.println("Fertig mit Kreditoren!");

        // ****************** Gebührencheck ******************
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




        return daten_final;

    }



    private static String[][] getDate(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows) {

        // ****************** DATUM ******************
        //bookingdate-position
        Integer position_datum = ItemPositionCoordinator.findRelevantPosition(positionen, RELEVANTE_ITEMS[0], RELEVANTE_ITEMS);

        //System.out.println("Position von Datum ist: " + position_datum);
        System.out.println("++++++++ DATUM ++++++++");

        // Schreibe Datum aus daten_original in 6. Reihe (also Position 5) von daten_final
        System.out.print("Daten final abgespeichert (" + RELEVANTE_ITEMS[0] + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][5] = daten_original[pointer_reihe][position_datum];
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][5]);

        }
        System.out.println("Fertig mit Datum!");

        return daten_final;

    }





    private static String[][] extractFees(String[][] daten_original, int rows, int columns, String[][] positionen) {


        String[][] daten_final = new String[rows][columns];

        // Zeile einlesen
        // https://stackoverflow.com/questions/25798958/iterate-through-2-dimensional-array
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
