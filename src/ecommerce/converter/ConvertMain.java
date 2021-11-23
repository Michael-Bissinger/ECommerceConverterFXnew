package ecommerce.converter;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConvertMain {

    public static void start (String platform, String operation, File filepath, String finalformat) throws IOException, CsvValidationException {
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");
        System.out.println("Endformat: [" + finalformat + "]");


        String [] [] daten = DataRecorder.loadData(platform, filepath);
        DataExtractor.extractData(platform, operation, daten);
        DataParser.parseData(filepath, platform, finalformat);




        System.out.println("Konvertierung abgeschlossen!");


    }

}
