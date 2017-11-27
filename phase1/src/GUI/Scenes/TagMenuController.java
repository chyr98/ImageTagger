package GUI.Scenes;

import GUI.GUIMain;
import TaggerSystem.ImageFile;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TagMenuController implements Initializable {

  @FXML
  private TableView<Tag> imageTagTable;

  @FXML
  private TableView<Tag> allTagTable;

  @FXML
  private TableColumn<Tag, String> TagsOfTheImage;

  @FXML
  private TableColumn<Tag, String> AllAvailableTags;

  @FXML
  private TextField tagNameIn;

  private ImageFile selectedImage;

  private MenuController parent;


    @FXML
    void AddTag() throws IOException {

        if (!tagNameIn.getText().isEmpty()) {
            selectedImage.addTag(new Tag(tagNameIn.getText()));
            tagNameIn.setText("");
        } else if (allTagTable.getSelectionModel().getSelectedItem() != null) {
            selectedImage.addTag(allTagTable.getSelectionModel().getSelectedItem());
        }
        refresh();
  }

  /**
   * This method accepts a image file to initialize the view with.
   */
  public void initData(ImageFile image, MenuController parent) {
    selectedImage = image;
    TagsOfTheImage.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
    ObservableList<Tag> tags = FXCollections.observableArrayList();

    if (selectedImage != null && selectedImage.getCurrentTagList() != null) {
      tags.addAll(selectedImage.getCurrentTagList());
    }
    imageTagTable.setItems(tags);
    this.parent = parent;
  }


  public void refresh() {
    ObservableList<Tag> tags1 = FXCollections.observableArrayList();
    tags1.addAll(selectedImage.getCurrentTagList());
    imageTagTable.setItems(tags1);
    ObservableList<Tag> tags2 = FXCollections.observableArrayList();
    tags2.addAll(SystemMain.tagManager.getTagList());
    allTagTable.setItems(tags2);
  }

  @FXML
  void DeleteTag() throws IOException {
    if (imageTagTable.getSelectionModel().getSelectedItem() != null) {
      selectedImage.deleteTag(imageTagTable.getSelectionModel().getSelectedItem());
    }
    refresh();
  }

  @FXML
  void DeleteTagFromAll() throws IOException {
    if (allTagTable.getSelectionModel().getSelectedItem() != null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/DeleteConfirmStage.fxml"));
      Parent deleteConfirmationScene = loader.load();

      DeleteConfirmController controller = loader.getController();
      controller.initData(allTagTable.getSelectionModel().getSelectedItem(),
          GUIMain.showStage(new Scene(deleteConfirmationScene), "Confirmation"), this);
    }
  }

  @FXML
  void Back() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
    GUIMain.showScene(new Scene(loader.load()));
  }

  @FXML
  void goFilesWithTag() throws IOException {
    if (allTagTable.getSelectionModel().getSelectedItem() != null) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/AllFilesWithTheTagScene.fxml"));
      Parent scene = loader.load();

      FilesWithTheTagController controller = loader.getController();
      controller.initData(allTagTable.getSelectionModel().getSelectedItem(),
          GUIMain.showStage(new Scene(scene),
              allTagTable.getSelectionModel().getSelectedItem().getName()), this);
    }
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AllAvailableTags.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));

    ObservableList<Tag> tags = FXCollections.observableArrayList();
    if (SystemMain.tagManager != null) {
      tags.addAll(SystemMain.tagManager.getTagList());
    }
    allTagTable.setItems(tags);
  }
}

