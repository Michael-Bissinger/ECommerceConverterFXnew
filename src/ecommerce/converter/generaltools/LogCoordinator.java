package ecommerce.converter.generaltools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import java.awt.Desktop;

import org.apache.commons.io.FileUtils;

public class LogCoordinator {

    public static final String LOGFILE_PATH = Path.of(System.getProperty("user.dir") + "/out/production/Design/logger/Log.log").toString();
    public static final File LOGFILE = new File(LOGFILE_PATH);

    public static boolean ACTIVITY_LOG = true; // Hier kann Log ein- oder ausgeschaltet werden

    public static void writeLog(String message) {


        if (ACTIVITY_LOG == true) {


            //https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger

            Logger logger = Logger.getLogger("MyLog");
            logger.setUseParentHandlers(false);

            FileHandler fh;

            try {

                fh = new FileHandler(LOGFILE_PATH);

                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

                logger.info(message);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    else {
        System.out.println("Kann Log nicht schreiben, da Log nicht aktiv");
    }

    }

    public static void openLog() {

        //https://www.javatpoint.com/how-to-open-a-file-in-java

        if (ACTIVITY_LOG == true) {

            try {

                System.out.println("Öffne Log-File");


                if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                {
                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (LOGFILE.exists())         //checks file exists or not
                    desktop.open(LOGFILE);              //opens the specified file
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("Log-File nicht aktiv");

        }

    }

    public static void deleteLog() {

        if (ACTIVITY_LOG == true) {

            File deletefile = LOGFILE;

            try {
                FileUtils.cleanDirectory(deletefile.getParentFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}