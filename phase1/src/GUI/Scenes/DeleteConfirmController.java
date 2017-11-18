package GUI.Scenes;


import GUI.GUIMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class DeleteConfirmController {

    @FXML
    void confirm(ActionEvent event) {

    }

    @FXML
    void reject(ActionEvent event) throws IOException {
        GUIMain.showScene("Scenes/TagScene.fxml");
    }

}
