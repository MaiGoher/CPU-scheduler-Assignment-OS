/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;
import java.util.*;

 public class SJFS_Preemptive {
     private List<Process> processes;
     private List<Integer> WaitingTime ;
    private List<Integer> TurnAroundTime;
    private List<String> GanttChart ;
    private  ArrayList<Integer> ProcessNames;
    private  ArrayList<Integer> ChartEnds;
    private  ArrayList<Integer> RemaingBurstTimes;
    private static ArrayList<Integer> RT;
   

    public SJFS_Preemptive(ArrayList<Process> processes) {
        this.processes = processes;
        this.RT = new ArrayList<>();
        this.WaitingTime = new ArrayList<>();
        this.TurnAroundTime=new ArrayList<>();
        this.GanttChart = new ArrayList<>();
        this.ProcessNames=new ArrayList<>();
        this.ChartEnds=new ArrayList<>();
        this.RemaingBurstTimes=new ArrayList<>();
        
        
    }

    
    public void schedulePreemptiveNew() {
    // Sort the processes by arrival time to ensure that the process selected is available
    Collections.sort(processes, Comparator.comparingInt(Process::get_ArrivalTime));
    
    int currentTime = 0;
    int completedProcesses = 0;
    Process previous = null;
    Process shortestJob = null;
    int steps = 1;
    int prev_steps=0;

    while (completedProcesses < processes.size()) {
        int minBurstTime = Integer.MAX_VALUE;
        previous = shortestJob;
        for (Process process : processes) {
            if (process.get_ArrivalTime() <= currentTime && !process.isCompleted()) {
                if (process.get_RemainingBurstTime() < minBurstTime) {
                    shortestJob = process;
                    minBurstTime = process.get_RemainingBurstTime();
                }
            }
        }
        if (shortestJob != null) {
            if (shortestJob != previous) {
                GanttChart.add(" |  P" + shortestJob.getProcessName());
                ProcessNames.add(shortestJob.get_ProcessName());
                if(previous!=null)
                { ChartEnds.add(currentTime-prev_steps);
                prev_steps=0;}
             //   System.out.println(ChartEnds);}
                if (previous == null) {
                    shortestJob.set_LowerEnd(0);
                } else {
                    steps = 1;
                }
            } else {
                steps++;
            }

            shortestJob.set_RemainingBurstTime(shortestJob.get_RemainingBurstTime() - 1);
            currentTime++;

            if (shortestJob.get_RemainingBurstTime() == 0) {
                shortestJob.setCompleted(true);
                shortestJob.set_HigherEnd(currentTime);
                shortestJob.set_LowerEnd(shortestJob.get_HigherEnd() - steps);
                shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd() - shortestJob.get_ArrivalTime());
                shortestJob.set_WaitingTime(shortestJob.get_HigherEnd() - (shortestJob.get_ArrivalTime() + shortestJob.get_BurstTime()));
                WaitingTime.add(shortestJob.get_WaitingTime());
                TurnAroundTime.add(shortestJob.get_TurnAroundTime());
                completedProcesses++;
            }
           
           
        } else {
            GanttChart.add("|     ");
            currentTime++;
        }
        prev_steps++;
   
    }
    ChartEnds.add(currentTime-prev_steps);
ChartEnds.add(currentTime);
ChartEnds.remove(0);
 for(int i=ChartEnds.size()-1;i>0;i--)
        {ChartEnds.set(i, ChartEnds.get(i)-ChartEnds.get(i-1));}
    GanttChart.add("|");
}
    
     public  ArrayList<Integer> ChartEnds()
     {   
        System.out.println(ChartEnds);
         return ChartEnds;
      }
    
    
    

    
    
    
    public void schedulePreemptive_Live() {
    // Sort the processes by arrival time to ensure that the process selected is available
    Collections.sort(processes, Comparator.comparingInt(Process::get_ArrivalTime));
    
    int currentTime = 0;
    int completedProcesses = 0;
    Process previous = null;
    Process shortestJob = null;
    int steps = 1;

    while (completedProcesses < processes.size()) {
        int minBurstTime = Integer.MAX_VALUE;
        previous = shortestJob;
        for (Process process : processes) {
            if (process.get_ArrivalTime() <= currentTime && !process.isCompleted()) {
                if (process.get_RemainingBurstTime() < minBurstTime) {
                    shortestJob = process;
                    minBurstTime = process.get_RemainingBurstTime();
                }
            }
        }
        if (shortestJob != null) {
            if (shortestJob != previous) {
                GanttChart.add(" |  P" + shortestJob.getProcessName());
                if (previous == null) {
                    shortestJob.set_LowerEnd(0);
                } else {
                    steps = 1;
                }
            } else {
                steps++;
            }

            shortestJob.set_RemainingBurstTime(shortestJob.get_RemainingBurstTime() - 1);
            currentTime++;

            if (shortestJob.get_RemainingBurstTime() == 0) {
                shortestJob.setCompleted(true);
                shortestJob.set_HigherEnd(currentTime);
                shortestJob.set_LowerEnd(shortestJob.get_HigherEnd() - steps);
                shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd() - shortestJob.get_ArrivalTime());
                shortestJob.set_WaitingTime(shortestJob.get_HigherEnd() - (shortestJob.get_ArrivalTime() + shortestJob.get_BurstTime()));
                WaitingTime.add(shortestJob.get_WaitingTime());
                TurnAroundTime.add(shortestJob.get_TurnAroundTime());
                completedProcesses++;
            }
            /*
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
*/
            // Print the remaining burst times in a separate line
            System.out.println("Remaining Burst Times:");
            for (Process process : processes) {
                RemaingBurstTimes.add(process.get_RemainingBurstTime());
                System.out.println("P" + process.getProcessName() + ": " + process.get_RemainingBurstTime());
            }
        } else {
            GanttChart.add("|     ");
            currentTime++;
        }
    }

    GanttChart.add("|");
}
    
    public ArrayList<Integer> RemainingBurstTimes()
    {System.out.println(RemaingBurstTimes);
        return RemaingBurstTimes;
    }
    
     public void PrintGanttChart()
    {   for (int i=0;i<GanttChart.size();i++)
        
        System.out.print(GanttChart.get(i));
     System.out.println("\n-----------------------------------------------------------");}
     
     public String GanttChart()
     {return GanttChart.toString();}

     public  ArrayList<Integer> ProcessNames()
     {System.out.println(ProcessNames);
         return ProcessNames;
     }
     /*
     public ArrayList<Integer> ChartEnds()
     {System.out.println(ChartEnds);
         return ChartEnds;
      }*/
     
     
     
     
     
      public void NewLive()
    {// Sort the processes by arrival time to ensure that the process selected is available
        ChartEnds.clear();
        ProcessNames.clear();

    Collections.sort(processes, Comparator.comparingInt(Process::get_ArrivalTime));
    
    int currentTime = 0;
    int completedProcesses = 0;
    Process previous = null;
    Process shortestJob = null;
    int steps = 1;
    int prev_steps=0;

    while (completedProcesses < processes.size()) {
        int minBurstTime = Integer.MAX_VALUE;
        previous = shortestJob;
        for (Process process : processes) {
            if (process.get_ArrivalTime() <= currentTime && !process.isCompleted()) {
                if (process.get_RemainingBurstTime() < minBurstTime) {
                    shortestJob = process;
                    minBurstTime = process.get_RemainingBurstTime();
                }
            }
        }
        if (shortestJob != null) {
            if (shortestJob != previous) {
                GanttChart.add(" |  P" + shortestJob.getProcessName());
                ProcessNames.add(shortestJob.get_ProcessName());
                if(previous!=null)
                { ChartEnds.add(currentTime-prev_steps);
                prev_steps=0;}
             //   System.out.println(ChartEnds);}
                if (previous == null) {
                    shortestJob.set_LowerEnd(0);
                } else {
                    steps = 1;
                }
            } else {
                steps++;
            }

            shortestJob.set_RemainingBurstTime(shortestJob.get_RemainingBurstTime() - 1);
            currentTime++;

            if (shortestJob.get_RemainingBurstTime() == 0) {
                shortestJob.setCompleted(true);
                shortestJob.set_HigherEnd(currentTime);
                shortestJob.set_LowerEnd(shortestJob.get_HigherEnd() - steps);
                shortestJob.set_TurnAroundTime(shortestJob.get_HigherEnd() - shortestJob.get_ArrivalTime());
                shortestJob.set_WaitingTime(shortestJob.get_HigherEnd() - (shortestJob.get_ArrivalTime() + shortestJob.get_BurstTime()));
                WaitingTime.add(shortestJob.get_WaitingTime());
                TurnAroundTime.add(shortestJob.get_TurnAroundTime());
                completedProcesses++;
                
            }
           
           
        } else {
            GanttChart.add("|     ");
            currentTime++;
        }
        prev_steps++;
         System.out.println("Remaining Burst Times:");
            for (Process process : processes) {
                RemaingBurstTimes.add(process.get_RemainingBurstTime());
                updateRemainingTimeTable();
                System.out.println("P" + process.getProcessName() + ": " + process.get_RemainingBurstTime());
            }
   
    }
    ChartEnds.add(currentTime-prev_steps);
ChartEnds.add(currentTime);
ChartEnds.remove(0);
 for(int i=ChartEnds.size()-1;i>0;i--)
        {ChartEnds.set(i, ChartEnds.get(i)-ChartEnds.get(i-1));}
    GanttChart.add("|");
}

     
     public static ArrayList<Integer> RemainingTime(ArrayList<Process> processes) {
        SJFS_Preemptive scheduler = new SJFS_Preemptive(processes);
        for (int i = 0; i < processes.size(); i++) {
           SJFS_Preemptive.RT.add(processes.get(i).get_BurstTime());
        }
        scheduler.NewLive();
        
       return SJFS_Preemptive.RT;
           
    }
     
      private void updateRemainingTimeTable() {
       // System.out.print("Process\t\tRemaining Burst Time\n");
        for (int i = 0; i < processes.size(); i++) {
           // System.out.print(processes.get(i).get_ProcessName() + "\t\t" + remainingTime[i] + "\n");
            RT.add(RemaingBurstTimes.get(i));
            
        }
      
    }
     
     
     
    public double CalcAvgWaitingTime()
    { double avg=0;
        for(int i=0;i<WaitingTime.size();i++)
           {avg+=WaitingTime.get(i);}
        avg=(double)avg/(WaitingTime.size());
        return avg;
    }
    
     public double CalcAvgTurnAroundTime()
    { double avg=0;
        for(int i=0;i<TurnAroundTime.size();i++)
           {avg+=TurnAroundTime.get(i);}
        avg=(double)avg/(TurnAroundTime.size());
        return avg;
    }
    
     
//         public static ArrayList<Integer> RemainingBurstTimes(ArrayList<Process> processes)
//    {    SJFS_Preemptive Scheduler = new SJFS_Preemptive(processes);
//        Scheduler.NewLive();
//       // System.out.println(RemaingBurstTimes);
//    
//        return RemaingBurstTimes;
//    }
//     
     
     
     
     
    public static void main(String[] args) {
       /*
        // Create a list of processes
        Scanner sc=new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();
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
       ArrayList<Process> processes = new ArrayList<>();
    /*    Process process1 = new Process(1, 3, 2);
        Process process2 = new Process(2, 4, 3);
         processes.add(process1);
          //System.out.println(processes);
        processes.add(process2);*/
         //System.out.println(processes);
     //   SJFS_Preemptive Scheduler = new SJFS_Preemptive(processes);
        //Scheduler.schedulePreemptive_Live();
       // Scheduler.PrintGanttChart();
    //    System.out.println("CalcAvgTurnAroundTime:"+Double.toString(Scheduler.CalcAvgTurnAroundTime()));
      //  System.out.println("CalcAvgWaitingTime:"+Double.toString(Scheduler.CalcAvgWaitingTime()));
                
       // Process process3 = new Process(3, 1, 4);
       // Process process4 = new Process(4, 4, 5);
  
        Process process1 = new Process(1, 8, 0);
        Process process2 = new Process(2, 4, 1);
       Process process3 = new Process(3, 9, 2);
     Process process4 = new Process(4, 5, 3);
            
        processes.add(process1);
        processes.add(process2);
        processes.add(process3);
        processes.add(process4);
        SJFS_Preemptive Scheduler = new SJFS_Preemptive(processes);
      //  Scheduler.schedulePreemptive();
      Scheduler.schedulePreemptive_Live();
     Scheduler.ProcessNames();
     Scheduler.ChartEnds();
        Scheduler.PrintGanttChart();
        System.out.println("CalcAvgTurnAroundTime:"+Double.toString(Scheduler.CalcAvgTurnAroundTime()));
        System.out.println("CalcAvgWaitingTime:"+Double.toString(Scheduler.CalcAvgWaitingTime()));
        Scheduler.RemainingBurstTimes();
   //    System.out.printf("Average Waiting Time: %.2f\n", Scheduler.CalcAvgWaitingTime());
     //   System.out.printf("Average Turn Around Time: %.2f\n", Scheduler.CalcAvgTurnAroundTime());
      //System.out.println(RemainingTime( processes));
//}
        }}