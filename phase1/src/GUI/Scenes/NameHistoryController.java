package GUI.Scenes;

        import GUI.GUIMain;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.TableColumn;

        import java.io.IOException;

public class NameHistoryController {

    @FXML
    private TableColumn<?, ?> Tags;

    @FXML
    void ApplyToNow(ActionEvent event) {

    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
    GUIMain.showScene("Scenes/Menu.fxml");
    }

}
