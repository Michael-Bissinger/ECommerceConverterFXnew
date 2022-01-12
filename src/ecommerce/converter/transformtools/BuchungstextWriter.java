package ecommerce.converter.transformtools;

public class BuchungstextWriter {

    public static String[][] getBuchungstext(String[][] daten_final, String[][] positionen, String[][] daten_roh, int rows, String[] relevanteItemsBuchungstext, String[] relevanteItems, int position_final) {

        // Positionen in Originaldatei finden
        int[] positionen_relevateItems =  new int[relevanteItemsBuchungstext.length];

        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

            positionen_relevateItems[i] = ItemPositionCoordinator.findRelevantPosition(positionen, relevanteItemsBuchungstext[i], relevanteItems);
        }

        System.out.print("++++++++ ");
        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

            System.out.print(relevanteItems[i] + " / ");
        }
        System.out.println(" ++++++++");

        System.out.print("Daten (Buchungstext) final abgespeichert (");
        for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

            System.out.print(relevanteItems[i] + " / ");
        }

        System.out.print("Position: ");

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) {
            // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String buchungstext_zeile = new String();

            for (int i=0; i<relevanteItemsBuchungstext.length; i++) {

                buchungstext_zeile = buchungstext_zeile + daten_roh[pointer_reihe][positionen_relevateItems[i]] + " ";

            }

            daten_final[pointer_reihe-1][position_final] = buchungstext_zeile;
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);
        }

        return daten_final;
    }
}
