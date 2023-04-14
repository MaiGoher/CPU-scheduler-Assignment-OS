package cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundRobin {

    private static List<Process> filter(List<Process> process, int arrival_time) {
        List<Process> processes_out = new ArrayList<>();
        process.stream().filter((i) -> (i.getArrivalTime() <= arrival_time && i.getRemainingBurstTime() != 0)).forEachOrdered((i) -> {
            processes_out.add(i);
        });
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
        int time = input_process.get(0).getArrivalTime();
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
        for (Process i : tempList1) {
            avg_w += i.getWaitingTime();
            avg_t += i.getTurnAroundTime();
        }
        return new Output(tempList1, Utility.FormatDouble(avg_w / tempList1.size()), Utility.FormatDouble(avg_t / tempList1.size()));
    }

}
