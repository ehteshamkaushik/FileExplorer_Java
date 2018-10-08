package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class Data extends TreeItem<String> {
    String fileName;
    String lastModified;
    String fileSize;
    ImageView image;
    ImageView imageView;
    String fullPath;
    Data(File file, ImageView image)
    {
        this.fileName = file.getName();
        this.lastModified = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(
                new Date(file.lastModified()));
        this.fileSize = String.valueOf(file.length());
        this.image = image;
        this.fullPath = file.getAbsolutePath();
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getLastModified()
    {
        return lastModified;
    }
    public void setLastModified(String lastModified)
    {
        this.lastModified = lastModified;
    }
    public String getFileSize()
    {
        return fileSize;
    }
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }
    public ImageView getImage()
    {
        return image;
    }
    public void setImage(ImageView image)
    {
        this.image = image;
    }
}
