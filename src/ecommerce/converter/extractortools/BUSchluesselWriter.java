package ecommerce.converter.extractortools;


public class BUSchluesselWriter {

    private static String[] BU_9 = { // Relevante Items zur Berechnung von Buchungsschlüssel 9
            "19" // 0
            };

    //private static String[] ALLE_BUSCHLUESSEL = BU_9; // Alle möglichen Buchungsschlüssel zusammen


    public static String[][] getBUSchluessel(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String relevantesItemBUSchluessel, String[] relevanteItems, int position_final) {


        int position_relevantesItemBuchungsschluessel = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItemBUSchluessel, relevanteItems);

        System.out.println("Position von fee_vat_%: " + position_relevantesItemBuchungsschluessel);





        //for (i=




        return daten_final;
    }

}
