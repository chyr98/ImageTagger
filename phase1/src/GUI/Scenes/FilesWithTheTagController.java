package GUI.Scenes;

import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class FilesWithTheTagController {

  private Tag selectedTag;

  private Stage stage;

  private TagMenuController parent;

  @FXML
  private TableView<ImageFile> TableOfImages;

  @FXML
  private TableColumn<ImageFile, String> ColumnOfImages;

  @FXML
  private ImageView ImageDisplay;

  @FXML
  private Text imagePath;


  public void initData(Tag selectedTag, Stage stage, TagMenuController parent) {
    this.stage = stage;
    this.selectedTag = selectedTag;
    this.parent = parent;

    TableOfImages.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> {
              String path = newSelection.getPath();
              imagePath.setText("Path: "+path);
              File image = new File(path);
              Image selectedImage = null;
              try {
                String url = image.toURI().toURL().toString();
                selectedImage = new Image(url, 359, 315, false, true);
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

  @FXML
  void goBack() {
    stage.close();
    parent.refresh();
  }

}
