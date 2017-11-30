package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class logDisplayController implements Initializable {

  @FXML
  private TextArea displayArea;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      Scanner sc = new Scanner(new File("nameLog.txt"));
      while (sc.hasNextLine()) {
        displayArea.appendText(sc.nextLine() + System.lineSeparator());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
