import data_structures.DictionaryADT;
import data_structures.RedBlackTree;
import java.util.*;

public class Driver {

	DictionaryADT<String,String>tree;
	
	public Driver() {
		tree = new RedBlackTree<String,String>();
		runTests();
	}
	
	private void runTests() {
		tree.add("ONE","one");
		tree.add("TWO", "two");
		tree.add("THREE", "three");
		Iterator<String> iter = tree.keys();
		while(iter.hasNext())
			System.out.println(iter.next());
	}
	
	public static void main(String[] args) {
		new Driver();
		
	}
}
