package ecommerce.converter.platformextractor;

import ecommerce.converter.DataRecorder;

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

    public static void extractRealData(String operation, String[][] daten_original, int rows, int columns) {

        //String [] [] daten_fertig = new String [columns_number] [row_number];
        String[][] daten_final = new String[rows][columns];


        String[][] positionen = findRelevantPositions(daten_original, columns);

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
        for(int i=0; i<daten_original.length; i++) {

            String[] zeile_aktuell = new String[columns];

            for(int j=0; j<daten_original[i].length; j++) {
                //System.out.println("Values at arr["+i+"]["+j+"] is "+daten_original[i][j]);

                zeile_aktuell[j] = daten_original[i][j];

                System.out.println(j +" mal iteriert");


                System.out.println("Column: " +columns);
                System.out.println("Rows: " +rows);



            }
            System.out.println("Alle gesammelten Ergebnisse lauten: " + Arrays.toString(zeile_aktuell));
            // Bestimmen welche Art von Gebühr es ist
            // An entsprechender Position von "booking text" erkennbar
            // 3 Möglichkeiten: Bezahlung Zusatzleistungen / Freigabe Verkaufserlös / Auszahlung
        }


        // Bestimmen welche Art von Gebühr es ist
                // An entsprechender Position von "booking text" erkennbar
                // 3 Möglichkeiten: Bezahlung Zusatzleistungen / Freigabe Verkaufserlös / Auszahlung

        // Zeile zur Berechnung von Gebühr an entsprechende Methode übergeben (jede Gebührenart hat Methode)



        return daten_final;

    }

    private static String[][] findRelevantPositions(String[][] daten_original, int columns) {

        // Positionen finden (Columns durchsuchen nach relevanten Items)
        System.out.println("++++++++ SUCHE POSITIONEN ITEMS ++++++++++");

        String[][] items_position = new String[RELEVANTE_ITEMS.length][2]; // Positionen der Daten so lange wie Anzahl relevante Daten (Reihen) und 2 Spalten

        for (int anzahl_relevante_items=0; anzahl_relevante_items<RELEVANTE_ITEMS.length; anzahl_relevante_items++) {

            //https://stackoverflow.com/questions/23160832/how-to-find-index-of-string-array-in-java-from-a-given-value
            //String carName = "billing.last_name";// insert code here
            String carName = RELEVANTE_ITEMS[anzahl_relevante_items];// insert code here
            int index = 0;
            for (int i = 0; i < columns; i++) {
                //System.out.println("Runde " + i);
                if (daten_original[0][i].equals(carName)) {
                    index = i;
                    //System.out.println("Item " + RELEVANTE_ITEMS[anzahl_relevante_items] + " gefunden an Position " + i);
                    items_position[anzahl_relevante_items][0] = RELEVANTE_ITEMS[anzahl_relevante_items];
                    items_position[anzahl_relevante_items][1] = Integer.toString(i);
                    System.out.println("Position gefunden und gespeichert: " + items_position[anzahl_relevante_items][0] +" / " + items_position[anzahl_relevante_items][1]);
                    break;

                }
            }
        }

        System.out.println("++++++++ ENDE POSITIONEN ITEMS ++++++++++");


        return items_position;

    }


}
