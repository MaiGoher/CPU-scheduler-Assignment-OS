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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javafxapplication9.RoundRobin.Arrange;
import static javafxapplication9.RoundRobin.ProcessTimeLive;
import static javafxapplication9.RoundRobin.paritionstime;

/**
 * FXML Controller class
 *
 * @author habib
 */

public class FXMLController implements Initializable {
    boolean rrcheck=true;
 int i=0;
int g=0;
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
    private Button livebtn;
    @FXML
    private TreeTableColumn<Process, Integer> Prioritycol;
    @FXML
    private AnchorPane anchorpanee;
    @FXML
    private HBox myHBox;
    @FXML
    private Button restartb; 
  TreeItem<Process> root = new TreeItem<>();
    public void initialize(URL url, ResourceBundle rb) {
         processIDCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("processName"));
         ArrivalTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("arrivalTime"));
         BurstTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("burstTime"));
          Prioritycol.setCellValueFactory(new TreeItemPropertyValueFactory<>("priority"));
      Prioritycol.setVisible(false);
        processIDCol.setStyle("-fx-alignment: center;");
        ArrivalTimeCol.setStyle("-fx-alignment: center;");
        BurstTimeCol.setStyle("-fx-alignment: center;");
       Prioritycol.setStyle("-fx-alignment: center;");
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
    
        private ArrayList<Process> change2priority(ObservableList<Process> input) {
        ArrayList<Process> r = new ArrayList<>();
        input.forEach((j) -> {
            r.add(new Process(j.get_ProcessName(), j.get_BurstTime(), j.get_ArrivalTime(),j.getPriority()));
        });
        return r;
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
            r.add(new Process2(i.get_ProcessName()+"", i.get_ArrivalTime(), i.get_BurstTime()));
        });
        return r;
    }

    @FXML
    private void ComputeAction(ActionEvent event) {
                String AlgorithmValue = ((RadioButton) Algorithm.getSelectedToggle()).getText();
        if (data.isEmpty()) {
            return;
        }
        if (null == AlgorithmValue) {
            System.out.println("ERROR");
        } else {
//                myPane.getChildren().clear();
            switch (AlgorithmValue) {
                case "FCFS":
                     myPane.getChildren().clear();
                    LiveController.nameradiobtn="FCFS";
                    LiveController.dataaa=data;
                     //LiveController.processes=data;
                     FCFS fcfs = new FCFS();
                     data.forEach((i) -> { fcfs.addProcess(new FCFSscheduler(i.get_ProcessName(), i.get_BurstTime(), i.get_ArrivalTime()));});        
                     fcfs.run();
                     AvgWaitingTimeLabel.setText(Double.toString(fcfs.getAverageWaitingTime()));
                     AvgTurnaroundTimeLabel.setText(Double.toString(fcfs.getAverageTurnaroundtTime()));
                     LiveController.AvgWaitingTime=Double.toString(fcfs.getAverageWaitingTime());
                     LiveController.AvgTurnaroundTime=Double.toString(fcfs.getAverageTurnaroundtTime());
                       Chart ganttChartfcfc = new Chart(10, 50, 500, 50, myPane);
                    ganttChartfcfc.divde(fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList),fcfs.ProcessIdGanttChart(fcfs.completedProcessesList), 0);
                    myPane.getChildren().add(ganttChartfcfc);
                     LiveController.chartname=fcfs.ProcessIdGanttChart(fcfs.completedProcessesList);
                    LiveController.charttime=fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList);                         
                    break;
                case "Preemptive SFJ":
                    myPane.getChildren().clear();
            ArrayList<Integer> timeforchart;
                     LiveController.nameradiobtn="Preemptive SFJ";
                      LiveController.processes=change(data);
                      SJFS_Preemptive SFJ=new SJFS_Preemptive(change(data));
                      SFJ.schedulePreemptiveNew();
                     AvgWaitingTimeLabel.setText(Double.toString(SFJ.CalcAvgWaitingTime()));
                    AvgTurnaroundTimeLabel.setText(Double.toString(SFJ.CalcAvgTurnAroundTime()));
                      LiveController.AvgWaitingTime=Double.toString(SFJ.CalcAvgWaitingTime());
                     LiveController.AvgTurnaroundTime=Double.toString(SFJ.CalcAvgTurnAroundTime());
                   
                 // Label resultLabelps = new Label(s.GanttChart());
                   // myPane.getChildren().add(resultLabelps);
                     
                    Chart ganttChart3 = new Chart(10, 50, 500, 50, myPane);
                 //    LiveController.charttime=SFJ.ChartEnds(); 
                    timeforchart=SFJ.ChartEnds();
                    ganttChart3.divde(timeforchart,SFJ.ProcessNames(), 0);
                    myPane.getChildren().add(ganttChart3);
                   LiveController.chartname=SFJ.ProcessNames();
                LiveController.charttime=timeforchart;                        
                  
                    break;
                case "Non Preemptive SFJ":
                     myPane.getChildren().clear();
                    LiveController.nameradiobtn="Non Preemptive SFJ";
                    LiveController.processes=change(data);
                    SJSScheduler r= new SJSScheduler(change(data));
                    r.scheduleNonPreemptive();
                     AvgWaitingTimeLabel.setText(Double.toString(r.CalcAvgWaitingTime()));
                    AvgTurnaroundTimeLabel.setText(Double.toString(r.CalcAvgTurnAroundTime()));
                     LiveController.AvgWaitingTime=Double.toString(r.CalcAvgWaitingTime());
                     LiveController.AvgTurnaroundTime=Double.toString(r.CalcAvgTurnAroundTime());
                    //Label resultLabelnps = new Label(r.GanttChart());
                    //myPane.getChildren().add(resultLabelnps);
                     Chart ganttChart4 = new Chart(10, 50, 400, 50, myPane);
                    ganttChart4.divde(r.ChartEnds(),r.ProcessNames(), 0);
                    myPane.getChildren().add(ganttChart4);
                     LiveController.chartname=r.ProcessNames();
                    LiveController.charttime=r.ChartEnds();                    
                    break;
                case "Preemptive Priority":
                myPane.getChildren().clear();
                    LiveController.nameradiobtn="Preemptive Priority";
                    LiveController.processes=change2priority(data);
                   PriorityScheduler p=new PriorityScheduler(change2priority(data) , true);
                  // p.schedule();
                   AvgWaitingTimeLabel.setText(Double.toString(p.Average_waiting_time(change2priority(data) , true)));
                    AvgTurnaroundTimeLabel.setText(Double.toString(p.Average_Turnaround_Time(change2priority(data) , true)));
                   LiveController.AvgWaitingTime=Double.toString(p.Average_waiting_time(change2priority(data) , true));
                     LiveController.AvgTurnaroundTime=Double.toString(p.Average_Turnaround_Time(change2priority(data) , true));
                   //Label resultLabel = new Label(PriorityScheduler.displayGanttChart(change(data) , true).toString());
                 //  Label resultLabel = new Label("|p1|p2|");
                   //myPane.getChildren().add(resultLabel);
            //        System.out.println("time"+PriorityScheduler.GanttChartparitionstime(change(data), true));
              //        System.out.println("id"+PriorityScheduler.GanttChartnameID(change(data), true));
                  Chart ganttChart2 = new Chart(10, 50, 400, 50, myPane);    
                    ganttChart2.divde(PriorityScheduler.GanttChartparitionstime(change2priority(data), true),PriorityScheduler.GanttChartnameID(change2priority(data), true) , 0);
                     myPane.getChildren().add(ganttChart2);
                     LiveController.chartname=PriorityScheduler.GanttChartnameID(change2priority(data), true);
                    LiveController.charttime=PriorityScheduler.GanttChartparitionstime(change2priority(data), true);
                    break;
                case "Non Preemptive priority":
                   myPane.getChildren().clear();
                      LiveController.nameradiobtn="Non Preemptive priority";
                       LiveController.processes=change2priority(data);
                  PriorityScheduler A=new PriorityScheduler(change2priority(data) , false);
                   AvgWaitingTimeLabel.setText(Double.toString(A.Average_waiting_time(change2priority(data) , false)));
                    AvgTurnaroundTimeLabel.setText(Double.toString(A.Average_Turnaround_Time(change2priority(data) , false)));
                    LiveController.AvgWaitingTime=Double.toString(A.Average_waiting_time(change2priority(data) , false));
                     LiveController.AvgTurnaroundTime=Double.toString(A.Average_Turnaround_Time(change2priority(data) , false));
                    Chart ganttChart = new Chart(10, 50, 400, 50, myPane);
                    ganttChart.divde(PriorityScheduler.GanttChartparitionstime(change2priority(data), false),PriorityScheduler.GanttChartnameID(change2priority(data), false) , 0);
                    myPane.getChildren().add(ganttChart);
                    LiveController.chartname=PriorityScheduler.GanttChartnameID(change2priority(data), false);
                    LiveController.charttime=PriorityScheduler.GanttChartparitionstime(change2priority(data), false);
                    break;                    
                case "RR":
                   if(rrcheck){
                     LiveController.nameradiobtn="Round Robin";
                     
                    int q;
                    try {
                        q = Integer.parseInt(QuantumTimeTextField.getText());
                    } catch (NumberFormatException e) {
                         showAlert("Please enter an integer value for quantum time.");
                        break;
                    }
                    Output rr = RoundRobin.Calc(change2list(data), q);
                      LiveController.procesRR=change2list(data);
                      LiveController.q=q;      
                    AvgWaitingTimeLabel.setText(rr.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(rr.getAvg_turnaround() + "");
                     LiveController.AvgWaitingTime=rr.getAvg_waiting() + "";
                     LiveController.AvgTurnaroundTime=rr.getAvg_turnaround() + "";
                   Chart ganttChartRR = new Chart(10, 50, 400, 50, myPane);          
                   ganttChartRR.divde((RoundRobin.paritionstime(change2list(data),q)),ProcessTimeLive( Arrange(change2list(data)), q,change2list(data)) , 0);
                   LiveController.chartname= RoundRobin.ProcessTimeLive( RoundRobin.Arrange(change2list(data)),q,  change2list(data));
                    LiveController.charttime=RoundRobin.paritionstime(change2list(data),q);
                   
                   myPane.getChildren().add(ganttChartRR);
                  // myPane.getChildren().add(resultLabelR);
                   
                   rrcheck=false;
                   QuantumTimeTextField.setDisable(true);
                   }
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }

    @FXML
    private void AddProcessAction(ActionEvent event) {
        if (!processName.isDisabled()) {
            if (Algorithm.getSelectedToggle() == null) {
        showAlert("Please select one of the CPU scheduling algorithms.");
        return;
    }
        
         if (!(((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Preemptive Priority")||((RadioButton) Algorithm.getSelectedToggle()).getText().equals("Non Preemptive priority"))) {
          
             try {

        if(!processName.getText().isEmpty() && isNumeric(processName.getText()) && !BurstTime.getText().isEmpty() && isNumeric(BurstTime.getText()) && !ArrivalTime.getText().isEmpty() && isNumeric(ArrivalTime.getText())){
            i++;
        } else {
            showAlert("Please enter an integer number.");
            return;
        }
              do{
                  Prioritycol.setVisible(false);
           //  LiveController.nameidprocess.add(Integer.parseInt(processName.getText()));
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
        noprocessesbtn.setDisable(true);
    } catch (NumberFormatException e) {
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }
          
    }else{
             
              try {
        if(!priorityTextField.getText().isEmpty() && isNumeric(priorityTextField.getText()) && !processName.getText().isEmpty() && isNumeric(processName.getText()) && !BurstTime.getText().isEmpty() && isNumeric(BurstTime.getText()) && !ArrivalTime.getText().isEmpty() && isNumeric(ArrivalTime.getText())){
            i++;
        } else {
            showAlert("Please enter an integer number.");
            return;
        }
                      do{
                  
             // LiveController.nameidprocess.add(Integer.parseInt(processName.getText()));
             Prioritycol.setVisible(true);
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
                        //    addbtn.setText("Add one Process");
       //  addbtn.setStyle("-fx-background-color: #ff0000;");
       priorityTextField.setDisable(true);
          noprocessesbtn.setDisable(true);
            processName.setDisable(true);
        ArrivalTime.setDisable(true);
        BurstTime.setDisable(true);
      //  addbtn.setText("Add one Process");
        // addbtn.setStyle("-fx-background-color: #ff0000;");
 
    } catch (NumberFormatException e) {
//         showAlert("Please enter an integer number.");
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }}}}
    @FXML
    private void noprocesses(ActionEvent event) {
       
    String numProcessesStr = noprocessesbtn.getText();
    if (isNumeric(numProcessesStr)) {
        int numProcesses = Integer.parseInt(numProcessesStr);
        if (numProcesses != 0) {
            processName.setDisable(false);
            ArrivalTime.setDisable(false);
            BurstTime.setDisable(false);
        }
    }else {
            showAlert("Please enter an integer number.");}
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
    private void livee(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("live.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root2);
        
        stage.setScene(scene);
        
        stage.setTitle("Live Scheduler");
         stage.getIcons().add(new Image(getClass().getResourceAsStream("live.jpg")));
        stage.show(); 
    }
    
    private void showAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    private boolean isNumeric(String str) {
    return str.matches("\\d+");
}

    @FXML
    private void restartbtn(ActionEvent event) {
         
         root.getChildren().clear();
        
         data.clear();
         i=0;
           root.setExpanded(true);
      for (Process record : data) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
    myPane.getChildren().clear();
        Table.setRoot(root);
        
        Table.setShowRoot(false);
        noprocessesbtn.setDisable(false);
        rrcheck=true;
        QuantumTimeTextField.setDisable(true);
        priorityTextField.setDisable(true);
        processName.setDisable(true);
        ArrivalTime.setDisable(true);
        BurstTime.setDisable(true);
        noprocessesbtn.setText("");
        QuantumTimeTextField.setText("");
             processName.setText("");
            ArrivalTime.setText("");
            BurstTime.setText("");
            priorityTextField.setText("");    
        AvgWaitingTimeLabel.setText("");
         AvgTurnaroundTimeLabel.setText("");
    }    
}
