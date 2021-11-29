package ecommerce.converter.extractortools;

public class SHCoordinator {

    public static String[][] getSH(String[][] daten_final, int rows, String operation, String relevantesItem) {


        if (operation == "Nur Gebühren") {

            System.out.println("++++++++ S/H ++++++++");

            // Schreibe Haben (H) in 2. Reihe (also Position 1) von daten_final
            System.out.print("Daten final abgespeichert (" + relevantesItem + ") Position: ");
            for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

                daten_final[pointer_reihe-1][1] = "H";
                System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][1]);

            }
        }

        System.out.println("Fertig mit S/H!");

        return daten_final;
    }



}
