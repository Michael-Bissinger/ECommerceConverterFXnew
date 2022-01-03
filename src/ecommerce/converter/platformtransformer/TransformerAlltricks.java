package ecommerce.converter.platformtransformer;

import ecommerce.converter.transformtools.*;

public class TransformerAlltricks {

    private static Integer KONTO_DEBITOR = 111111; // Debitoren-Konto von "Conrad" TODO
    private static Integer KONTO_KREDITOR = 11111; // Kreditoren-Konto von "Conrad" TODO

    private static String[] RELEVANTE_ITEMS = { // Relevante Items zur Berechnung
            "XXX"
            };

    private static String[] GEBUEHRENARTEN = { // Mögliche Gebührenarten bei "Conrad"
            "XXX", // 0
            "XXXX", // 1
            "XXX"}; // 2

    private static String DATUM_FORMAT = "dd.MM.yyyy - HH:mm:ss"; // Datumsformat von "Alltricks" (14.04.2020 - 20:58:24)

    
    public static String[][] transformAlltricksData(String operation, String[][] daten_original, int rows, int columns) {

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

        // ****************** FESTSCHREIBUNG ******************
        daten_final = FixationCoordinator.writeFixation(daten_final);


        return daten_final;

    }



}
