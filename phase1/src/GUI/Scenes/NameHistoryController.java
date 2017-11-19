package GUI.Scenes;

        import GUI.GUIMain;
        import TaggerSystem.ImageFile;
        import TaggerSystem.Main;
        import TaggerSystem.Tag;
        import com.sun.javafx.property.PropertyReference;
        import javafx.beans.InvalidationListener;
        import javafx.beans.property.ReadOnlyObjectWrapper;
        import javafx.beans.property.SimpleStringProperty;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
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
        import java.util.ArrayList;
        import java.util.ResourceBundle;

public class NameHistoryController implements Initializable{

    @FXML
    private TableView<ArrayList<Tag>> tableOfNames;

    @FXML
    private TableView<Tag> tableOfTags;

    @FXML
    private TableColumn<ArrayList<Tag>, String> Names;

    @FXML
    private TableColumn<Tag, String> Tags;

    @FXML
    void ApplyToNow(ActionEvent event) {

    }
    private ImageFile selectedImage;

    public void initData(ImageFile image){
        selectedImage = image;
        Names.setCellValueFactory(cellData -> new SimpleStringProperty(selectedImage.getNameWithTags(cellData.getValue())));
        Tags.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));

        tableOfNames.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection) -> {
                            ObservableList<Tag> tags = FXCollections.observableArrayList();
                            tags.addAll(newSelection);
                            tableOfTags.setItems(tags);
                        });

        ObservableList<ArrayList<Tag>> tagLists = FXCollections.observableArrayList();
        if (selectedImage != null)
            tagLists.addAll(selectedImage.getAllTagLists());
        tableOfNames.setItems(tagLists);
    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
