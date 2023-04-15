package cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RoundRobin {

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
    /*
    public static void generateGanttChart_Live(List<Process2> processes, int quantum) {
        int currentTime = 0;
        int remainingTime[] = new int[processes.size()];
        boolean completed[] = new boolean[processes.size()];

        // Initialize remaining time with burst time of each process
        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
        }

        while (true) {
            boolean allCompleted = true;
            StringBuilder ganttChart = new StringBuilder();

            // Traverse all processes one by one repeatedly
            for (int i = 0; i < processes.size(); i++) {
                if (remainingTime[i] > 0) {
                    allCompleted = false;

                    // Reduce remaining time of this process by quantum
                    if (remainingTime[i] > quantum) {
                        ganttChart.append(currentTime).append(" ");
                        // Print process name when it enters the execution
                        System.out.println(currentTime + " |" + processes.get(i).getProcessName() + "| Entered");
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        // If remaining time is smaller than or equal to quantum, then process is completed
                        ganttChart.append(currentTime).append(" ");
                        // Print process name when it enters the execution
                        System.out.println(currentTime + " |" + processes.get(i).getProcessName() + "| Entered");
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completed[i] = true;
                    }
                }
            }

            // If all processes are completed, then break the loop
            if (allCompleted)
                break;

            System.out.println(ganttChart.toString());

            try {
                // Sleep for one second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    */
}


