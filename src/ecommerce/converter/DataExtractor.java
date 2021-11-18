package ecommerce.converter;

import java.io.File;
import java.io.IOException;

public class DataExtractor {

    public static void extractData(String operation) {


    }



    static void copyFile(String filepath_origin) {

        // TODO: Original file is copyed to workspace-folder
        System.out.println("Copy original file to workspace!");

        //https://stackoverflow.com/questions/16433915/how-to-copy-file-from-one-location-to-another-location
        //https://www.youtube.com/watch?v=sgGGjisdNPA


        File f_origin = new File(filepath_origin);
        // File f_new = new File("\\out\\ressources\\test.csv");
        //File f_new = new File("\\D:\\Users\\Michael\\Desktop\\Studium\\Studium Wirtschaftsinformatik\\Semester\\Masterarbeit\\Arbeit\\Design\\E-CommerceConverter\\out\\ressources\\test.csv");


        //Files.copy(f_origin, f_new, StandardCopyOption.REPLACE_EXISTING);


        //Files.copy( f_origin.toPath(), f_new.toPath() );



        // TODO: Rename new file to orginal
    }


}
