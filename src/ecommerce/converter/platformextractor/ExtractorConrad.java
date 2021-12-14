package ecommerce.converter.platformextractor;

import ecommerce.converter.extractortools.AccountWriter;
import ecommerce.converter.extractortools.BroadcastCoordinator;
import ecommerce.converter.extractortools.ItemPositionCoordinator;

public class ExtractorConrad {

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
            "Bezahlung Zusatzleistungen",
            "Freigabe Verkaufserlös"};



    public static String[][] extractConradData(String operation, String[][] daten_original, int rows, int columns) {





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




        return daten_final;

    }



}
