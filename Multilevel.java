
package final_project;

import java.util.Queue; 
import java.util.*;

public class Multilevel {
    
    private static Queue<Process> Top_Priority = new LinkedList<>(); //Top Prioirty Queue
    private static Queue<Process> Mid_Priority = new LinkedList<>(); //Middle Prioirty Queue
    private static Queue<Process> Low_Priority = new LinkedList<>(); //acts like FCFS
    private static int Top_quantum = 8;  //1st level Round Robin Time
    private static int Mid_quantum = 16; //2nd level round Robin time
    private static int Low_quantum = 4000;  //4000 so it is FCFS then
    private final static int Processes = 15;     //Number of processes
    private static int pageNum; //Number of pages in Process, would use .lenght() to get length of each process to then be able to see each page.Did not use
    
    //Input pages, was going to try and connect processes with pages but ran out of time to connect them
    int p1[] = {1, 2, 3, 5, 3, 6, 1, 2, 3, 5, 4, 6, 5, 2, 4, 6, 5, 2, 5, 3};
    int p2[] = {2, 4, 1, 3, 6, 2, 1, 3, 4, 1, 2, 2, 6, 5, 5, 6, 2, 1, 2, 1};
    int p3[] = {5, 2, 6, 5, 3, 1, 1, 2, 3, 5, 6, 4, 5, 2, 4, 3, 5, 2, 5, 3};
    int p4[] = {3, 2, 4, 6, 3, 4, 5, 3, 6, 2, 4, 4, 5, 2, 5, 6, 1, 2, 5, 2};
    int p5[] = {2, 5, 6, 2, 3, 4, 5, 2, 6, 5, 3, 4, 1, 2, 4, 3, 6, 1, 5, 3};
    int p6[] = {1, 6, 4, 2, 3, 5, 1, 6, 3, 2, 2, 4, 5, 5, 1, 1, 5, 2, 5, 1};
    int p7[] = {1, 4, 5, 3, 4, 2, 4, 6, 3, 5, 4, 4, 2, 2, 3, 6, 4, 1, 5, 3};
    int p8[] = {1, 4, 5, 3, 4, 2, 4, 6, 3, 5, 4, 4, 2, 2, 3, 6, 4, 1, 5, 3};
    int p9[] = {5, 5, 6, 4, 3, 3, 4, 5, 6, 1, 3, 4, 5, 2, 4, 2, 5, 6, 2, 4};
    int p10[] = {3, 4, 2, 1, 6, 3, 3, 2, 3, 5, 5, 6, 3, 1, 2, 3, 5, 6, 5, 4};
    int p11[] = {2, 4, 3, 6, 3, 5, 2, 6, 3, 2, 5, 1, 5, 2, 1, 6, 2, 2, 3, 3};
    int p12[] = {4, 3, 3, 4, 5, 1, 1, 5, 3, 6, 2, 1, 5, 6, 4, 1, 5, 4, 6, 1};
    int p13[] = {4, 2, 5, 2, 5, 1, 6, 4, 3, 5, 3, 4, 4, 6, 2, 3, 5, 2, 5, 3};
    int p14[] = {5, 3, 2, 6, 6, 2, 6, 2, 3, 5, 3, 4, 5, 1, 3, 6, 1, 3, 6, 4};
    int p15[] = {1, 5, 3, 5, 3, 2, 6, 1, 3, 1, 4, 4, 5, 2, 6, 3, 5, 2, 2, 5};
    
    
    private static class Process implements Comparable<Process> {
        String id;     //Process identifier
        int start_time; //Time of arrival
        int execute_time;   //operation hours
        int time_left;  //Still need time
        char state;    //Process status
        int page; //Was gonna try to use page here to print the page numbers
        int leaving_time; //Was gonna try to use this to print out the leaving time of the pages
        

        //to display process
        @Override
        public String toString() {
            System.out.println();
            return String.format("Process %s: %10d %8d\n", id, start_time, time_left);
        }

        //sorts arrival time
        @Override
        public int compareTo( Process b ) {
            return Float.compare(start_time, b.start_time);
        }
    }
    //Multilevel Queue Scheduler
     static void Multi_Scheduler(Process[] pro){
        int t_quantum = Top_quantum; //Top RR counter
        int m_quantum = Mid_quantum; //Mid RR counter
        int l_quantum = Low_quantum; //FCFS counter
        int time = 0; //time of enitre scheduling
        int i = 0;
        
        while(i < Processes || !Top_Priority.isEmpty() || !Mid_Priority.isEmpty() || !Low_Priority.isEmpty()){
            //If arrival time add process to Top RR
            while(i < Processes && pro[i].start_time == time)
                Top_Priority.offer(pro[i++]);
            
            //Display every ten milliseconds
            if ((time % 10) == 0){
            Display(time);}
            
            //Top Queue 
            if(!Top_Priority.isEmpty()){
                
                //Time remaining so -1 to quantum
                Top_Priority.peek().time_left -= 1;
                Top_quantum -= 1;
                time++;
                
                //Process running
                if(Top_Priority.peek().time_left > 0){
                    
                    //Not doen so send to Mid Queue
                    if(Top_quantum == 0) {
                        Mid_Priority.offer(Top_Priority.poll());
                        Top_quantum = t_quantum;
                    }

                }
                //Process done
                else if(Top_Priority.peek().time_left == 0){  
                    Objects.requireNonNull(Top_Priority.poll()); //To take out process for future
                    Top_quantum = t_quantum;
                }
            }
            //Mid Queue
            else if(!Mid_Priority.isEmpty()){
                
                //Time remaining so -1 to quantum
                Mid_Priority.peek().time_left -= 1;
                Mid_quantum -= 1;
                time++;

                //Process done
                if(Mid_Priority.peek().time_left == 0){
                    Mid_quantum = m_quantum;
                    
                    Objects.requireNonNull(Mid_Priority.poll());
                }
                //Process running
                else if(Mid_Priority.peek().time_left > 0){
                    
                    //Remianing so send to Low Queue
                    if(Mid_quantum == 0) {                       
                        Low_Priority.offer(Mid_Priority.poll());
                        Mid_quantum = m_quantum;
                    }
                }
            }
            //Low Queue
            else if(!Low_Priority.isEmpty()){
                Low_Priority.peek().time_left -= 1;
                Low_quantum -= 1;              
                time++;

                //Process running
                if(Low_Priority.peek().time_left > 0){
                                       
                    if(Low_quantum == 0) {
                        Low_Priority.offer(Low_Priority.poll());
                        Low_quantum = l_quantum;
                    }
                }
                //Process Over
                else{
                    Top_quantum = t_quantum;         
                    Objects.requireNonNull(Low_Priority.poll());
                }
            }
        }
    }

    //Gets processes
     static Process[] getprocess(){
        int add_arrival = 0; 
        
        //build processes
        Process[] p = new Process[Processes];
        for( int i = 0; i < Processes; i++ ) {
            p[i] = new Process();
            p[i].id = ("P"+i); //Process id
            p[i].start_time = add_arrival; //arrival time
            p[i].execute_time = 200; //there are 20 pages and each takes 10ms to execute so total process takes 200 ms
            p[i].time_left = p[i].execute_time; //time remaining for process
            add_arrival = add_arrival + 20; //makes each process arrive 20ms intervals
            
        }
        Arrays.sort(p); //sort processes by arrival times
        return p;
    }
    
    //Print the current process
    private static void Display(int time){
        
        System.out.printf("\nTime(ms): %d\n",time);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("          Start Time(ms)|Time Left(ms)|Page #|Starting-Time(ms)|Leaving-Time(ms)");
        if(Top_Priority.isEmpty()) {
            System.out.println("Queue One: Empty");
        }
        else {
            System.out.println("Queue One:\n"+ Top_Priority.toString().replace("[", "").replace("]", "").replace(", ", ""));
        }
        
        if(Mid_Priority.isEmpty()) {
            System.out.println("Queue Two: Empty");
        }
        else {
            System.out.println("Queue Two:\n"+ Mid_Priority.toString().replace("[", "").replace("]", "").replace(", ", ""));
        }
        
        if(Low_Priority.isEmpty()) {
            System.out.println("Queue Three: Empty");
        }
        else {
            System.out.println("Queue Three:\n"+ Low_Priority.toString().replace("[", "").replace("]", "").replace(", ", ""));
        }
        
        System.out.println("--------------------------------------------------------------------------------");
    }

}






