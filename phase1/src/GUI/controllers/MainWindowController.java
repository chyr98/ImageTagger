package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class MainWindowController {

    @FXML
    public Label labelFileName;
    @FXML
    public TextField textFieldImagePath;
    @FXML
    public TilePane imageTilePaine;
    @FXML
    public ImageView selectedImageView;


    public void OpenFileChooser(ActionEvent actionEvent) throws MalformedURLException, FileNotFoundException {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open File");
        File folder = chooser.showDialog(labelFileName.getScene().getWindow());
        if (folder != null) {

            /*get list of files inside the selected folder*/
            File[] listOfFiles = folder.listFiles();
            /* Clear previous images from the UI*/
            imageTilePaine.getChildren().clear();
            /* Clear selected image*/
            selectedImageView.setImage(null);

            /* This flag is used to identify the first image inside the selected folder. We set the first image as the selected image*/
            boolean firstImage = true;
            for (final File file : listOfFiles) {

                /* We need to check whether file is an image or not. Only images are displayed in UI*/
                String mimetype = new MimetypesFileTypeMap().getContentType(file);
                String type = mimetype.split("/")[0];
                if (type.equals("image")) {
                    /*Create an image object using this file*/
                    ImageView imageView = createImageView(file);
                    /* Add the above created image to the image grid in UI*/
                    imageTilePaine.getChildren().addAll(imageView);
                    if (firstImage) {
                        /* First image should set as the selected image on right side panel of the UI */
                        Image image = new Image(new FileInputStream(file));
                        selectedImageView.setImage(image);
                        firstImage = false;
                    }
                } else {
                    System.out.println(file.getName() + " is not an image");
                }
            }

            /* Set selected folder path to the text field */
            textFieldImagePath.setText(folder.getPath());
        }
    }

    private ImageView createImageView(final File imageFile) {
        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    /* If we click on a particular image, that image should display as the selected image in right side panel*/
                    if (mouseEvent.getClickCount() == 1) {
                        try {
                            Image image1 = new Image(new FileInputStream(imageFile));
                            selectedImageView.setImage(image1);
                            labelFileName.setText(imageFile.getName());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public void moveFile(ActionEvent actionEvent) {
        //TODO - Write the code to copy this file to some other folder.
        // It should open a new dialog and should allow user to select the destination
    }
}
