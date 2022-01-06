package ecommerce.converter.transformtools;

public class ItemPositionCoordinator {

    public static String[][] storeRelevantPositions(String[][] daten_original, int columns, String[] relevanteItems) {

        // Positionen finden (Columns durchsuchen nach relevanten Items)
        System.out.println("++++++++ SUCHE POSITIONEN ITEMS ++++++++++");

        String[][] items_positions = new String[relevanteItems.length][2]; // Positionen der Daten so lange wie Anzahl relevante Daten (Reihen) und 2 Spalten

        for (int anzahl_relevante_items=0; anzahl_relevante_items<relevanteItems.length; anzahl_relevante_items++) {

            //https://stackoverflow.com/questions/23160832/how-to-find-index-of-string-array-in-java-from-a-given-value
            String carName = relevanteItems[anzahl_relevante_items];// insert code here
            int index = 0;
            for (int i = 0; i < columns; i++) {
                if (daten_original[0][i].equals(carName)) {
                    index = i;
                    items_positions[anzahl_relevante_items][0] = relevanteItems[anzahl_relevante_items];
                    items_positions[anzahl_relevante_items][1] = Integer.toString(i);
                    System.out.println("Position gefunden und gespeichert: " + items_positions[anzahl_relevante_items][0] +" / " + items_positions[anzahl_relevante_items][1]);
                    break;
                }
            }
        }

        System.out.println("++++++++ ENDE POSITIONEN ITEMS ++++++++++");

        return items_positions;
    }

    public static Integer findRelevantPosition(String[][] positionen, String relevantesItem, String[] relevanteItems) {

        int position_in_daten_roh = 0;

        System.out.println("Relevante position in Rohdatei gesucht");

        for (int positionszeiger=0; positionszeiger<relevanteItems.length; positionszeiger++) {
            //System.out.println("Durchlauf " + positionszeiger);
            if (positionen[positionszeiger][0] == relevantesItem) {

                System.out.println("Relevantes Item " + relevantesItem + " in Positionsliste bei Durchlauf " + positionszeiger + " gefunden.");
                position_in_daten_roh = Integer.parseInt(positionen[positionszeiger][1]);

                System.out.println("Relevantes Item " + relevantesItem + " hat in Originaldatei die Position " + positionen[positionszeiger][1] + ".");
                System.out.println("Relevantes Item " + relevantesItem + " hat in Originaldatei die Position " + position_in_daten_roh + ".");
                break;

            }
        }

        return position_in_daten_roh;
    }
}
