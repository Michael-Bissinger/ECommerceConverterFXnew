package ecommerce.converter.generaltools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogCoordinator {

    //File LOGFILE = System.getProperty("user.dir")+"out/production/Design/logger/Log.log";
    //public static final Path LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Log.log");
    public static final String LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"/out/production/Design/logger/Log.log").toString();
    //public static final String LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"/ressources/logger/Log.log").toString();

    //public static File LOGFILE = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Logfile.log");

    public static void writeLog(String message, File filepath_origin) {


        //String LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Log.log").toString();

        //https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        System.out.println("Aktuelel: " + System.getProperty("user.dir"));


        try {

            // This block configure the logger with handler and formatter
            //fh = new FileHandler("C:/temp/test/MyLogFile.log");
            //fh = new FileHandler(filepath_origin.getParent() + "\\" + "Log.log");
            fh = new FileHandler(LOGFILE_PATH);


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
