import data_structures.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class HashDriver {
	DictionaryADT<TestKey, TestValue> table = new BinarySearchTree<TestKey, TestValue>();;
	int sequenceNumber = 0;
	
	public HashDriver(){
		runTests1();	
	}
	
	private void runTests1(){
		System.out.println("\n-----------------------------------------------------------------------------------------");
		System.out.println("####### Now running ADD tests #######\n");
		System.out.println("Inserting up to max size");
		System.out.println("SIZE: " + table.size());
		System.out.println("Adding 'OE': " + table.add(new TestKey("OE"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'Y': " + table.add(new TestKey("Y"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'C': " + table.add(new TestKey("C"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'B': " + table.add(new TestKey("B"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'x': " + table.add(new TestKey("x"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'H': " + table.add(new TestKey("H"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'E': " + table.add(new TestKey("E"), new TestValue("98")) + "  |  Size: " + table.size()  );
		
		System.out.println("\nTrying to insert duplicates");
		System.out.println("Adding 'x': " + table.add(new TestKey("x"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'H': " + table.add(new TestKey("H"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'E': " + table.add(new TestKey("E"), new TestValue("98")) + "  |  Size: " + table.size()  );
		
		System.out.println("\nFinish inserting");
		System.out.println("Adding 'D': " + table.add(new TestKey("D"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'A': " + table.add(new TestKey("A"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'G': " + table.add(new TestKey("G"), new TestValue("98")) + "  |  Size: " + table.size()  );
		
		System.out.println("\nTrying to insert above max size");
		System.out.println("Adding 'DE': " + table.add(new TestKey("DE"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'AE': " + table.add(new TestKey("AE"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'GE': " + table.add(new TestKey("GE"), new TestValue("98")) + "  |  Size: " + table.size()  );
		
		System.out.println("\n -- Printing table --");
		printArray();
		
		System.out.println("\n-----------------------------------------------------------------------------------------");
		
		System.out.println("\n####### Now running DELETE tests #######\n");
		System.out.println("Removing 'OE': " + table.delete(new TestKey("OE")) +  " |  Size: " + table.size()  );
		printArray();
		System.out.println("Removing 'Y': " + table.delete(new TestKey("Y")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'x': " + table.delete(new TestKey("x")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'A': " + table.delete(new TestKey("A")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'OE' again: " + table.delete(new TestKey("OE")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'POE': " + table.delete(new TestKey("POE")) +  " |  Size: " + table.size()  );
		System.out.println("\n -- Printing table --");
		printArray();
		
		System.out.println("\n -- Removing the rest of the list --");
		System.out.println("Removing 'B': " + table.delete(new TestKey("B")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'C': " + table.delete(new TestKey("C")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'D': " + table.delete(new TestKey("D")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'E': " + table.delete(new TestKey("E")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'G': " + table.delete(new TestKey("G")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'H': " + table.delete(new TestKey("H")) +  " |  Size: " + table.size()  );
		
		System.out.println("\n -- Trying to remove from an empty list --");
		System.out.println("Removing 'E': " + table.delete(new TestKey("E")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'G': " + table.delete(new TestKey("G")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'H': " + table.delete(new TestKey("H")) +  " |  Size: " + table.size()  );
		
		System.out.println("\n-----------------------------------------------------------------------------------------");
		System.out.println("\n####### Now running CONTAINS tests #######\n");
		System.out.println("Adding 'x': " + table.add(new TestKey("x"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'H': " + table.add(new TestKey("H"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'E': " + table.add(new TestKey("E"), new TestValue("98")) + "  |  Size: " + table.size()  );
		System.out.println( "Checking for 'H': " + table.contains(new TestKey("H")) );
		System.out.println( "Checking for 'E': " + table.contains(new TestKey("E")) );
		System.out.println( "Checking for 'x': " + table.contains(new TestKey("x")) );
		System.out.println( "Checking for 'G': " + table.contains(new TestKey("G")) );
		System.out.println( "Checking for 'I': " + table.contains(new TestKey("I")) );
		System.out.println("Removing 'E': " + table.delete(new TestKey("E")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'H': " + table.delete(new TestKey("H")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'x': " + table.delete(new TestKey("x")) +  " |  Size: " + table.size()  );
		System.out.println("-- Testing contains on empty list");

		System.out.println( "Checking for 'E': " + table.contains(new TestKey("E")) );
		System.out.println( "Checking for 'x': " + table.contains(new TestKey("x")) );
		System.out.println( "Checking for 'H': " + table.contains(new TestKey("H")) );
		System.out.println( "Checking for 'I': " + table.contains(new TestKey("I")) );
		

		System.out.println("\n-----------------------------------------------------------------------------------------");
		System.out.println("\n####### Now running GetVALUE tests #######\n");
		System.out.println("Adding some nodes");
		System.out.println("Adding 'wfw': " + table.add(new TestKey("wfw"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'wegwe': " + table.add(new TestKey("wegwe"), new TestValue("B")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'tjtn': " + table.add(new TestKey("tjtn"), new TestValue("C")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'tnsn': " + table.add(new TestKey("tnsn"), new TestValue("D")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'ryjt': " + table.add(new TestKey("ryjt"), new TestValue("E")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'trhr': " + table.add(new TestKey("trhr"), new TestValue("F")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'agtaerb': " + table.add(new TestKey("agtaerb"), new TestValue("G")) + "  |  Size: " + table.size()  );
		System.out.println("Looking for 'wfw': " + table.getValue(new TestKey("wfw")));
		System.out.println("Looking for 'agtaerb': " + table.getValue(new TestKey("agtaerb")));
		System.out.println("Looking for 'NotInThere': " + table.getValue(new TestKey("NotInThere")));
		
		System.out.println("\n-----------------------------------------------------------------------------------------");
		System.out.println("\n####### Now running GetKey tests #######\n");
		System.out.println("Looking for 'tnsn': " + table.getKey(new TestValue("D")));
		System.out.println("Looking for 'wfw': " + table.getKey(new TestValue("A")));
		System.out.println("Looking for 'agtaerb': " + table.getKey(new TestValue("G")));
		System.out.println("-- Looking for something that is not in the table. Should return false --");
		System.out.println("Looking for 'EFE': " + table.getKey(new TestValue("EFE")));
		System.out.println("Looking for 'Hello': " + table.getKey(new TestValue("Hello")));
		
		System.out.println("\nClearing table");
		System.out.println("Removing 'wfw': " + table.delete(new TestKey("wfw")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'wegwe': " + table.delete(new TestKey("wegwe")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'tjtn': " + table.delete(new TestKey("tjtn")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'tnsn': " + table.delete(new TestKey("tnsn")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'ryjt': " + table.delete(new TestKey("ryjt")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'trhr': " + table.delete(new TestKey("trhr")) +  " |  Size: " + table.size()  );
		System.out.println("Removing 'agtaerb': " + table.delete(new TestKey("agtaerb")) +  " |  Size: " + table.size()  );
		
		System.out.println("\nTesting getValue on empyt list: " + table.getValue(new TestKey("wfw")));
		System.out.println("Testing getKey on empty list: " + table.getKey(new TestValue("Hello")));
		
		System.out.println("\n Testing getValue with duplicate values");
		System.out.println("Adding 'A': " + table.add(new TestKey("AA"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'B': " + table.add(new TestKey("BB"), new TestValue("B")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'C': " + table.add(new TestKey("CC"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'D': " + table.add(new TestKey("DD"), new TestValue("B")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'E': " + table.add(new TestKey("EE"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'F': " + table.add(new TestKey("FF"), new TestValue("B")) + "  |  Size: " + table.size()  );
		
		System.out.println("\n");
		printArray();
		System.out.println("\n");
		printArrayV();
		
		System.out.println("Looking for 'A': " + table.getKey(new TestValue("A")));
		System.out.println("Looking for 'B': " + table.getKey(new TestValue("B")));
		
		System.out.println("Adding 'wfw': " + table.add(new TestKey("wfw"), new TestValue("A")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'wegwe': " + table.add(new TestKey("wegwe"), new TestValue("B")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'tjtn': " + table.add(new TestKey("tjtn"), new TestValue("C")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'tnsn': " + table.add(new TestKey("tnsn"), new TestValue("D")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'ryjt': " + table.add(new TestKey("ryjt"), new TestValue("E")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'trhr': " + table.add(new TestKey("trhr"), new TestValue("F")) + "  |  Size: " + table.size()  );
		System.out.println("Adding 'agtaerb': " + table.add(new TestKey("agtaerb"), new TestValue("G")) + "  |  Size: " + table.size()  );
		
		printArray();
		table.clear();
		System.out.println("SIZE: " + table.size());
		printArray();
		
		print("\nHOORAY YOU ARE DONE!!!");
	}
	
	public void printArray() {
        Iterator<TestKey> keyIter = table.keys();
        Iterator<TestValue> valueIter = table.values();
        try {
            while (keyIter.hasNext()) {
                print(keyIter.next() + "" + valueIter.next());
            }
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException.");
        }
    }
	
	public void printArrayV() {
        Iterator<TestKey> keyIter = table.keys();
        Iterator<TestValue> valueIter = table.values();
        try {
            while (valueIter.hasNext()) {
                print(valueIter.next() + "" + keyIter.next());
            }
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException.");
        }
    }
	
	public static void print(Object o) {
        System.out.println(o);
    }
	
	public static void main(String[] args){
		new HashDriver();
	}

	
	private class TestKey implements Comparable<TestKey>{
		 protected String key;
		 
	     public TestKey(String s) {
	    	 key = s;
	     }
	 
	     public int compareTo(TestKey th) {
	         return key.compareTo(th.key);
	     }
	       
	     public String toString() {
	         return "    Key: " + key;
	     }
	 
	     public int hashCode() {
	         return key.hashCode();
	     }
	}
	
	 
    private class TestValue implements Comparable<TestValue> {
        private String value;
        private int insertOrder;
       
        public TestValue(String n) {
            value = n;
            insertOrder = sequenceNumber++;
        }
 
        public int compareTo(TestValue v) {
            return value.compareTo(v.value);
        }
   
        public String toString() {
            return "    Value: " + value + ", Insert Order: " + insertOrder;
        }
       
        public int hashCode() {
            return ((String)value).hashCode();
        }
    }
	
}