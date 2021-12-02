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

    //File LOGFILE = System.getProperty("user.dir")+"out/production/Design/logger/Log.log";
    //public static final Path LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Log.log");
    public static final String LOGFILE_PATH = Path.of(System.getProperty("user.dir") + "/out/production/Design/logger/Log.log").toString();
    //public static final String LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"/ressources/logger/Log.log").toString();

    //public static File LOGFILE = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Logfile.log");

    public static final File LOGFILE = new File(LOGFILE_PATH);

    private static boolean ACTIVITY_LOG = false; // Hier kann Log ein- oder ausgeschaltet werden

    public static void writeLog(String message) {


        if (ACTIVITY_LOG == true) {


            //String LOGFILE_PATH = Path.of(System.getProperty("user.dir")+"out/production/Design/logger/Log.log").toString();

            //https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger

            Logger logger = Logger.getLogger("MyLog");
            logger.setUseParentHandlers(false);

            FileHandler fh;


            //System.out.println("Aktuelel: " + System.getProperty("user.dir"));


            try {

                // This block configure the logger with handler and formatter
                //fh = new FileHandler("C:/temp/test/MyLogFile.log");
                //fh = new FileHandler(filepath_origin.getParent() + "\\" + "Log.log");
                fh = new FileHandler(LOGFILE_PATH);


                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);

                // the following statement is used to log any messages
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

        try {

            System.out.println("Öffne Log-File");

            //public static final File = new File(LOGFILE_PATH)

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


    }

    public static void deleteLog() {

        //https://stackoverflow.com/questions/13195797/delete-all-files-in-directory-but-not-directory-one-liner-solution

        File deletefile = LOGFILE;

        // https://www.baeldung.com/java-delete-directory
        // https://stackoverflow.com/questions/13195797/delete-all-files-in-directory-but-not-directory-one-liner-solution
        try {
            FileUtils.cleanDirectory(deletefile.getParentFile());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    }








