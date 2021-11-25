package ecommerce.converter.extractortools;

public class BroadcastCoordinator {

    public static String[][] transferData(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String[] relevanteItems, String relevantesItem, Integer position_final) {

        // Neu: Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};


        // position in Originaldatei
        Integer position_datum = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);

        //System.out.println("Position von Datum ist: " + position_datum);
        System.out.println("++++++++ "+ relevantesItem + " ++++++++");

        // Schreibe Datum aus daten_original in 6. Reihe (also Position 5) von daten_final
        System.out.print("Daten final abgespeichert (" + relevantesItem + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten_final[pointer_reihe-1][position_final] = daten_original[pointer_reihe][position_datum];
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);

        }
        System.out.println("");
        System.out.println("-> Fertig mit transferData von " + "(" +relevantesItem + ")");

        return daten_final;

    }


}
