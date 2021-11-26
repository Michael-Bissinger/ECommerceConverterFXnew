package ecommerce.converter.extractortools;

public class BroadcastCoordinator {

    public static String[][] transferData(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String[] relevanteItems, String relevantesItem, Integer position_final) {


        // Position in Originaldatei finden
        Integer position_relevantesItem = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem, relevanteItems);

        System.out.println("++++++++ "+ relevantesItem + " ++++++++");

        // Schreibe jeweiliges item in entsprechende Reihe (also Position -1) von daten_final
        // Reihen: "Umsatz" (0), "Soll-Haben" (1), "Kontonummer" (2), "Gegenkonto" (3), "BU-Schlüssel" (4), "Belegdatum" (5), "Belegfeld 1" (6), "Belegfeld 2" (7), "Buchungstext" (8), "Festschreibung" (9)};
        System.out.print("Daten final abgespeichert (" + relevantesItem + ") Position: ");

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String buchungstext_zeile = new String();
            buchungstext_zeile = daten_original[pointer_reihe][position_relevantesItem];

            //https://stackoverflow.com/questions/4975978/remove-specific-characters-from-string-in-java
            buchungstext_zeile = buchungstext_zeile.replace("\"", ""); // Sollte noch " drin sein, dann rausmachen

            daten_final[pointer_reihe-1][position_final] = buchungstext_zeile;
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final] + " I ");

        }

        System.out.println("");
        System.out.println("-> Fertig mit transferData von " + "(" +relevantesItem + ")");

        return daten_final;

    }


    private static String[][] getBuchungstext(String[][] daten_final, String[][] positionen, String[][] daten_original, int rows, String relevantesItem1, String relevantesItem2, String relevantesItem3, String[] relevanteItems, int position_final) {

        System.out.println("Get buchungstext aktiv");

        // Positionen in Originaldatei finden
        Integer position_relevantesItem1 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem1, relevanteItems);
        Integer position_relevantesItem2 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem2, relevanteItems);
        Integer position_relevantesItem3 = ItemPositionCoordinator.findRelevantPosition(positionen, relevantesItem3, relevanteItems);

        System.out.println("++++++++ " + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + " ++++++++");

        System.out.print("Daten (Buchungstext) final abgespeichert (" + relevantesItem1 + " / " + relevantesItem2 + " / " + relevantesItem3 + ") Position: ");

        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            String buchungstext_zeile = new String();
            buchungstext_zeile = daten_original[pointer_reihe][position_relevantesItem2] + " " + daten_original[pointer_reihe][position_relevantesItem3] + " " + daten_original[pointer_reihe][position_relevantesItem1];


            daten_final[pointer_reihe-1][position_final] = buchungstext_zeile;
            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][position_final]);

        }



        return daten_final;


    }


}
