package cpu_scheduling;

import java.util.List;

public class Output {

    private final List<Process2> process2s;
    private double avg_turnaround;
    private double avg_waiting;

    public Output(List<Process2> process2s, double avg_waiting, double avg_turnaround) {
        this.process2s = process2s;
        this.avg_waiting = avg_waiting;
        this.avg_turnaround = avg_turnaround;
    }

    @Override
    public String toString() {
        return "Output{" + "\nprocess2s=" + process2s + "\navg_waiting=" + avg_waiting + "\navg_turnaround=" + avg_turnaround + "\n}";
    }

    public List<Process2> getProcesses() {
        return process2s;
    }

    public double getAvg_turnaround() {
        return avg_turnaround;
    }

    public double getAvg_waiting() {
        return avg_waiting;
    }

}
