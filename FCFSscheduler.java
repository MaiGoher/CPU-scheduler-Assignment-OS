package process;

import java.util.ArrayList;

class FCFS {

    private ArrayList<FCFSscheduler> list;
    private int currentTime;
    double avgWaitingTime = 0;
    double avgTurnaroundTime = 0;

    public FCFS() {
        list = new ArrayList<>();
        currentTime = 0;
    }

    public void addProcess(FCFSscheduler process) {
        list.add(process);
    }

    public void run() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int completedProcesses = 0;
        list.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime()); // sort based on arrival time
        while (!list.isEmpty()) {
            FCFSscheduler currentProcess = null;
            for (FCFSscheduler process : list) {
                if (process.getArrivalTime() <= currentTime) {
                    currentProcess = process;
                    break;
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
                printRemainingBurstTimeTable(currentTime);
            }
            currentProcess.setEndTime(currentTime);
            totalWaitingTime += (currentProcess.getStartTime() - currentProcess.getArrivalTime());
            totalTurnaroundTime += (currentProcess.getEndTime() - currentProcess.getArrivalTime());
            completedProcesses++;
            list.remove(currentProcess);
        }
        avgWaitingTime = (double) totalWaitingTime / completedProcesses;
        avgTurnaroundTime = (double) totalTurnaroundTime / completedProcesses;
    }

    private void printRemainingBurstTimeTable(int time) {
        // Display remaining burst time table
        System.out.print("Time = " + time + " seconds\t");
        for (FCFSscheduler process : list) {
            System.out.print("| P" + process.getId() + " " + process.getRemainingBurstTime() + " ");
        }
        System.out.println("|");
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
        fcfs.addProcess(new FCFSscheduler(1, 5, 3));
        fcfs.addProcess(new FCFSscheduler(2, 3, 0));
        fcfs.addProcess(new FCFSscheduler(3, 9, 2));
        fcfs.run();
        System.out.println("Average Waiting Time = " + fcfs.getAverageWaitingTime());
        System.out.println("Average Turnaround Time = " + fcfs.getAverageTurnaroundtTime());
    }
}
