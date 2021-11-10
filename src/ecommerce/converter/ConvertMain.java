package ecommerce.converter;

public class ConvertMain {

    public static void start (String platform, String operation, String filepath) {

        System.out.println("Plattform: " + platform);

        DataRecorder.loadData(platform, filepath);
        DataParser.parseData();




        System.out.println("Konvertierung abgeschlossen!");


    }

}
