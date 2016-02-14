package jamp.file.reader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class CustomFileReader {

    private static Logger logger = Logger.getLogger(CustomFileReader.class);

    private final File file;
    private final Reader reader;

    public CustomFileReader(File file, Reader reader) {
        this.file = file;
        this.reader = reader;
    }

    public void performFileReading() {
        List<String> result = reader.read(file);

        logger.log(Level.INFO, "Result size - " + result.size());
    }
}
