package ecommerce.converter.transformtools;


public class FixationCoordinator {

    public static String[][] writeFixation(String[][] daten){


        for (int row_pointer = 0; row_pointer < daten.length; row_pointer++) {

            daten[row_pointer][9] = "0"; // Festschreibung ist an 9. Position, also hier einfügen


        }




        return daten;



    }
}
