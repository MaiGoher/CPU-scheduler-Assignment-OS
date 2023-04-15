/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication9;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author habib
 */
public class JavaFXApplication9 extends Application {
    
    @Override
    public void start(Stage stage) {
 
   try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
           // stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("CPU Scheduler Simulator");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("ICON.jpg")));
            stage.show();
        } catch (IOException e) {
            System.out.println("Exception in GUIMain -> Start Method:-" + e);
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
