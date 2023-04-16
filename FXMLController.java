/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication9;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author habib
 */

public class FXMLController implements Initializable {
 int i=0;

    @FXML
    private TextField processName;
    @FXML
    private TextField ArrivalTime;
    @FXML
    private TextField BurstTime;
    @FXML
    private TreeTableView<Process> Table;
    @FXML
    private TreeTableColumn<Process, Integer> processIDCol;
    @FXML
    private TreeTableColumn<Process, Integer> ArrivalTimeCol;
    @FXML
    private TreeTableColumn<Process, Integer> BurstTimeCol;
    @FXML
    private ToggleGroup Algorithm;
    @FXML
    private Label AvgWaitingTimeLabel;
    @FXML
    private Label AvgTurnaroundTimeLabel;
    @FXML
    private TextField QuantumTimeTextField;

    /**
     * Initializes the controller class.
     */
  
     ObservableList<Process> data = FXCollections.observableArrayList();
    @FXML
    private Button addbtn;
    @FXML
    private TextField priorityTextField;
    @FXML
    private TextField noprocessesbtn;
    @FXML
    private Pane myPane;
    @FXML
    private Button ganttbtn;
    @FXML
    private Button livebtn;
  


    public void initialize(URL url, ResourceBundle rb) {
        // TODO

         processIDCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("processName"));
         ArrivalTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("arrivalTime"));
         BurstTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("burstTime"));
       
        processIDCol.setStyle("-fx-alignment: center;");
        ArrivalTimeCol.setStyle("-fx-alignment: center;");
        BurstTimeCol.setStyle("-fx-alignment: center;");
       

      TreeItem<Process> root = new TreeItem<>();
      root.setExpanded(true);
      for (Process record : data) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
     
        Table.setRoot(root);
        Table.setShowRoot(false);

        QuantumTimeTextField.setDisable(true);
        priorityTextField.setDisable(true);
        processName.setDisable(true);
        ArrivalTime.setDisable(true);
        BurstTime.setDisable(true);

    }    
    
    private ArrayList<Process> change(ObservableList<Process> input) {
        ArrayList<Process> r = new ArrayList<>();
        input.forEach((j) -> {
            r.add(new Process(j.get_ProcessName(), j.get_BurstTime(), j.get_ArrivalTime()));
        });
        return r;
    }
    private List<Process2> change2list(ObservableList<Process> input) {
        List<Process2> r = new ArrayList<>();
        input.forEach((i) -> {
            r.add(new Process2("p"+i.get_ProcessName(), i.get_ArrivalTime(), i.get_BurstTime()));
        });
        return r;
    }
    /*
    private List<FCFSscheduler> change2fcfs(ObservableList<Process> input) {
         
        input.forEach((i) -> {
            fcfs.addProcess(new FCFSscheduler(i.get_ProcessName(), i.get_ArrivalTime(), i.get_BurstTime()));
        });
        return r;
    }*/
    @FXML
    private void ComputeAction(ActionEvent event) {
                String AlgorithmValue = ((RadioButton) Algorithm.getSelectedToggle()).getText();
        if (data.isEmpty()) {
            return;
        }
        if (null == AlgorithmValue) {
            System.out.println("ERROR");
        } else {
            switch (AlgorithmValue) {
                case "FCFS":
                     FCFS fcfs = new FCFS();
                     data.forEach((i) -> { fcfs.addProcess(new FCFSscheduler(i.get_ProcessName(), i.get_BurstTime(), i.get_ArrivalTime()));});        
                     fcfs.run();
                     AvgWaitingTimeLabel.setText(Double.toString(fcfs.getAverageWaitingTime()));
                     AvgTurnaroundTimeLabel.setText(Double.toString(fcfs.getAverageTurnaroundtTime()));
                  //   Label resultLabelfcfs = new Label();
                  //  myPane.getChildren().add(resultLabelfcfs);
                    break;
                case "Preemptive SFJ":
                    SJFS_Preemptive s=new SJFS_Preemptive(change(data));
                      s.schedulePreemptive();
                     AvgWaitingTimeLabel.setText(Double.toString(s.CalcAvgWaitingTime()));
                    AvgTurnaroundTimeLabel.setText(Double.toString(s.CalcAvgTurnAroundTime()));
                   // Label resultLabelps = new Label(s.GanttChart());
                 //   myPane.getChildren().add(resultLabelps);
                    Chart ganttChart3 = new Chart(3, 50, 500, 50, myPane);
                    ganttChart3.divde(s.ChartEnds(),s.ProcessNames(), 0);
                    myPane.getChildren().add(ganttChart3);
                    
                  
                    break;
                case "Non Preemptive SFJ":

                    SJSScheduler r= new SJSScheduler(change(data));
                    r.scheduleNonPreemptive();
                     AvgWaitingTimeLabel.setText(Double.toString(r.CalcAvgWaitingTime()));
                    AvgTurnaroundTimeLabel.setText(Double.toString(r.CalcAvgTurnAroundTime()));
                    //Label resultLabelnps = new Label(r.GanttChart());
                    //myPane.getChildren().add(resultLabelnps);
                     Chart ganttChart4 = new Chart(10, 50, 400, 50, myPane);
                    ganttChart4.divde(r.ChartEnds(),r.ProcessNames(), 0);
                    myPane.getChildren().add(ganttChart4);
                    break;
                case "Preemptive Priority":
                   PriorityScheduler p=new PriorityScheduler(change(data) , true);
                   AvgWaitingTimeLabel.setText(Double.toString(p.Average_waiting_time(change(data) , true)));
                    AvgTurnaroundTimeLabel.setText(Double.toString(p.Average_Turnaround_Time(change(data) , true)));
                   //Label resultLabel = new Label(PriorityScheduler.displayGanttChart(change(data) , true).toString());
                 //  Label resultLabel = new Label("|p1|p2|");
                   //myPane.getChildren().add(resultLabel);
            //        System.out.println("time"+PriorityScheduler.GanttChartparitionstime(change(data), true));
              //        System.out.println("id"+PriorityScheduler.GanttChartnameID(change(data), true));
                   Chart ganttChart2 = new Chart(10, 50, 400, 50, myPane);
                    ganttChart2.divde(PriorityScheduler.GanttChartparitionstime(change(data), true),PriorityScheduler.GanttChartnameID(change(data), true) , 0);
                     myPane.getChildren().add(ganttChart2);
                   

                    break;
                case "Non Preemptive priority":
                  PriorityScheduler A=new PriorityScheduler(change(data) , false);
                   AvgWaitingTimeLabel.setText(Double.toString(A.Average_waiting_time(change(data) , false)));
                    AvgTurnaroundTimeLabel.setText(Double.toString(A.Average_Turnaround_Time(change(data) , false)));
                   //Label resultLabe2 = new Label(PriorityScheduler.displayGanttChart(change(data) , false).toString());
                   // myPane.getChildren().add(resultLabe2);
                    Chart ganttChart = new Chart(10, 50, 400, 50, myPane);
                    ganttChart.divde(PriorityScheduler.GanttChartparitionstime(change(data), false),PriorityScheduler.GanttChartnameID(change(data), false) , 0);
                    myPane.getChildren().add(ganttChart);


                    break;                    
                case "RR":
                    int q;
                    try {
                        q = Integer.parseInt(QuantumTimeTextField.getText());
                    } catch (NumberFormatException e) {
                        break;
                    }
                    Output rr = RoundRobin.Calc(change2list(data), q);
                   
                    AvgWaitingTimeLabel.setText(rr.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(rr.getAvg_turnaround() + "");
                    Label resultLabelRR = new Label(RoundRobin.generateGanttChart(change2list(data), q));
                    myPane.getChildren().add(resultLabelRR);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }

    @FXML
    private void AddProcessAction(ActionEvent event) {
         if (!(((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Preemptive Priority")||((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Non Preemptive priority"))) {
          try {
              i++;
              do{
             
       Process r = new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),Integer.parseInt(ArrivalTime.getText()));
           data.add(r);
            processName.setText("");
            ArrivalTime.setText("");
            BurstTime.setText("");
      TreeItem<Process> root = new TreeItem<>();
      root.setExpanded(true);
      for (Process record : data) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
     
        Table.setRoot(root);
        Table.setShowRoot(false);

          }while((i!=Integer.parseInt(noprocessesbtn.getText())));
                    i=0;
            processName.setDisable(true);
        ArrivalTime.setDisable(true);
        BurstTime.setDisable(true);
  
    } catch (NumberFormatException e) {
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }
          
    }else{
             
              try {
                  if(!priorityTextField.getText().equals(null)){
              i++;}
              do{
             
       Process r = new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),Integer.parseInt(ArrivalTime.getText()),Integer.parseInt(priorityTextField.getText()));
           data.add(r);
            processName.setText("");
            ArrivalTime.setText("");
            BurstTime.setText("");
            priorityTextField.setText("");
      TreeItem<Process> root = new TreeItem<>();
      root.setExpanded(true);
      for (Process record : data) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
     
        Table.setRoot(root);
        Table.setShowRoot(false);

          }while((i!=Integer.parseInt(noprocessesbtn.getText())));
                    i=0;
            processName.setDisable(true);
        ArrivalTime.setDisable(true);
        BurstTime.setDisable(true);
  
    } catch (NumberFormatException e) {
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }
             
         
         
         }}

    @FXML
    private void noprocesses(ActionEvent event) {
       
         if(Integer.parseInt(noprocessesbtn.getText())!=0 ){
  
         processName.setDisable(false);
        ArrivalTime.setDisable(false);
        BurstTime.setDisable(false);
               
       
    }
    }

    @FXML
    private void radioHandle(ActionEvent event) {
            QuantumTimeTextField.setDisable(true);
            priorityTextField.setDisable(true);
          if (((RadioButton) Algorithm.getSelectedToggle()).getText().equals("RR")) {
            QuantumTimeTextField.setDisable(false);
        } 
          else if(((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Preemptive Priority")||((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Non Preemptive priority")){
                          priorityTextField.setDisable(false);
        } 


        
    }

    @FXML
    private void btngntt(ActionEvent event) throws IOException {
        
       Parent root = FXMLLoader.load(getClass().getResource("ganttchart.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setTitle("Gantt chart");
        stage.show(); 
        
    }

    @FXML
    private void livee(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("live.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root2);
        
        stage.setScene(scene);
        
        stage.setTitle("Live Scheduler");
        stage.show(); 
    }
    
}
