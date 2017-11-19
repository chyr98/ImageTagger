package GUI.Scenes;

import TaggerSystem.Folder;
import TaggerSystem.SystemMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveFileController implements Initializable{

    @FXML
    private TableView<Folder> tableOfFolder;

    @FXML
    private TableColumn<Folder, String> listOfFolder;


    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void goParent(ActionEvent event) {

    }

    @FXML
    void move(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Folder selectedFolder = SystemMain.fileManager.getFolder();
        listOfFolder.setCellValueFactory(new PropertyValueFactory<Folder, String>("name"));

        //Set a listener to open the folder when it is selected.
        tableOfFolder.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection) -> {
                            ObservableList<Folder> folders= FXCollections.observableArrayList();
                            folders.addAll(newSelection.getChildren());
                            tableOfFolder.setItems(folders);
                        });

        ObservableList<Folder> folders= FXCollections.observableArrayList();
        folders.addAll(selectedFolder.getChildren());
        tableOfFolder.setItems(folders);
    }
}
