package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

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
            showStartMenu();
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    private static void showStartMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scenes/StartScene.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scenes/Menu.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showTagMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scenes/TagScene.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void showNameHistory() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scenes/NameHistoryScene.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void showMoveFileStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scenes/MoveFileScene.fxml"));

        Stage moveFileStage = new Stage();
        moveFileStage.setTitle("Moving To ..");
        moveFileStage.initModality(Modality.WINDOW_MODAL);
        moveFileStage.initOwner(primaryStage);
        moveFileStage.setScene(new Scene(loader.load()));
        moveFileStage.showAndWait();
    }
}
