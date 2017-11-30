package GUI.Controllers;

import GUI.GUIMain;
import TaggerSystem.ImageFile;
import TaggerSystem.Tag;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class NameHistoryController {

  @FXML
  private TableView<ArrayList<Tag>> tableOfNames;

  @FXML
  private TableView<Tag> tableOfTags;

  @FXML
  private TableColumn<ArrayList<Tag>, String> Names;

  @FXML
  private TableColumn<Tag, String> Tags;

  private ImageFile selectedImage;

  /**
   * Initialize the controller with some information from the menu scene.
   *
   * @param image The selected image to display the name history.
   */
  public void initData(ImageFile image) {
    selectedImage = image;
    Names.setCellValueFactory(
        cellData -> new SimpleStringProperty(selectedImage.getNameWithTags(cellData.getValue())));
    Tags.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));

    tableOfNames.getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> {
              ObservableList<Tag> tags = FXCollections.observableArrayList();
              if (newSelection!=null) {
                tags.addAll(newSelection);
                tableOfTags.setItems(tags);
              }
            });
    refresh();
  }

  /**
   * Refresh the scene to catch the changes in back end data and display the updated information to
   * user.
   */
  private void refresh() {
    ObservableList<ArrayList<Tag>> tagLists = FXCollections.observableArrayList();
    if (selectedImage != null) {
      tagLists.addAll(selectedImage.getAllTagLists());
    }
    tableOfNames.setItems(tagLists);
  }

  /**
   * Catch the event when Cancel button is clicked. Go back to the menu scene.
   */
  @FXML
  void Cancel() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
    GUIMain.showScene(new Scene(loader.load()));
  }

  /**
   * Catch the event when Apply To Now button is clicked. Use the selected set of tags to be the
   * current set of tags and rename the file.
   */
  @FXML
  void ApplyToNow() {
    if (tableOfNames.getSelectionModel().getSelectedItem() != null) {
      String path = selectedImage.getPath();
      ArrayList<Tag> selectedTags = tableOfNames.getSelectionModel().getSelectedItem();
      selectedImage.getAllTagLists().remove(selectedTags);
      selectedImage.getAllTagLists().add(selectedTags);
      selectedImage.renameTo(selectedImage.getName(), path);
      refresh();
    }
  }
}
