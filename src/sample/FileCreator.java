package sample;

import java.io.File;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class FileCreator {
    File getFile(String path)
    {
        File file = new File(path);
        return file;
    }
}
