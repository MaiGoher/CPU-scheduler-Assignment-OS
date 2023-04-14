package cpu_scheduling;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utility {

    public static void sortArrival(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getArrivalTime() == ((Process) o2).getArrivalTime()) {
                return 0;
            } else if (((Process) o1).getArrivalTime() < ((Process) o2).getArrivalTime()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static void sortBrust(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Process) o1).getBurstTime() == ((Process) o2).getBurstTime()) {
                return 0;
            } else if (((Process) o1).getBurstTime() < ((Process) o2).getBurstTime()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch != str2_ch) {
                if (str1_ch - str2_ch < 0) {
                    return -1;
                } else if (str1_ch - str2_ch > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        // Edge case for strings like 
        // String 1="Geeks" and String 2="Geeksforgeeks" 
        if (l1 != l2) {
            if (l1 - l2 < 0) {
                return -1;
            } else if (l1 - l2 > 0) {
                return 1;
            } else {
                return 0;
            }
        } // If none of the above conditions is true, 
        // it implies both the strings are equal 
        else {
            return 0;
        }
    }

    public static void sortName(List<Process> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            return stringCompare(((Process) o1).getProcessName(), ((Process) o2).getProcessName());
        }
        );
    }

    public static List<Process> deepCopy(List<Process> oldList) {
        List<Process> newList = new ArrayList();

        for (Process process : oldList) {
            newList.add(new Process(process.getProcessName(), process.getArrivalTime(), process.getBurstTime(), process.getPriorityLevel()));
        }

        return newList;
    }

    public static int sumBurst(List<Process> L) {
        int sum = 0;
        for (Process i : L) {
            sum += i.getBurstTime();
        }
        return sum;
    }

    public static double FormatDouble(double x) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(x));
    }

}
