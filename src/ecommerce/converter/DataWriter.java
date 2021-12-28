package ecommerce.converter;

import com.opencsv.CSVWriter;
import ecommerce.converter.generaltools.LogCoordinator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    // Interne Nummer in DATEV, wird bei GmbH & Co KG zu "10755"
    // Als String deklariert, damit sich die Nummer in .csv schreiben lässt
    public static String MANDANTENUMMER = "10754";

    public static void writeData (File filepath_origin, String platform, String finalformat, String[][] daten_final) {

        System.out.println("Daten werden zu Format " + finalformat + " konvertiert!");

        switch (finalformat) {
            case "Maske (ASCII)" -> createMaskASCII(filepath_origin, daten_final);
            case "DATEV-Format (ASCII)" -> createDATEVFormat(platform, filepath_origin, daten_final);

            default -> System.out.println("FEHLER: Endformat kann nicht erstellt werden!");
        }

    }

    private static void createMaskASCII (File filepath_origin, String[][] daten_final) {

        LogCoordinator.writeLog("FINALDATEN: Kreiere ASCII-Maske!");

        System.out.println("Pfad: " + filepath_origin.getParent());

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(filepath_origin.getParent() + "\\" + "Mask_ASCII_test.csv"),';','"', '\\',CSVWriter.DEFAULT_LINE_END);
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
            //LogCoordinator.writeLog("FINALDATEN: Teste Spalte: " + column_pointer);

        for(int row_pointer=0; row_pointer<10; row_pointer++) {

            System.out.println("Schreibe: " + daten_final[column_pointer][row_pointer]);

            if (daten_final[column_pointer][row_pointer] == null) {
                row_aktuell[row_pointer] = "";

                } else {
                 row_aktuell[row_pointer] = daten_final[column_pointer][row_pointer];
                }

            }

            //System.out.println("Anzeige: " + row_aktuell[0]);
            if (row_aktuell[0] != "") {
                therows.add(row_aktuell);

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
    }

    private static void createDATEVFormat(String platform, File filepath_origin, String[][] daten_final) {

            // TODO: Neues CSV (DATEV-Format) schreiben
            // https://www.youtube.com/watch?v=sgGGjisdNPA


            System.out.println("DATEV-Format wird erstellt!");
            // TODO: Paragraph-Symbol rausnehmen https://stackoverflow.com/questions/6841622/writeallresultset-res-boolean-b-method-of-opencsv-adds-double-quotes-around-da/29362439#29362439
            try (CSVWriter writer = new CSVWriter(new FileWriter("DATEVformat_test.csv"),';','"','\\',CSVWriter.DEFAULT_LINE_END))
            {
                List<String[]> therows = new ArrayList<>();

                //Erste Zeile
                //DTVF	700	21	Buchungsstapel	12	2,02111E+16	2,02111E+16	RE	1000188U00027	1000188U00027	467742	11600	20210101	4	20210501	20210531	Visa 05	LK	1	0	0	EUR		KP		117301	3
                String[] header = new String[]{
                        "\""+"DTVF"+"\"",
                        "700",
                        "21",
                        "\""+"Buchungsstapel"+"\"",
                        "12",
                        "20211111143006400"+"\"",
                        "20211111143006400"+"\"",
                        "\""+"RE",
                        "\""+"1000188U00027"+"\"",
                        "\""+"1000188U00027"+"\"",
                        "467742",
                        MANDANTENUMMER, // Mandantennummer
                        "20210101", // Startdatum? TODO: Herausfinden
                        "4",
                        "20210501", // Aktueller Monat? TODO: Herausfinden
                        "20210531", // Ende des Monats? TODO: Herausfinden
                        "\""+platform + "_Stapel"+"\"", // Name Buchungsstapel TODO: Noch den jeweiligen Monat hinzufügen
                        "\""+"LK"+"\"",
                        "1",
                        "0",
                        "0",
                        "\""+"EUR"+"\"",
                        "",
                        "\""+"KP"+"\"",
                        "",
                        "117301",
                        "\""+"3"+"\"",
                        "",
                        "",
                        "\""+""+"\"",
                        "\""+""+"\""


                }; // Oberste Zeile muss alle nötigen Felder haben (Video 7:50)
                therows.add(header);

                //Zweite Zeile (eigentlich erste Zeile
                //booking_date;booking_text;order_date;order_number;amount;balance;price_gross;shipping_charges_gross;sum_price_gross;fee_%;fee_net;fee_vat_%;fee_gross;payout;ean;id_item;title_item;offer_id;unit_condition;delivery_time_min;delivery_time_max;note;unit_sent_timestamp;delivery_time_expires;buyer.id_buyer;buyer.email;pseudonym;shipping.first_name;shipping.last_name;shipping.gender;shipping.company_name;shipping.street;shipping.house_number;shipping.additional_field;shipping.postcode;shipping.city;shipping.country;shipping.phone;billing.first_name;billing.last_name;billing.gender;billing.company_name;billing.street;billing.house_number;billing.additional_field;billing.postcode;billing.city;billing.country;billing.phone

                String[] row1 = new String[]{"iyyxcd", "xcxc", "crew", "launchdate"}; // Muss alle nötigen Felder haben (Video 7:50)
                therows.add(row1);

                String[] row2 = new String[]{"1", "bla", "12", "2121212"};
                therows.add(row2);

                writer.writeAll(therows);



            } catch (IOException e) {
                e.printStackTrace();
            }




            //try (CSVWriter writer = new CSVWriter(new FileWriter(filepath_origin.getAbsolutePath()))) {
            //} catch (IOException e) {
            //   e.printStackTrace();
            //}


        }


    }



