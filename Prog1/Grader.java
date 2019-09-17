import data_structures.*;

public class Grader {
    private OrderedListADT<Integer> v;
        int size = 10000;    
    public Grader() {
      try {
        v = new OrderedArrayList<Integer>(size);

        for(int i=size-1; i >= 0; i--)
            v.insert(i);
        System.out.println("Size is now: " + v.size());
        for(int i=0; i < size; i++) {
            v.remove(0);
            }            
        if(v.size() != 0)
            print("*************ERROR, size should be 0, but is " + v.size());
        v.clear();
        for(Integer i : v)
            print("*************ERROR, iterator failed");
        
        v = new OrderedArrayList<Integer>(size); 
        size = 100;  
        for(int i=0; i < size; i++)
            v.insert(i);
        print("Before remove, size should be 100 and size is " + v.size());            
        v.remove(new Integer(50));  
        print("searching for REMOVED element, and found: " +
            v.get(new Integer(50))); 
            
        if(v.contains(new Integer(50)))
            print("*************ERROR, contains found removed element");
        if(!(v.contains(new Integer(51))))
            print("*************ERROR, failed to find element");           
        print("After remove, size should be 99 and is " + v.size()); 
        v = new OrderedArrayList<Integer>(500);        
        for(int i=0; i < 500; i++)
            v.insert(i);
        for(int i=499; i >= 0; i--)
            if(v.remove(new Integer(i)) == null)
                print("*************ERROR, failed to remove valid object");
        print("Size should be 0 and is now " + v.size());
        if(v.get(new Integer(10)) != null)
            print("*************ERROR, found removed object");
        v.clear();
        print("Should now print 1..10");
        for(int i=10; i > 0; i--)
            v.insert(new Integer(i));
        for(Integer i : v)
            print(""+i);
            
        v.remove(new Integer(5));
        v.remove(new Integer(6));
        v.remove(new Integer(4));
        v.remove(new Integer(7));
        
        print("Should now print 1 .. 3, 8 .. 10");  
        for(Integer i : v)
            print(""+i);              
      }
    catch(Exception e) {
        e.printStackTrace();
        }  
    }
        
    private void print(String val) {
        System.out.println(val);
        }
        
    public static void main(String [] args) {
        new Grader();
        }
    }