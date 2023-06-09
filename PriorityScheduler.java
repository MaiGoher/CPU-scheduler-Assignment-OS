/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;



import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mai Esmail
 */
public class PriorityScheduler {

    private ArrayList<Process> processes;
    private boolean preemptive;
    private static ArrayList<Integer> timeline;
    private int[] remainingTime;
    private int[] waitingTime;
    private int[] turnaroundTime;
    private static ArrayList<Integer> RT;
    private static ArrayList<ArrayList<Integer>> outerList ;

    public PriorityScheduler(ArrayList<Process> processes, boolean preemptive) {
        this.processes = processes;
        this.preemptive = preemptive;
        this.timeline = new ArrayList<>();
        this.RT = new ArrayList<>();
        this.outerList = new ArrayList<ArrayList<Integer>>();
        this.remainingTime = new int[processes.size()];
        this.waitingTime = new int[processes.size()];
        this.turnaroundTime = new int[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).get_BurstTime();
        }
    }

    public void Liveschedule() {
        int n = processes.size();
        int currentTime = 0;
        boolean[] completed = new boolean[n];
        int completedCount = 0;

        while (completedCount < n) {
            int minPriorityIndex = -1;
            int minPriority = Integer.MAX_VALUE;
            int minPriorityArrivalTime = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < minPriority) {
                    minPriorityIndex = i;
                    minPriority = processes.get(i).getPriority();
                    minPriorityArrivalTime = processes.get(i).get_ArrivalTime();
                }
            }

            if (minPriorityIndex == -1) {
                currentTime++;
                timeline.add(-1); // idle time
            } else {
                if (preemptive) {
                    int remainingBurstTime = remainingTime[minPriorityIndex];
                    int nextArrivalTime = minPriorityIndex < n - 1 ? processes.get(minPriorityIndex + 1).get_ArrivalTime() : Integer.MAX_VALUE;
                    int runTime = Math.min(remainingBurstTime, nextArrivalTime - currentTime);
                    int highestPriorityIndex = minPriorityIndex; // Keep track of the highest priority process
                    while (remainingTime[highestPriorityIndex] > 0) {
                        // Check if a higher-priority process arrives
                        for (int i = 0; i < n; i++) {
                            if (!completed[i] && i != highestPriorityIndex && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < processes.get(highestPriorityIndex).getPriority()) {
                                highestPriorityIndex = i;
                            }
                        }
                        // Check if the current process should be preempted
                        if (highestPriorityIndex != minPriorityIndex) {
                            minPriorityIndex = highestPriorityIndex;
                            remainingBurstTime = remainingTime[minPriorityIndex];
                            nextArrivalTime = minPriorityIndex < n - 1 ? processes.get(minPriorityIndex + 1).get_ArrivalTime() : Integer.MAX_VALUE;
                            runTime = Math.min(remainingBurstTime, nextArrivalTime - currentTime);
                        }
                        timeline.add(processes.get(minPriorityIndex).get_ProcessName());
                        remainingTime[minPriorityIndex]--;
                        currentTime++;
                        updateRemainingTimeTable();
                        updateprintGanttChart();

                        if (remainingTime[minPriorityIndex] == 0) {
                            completed[minPriorityIndex] = true;
                            completedCount++;
                            turnaroundTime[minPriorityIndex] = currentTime - processes.get(minPriorityIndex).get_ArrivalTime();
                            waitingTime[minPriorityIndex] = turnaroundTime[minPriorityIndex] - processes.get(minPriorityIndex).get_BurstTime();
                            // Reset the highest priority process to the next highest priority
                            for (int i = 0; i < n; i++) {
                                if (!completed[i] && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < processes.get(highestPriorityIndex).getPriority()) {
                                    highestPriorityIndex = i;
                                }
                            }
                        }
                    }
                } else {
                    int runTime = processes.get(minPriorityIndex).get_BurstTime();

                    for (int i = 0; i < runTime; i++) {
                        timeline.add(processes.get(minPriorityIndex).get_ProcessName());

                        remainingTime[minPriorityIndex]--;
                        currentTime++;
                        updateRemainingTimeTable();
                        updateprintGanttChart();
                    }
                    completed[minPriorityIndex] = true;
                    completedCount++;
                    turnaroundTime[minPriorityIndex] = currentTime - processes.get(minPriorityIndex).get_ArrivalTime();
                    waitingTime[minPriorityIndex] = turnaroundTime[minPriorityIndex] - processes.get(minPriorityIndex).get_BurstTime();
                }
            }
        }
    }

    public void schedule() {
        int n = processes.size();
        int currentTime = 0;
        boolean[] completed = new boolean[n];
        int completedCount = 0;

        while (completedCount < n) {
            int minPriorityIndex = -1;
            int minPriority = Integer.MAX_VALUE;
            int minPriorityArrivalTime = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < minPriority) {
                    minPriorityIndex = i;
                    minPriority = processes.get(i).getPriority();
                    minPriorityArrivalTime = processes.get(i).get_ArrivalTime();
                }
            }

            if (minPriorityIndex == -1) {
                currentTime++;
                timeline.add(-1); // idle time
            } else {
                if (preemptive) {
                    int remainingBurstTime = remainingTime[minPriorityIndex];
                    int nextArrivalTime = minPriorityIndex < n - 1 ? processes.get(minPriorityIndex + 1).get_ArrivalTime() : Integer.MAX_VALUE;
                    int runTime = Math.min(remainingBurstTime, nextArrivalTime - currentTime);
                    int highestPriorityIndex = minPriorityIndex; // Keep track of the highest priority process
                    while (remainingTime[highestPriorityIndex] > 0) {
                        // Check if a higher-priority process arrives
                        for (int i = 0; i < n; i++) {
                            if (!completed[i] && i != highestPriorityIndex && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < processes.get(highestPriorityIndex).getPriority()) {
                                highestPriorityIndex = i;
                            }
                        }
                        // Check if the current process should be preempted
                        if (highestPriorityIndex != minPriorityIndex) {
                            minPriorityIndex = highestPriorityIndex;
                            remainingBurstTime = remainingTime[minPriorityIndex];
                            nextArrivalTime = minPriorityIndex < n - 1 ? processes.get(minPriorityIndex + 1).get_ArrivalTime() : Integer.MAX_VALUE;
                            runTime = Math.min(remainingBurstTime, nextArrivalTime - currentTime);
                        }
                        timeline.add(processes.get(minPriorityIndex).get_ProcessName());
                        remainingTime[minPriorityIndex]--;
                        currentTime++;
                        if (remainingTime[minPriorityIndex] == 0) {
                            completed[minPriorityIndex] = true;
                            completedCount++;
                            turnaroundTime[minPriorityIndex] = currentTime - processes.get(minPriorityIndex).get_ArrivalTime();
                            waitingTime[minPriorityIndex] = turnaroundTime[minPriorityIndex] - processes.get(minPriorityIndex).get_BurstTime();
                            // Reset the highest priority process to the next highest priority
                            for (int i = 0; i < n; i++) {
                                if (!completed[i] && processes.get(i).get_ArrivalTime() <= currentTime && processes.get(i).getPriority() < processes.get(highestPriorityIndex).getPriority()) {
                                    highestPriorityIndex = i;
                                }
                            }
                        }
                    }
                } else {
                    int runTime = processes.get(minPriorityIndex).get_BurstTime();
                    for (int i = 0; i < runTime; i++) {
                        timeline.add(processes.get(minPriorityIndex).get_ProcessName());
                        remainingTime[minPriorityIndex]--;
                        currentTime++;
                    }
                    completed[minPriorityIndex] = true;
                    completedCount++;
                    turnaroundTime[minPriorityIndex] = currentTime - processes.get(minPriorityIndex).get_ArrivalTime();
                    waitingTime[minPriorityIndex] = turnaroundTime[minPriorityIndex] - processes.get(minPriorityIndex).get_BurstTime();
                }
            }
        }
    }

    private void updateRemainingTimeTable() {
        // System.out.print("Process\t\tRemaining Burst Time\n");
        for (int i = 0; i < processes.size(); i++) {
            // System.out.print(processes.get(i).get_ProcessName() + "\t\t" + remainingTime[i] + "\n");
            RT.add(remainingTime[i]);

        }

    }




    private void updateprintGanttChart() {

        // Print Gantt Chart
        int i = 0;
        // System.out.print("\n");
        for (int process : timeline) {

            // System.out.print(i + "|" + "p" + process + "|");
            i += 1;
        }
        // System.out.print(i);
        // System.out.print("\n");


    }

    public static ArrayList<String> displayGanttChart(ArrayList<Process> processes, boolean preemptive) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemptive);
        scheduler.schedule();

        ArrayList<String> ganttChart = new ArrayList<>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (int process : timeline) {
            sb.append(i).append(" ").append("|").append("p").append(process).append("|").append(" ");
            i += 1;
        }
        sb.append(i);
        ganttChart.add(sb.toString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ganttChart;
    }






    public static ArrayList<Integer> GanttChartnameID(ArrayList<Process> processes, boolean preemptive) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemptive);
        scheduler.schedule();
        ArrayList<Integer> nameID = new ArrayList<>();
        int previousProcess = -1; // Initialize with a value that doesn't exist in the ArrayList
        for (int process : timeline) {
            // Check if the process is the same as the previous process, if not, add it to the ArrayList
            if (process != previousProcess) {
                nameID.add(process);
                previousProcess = process;
            }
        }
        return nameID;
    }


    public static ArrayList<Integer> GanttChartparitionstime(ArrayList<Process> processes, boolean preemptive) {
        int i = 0;
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemptive);
        scheduler.schedule();
        ArrayList<Integer> paritionstime = new ArrayList<>();
        int previousProcess = -1; // Initialize with a value that doesn't exist in the ArrayList
        for (int process : timeline) {
            // Check if the process is the same as the previous process
            if (process == previousProcess) {
                i += 1; // Increment counter if the process is the same as the previous process
            } else {
                // If the process is different from the previous process, reset the counter
                // and add the counter value to the ArrayList
                if (previousProcess != -1) {
                    paritionstime.add(i);
                }
                i = 1; // Reset counter to 1 for the new process
                previousProcess = process;
            }
        }

        // Add the last counter value to the ArrayList after the loop
        if (previousProcess != -1) {
            paritionstime.add(i);
        }

        return paritionstime;
    }





    public void printRemainingTimeTable() {
        System.out.print("\nFinal Remaining Burst Time Table:\n");
        System.out.print("Process\t\tRemaining Burst Time\n");
        for (int i = 0; i < processes.size(); i++) {
            System.out.print(processes.get(i).get_ProcessName() + "\t\t" + remainingTime[i] + "\n");
            RT.add(remainingTime[i]);
        }

    }

    public static ArrayList<Integer> RemainingTime(ArrayList<Process> processes, boolean preemptive) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemptive);
        for (int i = 0; i < processes.size(); i++) {
            PriorityScheduler.RT.add(processes.get(i).get_BurstTime());
        }
        scheduler.Liveschedule();

        return PriorityScheduler.RT;

    }

    public void printStatistics(ArrayList<Process> processes, boolean preemptive) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemptive);
        scheduler.schedule();
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        System.out.print("\nStatistics:\n");
        System.out.print("Process\t\tWaiting Time\tTurnaround Time\n");
        for (int i = 0; i < processes.size(); i++) {
            System.out.print(processes.get(i).get_ProcessName() + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i] + "\n");
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }
        System.out.printf("Average waiting time: %.2f\n", totalWaitingTime / processes.size());
        System.out.printf("Average turnaround time: %.2f\n", totalTurnaroundTime / processes.size());
    }

    public float Average_waiting_time(ArrayList<Process> processes, boolean preemative) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemative);
        scheduler.schedule();
        float AWT = 0;
        for (int i = 0; i < waitingTime.length; i++) {

            AWT += scheduler.waitingTime[i];
        }

        return AWT / processes.size();
    }

    public float Average_Turnaround_Time(ArrayList<Process> processes, boolean preemative) {
        PriorityScheduler scheduler = new PriorityScheduler(processes, preemative);
        scheduler.schedule();
        float ATR = 0;
        for (int i = 0; i < turnaroundTime.length; i++) {

            ATR += scheduler.turnaroundTime[i];
        }

        return ATR / processes.size();
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 5, 0, 3));
        processes.add(new Process(2, 4, 2, 2));
        processes.add(new Process(3, 6, 3, 1));
        processes.add(new Process(4, 3, 5, 4));

        //PriorityScheduler scheduler = new PriorityScheduler(processes, true);
        //scheduler.Liveschedule();
        System.out.println(RemainingTime( processes,true));
        //scheduler.printRemainingTimeTable();
        //scheduler.printStatistics(processes,true);
//        for(int i=0; i<processes.size();i++ ){
//            System.out.println(processes.get(i).get_BurstTime());
//        }
        //System.out.println(scheduler.Average_Turnaround_Time(processes, true));

    }

}