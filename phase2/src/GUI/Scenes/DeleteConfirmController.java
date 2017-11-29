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

  public void initData(Tag tag, Stage stage, TagMenuController parent) {
    tagToDelete = tag;
    this.stage = stage;
    this.parent = parent;
  }

  @FXML
  void confirm() throws IOException {
    SystemMain.tagManager.deleteTag(tagToDelete);
    parent.refresh();
    stage.close();
  }

  @FXML
  void reject() throws IOException {
    parent.refresh();
    stage.close();
  }

}
