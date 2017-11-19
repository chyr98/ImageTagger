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

    public void initData(Tag tag, Stage stage){
        tagToDelete = tag;
        this.stage = stage;
    }

    @FXML
    void confirm(ActionEvent event) throws IOException {
        SystemMain.tagManager.DeleteTag(tagToDelete);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
        stage.close();
    }

    @FXML
    void reject(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
        stage.close();
    }

}
