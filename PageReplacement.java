/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

/**
 *
 * @author gsven
 */
import java.util.LinkedList;
import java.util.stream.IntStream;
import static final_project.PageReplacement.capacity;


/*This class represents a page variable inside the */


/*Represent the memory status at a particular moment*/
class MemoryPages{
	int pages[]; //defines the pages that are loaded into the memory
	int time_index; //represents a time point during the cpu requesting pages
	
	MemoryPages(int size, int idx)
	{
		
		pages = new int[size]; //initialize the number of pages within the memory
		                       
		time_index = idx; //this variable indicate the corresponding time index. 
        //For the "page_list" variable defined in the PageReplacment class below, the list index can be the same as this
        //"time_index" variable, starting from 0, increasing by 1 each time when a new page request is coming.
	}

        //Get index of pages
         static int Index(int[] mem, int x){
            int i;
            for( i=0; i<capacity; i++){
                if(x == mem[i]) {
                    break;
                }
            }    
            return i;
    }
         //Get min index
         static int MinIndex(int[] indx){
            int minindex = 0;
            for(int i=0; i<indx.length; i++){
                if(indx[i]<indx[minindex]){
                    minindex = i;
                }
        }
        return minindex;
    }	
}


public class PageReplacement{
	static int capacity = 4; //By default the RAM can load 4 pages at most
	int page_fault_count = 0; //This variable stores the number of page fault totally. 
	
	LinkedList <MemoryPages> page_list; //Stores all the RAM status for different time index
	int page_fault = 0; //This variable represents the total number of page fault based on the 
       
	//Algorithm 1: Least Recently Used (LRU)
	//For this function, you need to use the clock algorithm as discussed in the class with one bit as a reference indicator.
	//However, you can use a boolean variable, i.e. true means 1 that the page should not be replaced; false means 0 that you can modify the page
	//In addition, for the circular list, you can use whatever data structure to implement it, e.g. LinkedList. 
	static void LRUreplacement(int request_pages[])
	{
        //intialize
        int page_fault_count = 0; //page faults 
        int[] indxs = new int[capacity]; //indices
        int[][] memory = new int[request_pages.length][capacity]; //each column data
        
        //fill indxs
        for(int i=0; i<capacity; i++){
            indxs[i] = -1; //-1 means empty
        }

        for(int i=0; i<capacity; i++){
            memory[0][i] = -1; //assigning it is empty in index
        }
            
        for(int i=0; i<request_pages.length; i++){ // add numbers into columns
            for(int y=0; y<capacity; y++){
                if(i != 0)
                    memory[i][y] = memory[i-1][y]; 
            }
            
            int z = MemoryPages.Index(memory[i], request_pages[i]);
            
            if(z == capacity){ 
               z = MemoryPages.MinIndex(indxs);
               page_fault_count++;
            }

            indxs[z] = i;
            memory[i][z] = request_pages[i]; //combines memory
        }

        displayRAM(memory, page_fault_count, request_pages);   //sends the print the results
        //no need to clear variables b/c they get intialized in beginning
        }
     
	
        //values inputed directly to displayRAM
        static void displayRAM(int[][] memory, int page_faults,int request_pages[]){
            
            double line = 2.5 * request_pages.length; //table line
            System.out.println("Ram Status:"); 
            
            for (int i = 0; i < request_pages.length; i++) {
                System.out.printf("%4d ",request_pages[i]);
            }
            System.out.println("");
            
            for(int i=0; i<line; i++){
                System.out.print("--");
            }
            System.out.println("\n"); 
            
            //prints table
            for(int i = 0; i < capacity; i++){
                for(int j = 0; j < request_pages.length; j++){
                    System.out.printf("%4d ",memory[j][i]);
                }
                System.out.println("");       
            }
 //           System.out.println("Total Number of Page Fault: " + page_faults);    
    }
}
