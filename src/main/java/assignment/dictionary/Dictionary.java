/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Ouda
 */

public class Dictionary extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {         
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dictionary-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:src/main/resources/assignment/dictionary/UMIcon.png"));
        stage.setTitle("Spelling Checker (SampleApplication)");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
