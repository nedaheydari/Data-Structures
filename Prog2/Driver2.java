/* Prog2 Driver
*
*  AUTHOR: Todd Nguyen
*  AUTHOR'S COMMENTS: I hope this helps you guys out with your project 2 for
*  CS-310! This driver tests for most things I can think of. Please feel free
*  to edit this driver if you want to test for something specifically! If you
*  have any questions, feel free to send me an email at todd.nguyen47@gmail.com
*/
 
import data_structures.*;
import java.util.Iterator;
import java.util.Random;
 
public class Driver2 {
    private static final int MAX_SIZE = 10;
    private int sequenceNumber = 0;
    private OrderedListPriorityQueue<TestInteger> list;
    private Random randomInteger, randomPriority;
    private OrderedArrayPriorityQueue arrayQueue2, arrayQueue;
 
    @SuppressWarnings("unchecked")
	public Driver2() {
        list = new OrderedListPriorityQueue<TestInteger>();
        randomInteger = new Random(8);
        randomPriority = new Random(8);        
 
        print("");
        print("/////////////////////////////////////////////////////////");
        print("/// BEGIN TESTING LIST IMPLEMENTATION");
        print("/////////////////////////////////////////////////////////");
        print("");
 
        // Test insert
        printCurrentSize();
        print("--- BEGIN TESTING INSERT() --- ");
        print("Checking if the list is empty BEFORE insertion...");
        for (int i = 0; i < MAX_SIZE; i++) {
            print(i + ". Is it empty? [ " + list.isEmpty() + " ]");
            list.insert(new TestInteger(randomInteger.nextInt(10)));
        }
        printIterator();
        printCurrentSize();
        print("Is it full? [ " + list.isFull() + " ] ");
        print("--- FINISHED TESTING INSERT() --- \n");
 
        // Test remove
        print("--- BEGIN TESTING REMOVE() --- ");        
        for (int i = 0; i < 2; i++) {
            print("Removing: " + list.remove());
        }
        printIterator();
        printCurrentSize();
        print("--- FINISHED TESTING REMOVE() --- \n");
   
        // Test peek
        print("--- BEGIN TESTING PEEK() --- ");
        printIterator();
        printCurrentSize();
        print("Peek:     " + list.peek());
        print("Removing: " + list.remove());
        print("Peek:     " + list.peek());
        printCurrentSize();
        print("--- FINISHED TESTING PEEK() --- \n");
 
        // Test contains
        print("--- BEGIN TESTING CONTAINS() ---");
        printCurrentSize();
        printIterator();
        TestInteger successfulFindInt = new TestInteger(1);
        print("TESTING successful search. Searching for 1:    [ "
        + list.contains(successfulFindInt) + " ]");
        TestInteger unsuccessfulFindInt = new TestInteger(16);
        print("TESTING unsuccessful search. Searching for 16: [ " +
        list.contains(unsuccessfulFindInt) + " ]");
        print("TESTING successful search. Searching for 8:    [ " +
        list.contains(new TestInteger(8)) + " ]" );
        print("--- FINISHED TESTING CONTAINS() ---\n");
 
        // Testing insert with certain priority
        print("--- BEGIN TESTING INSERT WITH A CERTAIN PRIORITY---");
        TestInteger insertWithPriority = new TestInteger(1);
        TestInteger insertWithPriority2 = new TestInteger(9);
        print("Inserting: " + insertWithPriority);
        list.insert(insertWithPriority);
        printIterator();
        printCurrentSize();
        print("");
        print("Inserting: " + insertWithPriority2);
        list.insert(insertWithPriority2);
        printIterator();
        printCurrentSize();
        print("");
        TestInteger insertWithPriority3 = new TestInteger(0);
        print("Inserting: " + insertWithPriority3);
        list.insert(insertWithPriority3);
        printIterator();
        printCurrentSize();
        print("--- FINISHED TESTING INSERT WITH A CERTAIN PRIORITY ---\n");
               
        // Test clear
        print("--- BEGIN TESTING CLEAR() ---");
        list.clear();
        printCurrentSize();
        print("There should not be any list printed after this line.");
        printIterator();
        print("--- FINISHED TESTING CLEAR() ---\n");
 
        // Test when list is empty
        print("--- BEGIN TESTING METHODS WHEN LIST IS EMPTY ---");
        print("TESTING clear():");
        list.clear();
        printCurrentSize();
        print("  There should not be any errors when clearing an " +
        "empty list");
        print("TESTING remove:     " + list.remove());
        print("TESTING peek:       " + list.peek());        
        print("TESTING contains(): " + list.contains(new TestInteger(1)));
        print("TESTING isFull():   " + list.isFull());
        print("TESTING isEmpty():  " + list.isEmpty());
        print("--- FINISHED TESTING METHODS WHEN LIST IS EMPTY " +
        "--- ");
 
        print("");
        print("///////////////////////////////////////////////////////////");
        print("/// BEGIN TESTING ARRAY IMPLEMENTATION ");
        print("///////////////////////////////////////////////////////////");
        arrayQueue2 = new OrderedArrayPriorityQueue();
        arrayQueue  = new OrderedArrayPriorityQueue(10);
       
        print("");
        print("--- BEGIN TESTING INSERT() ---");
        print("Inserting DEFAULT_MAX_CAPACITY elements into default array...");
        for (int i = 0; i < 1000; i++) {
            arrayQueue2.insert(new TestInteger(randomInteger.nextInt(1000)));
        }
        print("Current size of default list: " +  arrayQueue2.size());
        print("");
        // Reset sequence number
        sequenceNumber = 0;
        print("Inserting 10 elements into a custom array with a max size " +
        "of 10...");
        print("Checking if the list is empty BEFORE insertion...");
        for (int i = 0; i < 10; i++) {
            print(i + ". Is the array empty? " + arrayQueue.isEmpty());
            arrayQueue.insert(new TestInteger(randomInteger.nextInt(10)));
        }
        print("Current size of custom list: " + arrayQueue.size());
        printArray();
        print("Is it full? " + arrayQueue.isFull());
        print("Trying to insert into a full list: " + arrayQueue.insert(5));
        print("--- FINISHED TESTING INSERT() ---\n");
 
        print("--- BEGIN TESTING REMOVE() ---");
        for (int i = 0; i < 3; i++) {
            print("Removing: " + arrayQueue.remove());
        }
        printArray();
        print("");
        TestInteger insertWithPriority4 = new TestInteger(0);
        TestInteger insertWithPriority5 = new TestInteger(5);
        TestInteger insertWithPriority6 = new TestInteger(8);
        print("Trying to insert: " + insertWithPriority4);
        arrayQueue.insert(insertWithPriority4);
        printArray();
        print("");
        print("Trying to insert: " + insertWithPriority5);
        arrayQueue.insert(insertWithPriority5);
        printArray();
        print("");
        print("Trying to insert: " + insertWithPriority6);
        arrayQueue.insert(insertWithPriority6);
        printArray();
        print("");
        print("--- FINISHED TESTING REMOVE() ---\n");
 
        print("--- BEGIN TESTING PEEK() ---");
        printArray();    
        print("Peek:     " + arrayQueue.peek());
        print("Removing: " + arrayQueue.remove());
        printArray();
        print("Peek:     " + arrayQueue.peek());
        print("--- FINISHED TESTING PEEK() ---\n");
 
        print("--- BEGIN TESTING CONTAINS ---");
        printArray();
        print("Testing successful contains(). Trying to find 5:    " +
        arrayQueue.contains(new TestInteger(5)));
        print("Testing unsuccessful contains(). Trying to find 17: " +
        arrayQueue.contains(new TestInteger(17)));
        print("Testing successful contains(). Trying to find 8:    " +
        arrayQueue.contains(new TestInteger(8)));
        print("--- FINISHED TESTING CONTAINS() ---\n");
 
        print("--- BEGIN TESTING WHEN LIST IS EMPTY ---");
        arrayQueue.clear();
        print("TESTING clear(). There should not be any arrays printed" +
        " after this line.");
        printArray();
        print("TESTING clear() when it is empty. SHOULD NOT CRASH.");
        arrayQueue.clear();
        print("... Great!");
        print("TESTING remove():   " + arrayQueue.remove());
        print("TESTING peek():     " + arrayQueue.peek());
        print("TESTING contains(): " +
        arrayQueue.contains(new TestInteger(5)));
        print("TESTING isEmpty():  " + arrayQueue.isEmpty());
        print("TESTING isFull():   " + arrayQueue.isFull());
        print("--- FINISHED TESTING WHEN ARRAY IS EMPTY ---\n");
        print("Yay you are done!");
    }
 
    public void print(Object o) {
        System.out.println(o);
    }
 
    public void printCurrentSize() {
        print("Current Size: " + list.size());        
    }
 
    public void printIterator() {
        Iterator<TestInteger> iterator = list.iterator();
        while (iterator.hasNext()) {
            print(iterator.next());
        }
    }
 
    public void printArray() {
        printArray1();
        printArraySize();
    }
 
    public void printArray1() {
        Iterator<TestInteger> iterator = arrayQueue.iterator();
        while (iterator.hasNext()) {
            print(iterator.next());
        }
    }
   
    public void printArraySize() {
        print("Current size: " + arrayQueue.size());
    }
 
    private class TestInteger implements Comparable<TestInteger> {
        // private Integer integerVariable;
        private int insertOrder;
        private int priority;
       
        public TestInteger(int input) {
            // integerVariable = input;
            //priority = randomPriority.nextInt(5);
            insertOrder = sequenceNumber++;
            priority = input;
        }
 
        /*public TestInteger(int input, int priority) {
            integerVariable = input;
            this.priority = priority;
            insertOrder = sequenceNumber++;
        }*/
 
        public int compareTo(TestInteger t) {
            return (priority - t.priority);  
        }
 
        public String toString() {
            /*return "Data: " + integerVariable + "    Priority: " + priority
            + "    Insert Order: " + insertOrder;*/
            return "[Priority: " + priority + ", Insert Order: " +
            insertOrder + "]";
        }
    }
 
    public static void main(String[] args) {
        new Driver2();
    }
}