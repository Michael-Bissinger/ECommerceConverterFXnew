package ecommerce.converter.loadertools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoaderDate {

    private static String DATEV_DATUM = "ddMM";

    private static String DATUM_FORMAT_CONRAD = "dd.MM.yy - HH:mm:ss"; // Datumsformat von "Conrad" (01.08.19 - 15:20:53)
    private static String DATUM_FORMAT_REAL = "yyyy-MM-dd HH:mm:ss"; // Datumsformat von "Real" (2019-12-01 00:23:11)

    public static List<Integer> collectMonths(String[][] daten_final) {

        List<Integer> months = new ArrayList<Integer>();

        for (int rowpointer = 0; rowpointer < daten_final.length; rowpointer++) {
            //System.out.println("Stelle: " + rowpointer + ": " + daten_final[rowpointer][5]);

            Date date_aktuell = null;
            try {
                date_aktuell = new SimpleDateFormat(DATEV_DATUM).parse(daten_final[rowpointer][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!months.contains(date_aktuell.getMonth()+1)) {

                months.add(date_aktuell.getMonth()+1);

            }

            System.out.println(date_aktuell.getMonth()+1);

        }

        System.out.println("Finale Liste (Monate): " + months);

        return months;
    }

    public static List<Integer> collectYears(String[][] daten_final) {

        List<Integer> years = new ArrayList<Integer>();

        for (int rowpointer = 0; rowpointer < daten_final.length; rowpointer++) {
            //System.out.println("Stelle: " + rowpointer + ": " + daten_final[rowpointer][5]);

            Date date_aktuell = null;
            try {
                date_aktuell = new SimpleDateFormat("ddMM").parse(daten_final[rowpointer][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!years.contains(date_aktuell.getYear())) {

                years.add(date_aktuell.getYear()+1);

            }

            System.out.println(date_aktuell.getMonth()+1);

        }

        System.out.println("Finale Liste (Jahre): " + years);

        return years;
    }


    public static boolean checkDate(String datum, Integer currentmonth) {

        boolean match = false;

        Date date_aktuell = null;
        try {
            date_aktuell = new SimpleDateFormat("ddMM").parse(datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date_aktuell.getMonth()+1 == currentmonth) {
            match = true;
        }

        return match;
    }

}
