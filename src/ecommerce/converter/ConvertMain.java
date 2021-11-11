package ecommerce.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConvertMain {

    public static void start (String platform, String operation, File filepath) throws IOException {
        System.out.println("Dateipfad: [" + filepath.getAbsolutePath() + "]");
        System.out.println("Plattform: [" + platform + "]");
        System.out.println("Operation: [" + operation + "]");

        DataRecorder.loadData(platform, filepath);
        DataParser.parseData(filepath);




        System.out.println("Konvertierung abgeschlossen!");


    }

}
