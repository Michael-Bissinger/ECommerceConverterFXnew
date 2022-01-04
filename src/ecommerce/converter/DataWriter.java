package ecommerce.converter;

import com.opencsv.CSVWriter;
import ecommerce.converter.generaltools.LogCoordinator;
import ecommerce.converter.loadertools.LoaderDate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ecommerce.converter.loadertools.LoaderDate.checkDate;

public class DataWriter {

    public static void writeData (File filepath, String finalformat, String[][] daten_final) {

        System.out.println("Daten werden zu Format " + finalformat + " konvertiert!");

        switch (finalformat) {
            case "Maske (ASCII)" -> createMaskASCII(filepath, daten_final);

            default -> System.out.println("FEHLER: Endformat kann nicht erstellt werden!");
        }
    }

    private static void createMaskASCII (File filepath_origin, String[][] daten_final) {

        List<Integer> months = LoaderDate.collectMonths(daten_final);

        int aktueller_monat = 0;
        while (aktueller_monat < months.size()) {

            LogCoordinator.writeLog("FINALDATEN: Kreiere ASCII-Maske!");

            System.out.println("Pfad: " + filepath_origin.getParent());

            CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter(filepath_origin.getParent() + "\\" +
                        "Mask_ASCII_" + months.get(aktueller_monat) + ".csv"),';','"', '\\',CSVWriter.DEFAULT_LINE_END);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String[]> therows = new ArrayList<>();

            String[] header = new String[]{
                            "Umsatz",
                            "Soll-Haben",
                            "Kontonummer",
                            "Gegenkonto",
                            "BU-Schlüssel",
                            "Belegdatum",
                            "Belegfeld 1",
                            "Belegfeld 2",
                            "Buchungstext",
                            "Festschreibung"};
            therows.add(header);

            for(int column_pointer=0; column_pointer<daten_final.length; column_pointer++) {
                String[] row_aktuell = new String[daten_final.length];

                System.out.println("Spalte " + column_pointer + ":");

            for(int row_pointer=0; row_pointer<10; row_pointer++) {

                System.out.println("Schreibe: " + daten_final[column_pointer][row_pointer]);

                if (daten_final[column_pointer][row_pointer] == null) {
                    row_aktuell[row_pointer] = "";
                    } else {
                     row_aktuell[row_pointer] = daten_final[column_pointer][row_pointer];
                    }
                }

            if (row_aktuell[0] != "") {
                if (checkDate(row_aktuell[5], months.get(aktueller_monat))  == true) {
                    therows.add(row_aktuell);
                }
            } else {
                System.out.println("Kein Buchungswert bei Spalte: " + column_pointer);
                System.out.println("Buchung wird daher NICHT aufgenommen!");
                LogCoordinator.writeLog("FINALDATEN: Buchung aus Spalte " + (column_pointer+2) + " wird nicht aufgenommen!");
                // Es soll Originalspalte ausgegeben werden.
                // (column_pointer+2) wird genutzt, weil die jetztige Datei keinen Header mehr hat (+1) und bei 0 begonnen wird (+1)

                }
            }

            writer.writeAll(therows);

            LogCoordinator.writeLog("FINALDATEN: Kreation ASCII-Maske abgeschlossen!");

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            aktueller_monat++;
        }
    }
}



