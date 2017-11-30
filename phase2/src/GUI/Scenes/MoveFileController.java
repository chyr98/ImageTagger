package GUI.Scenes;

import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

  /**
   * Initialize the controller with some data from menu scene.
   *
   * @param file The selected image file to move.
   * @param controller The MenuController that created this scene from.
   * @param stage The stage that this scene is displayed on.
   * */
  public void initData(ImageFile file, MenuController controller, Stage stage) {
    parent = controller;
    selectedFile = file;
    this.stage = stage;
  }

  /**
   * Catch the event when Cancel button is clicked. Cancel the moving event, close the stage
   * and go back to the menu scene.
   * */
  @FXML
  void cancel() {
    parent.refresh();
    stage.close();
  }

  /**
   * Catch the event when Open button is clicked, and open the selected directory and display
   * the files in it on the table.
   * */
  @FXML
  void open() {
    if (tableOfFolder.getSelectionModel().getSelectedItem() != null) {
      currentFolder = tableOfFolder.getSelectionModel().getSelectedItem();
      refresh();
    }
  }

  /**
   * Catch the event when Back button is clicked, and bring the user back to current directory's
   * parent directory and display the files in that on the table.
   * */
  @FXML
  void goParent() {
    if (currentFolder.getParent() != null) {
      currentFolder = currentFolder.getParent();
      refresh();
    }
  }

  /**
   * Catch the event when Move button is clicked. Confirm the moving request and move the selected image
   * to the folder that is selected on the table.
   * */
  @FXML
  void move() throws IOException {
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

  /**
   * Refresh the scene to catch the changes in back end data and display the
   * updated information to user.
   * */
  private void refresh() {
    ObservableList<Folder> folders = FXCollections.observableArrayList();
    listOfFolder.setText(currentFolder.getName());
    folders.addAll(currentFolder.getChildren());
    tableOfFolder.setItems(folders);
  }
}
