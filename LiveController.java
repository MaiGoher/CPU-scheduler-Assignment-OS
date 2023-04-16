/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication9;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class LiveController implements Initializable {

    @FXML
    private ImageView myimage;
    @FXML
    private TreeTableView<?> Table;
    @FXML
    private TreeTableColumn<?, ?> processIDCol;
    @FXML
    private TreeTableColumn<?, ?> ArrivalTimeCol;
    @FXML
    private TreeTableColumn<?, ?> BurstTimeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    myimage.setImage(new Image(getClass().getResourceAsStream("time.png")));

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(myimage);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();
    }    
    
}
