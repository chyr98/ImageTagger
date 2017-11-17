package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
            showStartMenu();
    }

    private void saving(){
        File targetDirectory = OpenDirectoryChooser();
    }

    public static File OpenDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(GUIMain.primaryStage);
    }

    private static void showStartMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/StartScene.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/Menu.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showTagMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/TagScene.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void showNameHistory() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource("Scenes/NameHistoryScene.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void showStage(String scene, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUIMain.class.getResource(scene));

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(new Scene(loader.load()));
        stage.showAndWait();
    }
}
