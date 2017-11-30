package GUI.Controllers;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TagMenuController implements Initializable,RefreshableController {

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


  /**
   * Catch the event that Add Tag button is clicked and if there is a tag entered in the text field,
   * the text entered will be convert to a tag and add to the image. Otherwise, if a tag is selected
   * in the table of all tags, then the selected tag will be add to the image.
   */
  @FXML
  void AddTag() throws IOException {

    if (!tagNameIn.getText().isEmpty()) {
      selectedImage.addTag(new Tag(tagNameIn.getText()));
      tagNameIn.setText("");
    } else if (!allTagTable.getSelectionModel().getSelectedItems().isEmpty()) {
      for (Tag t : allTagTable.getSelectionModel().getSelectedItems()) {
        selectedImage.addTag(t);
      }
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


  /**
   * Refresh the scene to catch the changes in back end data and display the updated information to
   * user.
   */
  public void refresh() {
    ObservableList<Tag> tags1 = FXCollections.observableArrayList();
    tags1.addAll(selectedImage.getCurrentTagList());
    imageTagTable.setItems(tags1);
    ObservableList<Tag> tags2 = FXCollections.observableArrayList();
    tags2.addAll(SystemMain.tagManager.getTagList());
    allTagTable.setItems(tags2);
  }

  /**
   * Catch the event when Delete Tag button is clicked and delete the selected tag from the image if
   * there is any.
   */
  @FXML
  void DeleteTag() throws IOException {
    if (imageTagTable.getSelectionModel().getSelectedItem() != null) {
      selectedImage.deleteTag(imageTagTable.getSelectionModel().getSelectedItem());
    }
    refresh();
  }

  /**
   * Catch the event when Delete Tag From All button is clicked and display a warning message to
   * tell user the result of delete a tag generally.
   */
  @FXML
  void DeleteTagFromAll() throws IOException {
    if (allTagTable.getSelectionModel().getSelectedItems().size() == 1) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(GUIMain.class.getResource("Scenes/DeleteConfirmStage.fxml"));
      Parent deleteConfirmationScene = loader.load();

      DeleteConfirmController controller = loader.getController();
      controller.initData(allTagTable.getSelectionModel().getSelectedItem(),
          GUIMain.showStage(new Scene(deleteConfirmationScene), "Confirmation"), this);
    }
  }

  /**
   * Catch the event when Back button is clicked, and go back to the menu scene.
   */
  @FXML
  void Back() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
    GUIMain.showScene(new Scene(loader.load()));
  }

  /**
   * Catch the event when View Files With Tag button is clicked, display a scene that displays a
   * table of all images with the selected tag.
   */
  @FXML
  void goFilesWithTag() throws IOException {
    if (allTagTable.getSelectionModel().getSelectedItems().size() == 1) {
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
    allTagTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }
}

