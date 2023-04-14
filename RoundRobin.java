package cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RoundRobin {

    private static List<Process> filter(List<Process> process, int arrival_time) {
        List<Process> processes_out = new ArrayList<>();
        process.stream().filter((i) -> (i.get_ArrivalTime() <= arrival_time && i.get_RemainingBurstTime() != 0)).forEachOrdered(processes_out::add);
        Utility.sortBrust(processes_out);
        return processes_out;
    }

    private static void sortchartarrivale(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getChartarrival() == ((Process) o2).getChartarrival()) {
                return 0;
            } else if (((Process) o1).getChartarrival() < ((Process) o2).getChartarrival()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static Output Calc(List<Process> input_process, int QuantumTime) {
        int time = input_process.get(0).get_ArrivalTime();
        List<Process> tempList1 = new ArrayList<>();
        while (true) {
            List<Process> tempList2 = filter(input_process, time);
            if (tempList1.size() == input_process.size()) {
                break;
            }
            if (tempList2.isEmpty()) {
                time++;
            } else {
                sortchartarrivale(tempList2);
                if (tempList2.get(0).get_RemainingBurstTime() >= QuantumTime) {
                    time += QuantumTime;
                    tempList2.get(0).set_RemainingBurstTime(tempList2.get(0).get_RemainingBurstTime() - QuantumTime);
                    tempList2.get(0).setChartarrival(time);
                    if (tempList2.get(0).get_RemainingBurstTime() == 0) {
                        tempList2.get(0).set_HigherEnd(time);
                        tempList1.add(tempList2.get(0));
                    }
                } else {
                    time += tempList2.get(0).get_RemainingBurstTime();
                    tempList2.get(0).set_RemainingBurstTime(0);
                    if (tempList2.get(0).get_RemainingBurstTime() == 0) {
                        tempList2.get(0).set_HigherEnd(time);
                        tempList1.add(tempList2.get(0));
                    }
                }
            }
        }
        for (int i = 0; i < tempList1.size(); i++) {
            tempList1.get(i).set_TurnAroundTime(tempList1.get(i).get_HigherEnd() - tempList1.get(i).get_ArrivalTime());
            tempList1.get(i).set_WaitingTime(tempList1.get(i).get_TurnAroundTime() - tempList1.get(i).get_BurstTime());
        }
        Utility.sortArrival(tempList1);
        double avg_w = 0;
        double avg_t = 0;
        for (Process i : tempList1) {
            avg_w += i.get_WaitingTime();
            avg_t += i.get_TurnAroundTime();
        }
        return new Output(tempList1, Utility.FormatDouble(avg_w / tempList1.size()), Utility.FormatDouble(avg_t / tempList1.size()));
    }
    public static String generateGanttChart(List<Process> processes, int quantum) {
        StringBuilder ganttChart = new StringBuilder();
        int currentTime = 0;
        int remainingTime[] = new int[processes.size()];
        boolean completed[] = new boolean[processes.size()];

        // Initialize remaining time with burst time of each process
        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).get_BurstTime();
        }

        while (true) {
            boolean allCompleted = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0; i < processes.size(); i++) {
                if (remainingTime[i] > 0) {
                    allCompleted = false;

                    // Reduce remaining time of this process by quantum
                    if (remainingTime[i] > quantum) {
                        ganttChart.append(currentTime).append(" |").append(processes.get(i).getProcessName()).append("| ");
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        // If remaining time is smaller than or equal to quantum, then process is completed
                        ganttChart.append(currentTime).append(" |").append(processes.get(i).getProcessName()).append("| ");
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completed[i] = true;

                    }
                }

            }

            // If all processes are completed, then break the loop
            if (allCompleted)
                break;
        }
        ganttChart.append(currentTime);

        return ganttChart.toString();
    }
