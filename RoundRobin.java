/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RoundRobin {

    private static List<Integer> ProcessNames = new ArrayList<>();
    private static List<Integer> ChartEnds = new ArrayList<>();

    private static List<Process2> filter(List<Process2> process2s, int arrival_time) {
        List<Process2> processes_out = new ArrayList<>();
        process2s.stream().filter((i) -> (i.getArrivalTime() <= arrival_time && i.getRemainingBurstTime() != 0)).forEachOrdered(processes_out::add);
        Utility.sortBrust(processes_out);
        return processes_out;
    }

    private static void sortchartarrivale(List<Process2> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process2) o1).getChartarrival() == ((Process2) o2).getChartarrival()) {
                return 0;
            } else if (((Process2) o1).getChartarrival() < ((Process2) o2).getChartarrival()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static Output Calc(List<Process2> input_process2s, int QuantumTime) {
        int time = input_process2s.get(0).getArrivalTime();
        List<Process2> tempList1 = new ArrayList<>();
        while (true) {
            List<Process2> tempList2 = filter(input_process2s, time);
            if (tempList1.size() == input_process2s.size()) {
                break;
            }
            if (tempList2.isEmpty()) {
                time++;
            } else {
                sortchartarrivale(tempList2);
                if (tempList2.get(0).getRemainingBurstTime() >= QuantumTime) {
                    time += QuantumTime;
                    tempList2.get(0).setRemainingBurstTime(tempList2.get(0).getRemainingBurstTime() - QuantumTime);
                    tempList2.get(0).setChartarrival(time);
                    if (tempList2.get(0).getRemainingBurstTime() == 0) {
                        tempList2.get(0).setHigher_end(time);
                        tempList1.add(tempList2.get(0));
                    }
                } else {
                    time += tempList2.get(0).getRemainingBurstTime();
                    tempList2.get(0).setRemainingBurstTime(0);
                    if (tempList2.get(0).getRemainingBurstTime() == 0) {
                        tempList2.get(0).setHigher_end(time);
                        tempList1.add(tempList2.get(0));
                    }
                }
            }
        }
        for (int i = 0; i < tempList1.size(); i++) {
            tempList1.get(i).setTurnAroundTime(tempList1.get(i).getHigher_end() - tempList1.get(i).getArrivalTime());
            tempList1.get(i).setWaitingTime(tempList1.get(i).getTurnAroundTime() - tempList1.get(i).getBurstTime());
        }
        Utility.sortArrival(tempList1);
        double avg_w = 0;
        double avg_t = 0;
        for (Process2 i : tempList1) {
            avg_w += i.getWaitingTime();
            avg_t += i.getTurnAroundTime();
        }
        return new Output(tempList1, Utility.FormatDouble(avg_w / tempList1.size()), Utility.FormatDouble(avg_t / tempList1.size()));
    }

    /*
    public static String generateGanttChart(List<Process2> process2s, int quantum) {
        StringBuilder ganttChart = new StringBuilder();
        int currentTime = 0;
        int remainingTime[] = new int[process2s.size()];
        boolean completed[] = new boolean[process2s.size()];
        // Initialize remaining time with burst time of each process
        for (int i = 0; i < process2s.size(); i++) {
            remainingTime[i] = process2s.get(i).getBurstTime();
        }
        while (true) {
            boolean allCompleted = true;
            // Traverse all process2s one by one repeatedly
            for (int i = 0; i < process2s.size(); i++) {
                if (remainingTime[i] > 0) {
                    allCompleted = false;
                    // Reduce remaining time of this process by quantum
                    if (remainingTime[i] > quantum) {
                        ganttChart.append(currentTime).append(" |").append(process2s.get(i).getProcessName()).append("| ");
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        // If remaining time is smaller than or equal to quantum, then process is completed
                        ganttChart.append(currentTime).append(" |").append(process2s.get(i).getProcessName()).append("| ");
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completed[i] = true;
                    }
                }
            }
            // If all process2s are completed, then break the loop
            if (allCompleted)
                break;
        }
        ganttChart.append(currentTime);
        return ganttChart.toString();
    }
     */
//    public static String generateGanttChart(List<Process2> process2s, int quantum) {
//        StringBuilder ganttChart = new StringBuilder();
//        int currentTime = 0;
//        int remainingTime[] = new int[process2s.size()];
//        boolean completed[] = new boolean[process2s.size()];
//
//        // Initialize remaining time with burst time of each process
//        for (int i = 0; i < process2s.size(); i++) {
//            remainingTime[i] = process2s.get(i).getBurstTime();
//        }
//
//        while (true) {
//            boolean allCompleted = true;
//
//            // Traverse all process2s one by one repeatedly
//            for (int i = 0; i < process2s.size(); i++) {
//                if (remainingTime[i] > 0) {
//                    allCompleted = false;
//
//                    // Reduce remaining time of this process by quantum
//                    if (remainingTime[i] > quantum) {
//                        ganttChart.append(currentTime).append(" | P").append(process2s.get(i).getProcessName()).append("| ");
//                        ProcessNames.add(Integer.parseInt(process2s.get(i).getProcessName()));
//                        if (currentTime != 0) {
//                            ChartEnds.add(currentTime);
//                        }
//
//                        currentTime += quantum;
//                        remainingTime[i] -= quantum;
//                    } else {
//                        // If remaining time is smaller than or equal to quantum, then process is completed
//                        ganttChart.append(currentTime).append(" | P").append(process2s.get(i).getProcessName()).append("| ");
//                        ProcessNames.add(Integer.parseInt(process2s.get(i).getProcessName()));
//                        if (currentTime != 0) {
//                            ChartEnds.add(currentTime);
//                        }
//                        currentTime += remainingTime[i];
//                        remainingTime[i] = 0;
//                        completed[i] = true;
//                    }
//                }
//            }
//
//            // If all process2s are completed, then break the loop
//            if (allCompleted) {
//                break;
//            }
//        }
//        ganttChart.append(currentTime);
//        ChartEnds.add(currentTime);
//        for (int i = ChartEnds.size() - 1; i > 0; i--) {
//            ChartEnds.set(i, ChartEnds.get(i) - ChartEnds.get(i - 1));
//        }
//        return ganttChart.toString();
//    }


//    public static void generateGanttChart_Live(List<Process2> processes, int quantum) {
//        int currentTime = 0;
//        int remainingTime[] = new int[processes.size()];
//        boolean completed[] = new boolean[processes.size()];
//
//        // Initialize remaining time with burst time of each process
//        for (int i = 0; i < processes.size(); i++) {
//            remainingTime[i] = processes.get(i).getBurstTime();
//        }
//
//        while (true) {
//            boolean allCompleted = true;
//            StringBuilder ganttChart = new StringBuilder();
//
//            // Traverse all processes one by one repeatedly
//            for (int i = 0; i < processes.size(); i++) {
//                if (remainingTime[i] > 0) {
//                    allCompleted = false;
//
//                    // Reduce remaining time of this process by quantum
//                    if (remainingTime[i] > quantum) {
//                        ganttChart.append(currentTime).append(" ");
//
//                        // Print process name when it enters the execution
//                        System.out.println(currentTime + " |" + processes.get(i).getProcessName() + "| Entered");
//                        currentTime += quantum;
//                        remainingTime[i] -= quantum;
//                    } else {
//                        // If remaining time is smaller than or equal to quantum, then process is completed
//                        ganttChart.append(currentTime).append(" ");
//                        // Print process name when it enters the execution
//                        System.out.println(currentTime + " |" + processes.get(i).getProcessName() + "| Entered");
//                        currentTime += remainingTime[i];
//                        remainingTime[i] = 0;
//                        completed[i] = true;
//                    }
//                }
//            }
//
//            // If all processes are completed, then break the loop
//            if (allCompleted) {
//                break;
//            }
//
//            System.out.println(ganttChart.toString());
//
//            try {
//                // Sleep for one second
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static List ProcessNames() {
        return ProcessNames;
    }

    public static List ChartEnds() {
        return ChartEnds;
    }
//        public static void RoundRobinLive(List<Process2> Processes, int QuantumTime)
//        { Collections.sort(Processes, Comparator.comparingInt(Process2::getArrivalTime));
//        int currentTime = 0;
//        int completedProcesses = 0;
//        int in_execution=0;
//        int order=0;
//        int i=0;
//        int steps=0;
//        Process2 shortestJob = null;
//        while (completedProcesses < Processes.size())
//        {
//            if(i>Processes.size()-1)
//            {i=0;}
//            if(in_execution==0)
//            {
//                if (Processes.get(i).getArrivalTime() <= currentTime && !Processes.get(i).isCompleted())
//                {shortestJob= Processes.get(i);}
//                else {for(Process2 process:Processes)
//                { if(!process.isCompleted())
//                {shortestJob=process;
//                    break;
//                }
//                }
//                }
//            }
//            if (shortestJob != null) {
//                GanttChart.add(" |  P" + shortestJob.getProcessName());
//                shortestJob.setRemainingBurstTime(shortestJob.getRemainingBurstTime() - 1);
//                steps++;
//                if(steps==QuantumTime)
//                {steps=0;
//                    in_execution=0;}
//                else
//                {in_execution=1;}
//
//                currentTime++;
//                if (shortestJob.getRemainingBurstTime() == 0) {
//                    in_execution=0;
//                    shortestJob.setCompleted(true);
//                    completedProcesses++;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                // Clear the console (optional)
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
//                System.out.println("Gantt Chart: ");
//                for (String entry : GanttChart) {
//                    System.out.print(entry);
//                }
//                System.out.println();
//
//                // Print the remaining burst times in a separate line
//
//                System.out.println("Remaining Burst Times:");
//                for (Process2 process : Processes) {
//                    RemaingBurstTimes.add(process.getRemainingBurstTime());
//                    System.out.println("P" + process.getProcessName() + ": " + process.getRemainingBurstTime());
//
//                }
//
//                i++;
//
//            }
//            else
//            { GanttChart.add("|     ");
//                currentTime++;
//            }
//        }
//        GanttChart.add("|");
//        System.out.println(RemaingBurstTimes);
//    }

    public static ArrayList<Integer> RemainingTimeLive(ArrayList<Integer> inputArrayList, int quantumTime , List<Process2> p) {
        ArrayList<Integer> remainingTime = new ArrayList<Integer>();

        boolean allZero = false;

        while (!allZero) {

            if(!(remainingTime.isEmpty())){

                int numToRemove = inputArrayList.size();
                int listSize = remainingTime.size();
                for (int i = 0; i < numToRemove; i++) {
                    remainingTime.remove(listSize - 1 - i);
                }
            }
            // iterate through each element in the inputArrayList
            for (int i = 0; i < inputArrayList.size(); i++) {
                int timeLeft = inputArrayList.get(i);

                // iterate quantumTime times or until element reaches 0
                for (int j = 0; j < quantumTime; j++) {
                    // subtract 1 from the element's remaining time
                    if (timeLeft > 0) {
                        timeLeft--;
                    }
                    inputArrayList.set(i, timeLeft);
                    for (int n = 0; n < inputArrayList.size(); n++) {

                        if (!allZero) {
                            remainingTime.add(inputArrayList.get(n));
                        }

                    }
                    allZero = inputArrayList.stream().allMatch(element -> element == 0);
                }

            }

        }
        return remainingTime;
    }

    public static ArrayList<Integer> ProcessTimeLive(ArrayList<Integer> inputArrayList, int quantumTime , List<Process2> p) {
        ArrayList<Integer> remainingTime = new ArrayList<Integer>();
        List<Process2> B = ArrangeP(p);
        ArrayList<String> A = new ArrayList<String>();
        for(int i=0 ; i < p.size() ; i++){
            A.add(B.get(i).getProcessName());
        }


        ArrayList<Integer> PL = new ArrayList<Integer>();
        boolean allZero = false;

        while (!allZero) {

            if(!(remainingTime.isEmpty())){

                int numToRemove = inputArrayList.size();
                int listSize = remainingTime.size();
                for (int i = 0; i < numToRemove; i++) {
                    remainingTime.remove(listSize - 1 - i);
                }
            }
            // iterate through each element in the inputArrayList
            for (int i = 0; i < inputArrayList.size(); i++) {
                int timeLeft = inputArrayList.get(i);
                int P_ID = Integer.parseInt(A.get(i));
                if(inputArrayList.get(i)!=0)  PL.add(P_ID);
                // iterate quantumTime times or until element reaches 0
                for (int j = 0; j < quantumTime; j++) {
                    // subtract 1 from the element's remaining time
                    if (timeLeft > 0) {
                        timeLeft--;
                    }
                    inputArrayList.set(i, timeLeft);

                    for (int n = 0; n < inputArrayList.size(); n++) {

                        if (!allZero) {
                            remainingTime.add(inputArrayList.get(n));
                        }

                    }
                    allZero = inputArrayList.stream().allMatch(element -> element == 0);
                }


            }

        }
        return PL;
    }


    public static ArrayList<Process2> ProcessR(ArrayList<Integer> inputArrayList, int quantumTime , List<Process2> p) {
        ArrayList<Integer> remainingTime = new ArrayList<Integer>();
        List<Process2> B = ArrangeP(p);



        ArrayList<Process2> PL = new ArrayList<>();
        boolean allZero = false;

        while (!allZero) {

            if(!(remainingTime.isEmpty())){

                int numToRemove = inputArrayList.size();
                int listSize = remainingTime.size();
                for (int i = 0; i < numToRemove; i++) {
                    remainingTime.remove(listSize - 1 - i);
                }
            }
            // iterate through each element in the inputArrayList
            for (int i = 0; i < inputArrayList.size(); i++) {
                int timeLeft = inputArrayList.get(i);
                Process2 P_ID = B.get(i);
                if(inputArrayList.get(i)!=0)  PL.add(P_ID);
                // iterate quantumTime times or until element reaches 0
                for (int j = 0; j < quantumTime; j++) {
                    // subtract 1 from the element's remaining time
                    if (timeLeft > 0) {
                        timeLeft--;
                    }
                    inputArrayList.set(i, timeLeft);

                    for (int n = 0; n < inputArrayList.size(); n++) {

                        if (!allZero) {
                            remainingTime.add(inputArrayList.get(n));
                        }

                    }
                    allZero = inputArrayList.stream().allMatch(element -> element == 0);
                }


            }

        }
        return PL;
    }




    public static List<Process2> ArrangeP(List<Process2> p) {

        for (int i = 1; i < p.size(); i++) {
            Process2 process = p.get(i);
            int j = i - 1;
            while (j >= 0 && process.getArrivalTime() < p.get(j).getArrivalTime()) {
                p.set(j + 1, p.get(j));
                j--;
            }
            p.set(j + 1, process);
        }
        List<Process2> i = p;


        return i;

    }



    public static ArrayList<Integer> Arrange(List<Process2> p) {

        for (int i = 1; i < p.size(); i++) {
            Process2 process = p.get(i);
            int j = i - 1;
            while (j >= 0 && process.getArrivalTime() < p.get(j).getArrivalTime()) {
                p.set(j + 1, p.get(j));
                j--;
            }
            p.set(j + 1, process);
        }
        ArrayList<Integer> i = new ArrayList<Integer>();
        for (int n = 0; n < p.size(); n++) {
            i.add(p.get(n).getBurstTime());
        }

        return i;

    }

    public static  ArrayList<Integer> paritionstime (List<Process2> processes, int quantumTime){
        ArrayList<Process2> A = ProcessR( Arrange(processes), quantumTime,processes);
        ArrayList<Integer> result = new ArrayList<>();

        for(int j =0 ; j< A.size();j++){

            if( j< processes.size() ){
                A.get(j).setRemainingBurstTime(A.get(j).getBurstTime());
            }else{

                A.get(j).setRemainingBurstTime(A.get(j).getRemainingBurstTime());
            }
            if(A.get(j).getRemainingBurstTime() >= quantumTime){
                A.get(j).setRemainingBurstTime(A.get(j).getRemainingBurstTime()-quantumTime);
                result.add(quantumTime);
            }else{
                if(A.get(j).getRemainingBurstTime() !=0){
                    result.add(A.get(j).getRemainingBurstTime());
                }
            }





        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Process2> processes = new ArrayList<>();
        processes.add(new Process2("1", 4, 0));
        processes.add(new Process2("2", 3, 2));
        processes.add(new Process2("3", 2, 3));
        processes.add(new Process2("4", 1, 5));
        processes.add(new Process2("5", 6, 1));

        System.out.println(RemainingTimeLive(Arrange(processes), 2,processes ));
        System.out.println(ProcessTimeLive( Arrange(processes), 2,processes));
        System.out.println(paritionstime ( processes, 2));

    }
}