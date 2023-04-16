/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication9;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class GanttchartController implements Initializable {


    @FXML
    private AnchorPane panee;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create an instance of Chart

      Chart ganttChart = new Chart(50, 50, 400, 50, panee);

        // Define partitions and names for Gantt chart data
        ArrayList<Integer> partitions = new ArrayList<>();
        partitions.add(80);
        partitions.add(100);
        partitions.add(75);
           partitions.add(5);
        ArrayList<Integer> names = new ArrayList<>();
        names.add(0);
        names.add(1);
        names.add(3);
           names.add(0);

        // Call the divide method on the Chart instance
        ganttChart.divde(partitions, names, 0);

        // Add the Chart instance to the panee AnchorPane
        panee.getChildren().add(ganttChart);
    }  
}
