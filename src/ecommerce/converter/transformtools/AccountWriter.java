package ecommerce.converter.transformtools;

public class AccountWriter {

    public static String[][] writeAccount(String[][] daten, int rows, Integer kontonummer, Integer position_final) {

        // Schreibe Konto/Gegenkonto an position_final von daten_final
        System.out.print("Daten final abgespeichert (" + "Konto" + ") Position: ");
        for(int pointer_reihe=1; pointer_reihe<rows; pointer_reihe++) {
            // Int bei 1 starten, damit die oberste Zeile nicht mitgenommen wird

            daten[pointer_reihe-1][position_final] = kontonummer.toString();
            System.out.print(pointer_reihe + ": " + daten[pointer_reihe-1][position_final] + " - ");

        }
        System.out.println("Fertig mit Konto: " + kontonummer);

        return daten;
    }
}
