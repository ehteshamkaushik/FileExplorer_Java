package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class FolderData extends Data {
    ObservableList<Data> fileLists = FXCollections.observableArrayList();
    boolean type;
    FolderData(File file)
    {
        super(file, new ImageView("foldericon.png"));
        this.imageView = new ImageView("foldericon2.png");
        this.type = true;

    }
    void makeList()
    {
        File file = new FileCreator().getFile(fullPath);
        File[] files = file.listFiles();
        for (File f : files) {
            FileFolderFactory factory = new FileFolderFactory();
            Data obj = factory.getFileorFolder(f);
            fileLists.add(obj);
        }
    }
    ObservableList<Data> getFileLists()
    {

        return fileLists;
    }
}
