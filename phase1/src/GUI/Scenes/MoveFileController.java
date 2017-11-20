package GUI.Scenes;

import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoveFileController implements Initializable {

  @FXML
  private TableView<Folder> tableOfFolder;

  @FXML
  private TableColumn<Folder, String> listOfFolder;

  private Folder currentFolder;

  private MenuController parent;

  private ImageFile selectedFile;

  private Stage stage;

  public void initData(ImageFile file, MenuController controller, Stage stage) {
    parent = controller;
    selectedFile = file;
    this.stage = stage;
  }

  @FXML
  void cancel(ActionEvent event) {
    parent.refresh();
    stage.close();
  }

  @FXML
  void open(){
    if (tableOfFolder.getSelectionModel().getSelectedItem()!=null) {
      currentFolder = tableOfFolder.getSelectionModel().getSelectedItem();
      refresh();
    }
  }

  @FXML
  void goParent(ActionEvent event) {
    if (currentFolder.getParent() != null) {
      currentFolder = currentFolder.getParent();
      refresh();
    }
  }

  @FXML
  void move(ActionEvent event) throws IOException {
      Folder targetFolder = currentFolder;
      selectedFile.moveTo(targetFolder);

      parent.refresh();
      stage.close();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listOfFolder.setCellValueFactory(new PropertyValueFactory<Folder, String>("name"));
    currentFolder = SystemMain.fileManager.getFolder();

    refresh();

  }

  private void refresh() {
    ObservableList<Folder> folders = FXCollections.observableArrayList();
    listOfFolder.setText(currentFolder.getName());
    folders.addAll(currentFolder.getChildren());
    tableOfFolder.setItems(folders);
  }
}
