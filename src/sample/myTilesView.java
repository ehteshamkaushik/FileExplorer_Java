package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.File;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class myTilesView {
    ListView<Data> listView = new ListView<>();
    private static myTilesView instance = null;
    static public myTilesView getInstance()
    {
        if (instance == null)
        {
            instance = new myTilesView();
        }
        return instance;
    }
    ListView<Data> getTilesView()
    {
        listView.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            @Override
            public ListCell<Data> call(ListView<Data> list) {
                return new myTilesCell();
            }
        });
        listView.setOrientation(Orientation.HORIZONTAL);

        listView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        String dir = listView.getSelectionModel().getSelectedItem().fullPath;
                        File f = new FileCreator().getFile(dir);
                        FileFolderFactory factory = new FileFolderFactory();
                        Data data = factory.getFileorFolder(f);
                        if (data instanceof FolderData) {
                            String item = f.getAbsolutePath();
                            setDirectory(item);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    ((FolderData) data).makeList();
                                    listView.setItems(((FolderData) data).getFileLists());
                                    Main.tf.setText(Main.dir);
                                }});
                        }
                    }catch (Exception e)
                    {

                    }
                }
            }
        });
        return listView;
    }

    public void setDirectory(String path)
    {
        Main.dir = path;
    }
}
