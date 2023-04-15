package cpu_scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CPU_Scheduling
 *
 * @author Esraa Amr
 */
public class CPU_Scheduling {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        List<Process2> list = new ArrayList();
        System.out.println("Enter number of processes : ");
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("No process !");
            return;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("( " + i + " )  Enter ProcessName, ArrialTime and BurstTime: ");
            list.add(new Process2(sc.next(), sc.nextInt(), sc.nextInt()));
        }
        System.out.println("Enter Quantum Time: ");
        int quantumTime =sc.nextInt();
        System.out.println(RoundRobin.Calc(list, quantumTime));
        //System.out.println(RoundRobin.generateGanttChart(list,quantumTime));
        //System.out.println(RoundRobin.generateGanttChart_Live(list,quantumTime));

    }
}
