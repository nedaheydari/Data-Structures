import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;
 
public class BSTDriver {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MAX_SIZE = 26;
    private int sequenceNumber = 0;
    private Random rndInt;
    private BinarySearchTree<TestKey,TestValue> binSearch;		//BinarySearchTree<TestKey,TestValue> hashTable;	
 
    public BSTDriver() {
        initializeStuff();
        isEmpty();
        testInsert();
        isFull();
        testRemove();
        getValue();
        geyKey();
        clear();
        
        // End tests
        print("\nDONE!");
    }
 
    public void initializeStuff() {
        binSearch = new BinarySearchTree<TestKey, TestValue>();		//HashTable<TestKey, TestValue>(MAX_SIZE-1);
        rndInt = new Random(10);
    }
    
    public void isEmpty() {
    	space();
    	print("Is it Empty?  [ " + binSearch.isEmpty() + " ]");
    }
 
    public void testInsert() {
        space();
        print("--- BEGIN TESTING INSERT ---");
        print("There are 15 total inserts, but some of them are duplicates.");
        print("The duplicates should NOT show up.");
        for (int i = 0; i < 15; i++) {
            int rndTemp = rndInt.nextInt(MAX_SIZE);
            String key = Character.toString(ALPHABET.charAt(rndTemp));
            binSearch.add(new TestKey(key), new TestValue(rndInt.nextInt(10)));
        }
        printArray();
        print("--- FINISHED TESTING INSERT ---"); 
        printCurrentSize();
        space(); 
        print("Is it Empty?  [ " + binSearch.isEmpty() + " ]");
    }
   
    
    public void isFull() {
    	space();
    	print("Is it Full?  [ " + binSearch.isFull() + " ]");
    }
 
 
    public void testRemove() {
    	space();
    	print("--- BEGIN TESTING REMOVE ---");
    	print("Removing C");
    	print("Removing P");
    	print("Removing V");
    	print("Removing A");
    	//print("Removing V");
    	//binSearch.delete(new TestKey("C"));
    	binSearch.delete(new TestKey("C"));
    	binSearch.delete(new TestKey("E"));
    	binSearch.delete(new TestKey("V"));
    	binSearch.delete(new TestKey("A"));
    	printCurrentSize();
    	printArray();
    	print("--- FINISHED TESTING REMOVE ---");
    	//printCurrentSize();
    }

    public void getValue() {
    	space();
    	print("--- BEGIN TESTING GET VALUE ---");
    	print("Getting value for Key:F " + binSearch.getValue(new TestKey("F")));
    	print("Getting value for Key:S " + binSearch.getValue(new TestKey("S")));
    	print("--- FINISHED TESTING GET VALUE ---");
    }
    
    public void geyKey() {
    	space();
    	print("--- BEGIN TESTING GET KEY ---");
    	print("Getting key for Value:9     " + binSearch.getKey(new TestValue(9)));
    	print("Getting key for Value:8     " + binSearch.getKey(new TestValue(8)));
    	print("--- FINISHED TESTING GET KEY ---");
    }
    
    public void clear() {
    	space();
    	print("Now clearing");
    	binSearch.clear();
    	print("Is it Empty? " + binSearch.isEmpty());
    	printCurrentSize();
    }
    
    public void printArray() {
       Iterator<TestKey> keyIter = binSearch.keys();
       Iterator<TestValue> valueIter = binSearch.values();
       while (keyIter.hasNext()) {
            print(keyIter.next() + " " + valueIter.next());
        }	
    }
    
    public void printCurrentSize() {
    	print("Current Size: " + binSearch.size());
    }
   
 
    private class TestKey implements Comparable<TestKey> {
        private String key;
 
        public TestKey(String s) {
            key = s;
        }
 
        public int compareTo(TestKey th) {
            return key.compareTo(th.key);
        }
       
        public String toString() {
            return "Key: " + key;
        }
 
        public int hashCode() {
            return key.hashCode();
        }
    }
 
    private class TestValue implements Comparable<TestValue> {
        private Integer value;
        private int insertOrder;
       
        public TestValue(int n) {
            value = n;
            insertOrder = sequenceNumber++;
        }
 
        public int compareTo(TestValue v) {
            return value.compareTo(v.value);
        }
   
        public String toString() {
            return "\t\tValue: " + value + "\tInsert Order: " + insertOrder;
        }
       
        public int hashCode() {
            return ((Integer)value).hashCode();
        }
    }
 
    public static void print(Object o) {
        System.out.println(o);
    }
 
    public static void space() {
        print("");
        print("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        print("");
    }
   
    public static void main(String[] agrs) {
        new BSTDriver();
    }
}