package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class MyDetailsView {
    TableView<Data> tableView = new TableView<>();
    private static MyDetailsView instance = null;

    static public MyDetailsView getInstance()
    {
        if (instance == null)
        {
            instance = new MyDetailsView();
        }
        return instance;
    }
    TableView<Data> getTable()
    {
        TableColumn fileNameCol = new TableColumn("File Name");
        fileNameCol.setMinWidth(220);
        fileNameCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        fileNameCol.setCellValueFactory(
                new PropertyValueFactory<Data, String>("fileName"));

        TableColumn lastModifiedCol = new TableColumn("Last Modified");
        lastModifiedCol.setMinWidth(100);
        lastModifiedCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        lastModifiedCol.setCellValueFactory(
                new PropertyValueFactory<Data, String>("lastModified"));

        TableColumn fileSizeCol = new TableColumn("Size");
        fileSizeCol.setMinWidth(100);
        fileSizeCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        fileSizeCol.setCellValueFactory(
                new PropertyValueFactory<Data, String>("fileSize"));
        fileSizeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn iconCol = new TableColumn("Icon");
        iconCol.setMinWidth(80);
        iconCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        iconCol.setCellValueFactory(
                new PropertyValueFactory<Data, ImageView>("image"));
        tableView.getColumns().addAll(iconCol, fileNameCol, fileSizeCol, lastModifiedCol);

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        String dir = tableView.getSelectionModel().getSelectedItem().fullPath;
                        setDirectory(dir);
                        File f = new FileCreator().getFile(dir);
                        FileFolderFactory factory = new FileFolderFactory();
                        Data data = factory.getFileorFolder(f);
                        if (data instanceof FolderData) {
                            String item = f.getAbsolutePath();
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    ((FolderData) data).makeList();
                                    tableView.setItems(((FolderData) data).getFileLists());
                                    Main.tf.setText(Main.dir);
                                }});
                        }
                    }catch (Exception e)
                    {

                    }
                }
            }
        });
        return tableView;
    }

    public void setDirectory(String path)
    {
        Main.dir = path;
    }
}
