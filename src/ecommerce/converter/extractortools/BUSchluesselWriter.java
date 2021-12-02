package ecommerce.converter.extractortools;


public class BUSchluesselWriter {

    private static String[] BU_9 = { // Relevante Items zur Berechnung von Buchungsschlüssel 9
            "19" // 0
            };

    //private static String[] ALLE_BUSCHLUESSEL = BU_9; // Alle möglichen Buchungsschlüssel zusammen


    public static String[][] getBUSchluessel(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String relevantesItemBUSchluessel, String[] relevanteItems, int position_final) {


        int position_relevantesItemBuchungsschluessel = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItemBUSchluessel, relevanteItems);

        System.out.println("Position von fee_vat_%: " + position_relevantesItemBuchungsschluessel);

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String BU_Schluessel = new String();


            // Checken ob Buchungsschlüssel 9 in Frage kommt
            for(int i=0; i<BU_9.length; i++) {

                System.out.println("BULENG" + i);
                System.out.println(daten_original[pointer_reihe][position_relevantesItemBuchungsschluessel]);
                System.out.println(BU_9[i]);

                if (daten_original[pointer_reihe][position_relevantesItemBuchungsschluessel].toString().equals(BU_9[i].toString())) {

                    daten_final[pointer_reihe-1][position_final] = "9";
                    System.out.println("Buchungsschlüssel 9 eingetragen an "+ daten_final[pointer_reihe-1][position_final]);


                }

            }



            //System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);

        }





        //for (i=




        return daten_final;
    }

}
