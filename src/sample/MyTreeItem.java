package sample;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Kaushik on 4/18/2017.
 */
public class MyTreeItem extends TreeItem<String> {
    protected String fullPath;
    protected boolean isDirectory;

    public String getFullPath() {
        return (this.fullPath);
    }
    public boolean isDirectory() {
        return (this.isDirectory);
    }
    public MyTreeItem(File file) {
        super(file.getName());
        this.fullPath = file.getAbsolutePath();

        if (!fullPath.endsWith(File.separator)) {
            //set the value (which is what is displayed in the tree)
            String value = fullPath;
            int indexOf = value.lastIndexOf(File.separator);
            if (indexOf > 0) {
                this.setValue(value.substring(indexOf + 1));
            } else {
                this.setValue(value);
            }
        }else {
            this.setValue(file.getAbsolutePath());
        }
        if (file.isDirectory())
        {
            ImageView imageView = new ImageView("foldericon.png");
            this.setGraphic(imageView);
        }
        else
        {
            ImageView imageView = new ImageView("fileicon.png");
            this.setGraphic(imageView);
        }
    }
}
