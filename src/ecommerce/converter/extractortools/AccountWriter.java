package ecommerce.converter.extractortools;

public class AccountWriter {

    public static String[][] writeAccount(String[][] daten, int rows, Integer kontonummer, Integer kontonummerDATEV) {


        // ****************** DEBITORENKONTO ******************
        System.out.println("++++++++ DEBITORENKONTO ++++++++");
        // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.print("Daten final abgespeichert (" + "Konto" + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten[pointer_reihe-1][kontonummerDATEV-1] = kontonummer.toString();
            System.out.print(pointer_reihe + ": " + daten[pointer_reihe-1][kontonummerDATEV-1] + " - ");

        }
        System.out.println("Fertig mit Konto: " + kontonummer);


        return daten;
    }


    }
