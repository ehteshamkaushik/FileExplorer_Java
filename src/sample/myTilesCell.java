package sample;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class myTilesCell extends ListCell<Data> {
    public void updateItem(Data item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            try {
                setText(item.fileName);
                setContentDisplay(ContentDisplay.TOP);
                setPrefSize(100, 100);
                setGraphic(item.imageView);
            }
            catch (Exception e)
            {
                System.out.println("Image cantbe opened");
            }

        }
    }
}
