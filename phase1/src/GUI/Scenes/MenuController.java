package GUI.Scenes;

import GUI.GUIMain;
import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
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

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    @FXML
    private TableView<ImageFile> FileTable;

    @FXML
    private TableColumn<ImageFile, String> NameColomn;

    @FXML
    private ImageView imgDisplay;

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
            GUIMain.showStage("Scenes/MoveFileScene.fxml", "Moving To..");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameColomn.setCellValueFactory(new PropertyValueFactory<ImageFile,String>("name"));
    // imgDisplay.getImage(new Image());

    FileTable.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> {
              if (newSelection instanceof ImageFile){
                  //Todo: show the image view of the selected image
              }
//              if (newSelection instanceof Folder){
//                  //Todo: open the folder and let the table view shows the items in the folder.
//              }
            });

        try {
            FileTable.setItems(getDummy());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
