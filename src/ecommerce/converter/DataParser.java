package ecommerce.converter;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataParser {

    public static void parseData(File filepath_origin) {




        System.out.println("Data is converted!");

        // TODO: Neues CSV schreiben
        // https://www.youtube.com/watch?v=sgGGjisdNPA
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(filepath_origin.getAbsolutePath()))) {
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
