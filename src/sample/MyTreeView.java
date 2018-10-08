package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by Kaushik on 4/28/2017.
 */
public class MyTreeView {

    TreeItem<String> root = new TreeItem<>("computer");
    TreeView<String> tree;
    TreeView<String> getTreeView()
    {
        tree = new TreeView<>(root);
        Iterable<Path> rootDirectories= FileSystems.getDefault().getRootDirectories();
        for(Path name:rootDirectories){
            File file = new FileCreator().getFile(String.valueOf(name));
            MyTreeItem node = new MyTreeItem(file);
            root.getChildren().add(node);
        }


        tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                MyTreeItem selectedItem = (MyTreeItem) newValue;
                String path = ((MyTreeItem) newValue).fullPath;
                Main.dir = path;
                Main.setDirectory();
                File f = new FileCreator().getFile(path);
                if (f.isDirectory()) {
                    File[] files = f.listFiles();
                    for (File file : files) {
                        MyTreeItem node = new MyTreeItem(file);
                        selectedItem.getChildren().add(node);
                    }
                }
                else
                {

                }
            }
        });
        return tree;
    }
}
