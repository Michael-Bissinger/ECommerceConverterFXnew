package ecommerce.converter.loadertools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoaderDate {

    private static String DATEV_DATUM = "ddMM";

    public static List<Integer> collectMonths(String[][] daten_final) {

        List<Integer> months = new ArrayList();
        // Hier werden alle vorhandenen Monate reingeschrieben

        for (int rowpointer = 0; rowpointer < daten_final.length; rowpointer++) {

            Date date_aktuell = null;
            try {
                date_aktuell = new SimpleDateFormat(DATEV_DATUM).parse(daten_final[rowpointer][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!months.contains(date_aktuell.getMonth()+1)) {
                // Test, ob Monat bereits vorhanden ist
                months.add(date_aktuell.getMonth()+1);
            }
        }

        System.out.println("Finale Liste (Monate): " + months);

        return months;
    }

    public static boolean checkDate(String datum, Integer currentmonth) {

        boolean match = false;
        // Hier wird gespeichert, ob Datum zu aktuellem Monat passt

        Date date_aktuell = null;
        try {
            date_aktuell = new SimpleDateFormat("ddMM").parse(datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date_aktuell.getMonth()+1 == currentmonth) {
            // Test, ob Datum zu aktuellem Monat passt
            match = true;
        }

        return match;
    }
}