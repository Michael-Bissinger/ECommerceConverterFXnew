package ecommerce.converter.platformtransformer;

import ecommerce.converter.parsertools.ParserDate;
import ecommerce.converter.transformtools.*;

public class TransformerReal {

    private static Integer KONTO_DEBITOR = 1469000; // Debitoren-Konto von "Real"
    private static Integer KONTO_KREDITOR = 7000130; // Kreditoren-Konto von "Real"

    private static String[] RELEVANTE_ITEMS = { // Relevante Items zur Berechnung
            "booking_date", // 0
            "booking_text", // 1
            "order_number", // 2
            "amount", // 3
            "fee_gross", // 4
            "buyer.email", // 5
            "billing.first_name", // 6
            "billing.last_name", // 7
            "fee_vat_%"}; // 8

    private static String[] GEBUEHRENARTEN = { // Mögliche Gebührenarten bei "Real"
            "Bezahlung Zusatzleistungen", // 0
            "Freigabe Verkaufserlös"}; // 1


    private static String DATUM_FORMAT = "yyyy-MM-dd HH:mm:ss"; // Datumsformat von "Real"


    public static String[][] transformRealData(String operation, String[][] daten_original, int rows, int columns) {

        // Alt: Reihen: Umsatz, S/H, Debit-K, Kreditoren-K, Datum, Buchungstext 1, Buchungstext 2
        // Neu: Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        String[][] daten_final = new String[rows-1][10];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_original, columns, RELEVANTE_ITEMS);


        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 3); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.println("Fertig mit Debitoren-Konto!");


        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 4); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final
        System.out.println("Fertig mit Kreditoren-Konto!");


        // ****************** BU-SCHLÜSSEL ******************
        daten_final = BUSchluesselWriter.getBUSchluessel(daten_final, positionen, daten_original, rows, RELEVANTE_ITEMS[8], RELEVANTE_ITEMS, 4);


        // ****************** DATUM ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_original, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[0], 5);
        daten_final = TransformerDate.reformatDate(daten_final, DATUM_FORMAT); // Transform to DATEV-format of Date

        // ****************** BELEGFELD 1 ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_original, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[2], 6);


        // ****************** BELEGFELD 2 ******************
        System.out.println("Belegfeld 2 wird bei Real nicht beschrieben.");


        // ****************** BUCHUNGSTEXT ******************

        String[] relevanteItemsBuchungstext = {RELEVANTE_ITEMS[5], RELEVANTE_ITEMS[6], RELEVANTE_ITEMS[7]};

        daten_final = BuchungstextWriter.getBuchungstext(daten_final, positionen, daten_original, rows, relevanteItemsBuchungstext, RELEVANTE_ITEMS, 8);

        // ****************** FESTSCHREIBUNG ******************
        daten_final = FixationCoordinator.writeFixation(daten_final);

        // ****************** Gebührencheck ******************



        switch (operation) {
            case "Nur Gebühren":
                daten_final = SHCoordinator.getSH(daten_final, rows, operation, RELEVANTE_ITEMS[0]);

                daten_final = extractFees(daten_final, daten_original, rows, columns, positionen, GEBUEHRENARTEN, RELEVANTE_ITEMS[1], RELEVANTE_ITEMS);

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



    private static String[][] getBuchungstext(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String relevantesItem1, String relevantesItem2, String relevantesItem3, String[] relevanteItems, int position_final) {

        System.out.println("Get buchungstext aktiv");

        // Positionen in Originaldatei finden
        Integer position_relevantesItem1 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem1, relevanteItems);
        Integer position_relevantesItem2 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem2, relevanteItems);
        Integer position_relevantesItem3 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem3, relevanteItems);

        System.out.println("++++++++ " + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + " ++++++++");

        System.out.print("Daten (Buchungstext) final abgespeichert (" + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + ") Position: ");

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String buchungstext_zeile = new String();
            buchungstext_zeile = daten_original[pointer_reihe][position_relevantesItem2] + " " + daten_original[pointer_reihe][position_relevantesItem3] + " " + daten_original[pointer_reihe][position_relevantesItem1];


            daten_final[pointer_reihe-1][position_final] = buchungstext_zeile;
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);

        }



        return daten_final;


    }


    private static String[][] getBelegfeld1(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows) {

        // ****************** BELEGFELD 1 ******************
        //bookingdate-position
        Integer position_belegfeld1 = ItemPositionCoordinator.findRelevantPosition(positionen, RELEVANTE_ITEMS[2], RELEVANTE_ITEMS);

        System.out.println("Position von Belegfeld 1 ist: " + position_belegfeld1);
        System.out.println("++++++++ BELEGFELD 1 ++++++++");

        // Schreibe Datum aus daten_original in 7. Reihe (also Position 6) von daten_final
        System.out.print("Daten final abgespeichert (" + RELEVANTE_ITEMS[2] + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][6] = daten_original[pointer_reihe][position_belegfeld1];
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][6]);

        }
        System.out.println("Fertig mit Belegfeld 1!");

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



    private static String[][] getSH(String[][] daten_final, int rows, String operation) {


        if (operation == "Nur Gebühren") {

        System.out.println("++++++++ S/H ++++++++");

        // Schreibe Haben (H) in 2. Reihe (also Position 1) von daten_final
        System.out.print("Daten final abgespeichert (" + RELEVANTE_ITEMS[0] + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][1] = "H";
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][1]);

        }
        }

        System.out.println("Fertig mit S/H!");

        return daten_final;
    }








    private static String[][] extractFees(String[][] daten_final, String[][] daten_original, int rows, int columns, String[][] positionen, String[] gebuehrenarten, String relevantesItem, String[] relevanteItems) {

        int position_relevantesItem = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);
        System.out.println("Position von bookingtext:" + position_relevantesItem);

        int position_amount = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[3], relevanteItems);
        System.out.println("Position von amount:" + position_amount);

        int position_fee_gross = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[4], relevanteItems);
        System.out.println("Position von fee_gross:" + position_fee_gross);





        System.out.println("Länge String: " + gebuehrenarten.length);
        for(int k=0; k<gebuehrenarten.length; k++) { // So lange iterieren wie man Gebührenarten hat

            System.out.println("--- Checke Gebührenart Nr. " + (k+1) + "/" + (gebuehrenarten.length) + ": " + gebuehrenarten[k] + " ---");

            for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Bei "i = 1" beginnen, damit oberste Zeile nicht mitgenommen wird

                System.out.println("Aktuell: " + daten_original[pointer_reihe][position_relevantesItem]);

                //if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[k])) {
                //
                //}

                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[0])) { // "Bezahlung Zusatzleistungen"

                    String value_String = daten_original[pointer_reihe][position_amount];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, true);

                    //System.out.println("Treffer");
                }


                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[1])) { // "Freigabe Verkaufserlös"

                    String value_String = daten_original[pointer_reihe][position_fee_gross];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, false);
                    //daten_final[pointer_reihe-1][0] = daten_original[pointer_reihe][position_fee_gross];
                    //daten_final[pointer_reihe-1][0] = daten_original[pointer_reihe][position_fee_gross];

                }

            }

        }


        //String[][] daten_final = new String[rows][columns];

        // Zeile einlesen
        // https://stackoverflow.com/questions/25798958/iterate-through-2-dimensional-array
//        for(int i=1; i<rows; i++) { // Bei "i = 1" beginnen, damit oberste Zeile nicht mitgenommen wird
//
//            String[] zeile_aktuell = new String[columns];
//
//
//            for(int j=0; j<columns; j++) {
//                //System.out.println("Values at arr["+i+"]["+j+"] is "+daten_original[i][j]);
//
//                zeile_aktuell[j] = daten_original[i][j];
//
//                //System.out.println("Über Zeile " + j +" iteriert");
//
//
//
//
//            }
//            System.out.println("-> Alle gesammelten Ergebnisse lauten: " + Arrays.toString(zeile_aktuell));
//
//            // Bestimmen welche Art von Gebühr es ist
//            String art_gebuehr = chooseFeeType(zeile_aktuell, positionen);
//
//            // Zeile zur Berechnung von Gebühr an entsprechende Methode übergeben (jede Gebührenart hat Methode)
//
//            switch (art_gebuehr) {
//                case "Bezahlung Zusatzleistungen":
//
//                    break;
//                case "Freigabe Verkaufserlös":
//
//                    break;
//
//                default:
//                    System.out.println("Gebührenart nicht gefunden!");
//                    break;
//            }
//
//        }




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
