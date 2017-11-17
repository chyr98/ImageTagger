package GUI.Scenes;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.io.IOException;

public class TagMenuController {

    @FXML
    private TableColumn<?, ?> TagsOfTheImage;

    @FXML
    private TableColumn<?, ?> TableOfAllTags;

    @FXML
    void AddTag(ActionEvent event) throws IOException {

    }

    @FXML
    void DeleteTag(ActionEvent event) {

    }

    @FXML
    void Back() throws IOException {
        Main.showMenu();
    }

}
