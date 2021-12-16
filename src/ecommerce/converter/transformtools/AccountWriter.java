package ecommerce.converter.transformtools;

public class AccountWriter {

    public static String[][] writeAccount(String[][] daten, int rows, Integer kontonummer, Integer kontonummerDATEV) {


        // ****************** DEBITORENKONTO ******************
        System.out.println("++++++++ KREDITORENKONTO ++++++++");
        // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
        System.out.print("Daten final abgespeichert (" + "Konto" + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten[pointer_reihe-1][kontonummerDATEV-1] = kontonummer.toString();
            System.out.print(pointer_reihe + ": " + daten[pointer_reihe-1][kontonummerDATEV-1] + " - ");

        }
        System.out.println("Fertig mit Konto: " + kontonummer);


        return daten;
    }


    // ****************** DEBITORENKONTO ******************
//        System.out.println("++++++++ DEBITORENKONTO ++++++++");
    // Schreibe Debitorenkonto  in 3. Reihe (also Position 2) von daten_final
//        System.out.print("Daten final abgespeichert (" + "Debitorenkonto" + ") Position: ");
//        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird
//
//            daten_final[pointer_reihe-1][2] = KONTO_DEBITOR.toString();
//            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][2] + " - ");
//
//        }
//        System.out.println("Fertig mit Debitoren-Konto!");


    // ****************** KREDITORENKONTO ******************
    //        System.out.println("++++++++ KREDITORENKONTO ++++++++");
    // Schreibe Kreditorenkonto in 4. Reihe (also Position 3) von daten_final
//        System.out.print("Daten final abgespeichert (" + "Debitorenkonto" + ") Position: ");
//        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) { // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird
//
//            daten_final[pointer_reihe-1][3] = KONTO_KREDITOR.toString();
//            System.out.print(pointer_reihe + ": " + daten_final[pointer_reihe-1][3] + " - ");
//
//        }
//        System.out.println("Fertig mit Kreditoren-Konto!");



}
