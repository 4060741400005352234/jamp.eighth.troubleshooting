package jamp.file.reader;

import java.io.File;
import java.util.List;

public interface Reader {

    public List<String> read(File file);
}
