package sample;

import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class FileData extends Data{
    boolean type;
    FileData(File file)
    {
        super(file, new ImageView("fileicon.png"));
        this.imageView = new ImageView("fileicon2.png");
        this.type = false;
    }
}
