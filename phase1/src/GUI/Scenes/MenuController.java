package GUI.Scenes;

import GUI.GUIMain;
import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    @FXML
    private TableView<Folder> tableOfFolders;

    @FXML
    private TableColumn<Folder, String> folderNames;

    @FXML
    private TableView<ImageFile> FileTable;

    @FXML
    private TableColumn<ImageFile, String> NameColomn;

    @FXML
    private ImageView imgDisplay;

    private static Folder currentFolder = SystemMain.fileManager.getFolder();

    public void initData(){

        FileTable.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection) -> {
                            File image = new File(newSelection.getPath());
                            Image selectedImage = null;
                            try {
                                String url = image.toURI().toURL().toString();
                                selectedImage = new Image(url,287,213,false,true);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            imgDisplay.setImage(selectedImage);
                        });
        tableOfFolders.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection) -> {
                            currentFolder=newSelection;
                            refresh();
                        });
    }

    @FXML
    private void openTagMenu() throws IOException {
        if(FileTable.getSelectionModel().getSelectedItem()!=null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
            Parent tagMenuParent = loader.load();

            TagMenuController controller = loader.getController();
            controller.initData(FileTable.getSelectionModel().getSelectedItem());

            GUIMain.showScene(new Scene(tagMenuParent));
        }
    }

    @FXML
    private void openNameHistory() throws IOException {
        if(FileTable.getSelectionModel().getSelectedItem()!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIMain.class.getResource("Scenes/NameHistoryScene.fxml"));
            Parent historyMenuParent = loader.load();

            NameHistoryController controller = loader.getController();
            controller.initData(FileTable.getSelectionModel().getSelectedItem());

            GUIMain.showScene(new Scene(historyMenuParent));
        }
    }

    @FXML
    private void openMoveToStage() throws IOException {
        if(FileTable.getSelectionModel().getSelectedItem()!=null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIMain.class.getResource("Scenes/MoveFileScene.fxml"));
            GUIMain.showStage(loader.load(), "Moving To..");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    private void refresh(){
        NameColomn.setCellValueFactory(new PropertyValueFactory<ImageFile,String>("currName"));
        folderNames.setCellValueFactory(new PropertyValueFactory<Folder, String>("name"));

        if (!currentFolder.getChildren().isEmpty()) {
            ObservableList<Folder> folders = FXCollections.observableArrayList();
            folders.addAll(currentFolder.getChildren());
            tableOfFolders.setItems(folders);
        }
        ObservableList<ImageFile> files = FXCollections.observableArrayList();
        files.addAll(currentFolder.getValue());
        FileTable.setItems(files);

    }

    private ObservableList<ImageFile> getDummy() throws IOException {
        ObservableList<ImageFile> files = FXCollections.observableArrayList();
        ImageFile image1 = new ImageFile("img001");
        image1.AddTag(new Tag("tag1"));
        files.add(image1);
        files.add(new ImageFile("img002"));
        files.add(new ImageFile("img003"));
        return files;
    }
}
