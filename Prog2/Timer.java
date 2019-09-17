

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
