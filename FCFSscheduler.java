package com.mycompany.priorityscheduler;

import java.util.LinkedList;
import java.util.Queue;

class FCFS {

    private Queue<FCFSscheduler> queue;
    private int currentTime;
    double avgWaitingTime = 0;
    double avgTurnaroundTime = 0;

    public FCFS() {
        queue = new LinkedList<>();
        currentTime = 0;
    }

    public void addProcess(FCFSscheduler process) {
        queue.add(process);
    }

    public void run() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int completedProcesses = 0;
        while (!queue.isEmpty()) {
            FCFSscheduler process = queue.poll();
            process.setStartTime(currentTime);
            int burstTime = process.getBurstTime();
            for (int i = 0; i < burstTime; i++) {
                currentTime++;
                process.setRemainingBurstTime(burstTime - i - 1);
                displayGanttChart(process, currentTime);
                displayRemainingBurstTimeTable(process, currentTime);
            }
            process.setEndTime(currentTime);
            totalWaitingTime += (process.getStartTime() - process.getArrivalTime());
            totalTurnaroundTime += (process.getEndTime() - process.getArrivalTime());
            completedProcesses++;
        }
        avgWaitingTime = (double) totalWaitingTime / completedProcesses;
        avgTurnaroundTime = (double) totalTurnaroundTime / completedProcesses;
        System.out.println("\nAverage Waiting Time = " + avgWaitingTime);
        System.out.println("Average Turnaround Time = " + avgTurnaroundTime);
    }

    private void displayGanttChart(FCFSscheduler currentProcess, int time) {
        // Display Time
        System.out.print("Time = " + time + " seconds" + "\t");
        System.out.println();
        // Display Gantt Chart
        System.out.print("| P" + currentProcess.getId() + " ");
        for (FCFSscheduler process : queue) {
            System.out.print("| P" + process.getId() + " ");
        }
        System.out.print("|");
        System.out.println();
    }
    
    private void displayRemainingBurstTimeTable(FCFSscheduler currentProcess, int time) {
        // Display remaining burst time table
        System.out.print(currentProcess.getRemainingBurstTime() + "\t");
        for (FCFSscheduler process : queue) {
            System.out.print(process.getRemainingBurstTime() + "\t");
        }
        System.out.println();
    }
    
      public double getAverageWaitingTime() {
        return avgWaitingTime;
    } 
      
        public double getAverageTurnaroundtTime() {
        return avgTurnaroundTime;
    }  
}

class FCFSscheduler {
    private int id;
    private int burstTime;
    private int remainingBurstTime;
    private int arrivalTime;
    private int startTime;
    private int endTime;

    public FCFSscheduler(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void setRemainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    
    public static void main(String[] args) {
        FCFS fcfs = new FCFS();
        fcfs.addProcess(new FCFSscheduler(1, 5, 0));
        fcfs.addProcess(new FCFSscheduler(2, 3, 1));
        fcfs.addProcess(new FCFSscheduler(3, 9, 2));
        fcfs.run();
    }
}