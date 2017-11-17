package GUI.Scenes;

import GUI.Main;
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
       Main.showTagMenu();
    }

    @FXML
    private void openNameHistory() throws IOException {
        Main.showNameHistory();
    }

    @FXML
    private void openMoveToStage() throws IOException {
        Main.showMoveFileStage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
