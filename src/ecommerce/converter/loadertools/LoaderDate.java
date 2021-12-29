package ecommerce.converter.loadertools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoaderDate {

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

    public static List<Integer> collectMonths(String[][] daten_final) {

        List<Integer> months = new ArrayList<Integer>();

        for (int rowpointer = 0; rowpointer < daten_final.length; rowpointer++) {
            //System.out.println("Stelle: " + rowpointer + ": " + daten_final[rowpointer][5]);

            Date date_aktuell = null;
            try {
                date_aktuell = new SimpleDateFormat("ddMM").parse(daten_final[rowpointer][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!months.contains(date_aktuell.getMonth()+1)) {

                months.add(date_aktuell.getMonth()+1);

            }

            System.out.println(date_aktuell.getMonth()+1);

        }

        System.out.println("Finale Liste: " + months);

        return months;
    }


}
