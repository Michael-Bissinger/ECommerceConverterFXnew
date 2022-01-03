package ecommerce.converter.platformtransformer;

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
            "Freigabe Verkaufserlös", // 1
            "Nettotransaktionsgebuehren fuer stornierte Transaktionen", // 2
            "Umsatzsteuer für stornierte Transaktionen"}; // 3

    private static String DATUM_FORMAT = "yyyy-MM-dd HH:mm:ss"; // Datumsformat von "Real" (2019-12-01 00:23:11)


    public static String[][] transformRealData(String operation, String[][] daten_original, int rows, int columns) {

        // Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        String[][] daten_final = new String[rows-1][10];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_original, columns, RELEVANTE_ITEMS);


        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 3); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final

        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 4); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final

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


        // ***************************************************
        // ****************** GEBÜHRENCHECK ******************
        // ***************************************************
        // Hauptinformationen für Buchung

        switch (operation) {
            case "Nur Gebühren" -> daten_final = extractFees(daten_final, daten_original, rows, positionen, GEBUEHRENARTEN, RELEVANTE_ITEMS[1], RELEVANTE_ITEMS);
            default -> System.out.println("FEHLER: Operation ist nicht verfügbar");
        }

        return daten_final;

    }



    private static String[][] extractFees(String[][] daten_final, String[][] daten_original, int rows, String[][] positionen, String[] gebuehrenarten, String relevantesItem, String[] relevanteItems) {

        // ****************** Relevante Items zur Bestimmung von Gebühren ******************

        int position_relevantesItem = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);
        System.out.println("Position von bookingtext:" + position_relevantesItem);

        int position_amount = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[3], relevanteItems);
        System.out.println("Position von amount:" + position_amount);

        int position_fee_gross = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[4], relevanteItems);
        System.out.println("Position von fee_gross:" + position_fee_gross);

        int position_fee_vat = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[8], relevanteItems);
        System.out.println("Position von fee_vat_%:" + position_fee_vat);


        // ****************** Durchtesten aller Gebührenarten ******************

        System.out.println("Anzahl Gebührenarten: " + gebuehrenarten.length);
        for(int k=1; k<gebuehrenarten.length; k++) { // So lange iterieren wie man Gebührenarten hat

            System.out.println("--- Checke Gebührenart Nr. " + (k) + "/" + (gebuehrenarten.length) + ": " + gebuehrenarten[k] + " ---");

            for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Bei "i = 1" beginnen, damit oberste Zeile nicht mitgenommen wird


                System.out.println("++++++++++ START REIHE: " + (pointer_reihe-1) + "++++++++++");


                // ***************************************************
                // GEBÜHR "Bezahlung Zusatzleistungen" // 0
                // ***************************************************
                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[0])) {

                    System.out.println("Gebührenart: " + daten_original[pointer_reihe][position_relevantesItem]);

                    // ****************** UMSATZ ******************

                    String value_String = daten_original[pointer_reihe][position_amount];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, true);

                    // ****************** SOLL/HABEN ******************

                    daten_final[pointer_reihe-1][1] = "H";
                    System.out.println("S/H: Reihe " + (pointer_reihe-1) + ": " + daten_final[pointer_reihe-1][1]);

                    // ****************** BU-SCHLÜSSEL ******************

                    String bu_schluessel_roh = daten_original[pointer_reihe][position_fee_vat];
                    System.out.println("Das sind die Informationen zum BU-Schlüssel: " + bu_schluessel_roh);

                    daten_final[pointer_reihe-1][4] = BUSchluesselWriter.getBUSchluessel(bu_schluessel_roh);

                    System.out.println("Buchungsschlüssel: Reihe " + (pointer_reihe-1) + ": \"" + daten_final[pointer_reihe-1][4] + "\"");

                    // ****************** FERTIG ******************

                    System.out.println("++++++++++ ENDE REIHE: " + (pointer_reihe-1) + "++++++++++");

                }
                // ***************************************************
                // GEBÜHR "Freigabe Verkaufserlös" // 1
                // ***************************************************
                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[1])) {

                    System.out.println("Gebührenart: " + daten_original[pointer_reihe][position_relevantesItem]);

                    // ****************** UMSATZ ******************

                    String value_String = daten_original[pointer_reihe][position_fee_gross];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, false);

                    // ****************** SOLL/HABEN ******************

                    daten_final[pointer_reihe-1][1] = "H";
                    System.out.println("S/H: Reihe " + (pointer_reihe-1) + ": " + daten_final[pointer_reihe-1][1]);

                    // ****************** BU-SCHLÜSSEL ******************

                    String bu_schluessel_roh = daten_original[pointer_reihe][position_fee_vat];
                    System.out.println("Das sind die Informationen zum BU-Schlüssel: " + bu_schluessel_roh);

                    daten_final[pointer_reihe-1][4] = BUSchluesselWriter.getBUSchluessel(bu_schluessel_roh);

                    System.out.println("Buchungsschlüssel: Reihe " + (pointer_reihe-1) + ": \"" + daten_final[pointer_reihe-1][4] + "\"");

                    // ****************** FERTIG ******************

                    System.out.println("++++++++++ ENDE REIHE: " + (pointer_reihe-1) + "++++++++++");

                }

                // ***************************************************
                // GEBÜHR "Nettotransaktionsgebuehren fuer stornierte Transaktionen" // 2
                // ***************************************************
                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[2])) {

                    System.out.println("Gebührenart: " + daten_original[pointer_reihe][position_relevantesItem]);

                    // ****************** UMSATZ ******************

                    String value_String = daten_original[pointer_reihe][position_amount];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, true);

                    // ****************** SOLL/HABEN ******************

                    daten_final[pointer_reihe-1][1] = "S";
                    System.out.println("S/H: Reihe " + (pointer_reihe-1) + ": " + daten_final[pointer_reihe-1][1]);

                    // ****************** BU-SCHLÜSSEL ******************

                    String bu_schluessel_roh = daten_original[pointer_reihe][position_fee_vat];
                    System.out.println("Das sind die Informationen zum BU-Schlüssel: " + bu_schluessel_roh);

                    daten_final[pointer_reihe-1][4] = BUSchluesselWriter.getBUSchluessel(bu_schluessel_roh);

                    System.out.println("Buchungsschlüssel: Reihe " + (pointer_reihe-1) + ": \"" + daten_final[pointer_reihe-1][4] + "\"");

                    // ****************** FERTIG ******************

                    System.out.println("++++++++++ ENDE REIHE: " + (pointer_reihe-1) + "++++++++++");

                }

                // ***************************************************
                // GEBÜHR "Umsatzsteuer für stornierte Transaktionen"}; // 3
                // ***************************************************
                if (daten_original[pointer_reihe][position_relevantesItem].contains(gebuehrenarten[3])) {

                    System.out.println("Gebührenart: " + daten_original[pointer_reihe][position_relevantesItem]);

                    // ****************** UMSATZ ******************

                    String value_String = daten_original[pointer_reihe][position_amount];
                    daten_final[pointer_reihe-1][0] = BroadcastCoordinator.trimNumber(value_String, true);

                    // ****************** SOLL/HABEN ******************

                    daten_final[pointer_reihe-1][1] = "S";
                    System.out.println("S/H: Reihe " + (pointer_reihe-1) + ": " + daten_final[pointer_reihe-1][1]);

                    // ****************** BU-SCHLÜSSEL ******************

                    String bu_schluessel_roh = daten_original[pointer_reihe][position_fee_vat];
                    System.out.println("Das sind die Informationen zum BU-Schlüssel: " + bu_schluessel_roh);

                    daten_final[pointer_reihe-1][4] = BUSchluesselWriter.getBUSchluessel(bu_schluessel_roh);

                    System.out.println("Buchungsschlüssel: Reihe " + (pointer_reihe-1) + ": \"" + daten_final[pointer_reihe-1][4] + "\"");

                    // ****************** FERTIG ******************

                    System.out.println("++++++++++ ENDE REIHE: " + (pointer_reihe-1) + "++++++++++");

                }

            }

        }

        return daten_final;

    }

}
