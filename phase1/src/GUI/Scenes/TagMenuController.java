package GUI.Scenes;

import GUI.GUIMain;
import TaggerSystem.ImageFile;
import TaggerSystem.Main;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TagMenuController implements Initializable{

    @FXML
    private TableView<Tag> imageTagTable;

    @FXML
    private TableView<Tag> allTagTable;

    @FXML
    private TableColumn<Tag, String> TagsOfTheImage;

    @FXML
    private TableColumn<Tag, String> AllAvailableTags;

    private ImageFile selectedImage;
    /**
     * This method accepts a image file to initialize the view with.
     * */
    public void initData(ImageFile image){
        selectedImage = image;
        TagsOfTheImage.setCellValueFactory(new PropertyValueFactory<Tag,String>("name"));

        ObservableList<Tag> tags = FXCollections.observableArrayList();
        if (selectedImage!=null && selectedImage.getCurrentTagList()!=null)
            tags.addAll(selectedImage.getCurrentTagList());
        imageTagTable.setItems(tags);
    }

    @FXML
    void AddTag(ActionEvent event) throws IOException {

    }

    @FXML
    void DeleteTag(ActionEvent event) throws IOException {
        GUIMain.showStage("Scenes/DeleteConfirmStage.fxml", "Confirmation");
    }

    @FXML
    void Back() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AllAvailableTags.setCellValueFactory(new PropertyValueFactory<Tag,String>("name"));

        ObservableList<Tag> tags = FXCollections.observableArrayList();
        if(Main.tagManager!=null)
            tags.addAll(Main.tagManager.getTagList());
        allTagTable.setItems(tags);
    }
}
