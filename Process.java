package cpu_scheduling;


public class Process {

    private String processName;
    private int ArrivalTime;
    private int BurstTime;
    private int Lower_end;
    private int Higher_end;
    private int priorityLevel;
    private int WaitingTime;
    private int TurnAroundTime;
    private int Chartarrival;
    private int remainingBurstTime;

    private Process(String processName, int arrivalTime, int burstTime, int Lower_end, int Higher_end, int priorityLevel, int waitingTime, int TurnAroundTime) {
        this.processName = processName;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Lower_end = Lower_end;
        this.Higher_end = Higher_end;
        this.priorityLevel = priorityLevel;
        this.WaitingTime = waitingTime;
        this.TurnAroundTime = TurnAroundTime;
        this.Chartarrival = arrivalTime;
        this.remainingBurstTime = burstTime;
    }

    public Process(String processName, int arrivalTime, int burstTime, int priorityLevel) {
        this(processName, arrivalTime, burstTime, 0, 0, priorityLevel, 0, 0);
    }

    public Process(String processName, int arrivalTime, int burstTime) {
        this(processName, arrivalTime, burstTime, 0, 0, 0, 0, 0);
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return BurstTime;
    }

    public void setBurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public int getLower_end() {
        return Lower_end;
    }

    public void setLower_end(int lower_end) {
        Lower_end = lower_end;
    }

    public int getHigher_end() {
        return Higher_end;
    }

    public void setHigher_end(int higher_end) {
        Higher_end = higher_end;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        WaitingTime = waitingTime;
    }

    public int getTurnAroundTime() {
        return TurnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        TurnAroundTime = turnAroundTime;
    }

    public int getChartarrival() {
        return Chartarrival;
    }

    public void setChartarrival(int chartarrival) {
        Chartarrival = chartarrival;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void setRemainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    @Override
    public String toString() {
        return "\nProcess{" + "\n\tprocessName=" + processName + "\n\tarrivalTime=" + ArrivalTime
                + "\n\tburstTime=" + BurstTime + "\n\tstartTime=" + Lower_end
                + "\n\tfinishTime=" + Higher_end + "\n\tpriorityLevel=" + priorityLevel
                + "\n\twaitingTime=" + WaitingTime + "\n\tturnaroundTime=" + TurnAroundTime + "\n}";
    }

}
