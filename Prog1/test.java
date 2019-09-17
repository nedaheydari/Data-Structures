package driver;

import data_structures.*;

public class test {
	private OrderedListADT<String> list;
	
	public test() {
		list = new OrderedArrayList<String>(1000);
		runTests();
		}
		
	private void runTests() {
		list.insert("John");
		list.insert("Sam");
		list.insert("Bob");
		list.insert("Anna");
		list.insert("Sarah");
		list.insert("Alex");
		for(String s : list) {
			System.out.println(s);	
		}
			System.out.println("Getting index: " + list.get(1));
			System.out.println("Getting index: " + list.get("Sarah"));
			System.out.println("Finding obj: " + list.find("Sam"));
			System.out.println("Contains: " + list.contains("John"));
			
			System.out.println("Removing first index: " + list.remove(0));
			System.out.println("Removing obj: " + list.remove("Bob"));
			System.out.println("Removing min: " + list.removeMin());
			System.out.println("Removing obj: " + list.removeMax());
			System.out.println("Iterator: " + list.iterator());
		
		}
	
	public static void main(String [] args) {
		new test();
		}
	}

//WORKING: Insertion 