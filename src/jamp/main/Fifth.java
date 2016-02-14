package jamp.main;

import jamp.file.reader.CustomFileReader;
import jamp.file.reader.ThreeCharsLineReader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

public class Fifth {

    private static Logger logger = Logger.getLogger(Fifth.class);

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Start.");

        CustomFileReader reader = new CustomFileReader(
                new File("D:\\Java\\JAMP\\8_troubleshooting\\jamp.eighth.troubleshooting\\5_data\\data.txt"),
                new ThreeCharsLineReader());

        reader.performFileReading();

        logger.log(Level.INFO, "Finish.");
    }
}
