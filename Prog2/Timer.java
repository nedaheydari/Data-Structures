/* Timer.java
   Here is a simple timing test framework.  This timer uses
   your array-based list from project #1.
   Alan Riggins, CS310, Summer 2016
   
   Here are the results/output on my computer:
   
Structure size: 100 Time: 3
Structure size: 200 Time: 1
Structure size: 400 Time: 2
Structure size: 800 Time: 1
Structure size: 1600 Time: 2
Structure size: 3200 Time: 3
Structure size: 6400 Time: 9
Structure size: 12800 Time: 34
Structure size: 25600 Time: 127
Structure size: 51200 Time: 346
Structure size: 102400 Time: 772
Structure size: 204800 Time: 1621
Structure size: 409600 Time: 3334
Structure size: 819200 Time: 6784
Structure size: 1638400 Time: 13612   
  
*/

import java.util.Random;

import data_structures.*;

public class Timer {
    private static final int SIZE = 10000000;
    private static final int WORK_CONSTANT = 10000;
    private static final int STEP_SIZE = 1000;
    private static int STRUCTURE_SIZE_N = 100;
                 
   
    private PriorityQueue<Integer> list;    
    private int [] arr;
    private long start, stop;
    
    public Timer() {
        //list = new OrderedArrayPriorityQueue(SIZE);    
        list = new OrderedListPriorityQueue<Integer>();
        arr = new int[SIZE];
        initializeArray();
        runTimer();
        }
        

	private void initializeArray() {       
        int newIndex = 0;
        // fill array with sequential integers
        for(int i=0; i < SIZE; i++)
            arr[i] = i;
        
        for(int i=0; i < SIZE; i++)
            arr[i] = i;
            
        // Now randomize the order in which they occur
        // For each element swap with a random index
        for(int i=0; i < SIZE; i++) {
            newIndex = (int)(STRUCTURE_SIZE_N*Math.random());
            int tmp = arr[i];
            arr[i] = arr[newIndex];
            arr[newIndex] = i; 
            }
        } 
   
    private void runTimer() {		//move numbers from random array to pq array
        System.out.println("Starting insert test");
    	for(int outer=0; outer < 12; outer++) {
            // Build initial structure
            for(int i=0; i < STRUCTURE_SIZE_N; i++)
                list.insert(arr[i]);
            start = System.currentTimeMillis();
            for(int i=0; i < WORK_CONSTANT; i++) {
                list.insert(arr[i]); // O(n);
                }
            stop = System.currentTimeMillis();
            for(int i=0; i < WORK_CONSTANT; i++) {
            	list.remove(); // O(1);
                }
            long numberOfMilliseconds = stop-start;
            System.out.println("Structure size: " + STRUCTURE_SIZE_N +
                " Time: " + numberOfMilliseconds);
            //STRUCTURE_SIZE_N += STEP_SIZE;   // INCREMENT N BY STEP SIZE 
            STRUCTURE_SIZE_N <<= 1; // DOUBLE N
            list.clear();
            } // end inner loop
    	
    	STRUCTURE_SIZE_N = 100;
    	System.out.println();
    	System.out.println("Starting remove test");
        for(int outer=0; outer < 12; outer++) {
            // Build initial structure
            for(int i=0; i < STRUCTURE_SIZE_N; i++)
                list.insert(arr[i]);
            start = System.currentTimeMillis();
            for(int i=0; i < WORK_CONSTANT; i++) {
                //list.insert(arr[i]); // O(n);
                list.remove(); // O(1);
                }
            stop = System.currentTimeMillis();
            long numberOfMilliseconds = stop-start;
            System.out.println("Structure size: " + STRUCTURE_SIZE_N +
                " Time: " + numberOfMilliseconds);
            //STRUCTURE_SIZE_N += STEP_SIZE;   // INCREMENT N BY STEP SIZE 
            STRUCTURE_SIZE_N <<= 1; // DOUBLE N
            list.clear();
            } // end inner loop
        }  // end method
          
    //Fill it completely. Then start your time before you remove everything. 
    //And then do it again with a different size.

  
       
    public static void main(String [] args) {
        new Timer();
        }
}