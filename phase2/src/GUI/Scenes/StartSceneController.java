package GUI.Scenes;

import GUI.GUIMain;
import TaggerSystem.SystemMain;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {

  @FXML
  private TextField PathField;

  @FXML
  private Button startButton;

  @FXML
  void OpenDirectoryChooser() {
    File selectedDirectory = GUIMain.OpenDirectoryChooser();
    if (selectedDirectory != null) {
      PathField.setText(selectedDirectory.getAbsolutePath());
    }
  }

  @FXML
  void goMenu() throws IOException {
    //sets the static variables in SystemMain.
    SystemMain.reading(PathField.getText());

    File choosenFile = new File(PathField.getText());
    if (choosenFile.exists() && choosenFile.isDirectory()) {
      MenuController.currentFolder=SystemMain.fileManager.getFolder();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
      Parent menuScene = loader.load();
      GUIMain.showScene(new Scene(menuScene));
    } else {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/PathNotAvalibleWarning.fxml"));
      GUIMain.showStage(loader.load(), "Warning");
    }
  }


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    BooleanBinding pathFieldEmpty = Bindings.createBooleanBinding(() ->
      PathField.getText().isEmpty()
    , PathField.textProperty());
    startButton.disableProperty().bind(pathFieldEmpty);
  }

}