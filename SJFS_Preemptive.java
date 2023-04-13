/**
 *
 * @author: Maram Ahmed
 */
import java.util.*;

public class SJFS_Preemptive {
     private List<Process> processes;

    public SJFS_Preemptive(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public void schedulePreemptive() {
        // Sort the processes by arrival time to ensure that the process selected is available
        Collections.sort(processes, Comparator.comparingInt(Process::get_ArrivalTime));

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int currentTime = 0;
        int completedProcesses = 0;
        int originalBT=0;
        Process previous= null;
        Process shortestJob = null;
        int steps=1;
        while (completedProcesses < processes.size()) {
           // Process shortestJob = null;
            int minBurstTime = Integer.MAX_VALUE;
            previous=shortestJob;
            for (Process process : processes) {
                if (process.get_ArrivalTime() <= currentTime && !process.isCompleted()) {
                    if (process.get_RemainingBurstTime() < minBurstTime) {
                        shortestJob = process;
                        minBurstTime = process.get_RemainingBurstTime();
                    }
                }
            }
            if (shortestJob != null) {
                if(shortestJob !=previous)
                {  System.out.printf("|  P%d ", shortestJob.get_ProcessName());
                   if(previous==null){shortestJob.set_LowerEnd(0);}
                   else { steps=1;}
                }
                else 
                {  
                   steps++;
                }
                
                shortestJob.set_RemainingBurstTime(shortestJob.get_RemainingBurstTime() - 1);
                currentTime++;
                
                if (shortestJob.get_RemainingBurstTime() == 0) {               
                    shortestJob.setCompleted(true);
                    shortestJob.set_HigherEnd(currentTime);
                    shortestJob.set_LowerEnd(shortestJob.get_HigherEnd()-steps);
                    shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd() - shortestJob.get_ArrivalTime());
               shortestJob.set_WaitingTime(shortestJob.get_HigherEnd()-(shortestJob.get_ArrivalTime()+shortestJob.get_BurstTime()));
                    totalWaitingTime += shortestJob.get_WaitingTime();
                    totalTurnaroundTime += shortestJob.get_TurnAroundTime();
                    completedProcesses++;
             
                }
            } else {
                System.out.print("|       ");
                currentTime++;
            }
        }

        System.out.println("|");
        System.out.println("-----------------------------------------------------------");

        System.out.println("Total waiting time = " + totalWaitingTime);
        System.out.println("Total Turnaround time = " + totalTurnaroundTime);

        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
    }
    public static void main(String[] args) {
        // Create a list of processes
        ArrayList<Process> processes = new ArrayList<>();

        Process process1 = new Process(1, 7, 0);
        Process process2 = new Process(2, 4, 2);
        Process process3 = new Process(3, 1, 4);
        Process process4 = new Process(4, 4, 5);
        
   /* Process process1 = new Process(1, 8, 0);
        Process process2 = new Process(2, 4, 1);
        Process process3 = new Process(3, 9, 2);
        Process process4 = new Process(4, 5, 3);*/
        
        processes.add(process1);
        processes.add(process2);
        processes.add(process3);
        processes.add(process4);
        SJFS_Preemptive Scheduler = new SJFS_Preemptive(processes);
        Scheduler.schedulePreemptive();
}
}