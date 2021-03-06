package GUI.Controllers;

import GUI.GUIMain;
import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable, RefreshableController {

  @FXML
  private TableView<Folder> tableOfFolders;

  @FXML
  private TableColumn<Folder, String> folderNames;

  @FXML
  private TableView<ImageFile> FileTable;

  @FXML
  private TableColumn<ImageFile, String> NameColumn;

  @FXML
  private Text imagePath;

  @FXML
  private ImageView imgDisplay;

  public static Folder currentFolder;

  private boolean onlyImage = false;


  /**
   * Catch the event when Manage Tags button is clicked, and display the tag menu scene of the
   * selected image that allows user to add or delete tags in that image and the all available
   * tags.
   */
  @FXML
  private void openTagMenu() throws IOException {
    if (FileTable.getSelectionModel().getSelectedItem() != null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
      Parent tagMenuParent = loader.load();

      TagMenuController controller = loader.getController();
      controller.initData(FileTable.getSelectionModel().getSelectedItem(), this);

      GUIMain.showScene(new Scene(tagMenuParent));
    } else {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/GeneralTagScene.fxml"));
      GUIMain.showScene(new Scene(loader.load()));
    }
  }

  /**
   * Catch the event when Back button is clicked, and let the table display the items in current
   * folder's parent folder.
   */
  @FXML
  private void goParent() {
    if (currentFolder.getParent() != null) {
      currentFolder = currentFolder.getParent();
      refresh();
    }
  }

  /**
   * Catch the event when Name History button is clicked, and display the scenes shows all previous
   * name for the selected image and allows user to choose any of them to rename the image.
   */
  @FXML
  private void openNameHistory() throws IOException {
    if (FileTable.getSelectionModel().getSelectedItem() != null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/NameHistoryScene.fxml"));
      Parent historyMenuParent = loader.load();

      NameHistoryController controller = loader.getController();
      controller.initData(FileTable.getSelectionModel().getSelectedItem());

      GUIMain.showScene(new Scene(historyMenuParent));
    }
  }

  /**
   * Catch the event when Move To.. button is clicked, and display the scene that allows user to
   * select a folder and move to selected image into that folder.
   */
  @FXML
  private void openMoveToStage() throws IOException {
    if (FileTable.getSelectionModel().getSelectedItem() != null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/MoveFileScene.fxml"));
      Parent moveScene = loader.load();

      MoveFileController controller = loader.getController();
      controller.initData(FileTable.getSelectionModel().getSelectedItem(), this,
          GUIMain.showStage(new Scene(moveScene), "Moving To.."));
    }
  }

  /**
   * Catch the event when Open button is clicked, and display the subdirectories and files inside
   * selected directory on the tables.
   */
  @FXML
  void openFolder() {
    if (tableOfFolders.getSelectionModel().getSelectedItem() != null) {
      currentFolder = tableOfFolders.getSelectionModel().getSelectedItem();
      refresh();
    }
  }

  /**
   * Catch the event when History button is clicked, and display a scene contains information in the
   * log file, which is the history of all renaming events.
   */
  @FXML
  void showLogStage() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GUIMain.class.getResource("Scenes/logDisplayStage.fxml"));
    GUIMain.showStage(new Scene(loader.load()), "History");
  }

  /**
   * Catch the event when All Images button is clicked, and display all the images under current
   * directory and its subdirectories in the FileTable.
   */
  @FXML
  void ShowAllImages() {
    if (!onlyImage) {
      NameColumn.setCellValueFactory(new PropertyValueFactory<ImageFile, String>("name"));

      ObservableList<ImageFile> files = FXCollections.observableArrayList();
      files.addAll(currentFolder.getAllImages());
      FileTable.setItems(files);

      ObservableList<Folder> folders = FXCollections.observableArrayList();
      folders.addAll(new ArrayList<>());
      tableOfFolders.setItems(folders);
    } else {
      refresh();
    }
    onlyImage = !onlyImage;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    FileTable.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> {
              if (newSelection == null) {
                imagePath.setText("Path: ");
                imgDisplay.setImage(null);
              } else {
                String path = newSelection.getPath();
                imagePath.setText("Path: " + path);
                Image selectedImage = null;
                try {
                  String url = new File(path).toURI().toURL().toString();
                  Image image = new Image(url);
                  if (image.getWidth() / imgDisplay.getFitWidth() > image.getHeight() / imgDisplay
                      .getFitHeight()) {
                    selectedImage = new Image(url, imgDisplay.getFitWidth(),
                        image.getHeight() * (imgDisplay.getFitWidth() / image.getWidth()), false,
                        true);
                  } else {
                    selectedImage = new Image(url,
                        image.getWidth() * (imgDisplay.getFitHeight() / image.getWidth()),
                        imgDisplay.getFitHeight(),
                        false, true);
                  }
                } catch (MalformedURLException e) {
                  e.printStackTrace();
                }
                imgDisplay.setImage(selectedImage);
              }
            });
    refresh();
  }

  /**
   * Catch the event when New Project button is clicked, and bring the user to the start scene that
   * allows user to choose another root directory to start with.
   */
  @FXML
  void reStart() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GUIMain.class.getResource("Scenes/StartScene.fxml"));
    GUIMain.showScene(new Scene(loader.load()));
  }

  @Override
  public void refresh() {
    NameColumn.setCellValueFactory(new PropertyValueFactory<ImageFile, String>("name"));
    folderNames.setCellValueFactory(new PropertyValueFactory<Folder, String>("name"));

    ObservableList<Folder> folders = FXCollections.observableArrayList();
    if (currentFolder == null) {
      currentFolder = SystemMain.fileManager.getFolder();
    }
    if (currentFolder.getChildren() != null) {
      if (!currentFolder.getChildren().isEmpty()) {
        folders.addAll(currentFolder.getChildren());
      }
    }
    tableOfFolders.setItems(folders);
    ObservableList<ImageFile> files = FXCollections.observableArrayList();
    files.addAll(currentFolder.getValue());
    FileTable.setItems(files);


  }
}
