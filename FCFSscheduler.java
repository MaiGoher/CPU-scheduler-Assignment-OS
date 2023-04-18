package com.mycompany.priorityscheduler;

import java.util.ArrayList;


class FCFS {

    ArrayList<FCFSscheduler> queue;
    private int currentTime;
    double avgWaitingTime = 0;
    double avgTurnaroundTime = 0;
    public  ArrayList<FCFSscheduler> completedProcessesList;

    public FCFS() {
        queue = new ArrayList<>();
        completedProcessesList= new ArrayList<>();
        currentTime = 0;
    }

    public void addProcess(FCFSscheduler process) {
        queue.add(process);
    }

    public void run() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int completedProcesses = 0;

        queue.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime()); // sort based on arrival time
        while (!queue.isEmpty()) {
            FCFSscheduler currentProcess = null;
            if (!queue.isEmpty()) {
                for (FCFSscheduler process : queue) {
                    if (process.getArrivalTime() <= currentTime) {
                        currentProcess = process;
                        break;
                    }
                }
            }
            if (currentProcess == null) {
                currentTime++;
                continue;
            }
            currentProcess.setStartTime(currentTime);
            int burstTime = currentProcess.getRemainingBurstTime();
            for (int i = 0; i < burstTime; i++) {
                currentTime++;
                currentProcess.setRemainingBurstTime(burstTime - i - 1);

            }
            currentProcess.setEndTime(currentTime);
            completedProcessesList.add(currentProcess);
            totalWaitingTime += (currentProcess.getStartTime() - currentProcess.getArrivalTime());
            totalTurnaroundTime += (currentProcess.getEndTime() - currentProcess.getArrivalTime());
            completedProcesses++;

            queue.remove(currentProcess);
        }
        avgWaitingTime = (double) totalWaitingTime / completedProcesses;
        avgTurnaroundTime = (double) totalTurnaroundTime / completedProcesses;
    }

    public ArrayList<Integer> printRemainingBurstTimeTable(ArrayList<Integer> R) {
        ArrayList<Integer> remainingTimes = new ArrayList<>();
        int n = R.size();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = R.get(i);
        }
        remainingTimes.add(times[0]);
        int time = 0;

        while (true) {
            int remaining = 0;
            for (int i = 0; i < n; i++) {
                if (times[i] > 0) {
                    remaining++;
                    if (i == 0) {
                        times[i]--;
                        remainingTimes.add(times[i]);
                    } else if (remaining == 1) {
                        times[i]--;
                        remainingTimes.add(times[i]);
                    } else {
                        remainingTimes.add(times[i]);
                    }
                } else {
                    remainingTimes.add(0);
                }
            }
            time++;
            if (remaining == 0) {
                break;
            }
        }
        remainingTimes.remove(0);

        return remainingTimes;
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



    public ArrayList<Integer> ProcessIdGanttChart(ArrayList<FCFSscheduler> queue) {

        ArrayList<Integer> ProcessId = new ArrayList<>();
        for(int i=0 ; i< queue.size() ; i++ ){
            ProcessId.add(queue.get(i).getId());
        }
        return ProcessId;

    }

    public ArrayList<Integer> ProcessTimeGanttChart(ArrayList<FCFSscheduler> queue) {

        ArrayList<Integer> ProcessTime = new ArrayList<>();
        for(int i=0 ; i< queue.size() ; i++ ){
            ProcessTime.add(queue.get(i).getEndTime()-queue.get(i).getStartTime());
        }
        return ProcessTime;

    }


    public double getAverageWaitingTime() {
        return avgWaitingTime;
    }

    public double getAverageTurnaroundtTime() {
        return avgTurnaroundTime;
    }
}

class FCFSscheduler {

    private static boolean ProcessIdGanttChart(ArrayList<FCFSscheduler> completedProcessesList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
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
        fcfs.queue.add(new FCFSscheduler(1, 5, 1));
        fcfs.queue.add(new FCFSscheduler(2, 3, 2));
        fcfs.queue.add(new FCFSscheduler(3, 9, 0));
        fcfs.run();
        ArrayList <Integer> R = fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList);
        System.out.println(fcfs.ProcessIdGanttChart(fcfs.completedProcessesList));
        System.out.println(fcfs.ProcessTimeGanttChart(fcfs.completedProcessesList));
        System.out.println(fcfs.printRemainingBurstTimeTable(R));

    }
}