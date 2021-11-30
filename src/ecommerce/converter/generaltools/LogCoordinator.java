package ecommerce.converter.generaltools;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogCoordinator {

    public static void writeLog(String message, File filepath_origin) {


        //https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        System.out.println("Aktuelel: " + System.getProperty("user.dir"));

        try {

            // This block configure the logger with handler and formatter
            //fh = new FileHandler("C:/temp/test/MyLogFile.log");
            fh = new FileHandler(filepath_origin.getParent() + "\\" + "Log.log");

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Hi How r u?");



    }

}
