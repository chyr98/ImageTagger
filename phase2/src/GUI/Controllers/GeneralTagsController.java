package GUI.Controllers;

import GUI.GUIMain;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GeneralTagsController implements Initializable, RefreshableController {

    @FXML
    private TableView<Tag> allTagTable;

    @FXML
    private TableColumn<Tag, String> allTags;

    @FXML
    private TextField inputField;


    /**
     * Catch the event when Add Tag button is clicked.
     * */
    @FXML
    void addTag() {
        if (!inputField.getText().isEmpty()) {
            SystemMain.tagManager.addTag(new Tag(inputField.getText()));
            inputField.setText("");
            refresh();
        }
    }

    @FXML
    void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
    }

    @FXML
    void deleteTag() throws IOException {
        if (allTagTable.getSelectionModel().getSelectedItem()!=null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIMain.class.getResource("Scenes/DeleteConfirmStage.fxml"));
            Parent deleteConfirmationScene = loader.load();

            DeleteConfirmController controller = loader.getController();
            controller.initData(allTagTable.getSelectionModel().getSelectedItem(),
                    GUIMain.showStage(new Scene(deleteConfirmationScene), "Confirmation"), this);
        }
    }

    @FXML
    void viewFiles() throws IOException {
        if (allTagTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIMain.class.getResource("Scenes/AllFilesWithTheTagScene.fxml"));
            Parent scene = loader.load();

            FilesWithTheTagController controller = loader.getController();
            controller.initData(allTagTable.getSelectionModel().getSelectedItem(),
                    GUIMain.showStage(new Scene(scene),
                            allTagTable.getSelectionModel().getSelectedItem().getName()), this);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allTags.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));

        ObservableList<Tag> tags = FXCollections.observableArrayList();
        if (SystemMain.tagManager != null) {
            tags.addAll(SystemMain.tagManager.getTagList());
        }
        allTagTable.setItems(tags);
    }

    @Override
    public void refresh() {
        ObservableList<Tag> tags = FXCollections.observableArrayList();
        tags.addAll(SystemMain.tagManager.getTagList());
        allTagTable.setItems(tags);
    }
}
