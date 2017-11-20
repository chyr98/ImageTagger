package GUI;

import TaggerSystem.SystemMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GUIMain extends Application {

  private static Stage primaryStage;
  private static AnchorPane mainLayout;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("Photo Tager");
    primaryStage.setResizable(false);
    primaryStage.setOnCloseRequest(event -> saving());

    SystemMain.loading();
    FXMLLoader loader = new FXMLLoader();

    if (SystemMain.tagManager == null && SystemMain.fileManager == null) {
      loader.setLocation(GUIMain.class.getResource("Scenes/StartScene.fxml"));
      showScene(new Scene(loader.load()));
    } else {
      loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
      Parent menuScene = loader.load();

      GUIMain.showScene(new Scene(menuScene));
    }

  }

  private void saving() {
    SystemMain.saving();
  }

  public static File OpenDirectoryChooser() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    return directoryChooser.showDialog(GUIMain.primaryStage);
  }

  public static void showScene(Scene scene) throws IOException {
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static Stage showStage(Scene scene, String title) throws IOException {

    Stage stage = new Stage();
    stage.setTitle(title);
    stage.setResizable(false);
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
    return stage;
  }
}
