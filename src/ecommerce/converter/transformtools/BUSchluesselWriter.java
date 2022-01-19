package ecommerce.converter.transformtools;

public class BUSchluesselWriter {

    private static String[] BU_9 = { // Relevante Items zur Berechnung von Buchungsschlüssel 9
            "19" // 0
            };

    private static String[] BU_5 = { // Relevante Items zur Berechnung von Buchungsschlüssel 5 // Corona-Spezial
            "16" // 0
    };

        public static String getBUSchluessel(String bu_information) {

            String BUSchluessel = "";

            for(int i = 0; i < BU_9.length; i++)
            {
                if (bu_information.equals(BU_9[i])) {

                    BUSchluessel = "9";

                }
            }

            for(int i = 0; i < BU_5.length; i++)
            {
                if (bu_information.equals(BU_5[i])) {

                    BUSchluessel = "5"; // Corona-Maßnahme (weniger Mehrwertsteuer)

                }
            }

            return BUSchluessel;
        }
}
