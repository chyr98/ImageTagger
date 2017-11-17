package GUI.Scenes;

        import GUI.Main;
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
    Main.showMenu();
    }

}
