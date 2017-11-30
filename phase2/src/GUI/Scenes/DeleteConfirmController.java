package GUI.Scenes;


import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController {

  private Tag tagToDelete;

  private Stage stage;

  private TagMenuController parent;

  /**
   * Initialize the controller with some information from the tag menu scene.
   *
   * @param tag The selected tag to delete from the set of all tags.
   * @param stage The stage that this scene is displayed on.
   * @param parent The <code>TagMenuController<code/> that this scene is called from.
   */
  public void initData(Tag tag, Stage stage, TagMenuController parent) {
    tagToDelete = tag;
    this.stage = stage;
    this.parent = parent;
  }

  /**
   * Catch the event when Yes button is clicked. Confirm the delete event and delete the tag from
   * all files and the set of all tags.
   */
  @FXML
  void confirm() throws IOException {
    SystemMain.tagManager.deleteTag(tagToDelete);
    parent.refresh();
    stage.close();
  }

  /**
   * Catch the event when No button is clicked. Reject the delete event and close the stage.
   */
  @FXML
  void reject() throws IOException {
    parent.refresh();
    stage.close();
  }

}
