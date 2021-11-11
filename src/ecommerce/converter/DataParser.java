package ecommerce.converter;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public static String MANDANTENUMBER = "10754"; // Internal number in DATEV, with GmbH & Co KG change to "10755"

    public static void parseData(File filepath_origin) {




        System.out.println("Data is converted!");

        // TODO: Neues CSV (DATEV-Format) schreiben
        // https://www.youtube.com/watch?v=sgGGjisdNPA




        try (CSVWriter writer = new CSVWriter(new FileWriter("DATEVformat_test.csv"))) {
            List<String[]> therows = new ArrayList<>();

            //Erste Zeile
            //DTVF	700	21	Buchungsstapel	12	2,02111E+16	2,02111E+16	RE	1000188U00027	1000188U00027	467742	11600	20210101	4	20210501	20210531	Visa 05	LK	1	0	0	EUR		KP		117301	3
            String[] header = new String[]{"DTVF",
                    "700",
                    "21",
                    "Buchungsstapel",
                    "12",
                    "20211111143006400",
                    "20211111143006400",
                    "RE",
                    "1000188U00027",
                    "1000188U00027",
                    "467742",
                    MANDANTENUMBER, // Mandantennummer
                    "20210101", // Startdatum? TODO: Herausfinden
                    "4",
                    "20210501", // Aktueller Monat? TODO: Herausfinden
                    "20210531", // Ende des Monats? TODO: Herausfinden
                    "Teststapel", // Name Buchungsstapel
                    "LK",
                    "1",
                    "0",
                    "0",
                    "",
                    "KP",
                    "",
                    "117301",
                    "3"


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
