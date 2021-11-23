package ecommerce.converter.platformextractor;

import ecommerce.converter.DataRecorder;

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
        String[][] daten_final = new String[columns][rows];


        String[][] positionen = findRelevantPositions(daten_original, rows, columns);

        switch (operation) {
            case "Nur Gebühren":

                daten_final = extractFees(daten_original, rows, columns);


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

    private static String[][] extractFees(String[][] daten_original, int rows, int columns) {


        String[][] daten_final = new String[rows][columns];


        return daten_final;

    }

    private static String[][] findRelevantPositions(String[][] daten_original, int rows, int columns) {

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
                    items_position[anzahl_relevante_items][0] = RELEVANTE_ITEMS[anzahl_relevante_items].toString();
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
