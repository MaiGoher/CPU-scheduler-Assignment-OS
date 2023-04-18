/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication9;
import javax.swing.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class LiveController implements Initializable {
     private Timeline timeline;
      private Timeline timeline2;
      private int seconds = 1;
            int index=0;
             int i=0;
            int j=0;
    
   public static String nameradiobtn="";
   public static ArrayList<Integer> nameidprocess;
   public static String AvgWaitingTime;
   public static String AvgTurnaroundTime;
   public static ArrayList<Integer> chartname;
   public static ArrayList<Integer> charttime;
    public  ArrayList<Integer> timepartforchart=new ArrayList<>();
     public  ArrayList<Integer> namepartforchart=new ArrayList<>();
     ArrayList<Integer>temp=new ArrayList<>();
 //   int counter=charttime.get(charttime.size() - 1);
   int counter =sumArrayList(charttime);
//int off=sumArrayList(charttime);
    @FXML
    private ImageView myimage;
    @FXML
    private TreeTableView<Process> Table;
    @FXML
    private TreeTableColumn<Process, Integer> processIDCol;
    @FXML
    private Pane myPane;
    @FXML
    private Label AvgWaitingTimeLabel;
    @FXML
    private Label AvgTurnaroundTimeLabel;
    @FXML
    private Label radiobuttonname;
    @FXML
    private TreeTableColumn<Process, Integer> remainingBTcol;
    ObservableList<Process> data = FXCollections.observableArrayList();
    @FXML
    private Label timerlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               Process r = new  Process(1, 3,5);
           data.add(r);
    myimage.setImage(new Image(getClass().getResourceAsStream("time.png")));

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(myimage);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();
        radiobuttonname.setText(nameradiobtn);
       //  processIDCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("processName"));
        // processIDCol.setStyle("-fx-alignment: center;");
         //remainingBTcol.setStyle("-fx-alignment: center;");
         
         
         /*
               TreeItem<Process> root = new TreeItem<>();
      root.setExpanded(true);
      for (Process record : data) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
     
    }
     
        Table.setRoot(root);
        Table.setShowRoot(false);
  */
         
         
            switch (nameradiobtn) {
                case "FCFS":
                 break;
                case "Preemptive SFJ":
                 break;
                case "Non Preemptive SFJ":
                 break;
                case "Preemptive Priority":
                  
                break;
                case "Non Preemptive priority":

                    break;                    
                case "Round Robin":  
             default:
                    System.out.println("ERROR");
                    break;
            }
            
 
            timepartforchart=cummsumArrayList(charttime);
                namepartforchart.add(chartname.get(0));
                temp.add(0);
                
                
                 timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {                    
   if(timepartforchart.get(i) >= seconds && i < timepartforchart.size()) {
  j++;
        temp.set(index,j);
    }else{
       j=0;
  
    temp.add(0);
    i++;
    index++;
    namepartforchart.add(chartname.get(i));
   }

 seconds++;
 if(i!=0){
  temp.set(index,temp.get(index)+1);}
 Chart ganttChart3 = new Chart(10, 50, seconds+400, 50+seconds, myPane);
      myPane.getChildren().clear();
          
      ganttChart3.divde(temp, namepartforchart, 0);
        myPane.getChildren().add(ganttChart3);
 

 

              }));
                

                 
           timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {

                   
                   counter--; 
                   timerlabel.setText(Integer.toString(counter));

                  if(counter==0){  
                  timeline.stop();
                  AvgWaitingTimeLabel.setText(AvgWaitingTime);
                  AvgTurnaroundTimeLabel.setText(AvgTurnaroundTime);
                  }
                    
                })
        );
   timeline2.setCycleCount(Animation.INDEFINITE); // Set the timeline to repeat indefinitely
        timeline2.play(); // Start the timeline
        timeline.setCycleCount(Animation.INDEFINITE); // Set the timeline to repeat indefinitely
        timeline.play(); // Start the timeline


    }



       
    
       public static int sumArrayList(ArrayList<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
       
           public static ArrayList<Integer> cummsumArrayList(ArrayList<Integer> inputList) {
        ArrayList<Integer> outputList = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < inputList.size(); i++) {
            sum += inputList.get(i);
            outputList.add(sum);
        }
        return outputList;
    }
    }

