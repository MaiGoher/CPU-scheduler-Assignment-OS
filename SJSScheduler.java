/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;
/**
 *
 * @author: Maram Ahmed
 */
import java.util.*;
public class SJSScheduler {
    private List<Process> Processes;
    private List<Integer> WaitingTime = new ArrayList<>();
    private List<Integer> TurnAroundTime=new ArrayList<>();
    private List<String> GanttChart = new ArrayList<>();
    private List<Integer> ProcessNames=new ArrayList<>();
    private List<Integer> ChartEnds=new ArrayList<>();
    private ArrayList<Integer> RemaingBurstTimes=new ArrayList<>();
    
    public SJSScheduler(ArrayList<Process> Processes)
    {this.Processes=Processes;}
    public void scheduleNonPreemptive() {
        // Sort the processes by arrival time to ensure that the process selected is available 
        Collections.sort(Processes, Comparator.comparingInt(Process::get_ArrivalTime));
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
                GanttChart.add(" | P"+shortestJob.get_ProcessName());
                ProcessNames.add(shortestJob.get_ProcessName());
               if(i==0)
                        {shortestJob.set_LowerEnd(0);
                      //  ChartEnds.add(0);
                        ChartEnds.add(shortestJob.get_BurstTime());
                        CurrentBT=shortestJob.get_BurstTime();
                        shortestJob.set_HigherEnd(CurrentBT);
                        shortestJob.set_WaitingTime(shortestJob.get_LowerEnd()-shortestJob.get_ArrivalTime());
                        WaitingTime.add(shortestJob.get_WaitingTime());
                        shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd()-shortestJob.get_ArrivalTime());
                       TurnAroundTime.add(shortestJob.get_TurnAroundTime());
                        }
               else
                        {shortestJob.set_LowerEnd(CurrentBT);
                        ChartEnds.add(shortestJob.get_BurstTime());
                        high=shortestJob.get_BurstTime();
                        shortestJob.set_HigherEnd(high+CurrentBT);
                        CurrentBT=high+CurrentBT;
                        shortestJob.set_WaitingTime(shortestJob.get_LowerEnd()-shortestJob.get_ArrivalTime());
                        WaitingTime.add(shortestJob.get_WaitingTime());
                        shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd()-shortestJob.get_ArrivalTime());
                        TurnAroundTime.add(shortestJob.get_TurnAroundTime());
                        }
               
                currentTime += shortestJob.get_BurstTime();
               Processes.remove(shortestJob);
                i++;
            } else {
                GanttChart.add("|   ");
                currentTime++;
            }
        }

          GanttChart.add(" |");
    }
    
 public void scheduleNonPreemptive_Live() {
    // Sort the processes by arrival time to ensure that the process selected is available
    Collections.sort(Processes, Comparator.comparingInt(Process::get_ArrivalTime));
    int currentTime = 0;
    int i = 0;
    int currentBT = 0;
    int high = 0;

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
            String processName = "P" + shortestJob.get_ProcessName();
            while (shortestJob.get_BurstTime() > 0) {
                GanttChart.add(" | " + processName);
                currentTime++;
                shortestJob.set_BurstTime(shortestJob.get_BurstTime() - 1);

                // Print the updated Gantt Chart with remaining burst time every second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Clear the console (optional)
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Gantt Chart: ");
                for (String entry : GanttChart) {
                    System.out.print(entry);
                }
                System.out.println();

                // Print the remaining burst times in a separate line
                System.out.println("Remaining Burst Times:");
                for (Process process : Processes) {
                    System.out.println("P" + process.get_ProcessName() + ": " + process.get_BurstTime());
                }
            }

            Processes.remove(shortestJob);
            i++;
        } else {
            GanttChart.add("|   ");
            currentTime++;
        }
    }

    GanttChart.add(" |");
}
 
    public  ArrayList<Integer> ProcessNames()
     {System.out.println(ProcessNames);
      ArrayList<Integer> arrayList = new ArrayList<>(ProcessNames);
         return arrayList;
      }
    
    public  ArrayList<Integer> ChartEnds()
     {System.out.println(ChartEnds);
          ArrayList<Integer> arrayList = new ArrayList<>(ChartEnds);
         return arrayList;
      }
    
    public void PrintGanttChart()
    {   for (int i=0;i<GanttChart.size();i++)
        System.out.print(GanttChart.get(i));
     System.out.println("\n-----------------------------------------------------------");}

    public double CalcAvgWaitingTime()
    { double avg=0;
        for(int i=0;i<WaitingTime.size();i++)
           {avg+=WaitingTime.get(i);}
        avg=(double)avg/(WaitingTime.size());
        return avg;
    }
      public void newFunction()
     { Collections.sort(Processes, Comparator.comparingInt(Process::get_ArrivalTime));
        int currentTime = 0;
        int completedProcesses = 0;
        int in_execution=0;
        Process shortestJob = null;
        while (completedProcesses < Processes.size())
        {    
            int minBurstTime = Integer.MAX_VALUE;
            if(in_execution==0)
            { for (Process process : Processes) {
             if (process.get_ArrivalTime() <= currentTime && !(process.isCompleted())) {
                    if (shortestJob == null || process.get_BurstTime() < minBurstTime) {
                        shortestJob = process;
                        minBurstTime=process.get_BurstTime();
                    }  
          }
        }}
          
           if (shortestJob != null) {
               GanttChart.add(" |  P" + shortestJob.getProcessName());
               shortestJob.set_RemainingBurstTime(shortestJob.get_RemainingBurstTime() - 1);
            currentTime++;
            if (shortestJob.get_RemainingBurstTime() == 0) {
                in_execution=0;
                shortestJob.setCompleted(true);
                 completedProcesses++;
            }
            else{in_execution=1;}
            /* 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
            // Clear the console (optional)
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Gantt Chart: ");
            for (String entry : GanttChart) {
                System.out.print(entry);
            }
            System.out.println();

            // Print the remaining burst times in a separate line
            
            System.out.println("Remaining Burst Times:");
            for (Process process : Processes) {
                RemaingBurstTimes.add(process.get_RemainingBurstTime());
                System.out.println("P" + process.getProcessName() + ": " + process.get_RemainingBurstTime());
            }
            
           }
           else
           { GanttChart.add("|     ");
            currentTime++;
        }
        }
              GanttChart.add("|");
   }
        
        public ArrayList<Integer> RemainingBurstTimes()
    {System.out.println(RemaingBurstTimes);
        return RemaingBurstTimes;
    }
     public double CalcAvgTurnAroundTime()
    { double avg=0;
        for(int i=0;i<TurnAroundTime.size();i++)
           {avg+=TurnAroundTime.get(i);}
        avg=(double)avg/(TurnAroundTime.size());
        return avg;
    }
 /*
      public static void main(String[] args) {
        // Create a list of processes
        ArrayList<Process> processes = new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the number of processes");
        int size=sc.nextInt();
        for(int i=0;i<size;i++)
        {System.out.println("enter the process info");
        int a1=sc.nextInt();
        int a2=sc.nextInt();
        int a3=sc.nextInt();
        Process p = new Process(a1,a2,a3);
        processes.add(p);
        }*/
     public static void main(String[] args) {
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
        sjfNonPreemptiveScheduler.ProcessNames();
        sjfNonPreemptiveScheduler.ChartEnds();
       sjfNonPreemptiveScheduler.PrintGanttChart();
        System.out.printf("Average Waiting Time: %.2f\n", sjfNonPreemptiveScheduler.CalcAvgWaitingTime());
        System.out.printf("Average Turn Around Time: %.2f\n", sjfNonPreemptiveScheduler.CalcAvgTurnAroundTime());
  //  }
}
}
    