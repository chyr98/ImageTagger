package GUI.Scenes;


import GUI.GUIMain;
import TaggerSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class DeleteConfirmController {

    public void initData(){

    }

    @FXML
    void confirm(ActionEvent event) {
        //Main.tagManager.DeleteTag();
    }

    @FXML
    void reject(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
        GUIMain.showScene(new Scene(loader.load()));
    }

}
