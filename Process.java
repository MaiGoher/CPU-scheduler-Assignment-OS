
/**
 *
 * @author: Maram Ahmed
 */

public class Process {
    private int ProcessName;
    private int WaitingTime;
    private int BurstTime;
    private int ArrivalTime;
    private int TurnAroundTime;
    private int Lower_end;
    private int Higher_end;
    private int remainingBurstTime;
    private boolean completed;
    
    public Process(int ProcessName,int BurstTime,int ArrivalTime)
    {this.ProcessName=ProcessName;
    this.BurstTime=BurstTime;
    this.ArrivalTime=ArrivalTime;
    this.WaitingTime=0;    //initialize waiting time of new process to 0
    this.TurnAroundTime=0;   //initialize turn around time of new process to 0
    this.remainingBurstTime=BurstTime;
    }
    
    public void set_ProcessName(int ProcessName)
    {this.ProcessName=ProcessName;}
    
    public void set_WaitingTime(int WaitingTime)
    {this.WaitingTime=WaitingTime;}
    
    public void set_BurstTime(int BurstTime)
    {this.BurstTime=BurstTime;}
    
    public void set_ArrivalTime(int ArrivalTime)
    {this.ArrivalTime=ArrivalTime;}
    
    public void set_TurnAroundTime(int TurnAroundTime)
    {this.TurnAroundTime=TurnAroundTime;}
    
    public void set_LowerEnd(int LowerEnd)
    {this.Lower_end=LowerEnd;}
    
    public void set_HigherEnd(int HigherEnd)
    {this.Higher_end=HigherEnd;}
    
    public void set_RemainingBurstTime(int remainingBurstTime) 
    {this.remainingBurstTime = remainingBurstTime;}
    
    public int get_ProcessName()
    {return this.ProcessName;}
    
    public int get_WaitingTime()
    {return this.WaitingTime;}
    
    public int get_BurstTime()
    {return this.BurstTime;}
    
    public int get_ArrivalTime()
    {return this.ArrivalTime;}
    
    public int get_TurnAroundTime()
    {return this.TurnAroundTime;}
    
    public int get_LowerEnd()
    {return this.Lower_end;}
    
    public int get_HigherEnd()
    {return this.Higher_end;}
    
    public int get_RemainingBurstTime() 
    {return remainingBurstTime;}
    
    public boolean isCompleted() 
    {return completed;}

    public void setCompleted(boolean completed)
    {this.completed = completed;}
    
}
