package ecommerce.converter.platformtransformer;

import ecommerce.converter.transformtools.*;

public class TransformerAlltricks {

    private static Integer KONTO_DEBITOR = 1464001; // Debitoren-Konto von "Alltricks"
    private static Integer KONTO_KREDITOR = 7000199; // Kreditoren-Konto von "Alltricks"

    private static String[] RELEVANTE_ITEMS = { // Relevante Items zur Berechnung
            "Erstellungsdatum", // 0
            "Rechnungsnummer", // 1
            "Bestellnr.", // 2
            "Typ", // 3
            "Betrag" // 4
    };

    private static String[] GEBUEHRENARTEN = { // Mögliche Gebührenarten bei "Alltricks"
            "XXX", // 0
            "XXXX", // 1
            "XXX"}; // 2

    private static String DATUM_FORMAT = "dd.MM.yyyy - HH:mm:ss"; // Datumsformat von "Alltricks" (14.04.2020 - 20:58:24)
                                        //08.06.2020 - 00:09:12
    
    public static String[][] transformData(String operation, String[][] daten_roh, int rows, int columns) {

        // Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        String[][] daten_final = new String[rows-1][10];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_roh, columns, RELEVANTE_ITEMS);


        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 3); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final

        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 4); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final

        // ****************** DATUM ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_roh, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[0], 5);
        daten_final = TransformerDate.reformatDate(daten_final, DATUM_FORMAT); // Transform to DATEV-format of Date

        // ****************** BELEGFELD 1 ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_roh, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[1], 6);

        // ****************** BELEGFELD 2 ******************
        System.out.println("Belegfeld 2 wird bei Real nicht beschrieben.");

        // ****************** BUCHUNGSTEXT ******************
        String[] relevanteItemsBuchungstext = {RELEVANTE_ITEMS[2], RELEVANTE_ITEMS[3]};
        daten_final = BuchungstextWriter.getBuchungstext(daten_final, positionen, daten_roh, rows, relevanteItemsBuchungstext, RELEVANTE_ITEMS, 8);

        // ****************** FESTSCHREIBUNG ******************
        daten_final = FixationCoordinator.writeFixation(daten_final);

        // ***************************************************
        // ****************** GEBÜHRENCHECK ******************
        // ***************************************************
        // Hauptinformationen für Buchung

        switch (operation) {
            case "Nur Gebühren" -> daten_final = extractFees(daten_final, daten_roh, rows, positionen, GEBUEHRENARTEN, RELEVANTE_ITEMS[3], RELEVANTE_ITEMS);
            default -> System.out.println("FEHLER: Operation ist nicht verfügbar");
        }


        return daten_final;

    }

    private static String[][] extractFees(String[][] daten_final, String[][] daten_roh, int rows, String[][] positionen, String[] gebuehrenarten, String relevantesItem, String[] relevanteItems) {

        // ****************** Relevante Items zur Bestimmung von Gebühren ******************

        int position_relevantesItemGebuehrenart = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);
        System.out.println("Position von Typ:" + position_relevantesItemGebuehrenart);

        int position_amount = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[4], relevanteItems);
        System.out.println("Position von Betrag:" + position_amount);


















        return daten_final;
    }



}