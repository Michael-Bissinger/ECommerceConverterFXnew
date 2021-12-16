package ecommerce.converter.transformtools;

public class BuchungstextWriter {

    public static String[][] getBuchungstext(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String[] relevanteItemsBuchungstext, String[] relevanteItems, int position_final) {

        System.out.println("Get buchungstext aktiv");

        // Positionen in Originaldatei finden

        int[] positionen_relevateItems =  new int[relevanteItemsBuchungstext.length];

        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

            positionen_relevateItems[i] = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItemsBuchungstext[i], relevanteItems);

        }

        //Integer position_relevantesItem1 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem1, relevanteItems);
        //Integer position_relevantesItem2 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem2, relevanteItems);
        //Integer position_relevantesItem3 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem3, relevanteItems);

        System.out.print("++++++++ ");
        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {


            System.out.print(relevanteItems[i] + " / ");

        }
        System.out.println(" ++++++++");

        //System.out.println("++++++++ " + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + " ++++++++");

        System.out.print("Daten (Buchungstext) final abgespeichert (");
        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {


            System.out.print(relevanteItems[i] + " / ");

        }
        System.out.print("Position: ");

        //System.out.print("Daten (Buchungstext) final abgespeichert (" + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + ") Position: ");

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String buchungstext_zeile = new String();

            for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

                buchungstext_zeile = buchungstext_zeile + daten_original[pointer_reihe][positionen_relevateItems[i]] + " ";
                //positionen_relevateItems[i] = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItems[i], relevanteItems);

            }

            //buchungstext_zeile = daten_original[pointer_reihe][position_relevantesItem2] + " " + daten_original[pointer_reihe][position_relevantesItem3] + " " + daten_original[pointer_reihe][position_relevantesItem1];


            daten_final[pointer_reihe-1][position_final] = buchungstext_zeile;
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);

        }



        return daten_final;


    }


}
