package GUI.Controllers;

import GUI.GUIMain;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class FilesWithTheTagController {

  private Tag selectedTag;

  private Stage stage;

  private RefreshableController parent;

  @FXML
  private TableView<ImageFile> TableOfImages;

  @FXML
  private TableColumn<ImageFile, String> ColumnOfImages;

  @FXML
  private ImageView ImageDisplay;

  @FXML
  private Text imagePath;


  /**
   * Initialize the controller with some information from the tag menu scene.
   *
   * @param selectedTag All the images with this tag should be displayed on the table.
   * @param stage The stage that this scene is displayed on.
   * @param parent The <code>TagMenuController<code/> that this scene is called from.
   */
  public void initData(Tag selectedTag, Stage stage, RefreshableController parent) {
    this.stage = stage;
    this.selectedTag = selectedTag;
    this.parent = parent;

    TableOfImages.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> {
              String path = newSelection.getPath();
              imagePath.setText("Path: " + path);
              File file = new File(path);
              Image selectedImage = null;
              try {
                String url = file.toURI().toURL().toString();
                Image image = new Image(url);
                if (image.getWidth() / ImageDisplay.getFitWidth() > image.getHeight() / ImageDisplay.getFitHeight()) {
                    selectedImage = new Image(url, ImageDisplay.getFitWidth(),
                            image.getHeight() * (ImageDisplay.getFitWidth() / image.getWidth()), false, true);
                } else {
                    selectedImage = new Image(url, image.getWidth() * (ImageDisplay.getFitHeight() / image.getWidth()), ImageDisplay.getFitHeight(),
                            false, true);
                }
              } catch (MalformedURLException e) {
                e.printStackTrace();
              }
              if (selectedImage != null) {
                ImageDisplay.setImage(selectedImage);
              }
            });

    ColumnOfImages.setCellValueFactory(new PropertyValueFactory<ImageFile, String>("name"));

    ObservableList<ImageFile> files = FXCollections.observableArrayList();
    files.addAll(SystemMain.fileManager.getFilesWithTag(selectedTag));
    TableOfImages.setItems(files);
  }

  /**
   * Catch the event when Back button is clicked. Close the stage.
   */
  @FXML
  void goBack() {
    stage.close();
    parent.refresh();
  }

  /**
   * Catch the event when Copy Files With The Tag To.. button is clicked. Open the folder
   * selection menu to select the target folder and copy the files with this tag to the folder.
   * */
  @FXML
  void copyFilesWithTag() throws IOException {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/MoveFileScene.fxml"));
      Parent moveScene = loader.load();

      MoveFileController controller = loader.getController();
      controller.initData(SystemMain.fileManager.getFilesWithTag(selectedTag),
              GUIMain.showStage(new Scene(moveScene), "Copying To.."));
  }

}
