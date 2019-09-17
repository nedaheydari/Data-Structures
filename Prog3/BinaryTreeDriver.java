/*  Binary Tree Driver 
    AUTHOR: Todd Nguyen
    UPDATED: August 13, 2016 at 3:35 

	The first seven insertions are in this order:
	http://i.imgur.com/ZNRrleA.png
*/


import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

public class BinaryTreeDriver {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MAX_SIZE = 26;
    private int sequenceNumber = 0;
    private Random rndInt;
    private DictionaryADT<TestKey,TestValue> testDataStructure;

    public BinaryTreeDriver() {
        initializeStuff();
        testInsert();
        testContains();
        testGetValue();
        testGetKey();
        testRemove();
        testClear();
        testEmpty();
        testListFull();
        testFailFastKey();
        testFailFastValue();
        testKeyNoSuchElementException();
        testValueNoSuchElementException();

        // End tests
        space();
        print("DONE!");
    }

    public void initializeStuff() {
        testDataStructure = new BinarySearchTree<TestKey, TestValue>();
        rndInt = new Random(10);
    }

    public void testInsert() {
        space("insert");
        print("There are 22 total inserts, but a few of them are duplicates.");
        print("The duplicates should NOT show up.");
        testDataStructure.add(new TestKey("M"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("H"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("S"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("F"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("J"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("P"), new TestValue(rndInt.nextInt(10)));
        testDataStructure.add(new TestKey("W"), new TestValue(rndInt.nextInt(10)));
        for (int i = 0; i < 15; i++) {
            int rndTemp = rndInt.nextInt(MAX_SIZE-1) % 26;
            String key = Character.toString(ALPHABET.charAt(rndTemp));
            TestValue valTemp = new TestValue(rndInt.nextInt(10));
            if (testDataStructure.add(new TestKey(key), valTemp) == false) {
                int temp = sequenceNumber;
                print("This key is a duplicate: " + key + ", " + valTemp);   
            }
        }
        printArray();
        print("Testing inserting a duplicate key. Trying to insert key M: " +
            testDataStructure.add(new TestKey("M"), new TestValue(4)));
        finish("insert");
    }
        
    public void testRemove() {
        space("remove");
        print("Removing H: " + testDataStructure.delete(new TestKey("H")));
        printArrayForRemoveTesting();
        print("");
        print("Removing B: " + testDataStructure.delete(new TestKey("B")));
        printSize();
        print("Removing S: " + testDataStructure.delete(new TestKey("S")));
        printArrayForRemoveTesting();
        print("");
        print("Removing M: " + testDataStructure.delete(new TestKey("M")));
        printSize();
        print("Removing M: " + testDataStructure.delete(new TestKey("M")));
        printArrayForRemoveTesting();
        print("");        
        print("Removing U: " + testDataStructure.delete(new TestKey("U")));
        printArrayForRemoveTesting();
        print("");        
        print("Removing E: " + testDataStructure.delete(new TestKey("E")));
        print("Removing F: " + testDataStructure.delete(new TestKey("F")));
        printSize();
        printArrayForRemoveTesting();
        print("");
        print("Removing V: " + testDataStructure.delete(new TestKey("V")));
        printArrayForRemoveTesting();
        print("");
        print("Removing Q: " + testDataStructure.delete(new TestKey("Q")));
        printSize();
        print("Removing O: " + testDataStructure.delete(new TestKey("O")));
        printArrayForRemoveTesting();
        finish("remove");
    }

    public void testContains() {
        space("contains");
        print("Is there a C? " + testDataStructure.contains(new TestKey("C")));
        print("Is there a I? " + testDataStructure.contains(new TestKey("I")));
        print("Is there a Q? " + testDataStructure.contains(new TestKey("Q")));
        print("Is there a Z? " + testDataStructure.contains(new TestKey("Z")));
        finish("contains");
    }

    public void testGetValue() {
        space("getValue");
        printArray();
        print("--- BEGIN TESTING GETVALUE() ---");
        print("Value of Key L? " + testDataStructure.getValue(new TestKey("L")));
        print("Value of Key A? " + testDataStructure.getValue(new TestKey("A")));
        print("Value of Key I? " + testDataStructure.getValue(new TestKey("I")));
        print("Value of Key B? " + testDataStructure.getValue(new TestKey("B")));
        print("Value of Key Y? " + testDataStructure.getValue(new TestKey("Y")));
        finish("getValue");
    }

    public void testGetKey() {
        space("getKey");
        for (int outer = 0; outer < 2; outer++) {    
            for (int i = 0; i < 12; i++) {
                print("Key of Value " + i + "?    " + testDataStructure.getKey(new
                    TestValue(i)));
            }
            print("");
        }
        finish("getKey");
    }
    
    public void testClear() {
        space("clear");
        print("Should not print any lists below this line.");
        testDataStructure.clear();
        printArray();
        finish("clear");
    }

    public void testEmpty() {
        space("when array is empty");
        printArray();
        print("Is it empty?      " + testDataStructure.isEmpty());
        print("Is it full?       " + testDataStructure.isFull()); 
        print("Testing contains: " + testDataStructure.contains(new TestKey("Z")));
        print("Testing delete:   " + testDataStructure.delete(new TestKey("Z")));
        print("Testing getValue: " + testDataStructure.getValue(new TestKey("Z")));
        print("Testing getKey:   " + testDataStructure.getKey(new TestValue(0)));
        finish("when array is empty");
    }

    public void testListFull() {
        space("when array is full");
        sequenceNumber = 0;
        // Fill the array
        for (int i = 0; i < MAX_SIZE; i++) {
            String key = Character.toString(ALPHABET.charAt(i));
            testDataStructure.add(new TestKey(key), new TestValue(rndInt.nextInt(10)));
        }
        printArray();
        print("");
        print("Inserting into a full list: "+ testDataStructure.add(new TestKey("Z"),
            new TestValue(rndInt.nextInt())));
        print("Is it empty? " + testDataStructure.isEmpty());
        print("Is it full?  " + testDataStructure.isFull()); 
        print("Testing contains. Searching for a Key Z: " + 
            testDataStructure.contains(new TestKey("Z")));
        print("Testing delete. Deleting Key X: " + 
            testDataStructure.delete(new TestKey("X")));
        print("Testing getValue. Getting the value of Z: " + 
            testDataStructure.getValue(new TestKey("Z")));
        print("Testing getKey. Getting the value of 0: " + 
            testDataStructure.getKey(new TestValue(0)));
        printArray();
        print("");
        finish("when array is full");
    }

    public void testFailFastKey() {
        space("key iterator fail-fast");
        Iterator<TestKey> keyIter = testDataStructure.keys();
        Iterator<TestValue> valueIter = testDataStructure.values();
        try {
            while (keyIter.hasNext()) {
                print("Removing B: " + testDataStructure.delete(new TestKey("B")));
                print(keyIter.next() + "" + valueIter.next());
            }
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException");
        }
        finish("key iterator fail-fast");
    }

    public void testFailFastValue() {
        space("value iterator fail-fast");
        Iterator<TestKey> keyIter = testDataStructure.keys();
        Iterator<TestValue> valueIter = testDataStructure.values();
        try {
            while (valueIter.hasNext()) {
                print("Removing C: " + testDataStructure.delete(new TestKey("C")));
                print(keyIter.next() + "" + valueIter.next());
            }
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException");
        }
        finish("value iterator fail-fast");
    }
    
    public void testKeyNoSuchElementException() {
        space("key iterator NoSuchElementException");
        Iterator<TestKey> keyIter = testDataStructure.keys();
        Iterator<TestValue> valueIter = testDataStructure.values();
        try {
            while (keyIter.hasNext()) {
                keyIter.next();
            }
            keyIter.next();
        } catch (NoSuchElementException e) {
            print("Caught the NoSuchElementException");
        }
        finish("key iterator NoSuchElementException");
    }
    
    public void testValueNoSuchElementException() {
        space("value iterator NoSuchElementException");
        Iterator<TestKey> keyIter = testDataStructure.keys();
        Iterator<TestValue> valueIter = testDataStructure.values();
        try {
            while (valueIter.hasNext()) {
                valueIter.next();
            }
            valueIter.next();
        } catch (NoSuchElementException e) {
            print("Caught the NoSuchElementException");
        }
        finish("value iterator NoSuchElementException");
    } 

    public void printArray() {
        Iterator<TestKey> keyIter = testDataStructure.keys();
        Iterator<TestValue> valueIter = testDataStructure.values();
        try {
            while (keyIter.hasNext()) {
                print(keyIter.next() + "" +  valueIter.next());
            }
            printSize();
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException");
        }
    }

    public void printArrayForRemoveTesting() {
        print("--Array AFTER removal--");
        printArray();
    }
    
    public void printSize() {
        print("TEST CURRENT SIZE: " + testDataStructure.size());
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
            return "Key: " + key + "    ";
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
            return "Value: " + value + ", Insert Order: " + insertOrder;
        }
        
        public int hashCode() {
            return value.hashCode(); 
        }
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void space() {
        print("");
        print("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
    }

    public static void space(String s) {
        print("\n");
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        print("<><><> BEGIN TESTING " + s.toUpperCase() + " <><><>");
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        print(""); 
    }

    public static void finish(String s) {
        print("");
        print("<><><> FINISHED TESTING " + s.toUpperCase() + " <><><>");
    }
    
    public static void main(String[] agrs) {
        new BinaryTreeDriver();
    }
}