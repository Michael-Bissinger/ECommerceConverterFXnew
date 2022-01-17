package ecommerce.converter.platformtransformer;

import ecommerce.converter.transformtools.*;

public class TransformerConrad {

    private static Integer KONTO_DEBITOR = 1462000; // Debitoren-Konto von "Conrad"
    private static Integer KONTO_KREDITOR = 7000166; // Kreditoren-Konto von "Conrad"

    private static String[] RELEVANTE_ITEMS = { // Relevante Items zur Berechnung
            "Erstellungsdatum", // 0
            "Bestellnr.", // 1
            "Rechnungsnummer", // 2
            "Transaktionsnummer", // 3
            "Beschreibung", // 4
            "Typ", // 5
            "Betrag" // 6
            };

    private static String[] GEBUEHRENARTEN = { // Mögliche Gebührenarten bei "Conrad"
            "Provisionen", // 0
            "Provisionssteuer", // 1
            "Rückerstattung der Provision"}; // 2

    private static String DATUM_FORMAT = "dd.MM.yy - HH:mm:ss"; // Datumsformat von "Conrad" (01.08.19 - 15:20:53)


    public static String[][] transformData(String operation, String[][] daten_roh, int rows, int columns) {

        // Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        String[][] daten_final = new String[rows-1][10];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_roh, columns, RELEVANTE_ITEMS);

        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 2); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final

        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 3); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final

        // ****************** DATUM ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_roh, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[0], 5);
        daten_final = TransformerDate.reformatDate(daten_final, DATUM_FORMAT); // Transform to DATEV-format of Date

        // ****************** BELEGFELD 1 ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_roh, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[2], 6);

        // ****************** BELEGFELD 2 ******************
        System.out.println("Belegfeld 2 wird bei Conrad nicht beschrieben.");

        // ****************** BUCHUNGSTEXT ******************

        String[] relevanteItemsBuchungstext = {RELEVANTE_ITEMS[1], RELEVANTE_ITEMS[3]};

        daten_final = BuchungstextWriter.getBuchungstext(daten_final, positionen, daten_roh, rows, relevanteItemsBuchungstext, RELEVANTE_ITEMS, 8);

        // ****************** FESTSCHREIBUNG ******************
        daten_final = FixationCoordinator.writeFixation(daten_final);

        // ***************************************************
        // ****************** GEBÜHRENCHECK ******************
        // ***************************************************
        // Hauptinformationen für Buchung

        switch (operation) {
            case "Nur Gebühren" -> daten_final = extractFees(daten_final, daten_roh, rows, positionen, GEBUEHRENARTEN, RELEVANTE_ITEMS[5], RELEVANTE_ITEMS);
            default -> System.out.println("FEHLER: Operation ist nicht verfügbar");
        }

        return daten_final;

    }

    private static String[][] extractFees(String[][] daten_final, String[][] daten_roh, int rows, String[][] positionen, String[] gebuehrenarten, String relevantesItem, String[] relevanteItems) {

        // ****************** Relevante Items zur Bestimmung von Gebühren ******************

        int position_relevantesItemGebuehrenart = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);
        System.out.println("Position von Typ:" + position_relevantesItemGebuehrenart);

        int position_betrag = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[4], relevanteItems);
        System.out.println("Position von Betrag:" + position_betrag);

        int position_beschreibung = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[4], relevanteItems);
        System.out.println("Position von Beschreibung:" + position_beschreibung);

        // ****************** Durchtesten aller Gebührenarten ******************

        System.out.println("Anzahl Gebührenarten: " + gebuehrenarten.length);
        for (int k = 1; k < gebuehrenarten.length; k++) { // So lange iterieren wie man Gebührenarten hat

            System.out.println("--- Checke Gebührenart Nr. " + (k) + "/" + (gebuehrenarten.length) + ": " + gebuehrenarten[k] + " ---");

            for (int pointer_reihe = 1; pointer_reihe < rows; pointer_reihe++) { // Bei "i = 1" beginnen, damit oberste Zeile nicht mitgenommen wird

                System.out.println("++++++++++ START REIHE: " + (pointer_reihe - 1) + "++++++++++");

            }
        }



        return daten_final;
    }
}
