package sample;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class FileFolderFactory {
    public Data getFileorFolder(File file)
    {
        if (file.isDirectory()) {
            return new FolderData(file);
        }
        else
        {
            return new FileData(file);
        }
    }
}
