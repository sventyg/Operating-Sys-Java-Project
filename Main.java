/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import static final_project.Multilevel.Multi_Scheduler;
import static final_project.Multilevel.getprocess;
import static final_project.RoundRobin.findAvgTime;
import java.util.Scanner;

/**
 *
 * @author gsven
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                    //Input pages
		int p0[] = {1, 2, 3, 5, 3, 6, 1, 2, 3, 5, 4, 6, 5, 2, 4, 6, 5, 2, 5, 3 };
                int p1[] = {2, 4, 1, 3, 6, 2, 1, 3, 4, 1, 2, 2, 6, 5, 5, 6, 2, 1, 2, 1};
                int p2[] = {5, 2, 6, 5, 3, 1, 1, 2, 3, 5, 6, 4, 5, 2, 4, 3, 5, 2, 5, 3 };
                int p3[] = {3, 2, 4, 6, 3, 4, 5, 3, 6, 2, 4, 4, 5, 2, 5, 6, 1, 2, 5, 2};
                int p4[] = {2, 5, 6, 2, 3, 4, 5, 2, 6, 5, 3, 4, 1, 2, 4, 3, 6, 1, 5, 3};
                int p5[] = {1, 6, 4, 2, 3, 5, 1, 6, 3, 2, 2, 4, 5, 5, 1, 1, 5, 2, 5, 1};
                int p6[] = {1, 4, 5, 3, 4, 2, 4, 6, 3, 5, 4, 4, 2, 2, 3, 6, 4, 1, 5, 3 };
                int p7[] = {1, 4, 5, 3, 4, 2, 4, 6, 3, 5, 4, 4, 2, 2, 3, 6, 4, 1, 5, 3 };
                int p8[] = {5, 5, 6, 4, 3, 3, 4, 5, 6, 1, 3, 4, 5, 2, 4, 2, 5, 6, 2, 4};
                int p9[] = {3, 4, 2, 1, 6, 3, 3, 2, 3, 5, 5, 6, 3, 1, 2, 3, 5, 6, 5, 4 };
                int p10[] = {2, 4, 3, 6, 3, 5, 2, 6, 3, 2, 5, 1, 5, 2, 1, 6, 2, 2, 3, 3};
                int p11[] = {4, 3, 3, 4, 5, 1, 1, 5, 3, 6, 2, 1, 5, 6, 4, 1, 5, 4, 6, 1 };
                int p12[] = {4, 2, 5, 2, 5, 1, 6, 4, 3, 5, 3, 4, 4, 6, 2, 3, 5, 2, 5, 3  };
                int p13[] = {5, 3, 2, 6, 6, 2, 6, 2, 3, 5, 3, 4, 5, 1, 3, 6, 1, 3, 6, 4};
                int p14[] = {1, 5, 3, 5, 3, 2, 6, 1, 3, 1, 4, 4, 5, 2, 6, 3, 5, 2, 2, 5 };
                
               
                
             //Executs the page replacement,capcity 4
                System.out.println("P0:");
                PageReplacement.LRUreplacement(p0);
                System.out.println("P1:");
		PageReplacement.LRUreplacement(p1);
                System.out.println("P2:");
                PageReplacement.LRUreplacement(p2);
                System.out.println("P3:");
                PageReplacement.LRUreplacement(p3);
                System.out.println("P4:");
                PageReplacement.LRUreplacement(p4);
                System.out.println("P5:");
                PageReplacement.LRUreplacement(p5);
                System.out.println("P6:");
                PageReplacement.LRUreplacement(p6);
                System.out.println("P7:");
                PageReplacement.LRUreplacement(p7);
                System.out.println("P8:");
                PageReplacement.LRUreplacement(p8);
                System.out.println("P9:");
                PageReplacement.LRUreplacement(p9);
                System.out.println("P10:");
                PageReplacement.LRUreplacement(p10);
                System.out.println("P11:");
                PageReplacement.LRUreplacement(p11);
                System.out.println("P12:");
                PageReplacement.LRUreplacement(p12);
                System.out.println("P13:");
                PageReplacement.LRUreplacement(p13);
                System.out.println("P14:");
                PageReplacement.LRUreplacement(p14);
                
                		
                //multilevel scheduler
                Multilevel.Multi_Scheduler(getprocess());
    }
    
}
