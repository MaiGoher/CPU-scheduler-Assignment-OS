/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.priorityscheduler;

import java.util.ArrayList;

/**
 *
 * @author Maiosha
 */
public class Process {

    private int ProcessName;
    private String processName;
    private int WaitingTime;
    private int BurstTime;
    private int ArrivalTime;
    private int TurnAroundTime;
    private int priority;
    private int priorityLevel;
    private int Lower_end;
    private int Higher_end;
    private int remainingBurstTime;
    private boolean completed;
    private int Chartarrival;
    
    
     private Process(String processName, int arrivalTime, int burstTime, int Lower_end, int Higher_end, int priorityLevel, int waitingTime, int TurnAroundTime) {
        this.processName = processName;
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

    public Process(String processName, int arrivalTime, int burstTime, int priorityLevel) {
        this(processName, arrivalTime, burstTime, 0, 0, priorityLevel, 0, 0);
    }

    public Process(String processName, int arrivalTime, int burstTime) {
        this(processName, arrivalTime, burstTime, 0, 0, 0, 0, 0);
    }


    public Process(int ProcessName, int BurstTime, int ArrivalTime) {
        this.ProcessName = ProcessName;
        this.BurstTime = BurstTime;
        this.ArrivalTime = ArrivalTime;
        this.WaitingTime = 0;
        this.TurnAroundTime = 0;
    }

    public Process(int ProcessName, int BurstTime, int ArrivalTime,int priority) {
        this.ProcessName = ProcessName;
        this.BurstTime = BurstTime;
        this.ArrivalTime = ArrivalTime;
        this.WaitingTime = 0;
        this.TurnAroundTime = 0;
        this.priority = priority;

    }

    public void set_ProcessName(int ProcessName) {
        this.ProcessName = ProcessName;
    }

    public void set_WaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    public void set_BurstTime(int BurstTime) {
        this.BurstTime = BurstTime;
    }

    public void set_ArrivalTime(int ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    public void set_TurnAroundTime(int TurnAroundTime) {
        this.TurnAroundTime = TurnAroundTime;
    }

    public void set_LowerEnd(int LowerEnd) {
        this.Lower_end = LowerEnd;
    }

    public void set_HigherEnd(int HigherEnd) {
        this.Higher_end = HigherEnd;
    }

    public void set_RemainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    public int get_ProcessName() {
        return this.ProcessName;
    }

    public int get_WaitingTime() {
        return this.WaitingTime;
    }

    public int get_BurstTime() {
        return this.BurstTime;
    }

    public int get_ArrivalTime() {
        return this.ArrivalTime;
    }

    public int get_TurnAroundTime() {
        return this.TurnAroundTime;
    }

    public int get_LowerEnd() {
        return this.Lower_end;
    }

    public int get_HigherEnd() {
        return this.Higher_end;
    }

    public int get_RemainingBurstTime() {
        return this.remainingBurstTime;
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
    
     public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
    
      public int getChartarrival() {
        return Chartarrival;
    }

    public void setChartarrival(int chartarrival) {
        Chartarrival = chartarrival;
    }

}
