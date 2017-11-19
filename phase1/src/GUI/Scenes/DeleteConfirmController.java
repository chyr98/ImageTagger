package GUI.Scenes;


import GUI.GUIMain;
import TaggerSystem.SystemMain;
import TaggerSystem.Tag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmController {

    private Tag tagToDelete;

    private Stage stage;

    private TagMenuController parent;

    public void initData(Tag tag, Stage stage,TagMenuController parent){
        tagToDelete = tag;
        this.stage = stage;
        this.parent = parent;
    }

    @FXML
    void confirm(ActionEvent event) throws IOException {
        SystemMain.tagManager.deleteTag(tagToDelete);
        parent.refresh();
        stage.close();
    }

    @FXML
    void reject(ActionEvent event) throws IOException {
        parent.refresh();
        stage.close();
    }

}
