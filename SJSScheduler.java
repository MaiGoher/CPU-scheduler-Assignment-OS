

/**
 *
 * @author: Maram Ahmed
 */
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SJSScheduler {
    private List<Process> Processes;
    
    public SJSScheduler(ArrayList<Process> Processes)
    {this.Processes=Processes;}
    public void scheduleNonPreemptive() {
        // Sort the processes by arrival time to ensure that the process selected is available 
        Collections.sort(Processes, Comparator.comparingInt(Process::get_ArrivalTime));

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int currentTime = 0;
        int i=0;
        int CurrentBT=0;
        int high=0;

        while (!Processes.isEmpty()) {
            Process shortestJob = null;
            for (Process process : Processes) {
                if (process.get_ArrivalTime() <= currentTime) {
                    if (shortestJob == null || process.get_BurstTime() < shortestJob.get_BurstTime()) {
                        shortestJob = process;
                    }        
                }
            }

            if (shortestJob != null) {
               System.out.printf("|  P%d ", shortestJob.get_ProcessName());
               if(i==0)
                        {shortestJob.set_LowerEnd(0);
                        CurrentBT=shortestJob.get_BurstTime();
                        shortestJob.set_HigherEnd(CurrentBT);
                        shortestJob.set_WaitingTime(shortestJob.get_LowerEnd()-shortestJob.get_ArrivalTime());
                        shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd()-shortestJob.get_ArrivalTime());
                        }
               else
                        {shortestJob.set_LowerEnd(CurrentBT);
                        high=shortestJob.get_BurstTime();
                        shortestJob.set_HigherEnd(high+CurrentBT);
                        CurrentBT=high+CurrentBT;
                        shortestJob.set_WaitingTime(shortestJob.get_LowerEnd()-shortestJob.get_ArrivalTime());
                        shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd()-shortestJob.get_ArrivalTime());
                        }
               
                currentTime += shortestJob.get_BurstTime();
            totalWaitingTime+=shortestJob.get_WaitingTime();
            totalTurnaroundTime+=shortestJob.get_TurnAroundTime();
            
                Processes.remove(shortestJob);
                i++;
            } else {
                System.out.print("|       ");
                currentTime++;
            }
        }

        System.out.println("|");

        System.out.println("-----------------------------------------------------------");
        
        System.out.println("Total waiting time = "+totalWaitingTime);
        System.out.println("Total Turn around time ="+totalTurnaroundTime);
        double avgWaitingTime = (double) totalWaitingTime / (i);
        double avgTurnaroundTime = (double) totalTurnaroundTime / (i);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
    }
 
      public static void main(String[] args) {
        // Create a list of processes
        ArrayList<Process> processes = new ArrayList<>();

        Process process1 = new Process(1, 2, 0);
        Process process2 = new Process(2, 4, 0);
        Process process3 = new Process(3, 1, 2);
        Process process4 = new Process(4, 3, 2);
        Process process5 = new Process(5, 2, 3);
        
        processes.add(process1);
        processes.add(process2);
        processes.add(process3);
        processes.add(process4);
        processes.add(process5);

        // Schedule processes using SJF Non-preemptive algorithm
        SJSScheduler sjfNonPreemptiveScheduler = new SJSScheduler(processes);
        sjfNonPreemptiveScheduler.scheduleNonPreemptive();
    }
}
    

