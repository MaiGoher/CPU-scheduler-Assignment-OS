/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;


/**
 *
 * @author: Maram Ahmed
 */
public class Process {

  //  private String processName;
    private int ArrivalTime;
    private int BurstTime;
    private int Lower_end;
    private int Higher_end;
    private int priorityLevel;
    private int WaitingTime;
    private int TurnAroundTime;
    private int Chartarrival;
    private int remainingBurstTime;
    private boolean completed;
    private int priority;
    private int ProcessName;
/*
    private Process(int processName, int arrivalTime, int burstTime, int Lower_end, int Higher_end, int priorityLevel, int waitingTime, int TurnAroundTime) {
        this.ProcessName = processName;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Lower_end = Lower_end;
        this.Higher_end = Higher_end;
        this.priorityLevel = priorityLevel;
        this.WaitingTime = waitingTime;
        this.TurnAroundTime = TurnAroundTime;
        this.Chartarrival = arrivalTime;
        this.remainingBurstTime = burstTime;
    }
*/
 //   public Process(String processName, int arrivalTime, int burstTime, int priorityLevel) {
   //     this(processName, arrivalTime, burstTime, 0, 0, priorityLevel, 0, 0);
    //}

  //  public Process(String processName, int arrivalTime, int burstTime) {
    //    this(processName, arrivalTime, burstTime, 0, 0, 0, 0, 0);
   // }

    public Process(int ProcessName, int BurstTime, int ArrivalTime) {
        this.ProcessName = ProcessName;
        this.BurstTime = BurstTime;
        this.ArrivalTime = ArrivalTime;
        this.WaitingTime = 0;
        this.TurnAroundTime = 0;
        this.remainingBurstTime=BurstTime;
    }

    public Process(int ProcessName, int BurstTime, int ArrivalTime, int priority) {
        this.ProcessName = ProcessName;
        this.BurstTime = BurstTime;
        this.ArrivalTime = ArrivalTime;
        this.WaitingTime = 0;
        this.TurnAroundTime = 0;
        this.priority = priority;

    }

    public String getProcessName() {
        return "" + ProcessName;
    }
/*
    public void setProcessName(String processName) {
        this.processName = processName;
    }
*/
    public int get_ArrivalTime() {
        return ArrivalTime;
    }
    public String getArrivalTime() {
        return "" + ArrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int get_BurstTime() {
        return BurstTime;
    }
    public String getBurstTime() {
        return "" + BurstTime;
    }
    public void set_BurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public int get_LowerEnd() {
        return Lower_end;
    }

    public void set_LowerEnd(int lower_end) {
        Lower_end = lower_end;
    }

    public int get_HigherEnd() {
        return Higher_end;
    }

    public void set_HigherEnd(int higher_end) {
        Higher_end = higher_end;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int get_WaitingTime() {
        return WaitingTime;
    }

    public void set_WaitingTime(int waitingTime) {
        WaitingTime = waitingTime;
    }

    public int get_TurnAroundTime() {
        return TurnAroundTime;
    }

    public void set_TurnAroundTime(int turnAroundTime) {
        TurnAroundTime = turnAroundTime;
    }

    public int getChartarrival() {
        return Chartarrival;
    }

    public void setChartarrival(int chartarrival) {
        Chartarrival = chartarrival;
    }

    public int get_RemainingBurstTime() {
        return remainingBurstTime;
    }

    public void set_RemainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    @Override
    public String toString() {
        return "\nProcess{" + "\n\tprocessName=" + ProcessName + "\n\tarrivalTime=" + ArrivalTime
                + "\n\tburstTime=" + BurstTime + "\n\tstartTime=" + Lower_end
                + "\n\tfinishTime=" + Higher_end + "\n\tpriorityLevel=" + priorityLevel
                + "\n\twaitingTime=" + WaitingTime + "\n\tturnaroundTime=" + TurnAroundTime + "\n}";
    }

    public int get_ProcessName() {
        return this.ProcessName;
    }

    public void set_ProcessName(int ProcessName) {
        this.ProcessName = ProcessName;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}