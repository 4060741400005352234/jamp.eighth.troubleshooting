package jamp.file.reader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThreeCharsLineReader implements Reader {

    private static Logger logger = Logger.getLogger(ThreeCharsLineReader.class);

    @Override
    public List<String> read(File file) {
        List<String> result = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line.substring(0, 3));
                Thread.sleep(50);
            }
            return result;
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.log(Level.ERROR, e);
                }
            }
        }
        return null;
    }
}
