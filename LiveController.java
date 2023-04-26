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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import static javafxapplication9.RoundRobin.ProcessTimeLive;
import static javafxapplication9.RoundRobin.paritionstime;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class LiveController implements Initializable {
    boolean maramcheck = false;
    public static ObservableList<Process> dataaa = FXCollections.observableArrayList();
     SJFS_Preemptive  s;
     int test=0;
     private Timeline timeline;
      private Timeline timeline2;
      private Timeline timeline3;
       private Timeline timeline4;
       private  boolean myVarmaram =true;
      private int seconds = 1;
            int index=0;
             int i=0;
            int j=0;
    int co=0;
   public static String nameradiobtn="";
   public static ArrayList<Process> processes;
   
   public static List<Process2>procesRR;
    public static int q;
   public static ArrayList<Integer> nameidprocess;
   public static String AvgWaitingTime;
   public static String AvgTurnaroundTime;
   public static ArrayList<Integer> chartname;
   public static ArrayList<Integer> charttime;
    public  ArrayList<Integer> timepartforchart=new ArrayList<>();
     public  ArrayList<Integer> namepartforchart=new ArrayList<>();
     ArrayList<Integer>temp=new ArrayList<>();
     public static ArrayList<Integer> Remainingbrusttimee=new ArrayList<>();
     ObservableList<Process> tempo = FXCollections.observableArrayList();
      ArrayList<Integer> tempRemainingbrusttimee=new ArrayList<>();
 //   int counter=charttime.get(charttime.size() - 1);
   int counter =sumArrayList(charttime)+1;
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
    @FXML
    private AnchorPane cov;
    @FXML
    private HBox hboxlive;
    @FXML
    private javafx.scene.control.TextField processName;
    @FXML
    private javafx.scene.control.TextField BurstTime;
    @FXML
    private javafx.scene.control.TextField priorityTextField;
    private javafx.scene.control.TextField QuantumTimeTextField;
    @FXML
    private Label proclabell;
    @FXML
    private Label brustlabell;
    @FXML
    private Label prioritylabell;
    private Label quantumlabell;
    @FXML
    private javafx.scene.control.Button computebtn;
    @FXML
    private javafx.scene.control.Button addpbtn;

    RotateTransition rotate = new RotateTransition();
 FCFS fcfs = new FCFS();
    @FXML
    private javafx.scene.control.Button cancelbtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        processName.setVisible(false);
         BurstTime.setVisible(false);
         priorityTextField.setVisible(false);
     
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          prioritylabell.setVisible(false); 
         
          computebtn.setVisible(false);
          cancelbtn.setVisible(false);
         
        
               Process r = new  Process(1, 3,5);
           data.add(r);
    myimage.setImage(new Image(getClass().getResourceAsStream("time.png")));

     //   RotateTransition rotate = new RotateTransition();
        rotate.setNode(myimage);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();
        radiobuttonname.setText(nameradiobtn);
         processIDCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("processName"));
         remainingBTcol.setCellValueFactory(new TreeItemPropertyValueFactory<>("RemainingBrustTime"));
         processIDCol.setStyle("-fx-alignment: center;");
         remainingBTcol.setStyle("-fx-alignment: center;");
         
            switch (nameradiobtn) {
                case "FCFS":
                    
                   //  FCFS fcfs = new FCFS();
                     dataaa.forEach((i) -> { fcfs.addProcess(new FCFSscheduler(i.get_ProcessName(), i.get_BurstTime(), i.get_ArrivalTime()));});        
                     //fcfs.run(); 
                     //Remainingbrusttimee=fcfs.printRemainingBurstTimeTable(fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList));
 
                      
                     timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {  
                     fcfs.run(); 
                     Remainingbrusttimee=fcfs.printRemainingBurstTimeTable(fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList));
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(dataaa.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));
                
            }

                   co+=dataaa.size();   
                   
            dataaa.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);
                           
                    }else{timeline3.stop();}   }));    
                 break;
                case "Preemptive SFJ":
             s=new SJFS_Preemptive(processes);
                      s.schedulePreemptive_Live();
                       addpbtn.setVisible(false);
                        timeline3 = new Timeline(            
                new KeyFrame(Duration.seconds(1), event -> {  
                     Remainingbrusttimee=s.RemainingBurstTimes();                  
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(processes.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));  
            }
  
                   co+=processes.size();   
                   
            processes.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);
                           
                    }else{timeline3.stop();}})); 
           
                 break;
                case "Non Preemptive SFJ":
                    
                     SJSScheduler nonsfj = new SJSScheduler(processes);
                     nonsfj.newFunction();
                     Remainingbrusttimee=nonsfj.RemainingBurstTimes();
                     addpbtn.setVisible(false);
                                             timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {  
 
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(processes.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));
                
            }

                   co+=processes.size();   
                   
            processes.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);
                           
                    }else{timeline3.stop();}   })); 
                   
                    
                 break;
                case "Preemptive Priority":

                Remainingbrusttimee=PriorityScheduler.RemainingTime(processes,true);

                 timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {  
 
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(processes.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));
                
            }

                   co+=processes.size();   
                   
            processes.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);
                           
                    }else{timeline3.stop();}   })); 
                  
                break;
                case "Non Preemptive priority":

                    
                Remainingbrusttimee=PriorityScheduler.RemainingTime(processes,false);
                 timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {  
 
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(processes.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));
                
            }

                   co+=processes.size();   
                   
            processes.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);                       
                    }else{timeline3.stop();}   })); 
                    break;                    
                case "Round Robin": 
                 Remainingbrusttimee=  RoundRobin.RemainingTimeLive(RoundRobin.Arrange(procesRR), q,procesRR);
                // addpbtn.setVisible(false);
               
                  timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {  
         
                    TreeItem<Process> root = new TreeItem<>();
                   root.setExpanded(true);
                    if(co<Remainingbrusttimee.size()){
                    tempRemainingbrusttimee.clear();
                    tempo.clear();
            for(int i=co;i<(procesRR.size()+co);i++){
                root.getChildren().clear();
                tempRemainingbrusttimee.add(Remainingbrusttimee.get(i));
                
            }
                    // testpane.getChildren().clear();
               // Label resultLabe2 = new Label(tempRemainingbrusttimee.toString());
                 //  testpane.getChildren().add(resultLabe2);  
                   co+=procesRR.size();   
                   
            procesRR.forEach((j) -> {
                   tempo.add(new Process(Integer.parseInt(j.getProcessName()),tempRemainingbrusttimee.get(test)));
                   test++;
            });  
                   
test=0;
                   root.getChildren().clear();
      for (Process record : tempo) {
      TreeItem<Process> item = new TreeItem<>(record);
      root.getChildren().add(item);
    }
        Table.setRoot(root);
        Table.setShowRoot(false);                  
                    }else{timeline3.stop();}   })); 
                      break;                  
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
                  addpbtn.setVisible(false);
                  AvgWaitingTimeLabel.setText(AvgWaitingTime);
                  AvgTurnaroundTimeLabel.setText(AvgTurnaroundTime);
                  }
                    
                })
        );
   timeline2.setCycleCount(Animation.INDEFINITE); // Set the timeline to repeat indefinitely
        timeline2.play(); // Start the timeline
        timeline.setCycleCount(Animation.INDEFINITE); // Set the timeline to repeat indefinitely
        timeline.play(); // Start the timeline
timeline3.setCycleCount(Animation.INDEFINITE); // Set the timeline to repeat indefinitely
        timeline3.play(); 
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

    @FXML
    private void ComputeAction(javafx.event.ActionEvent event) {     
    }
    @FXML
    private void compute(javafx.event.ActionEvent event) {
          if(!processName.getText().isEmpty() && isNumeric(processName.getText()) && !BurstTime.getText().isEmpty() && isNumeric(BurstTime.getText()) ){

                      switch (nameradiobtn) {
                       case "FCFS":
                            processName.setVisible(false);
         BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
                cancelbtn.setVisible(false);            
                            int fcfvar=co/dataaa.size();
                           dataaa.add(new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),seconds));
                           fcfs.addProcess(new FCFSscheduler(Integer.parseInt(processName.getText()),Integer.parseInt(BurstTime.getText()), seconds));
                            fcfs.run();
                            co=fcfvar*dataaa.size();
                       counter+=Integer.parseInt(BurstTime.getText());
                     //fcfs.addProcess(new FCFSscheduler(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()), seconds));         
                            AvgWaitingTime= Double.toString(fcfs.getAverageWaitingTime());
                   AvgTurnaroundTime=Double.toString(fcfs.getAverageTurnaroundtTime());
                   charttime=fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList);
                  timepartforchart=cummsumArrayList(charttime);
                chartname=fcfs.ProcessIdGanttChart(fcfs.completedProcessesList);
                         timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");
                       break;
                        case "Preemptive SFJ":
                     processName.setVisible(false);
         BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
           cancelbtn.setVisible(false);
                                int sfjvar=co/processes.size();
                           processes.add(new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),seconds));
                          counter+=Integer.parseInt(BurstTime.getText());
                           co= sfjvar*processes.size();
                        SJFS_Preemptive sff=new SJFS_Preemptive(processes);
                        sff.schedulePreemptive_Live();
    Remainingbrusttimee=sff.RemainingBurstTimes();        
           timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");             
                        break;
                        case "Non Preemptive SFJ":
                         processName.setVisible(false);
         BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
           cancelbtn.setVisible(false);
                               int sfjvr=co/processes.size();
                           processes.add(new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),seconds));
                          counter+=Integer.parseInt(BurstTime.getText());
                           co= sfjvr*processes.size();
                      SJSScheduler  sfkf=new SJSScheduler(processes);
                       sfkf.newFunction();
                          Remainingbrusttimee=sfkf.RemainingBurstTimes();
          timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");   
                        break;
                        case "Preemptive Priority":
                             if(!priorityTextField.getText().isEmpty() && isNumeric(priorityTextField.getText())){
               processName.setVisible(false);
         BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
              priorityTextField.setVisible(false);
             prioritylabell.setVisible(false); 
              cancelbtn.setVisible(false);    
                            int newvar=co/processes.size();
                      processes.add(new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),seconds,Integer.parseInt(priorityTextField.getText())));
                       Remainingbrusttimee=PriorityScheduler.RemainingTime(processes,true);   
                       co=newvar*processes.size();
                       counter+=Integer.parseInt(BurstTime.getText());
                      //seconds=seconds+Integer.parseInt(BurstTime.getText());
                      PriorityScheduler p=new PriorityScheduler(processes, true);
                    AvgWaitingTime= Double.toString(p.Average_waiting_time(processes, true));
                   AvgTurnaroundTime=Double.toString(p.Average_Turnaround_Time(processes , true));
                  charttime=PriorityScheduler.GanttChartparitionstime(processes, true);
                  timepartforchart=cummsumArrayList(charttime);
                  chartname=PriorityScheduler.GanttChartnameID(processes, true);
                            timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");          
                              }else{
                               showAlert("Please enter an integer number in priority field.");
                              }
                      
                        break;
                        case "Non Preemptive priority":
                                            if(!priorityTextField.getText().isEmpty() && isNumeric(priorityTextField.getText())){
                                                          processName.setVisible(false);
         BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
           priorityTextField.setVisible(false);
             prioritylabell.setVisible(false);
              cancelbtn.setVisible(false);
                             int nonnewvar=co/processes.size();            
                       processes.add(new  Process(Integer.parseInt(processName.getText()), Integer.parseInt(BurstTime.getText()),seconds,Integer.parseInt(priorityTextField.getText())));
                       Remainingbrusttimee=PriorityScheduler.RemainingTime(processes,false);  
                       co=nonnewvar*processes.size();
                       counter+=Integer.parseInt(BurstTime.getText());
                   //  seconds=seconds+Integer.parseInt(BurstTime.getText());
                       PriorityScheduler nonp=new PriorityScheduler(processes, false);
                 AvgWaitingTime= Double.toString(nonp.Average_waiting_time(processes, false));
                 AvgTurnaroundTime=Double.toString(nonp.Average_Turnaround_Time(processes , false));
                 charttime=PriorityScheduler.GanttChartparitionstime(processes, false);
                 timepartforchart=cummsumArrayList(charttime);
                 chartname=PriorityScheduler.GanttChartnameID(processes, false);                   
            timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");                                     
                               }else{
                               showAlert("Please enter an integer number in priority field.");
                              }      
                        break;
                         case "Round Robin":
                      processName.setVisible(false);
                     BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
                              cancelbtn.setVisible(false);
                      int rrvar=co/procesRR.size();
                            procesRR.add(new Process2(processName.getText(), seconds, Integer.parseInt(BurstTime.getText())));
                            counter+=Integer.parseInt(BurstTime.getText());
                             Remainingbrusttimee= RoundRobin.RemainingTimeLive(RoundRobin.Arrange(procesRR), q,procesRR);
                             co=rrvar*procesRR.size();
                              Output rr = RoundRobin.Calc(procesRR, q);
                              AvgWaitingTime= Double.toString(rr.getAvg_waiting());
                              AvgTurnaroundTime=Double.toString(rr.getAvg_turnaround());           
                 chartname=ProcessTimeLive(RoundRobin.Arrange(procesRR),q,  procesRR);
                 charttime=paritionstime(procesRR,q);
                 timepartforchart=cummsumArrayList(charttime);                  
                                  timeline.play();
          timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");
                                break;                  
                       default:
                    System.out.println("ERROR");
                    break;         
                      }                     
          }else{
           showAlert("Please enter an integer number.");
          }
    }

    @FXML
    private void addd(javafx.event.ActionEvent event) {
        rotate.pause();
        timeline.pause();
         timeline2.pause();
          timeline3.pause();
         processName.setVisible(true);
         BurstTime.setVisible(true);
         proclabell.setVisible(true);
         brustlabell.setVisible(true);
          computebtn.setVisible(true);
           cancelbtn.setVisible(true);
          if(nameradiobtn.equals("Preemptive Priority")||nameradiobtn.equals("Non Preemptive priority")){
            priorityTextField.setVisible(true);
             prioritylabell.setVisible(true); 
          }
        
    }
       private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    private boolean isNumeric(String str) {
    return str.matches("\\d+");
}

    @FXML
    private void cancelaction(javafx.event.ActionEvent event) {
          processName.setVisible(false);
                 BurstTime.setVisible(false);
         proclabell.setVisible(false);
         brustlabell.setVisible(false);
          computebtn.setVisible(false);
           priorityTextField.setVisible(false);
             prioritylabell.setVisible(false); 
             cancelbtn.setVisible(false);
         timeline.play();
                  timeline2.play();
           timeline3.play();
               rotate.play();
             processName.setText("");
           priorityTextField.setText("");
           BurstTime.setText("");   
        
    }

}

