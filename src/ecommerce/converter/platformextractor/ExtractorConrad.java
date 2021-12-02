package ecommerce.converter.platformextractor;

import ecommerce.converter.extractortools.AccountWriter;

public class ExtractorConrad {

    private static Integer KONTO_DEBITOR = 1462000; // Debitoren-Konto von "Conrad"
    private static Integer KONTO_KREDITOR = 7000166; // Kreditoren-Konto von "Conrad"

    private static String[] RELEVANTE_ITEMS = { // Relevante Items zur Berechnung
            "", // 0
            "", // 1
            "", // 2
            "", // 3
            "", // 4
            "", // 5
            "", // 6
            "", // 7
            ""}; // 8

    private static String[] GEBUEHRENARTEN = { // Mögliche Gebührenarten bei "Conrad"
            "Bezahlung Zusatzleistungen",
            "Freigabe Verkaufserlös"};



    public static String[][] extractConradData(String operation, String[][] daten_original, int rows, int columns) {





        String[][] daten_final = new String[rows-1][10];

        // ****************** DEBITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_DEBITOR, 3); // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.println("Fertig mit Debitoren-Konto!");


        // ****************** KREDITORENKONTO ******************
        daten_final = AccountWriter.writeAccount(daten_final, rows, KONTO_KREDITOR, 4); // Schreibe Kreditorenkonto  in 4. Reihe (also Position 3) von daten_final
        System.out.println("Fertig mit Kreditoren-Konto!");





        return daten_final;

    }



}
