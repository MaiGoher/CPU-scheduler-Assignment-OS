# CPU-scheduler

This Project was a way to visualize How different CPU scheduling algorithms work, CPU Scheduling is the process of selecting which process will have exclusive use of the CPU, This is to ensure that the CPU is used to its full potential and not idle at any time. The user can input tasks, The tasks are displayed in the Task Queue in the same order as they are put into the program, The program then sorts the tasks based on the algorithm and displays The Gantt chart, and computes Average Waiting Time and Average Turnaround Time. In addition, a  live scheduler is run with each 1 unit of time mapped to 1 second.

## Algorithims

This project Supports any number of processes in simulation. and Support 6 Algorithm:


| Algorithm                             |
|---------------------------------------|
| First come first served (FCFS)        |
| Shortest Job First Preemptive (SJF-P) |
| Shortest Job First Nonpreemptive (SJF-NP)|
| Priority Preemptive                   |
| Priority Nonpreemptive                |
| Round Robin                           |


![](https://faculty.cc.gatech.edu/~rama/CS2200-External/projects/p4/states.gif)

## First come first served (FCFS)

<div style="border: 1px solid #ddd; padding: 10px;">
  <p align="center">
    <img src="/images/fcfs.jpg" width="300" />
    <img src="/images/fcfs live.jpg" width="300" />
  </p>
</div>

## Priority Preemptive

<div style="border: 3px solid #ddd; padding: 30px;">
  <p align="center">
    <img src="/images/pp.jpg" width="300" />
    <img src="/images/RRlivee.jpg" width="300" />
    <img src="/images/pp add a process.jpg" width="300" />
  </p>
</div>

## Priority Nonpreemptive

<div style="border: 3px solid #ddd; padding: 30px;">
  <p align="center">
    <img src="/images/live.jpg" width="300" />
    <img src="/images/if the user inter non integer value in any textfield.jpg" width="300" />
    <img src="/images/if user enter eny non integer value.jpg" width="300" />
  </p>
</div>

## Round Robin

<div style="border: 3px solid #ddd; padding: 30px;">
  <p align="center">
    <img src="/images/RR.jpg" width="300" />
    <img src="/images/RRlivee.jpg" width="300" />
  </p>
</div>
