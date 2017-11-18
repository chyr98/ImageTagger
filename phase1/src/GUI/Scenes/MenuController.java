package GUI.Scenes;

import GUI.GUIMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    @FXML
    private Button TagButton;

    @FXML
    private TableView<?> FileTable;

    @FXML
    private TableColumn<?, ?> NameColomn;

    @FXML
    private TableColumn<?, ?> LocationColomn;

    @FXML
    private ImageView imgDisplay;

    @FXML
    private Button MoveButton;

    @FXML
    private void openTagMenu() throws IOException {
       GUIMain.showScene("Scenes/TagScene.fxml");
    }

    @FXML
    private void openNameHistory() throws IOException {
        GUIMain.showScene("Scenes/NameHistoryScene.fxml");
    }

    @FXML
    private void openMoveToStage() throws IOException {
        GUIMain.showStage("Scenes/MoveFileScene.fxml","Moving To..");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
