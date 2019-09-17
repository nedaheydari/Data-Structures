/*  P2Driver.java
    A simple driver program for project #2.  Enable the implementation you 
    want to test.  
    CS310 Summer 2016
    Alan Riggins
    
//////////////////////////////////////////////////////////////////////    
   ** Correct output is: **
    
AAAA
CCCC
PPPP
ZZZZ
F priority=1
H priority=1
C priority=2
A priority=5
E priority=5
G priority=5
B priority=7
D priority=10
//////////////////////////////////////////////////////////////////////
*/  

import data_structures.*;

public class P2Driver {
	
	private PriorityQueue<Integer> list;  
	private long start, stop;
	private static int STRUCTURE_SIZE_N = 100;

    public P2Driver() {
    	list = new OrderedListPriorityQueue<Integer>();
        doStringTest();
        doJobTest();
        }
    

        
    private void doStringTest() {
        PriorityQueue<String> pq = 
                    new OrderedArrayPriorityQueue<String>();
                    //new OrderedListPriorityQueue<String>();
                                    
        pq.insert("ZZZZ");
        pq.insert("PPPP");
        pq.insert("AAAA");
        pq.insert("CCCC");
   

        while(!pq.isEmpty())
            System.out.println(pq.remove());
        }  
        
    private void doJobTest() {
        PriorityQueue<Job> pq = 
                    new OrderedArrayPriorityQueue<Job>();
                    //new OrderedListPriorityQueue<String>(); 
        System.out.println("Starting insert test");
    	start = System.currentTimeMillis();  
        pq.insert(new Job(5,"A"));      
        pq.insert(new Job(7,"B"));
        pq.insert(new Job(2,"C"));
        pq.insert(new Job(10,"D"));
        pq.insert(new Job(5,"E"));
        pq.insert(new Job(1,"F"));
        pq.insert(new Job(5,"G"));
        pq.insert(new Job(1,"H"));
        
        stop = System.currentTimeMillis();
        for(int i=0; i < 10000; i++) {
        	pq.remove(); // O(1);
            }
        long numberOfMilliseconds = stop-start;
        System.out.println("Structure size: " + STRUCTURE_SIZE_N +
            " Time: " + numberOfMilliseconds);
        //STRUCTURE_SIZE_N += STEP_SIZE;   // INCREMENT N BY STEP SIZE 
        STRUCTURE_SIZE_N <<= 1; // DOUBLE N
        list.clear();
        
/* Enable this to test the iterator        
        System.out.println("Now printing contents of the PQ");
        for(Job j : pq)
            System.out.println(j);
*/            
        
        System.out.println("Now dequeuing, the must come out in proper order");        
        while(!pq.isEmpty())
            System.out.println(pq.remove());
        }
        
    private class Job implements Comparable<Job> {
        private int priority;
        private String label;
        
        public Job(int p, String s) {
            priority = p;
            label = s;
            }
            
        public int compareTo(Job j) {
            return priority-j.priority;
            }
            
        public String toString() {
            return label + " priority=" + priority;
            }
        }    
    
    
        
    public static void main(String [] args) {
        new P2Driver();
        }
    }