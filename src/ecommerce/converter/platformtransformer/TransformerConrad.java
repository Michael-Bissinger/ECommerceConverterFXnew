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
            "Provisionssteuer",
            "Rückerstattung Provisionen"}; // 1

    private static String DATUM_FORMAT = "dd.MM.yy - HH:mm:ss"; // Datumsformat von "Conrad" (01.08.19 - 15:20:53)



    public static String[][] transformConradData(String operation, String[][] daten_original, int rows, int columns) {





        String[][] daten_final = new String[rows-1][10];

        // Positionen relevanter Items herausfinden
        String[][] positionen = ItemPositionCoordinator.storeRelevantPositions(daten_original, columns, RELEVANTE_ITEMS);


        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 3); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.println("Fertig mit Debitoren-Konto!");


        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 4); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final
        System.out.println("Fertig mit Kreditoren-Konto!");

        // ****************** DATUM ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_original, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[0], 5);

        // ****************** BELEGFELD 1 ******************
        daten_final = BroadcastCoordinator.transferData(daten_final, positionen, daten_original, rows, RELEVANTE_ITEMS, RELEVANTE_ITEMS[2], 6);

        // ****************** BELEGFELD 2 ******************
        System.out.println("Belegfeld 2 wird bei Conrad nicht beschrieben.");

        // ****************** BUCHUNGSTEXT ******************

        String[] relevanteItemsBuchungstext = {RELEVANTE_ITEMS[1], RELEVANTE_ITEMS[3]};

        daten_final = BuchungstextWriter.getBuchungstext(daten_final, positionen, daten_original, rows, relevanteItemsBuchungstext, RELEVANTE_ITEMS, 8);

        // ****************** FESTSCHREIBUNG ******************
        daten_final = FixationCoordinator.writeFixation(daten_final);


        return daten_final;

    }



}
