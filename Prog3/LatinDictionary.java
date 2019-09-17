import java.util.Iterator;
import data_structures.*;

public class LatinDictionary {
	    private DictionaryADT<String,String> dictionary;

	    
	    public LatinDictionary() {
	    	dictionary = new HashTable<String,String>(10000);
	        }

	    
	    public void loadDictionary(String fileName) {     
	    	DictionaryEntry[] entries = DictionaryReader.getDictionaryArray("Latin.txt");
	    	for(int i = 0; i < entries.length; i++)
	    		insertWord(entries[i].getKey(),entries[i].getValue());
	        }

	   
	    public boolean insertWord(String word, String definition) { 
	    	return dictionary.add(word, definition);
	        }

	    
	    public boolean deleteWord(String word) {
	    	return dictionary.delete(word);
	        }

	    
	    public String getDefinition(String word) {
	    	return dictionary.getValue(word);
	        }

	    
	    public boolean containsWord(String word) {
			return dictionary.contains(word);
	        }
	    
	     
	    public String[] getRange(String start, String finish) {
	    	Iterator<String> keys = dictionary.keys();
	    	UnorderedList<String> list = new UnorderedList<String>();

	    	while(keys.hasNext()) {
	    		String current = keys.next();
	    		if(((Comparable<String>)start).compareTo(current) <= 0)
	    			list.insert(current);	
	    		if(((Comparable<String>)finish).compareTo(current) == 0)
	    			break;
	    	}
	    	if(list.size() ==0) 
	    		return null;
			String [] array = new String[list.size()];
			for(int i = array.length - 1; i >= 0; i--)
				array[i] = list.removeFirst();	
			return array;		
	        }
	            
	  
	    public Iterator<String> words() {
			return dictionary.keys();
	    }

	    
	    public Iterator<String> definitions() {
			return dictionary.values();
	        }
	}