package GUI.Scenes;

import GUI.GUIMain;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable{

    @FXML
    private TextField PathField;

    @FXML
    private Button startButton;

    @FXML
    void OpenDirectoryChooser() {
        File selectedDirectory = GUIMain.OpenDirectoryChooser();
        if(selectedDirectory != null){
            PathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    void goMenu(ActionEvent event) throws IOException {
        File choosenFile = new File(PathField.getText());
    if (choosenFile.exists()&&choosenFile.isDirectory()){
        GUIMain.showScene("Scenes/Menu.fxml");
    }
    else
        GUIMain.showStage("Scenes/PathNotAvalibleWarning.fxml","Warning");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        BooleanBinding pathFieldEmpty = Bindings.createBooleanBinding(() -> {return PathField.getText().isEmpty();},PathField.textProperty());
        startButton.disableProperty().bind(pathFieldEmpty);
    }

}
