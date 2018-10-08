package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class Main extends Application {
    public static String dir;
    public static Text tf;
    public static ListView<Data> tilesView;
    public static TableView<Data> detailsView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        dir = System.getProperty("user.dir");
        File file = new FileCreator().getFile(dir);
        FolderData folderData = new FolderData(file);
        folderData.makeList();

        SplitPane mainView = new SplitPane();
        mainView.setOrientation(Orientation.VERTICAL);
        mainView.setDividerPositions(0.2, 0.8);

        SplitPane root2 = new SplitPane();
        root2.setDividerPositions(0.3, 0.7);

        GridPane gridPane = new GridPane();
        Button listbutton = new Button("List View");
        Button Tilesbutton = new Button("Tiles View");
        Button backbutton = new Button("Back");
        Text text = new Text("Current Directory");
        tf = new Text();
        gridPane.add(listbutton,0, 0);
        gridPane.add(Tilesbutton, 1, 0);
        gridPane.add(text, 2,0);
        gridPane.add(tf, 3, 0, 10, 1);
        gridPane.add(backbutton, 0, 1);

        MyDetailsView myDetailsView = MyDetailsView.getInstance();
        detailsView = myDetailsView.getTable();
        detailsView.setItems(folderData.getFileLists());

        myTilesView myTilesView = sample.myTilesView.getInstance();
        tilesView = myTilesView.getTilesView();
        tilesView.setItems(folderData.getFileLists());

        TreeView<String> tree = new MyTreeView().getTreeView();



        StackPane treeView = new StackPane();
        StackPane listTileview = new StackPane();

        treeView.getChildren().add(tree);
        listTileview.getChildren().add(detailsView);

        root2.getItems().addAll(treeView, listTileview);
        mainView.getItems().addAll(gridPane, root2);
        tf.setText(dir);

        listbutton.setOnAction(event -> {
            detailsView.setItems(setData());
            listTileview.getChildren().remove(tilesView);
            listTileview.getChildren().add(detailsView);
        });
        Tilesbutton.setOnAction(event -> {
            tilesView.setItems(setData());
            listTileview.getChildren().remove(detailsView);
            listTileview.getChildren().add(tilesView);
        });
        backbutton.setOnAction(event -> {
                File file1 = new FileCreator().getFile(dir);
                if (file1.getParent() != null) {
                String p = file1.getParent();
                dir = p;
                tf.setText(dir);
                try {
                    ObservableList<Data> d = setData();
                    tilesView.setItems(d);
                    detailsView.setItems(d);
                } catch (Exception e) {
                    System.out.println("something wrong");
                }
            }
        });

        primaryStage.setTitle("File Explorer");
        Scene scene = new Scene(mainView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static ObservableList<Data> setData()
    {
        ObservableList<Data> files = FXCollections.observableArrayList();
        File f = new FileCreator().getFile(dir);
        FileFolderFactory factory = new FileFolderFactory();
        Data data = factory.getFileorFolder(f);
        if (data instanceof FolderData) {
            ((FolderData) data).makeList();
            files = ((FolderData) data).getFileLists();
        }
        return files;
    }

    public static void setDirectory()
    {
        ObservableList<Data> d = setData();
        detailsView.setItems(d);
        tilesView.setItems(d);
        tf.setText(dir);
    }
}
