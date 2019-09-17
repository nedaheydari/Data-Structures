import java.util.Iterator;
import data_structures.*;

public class DictionaryDriver {
    public static void main(String [] args) {
        new DictionaryDriver();
        }
        
    public DictionaryDriver() {
        String [] words = {
        "vobis","castanea","agricola","basilica","consilium","atavus","vulgus",
        "iuglans"};
        LatinDictionary dictionary = new LatinDictionary();
        dictionary.loadDictionary("Latin.txt");	//might have to put path to file
        String definition;
        
        for(int i=0; i < words.length; i++) {
            definition = dictionary.getDefinition(words[i]);
            if(definition == null)
                System.out.println(
                    "Sorry, " + words[i] + " was not found.\n");
            else
                System.out.println(
                    "The definition of " + words[i] + " is:\n" + 
                    definition + ".\n");
            }
    
        dictionary.insertWord("iuglans","A walnut.  Either the nut or the tree");
        definition = dictionary.getDefinition("iuglans");
        if(definition == null)
            System.out.println(
                "Sorry, iuglans" + " was not found.\n");
        else
            System.out.println(
                "The definition of iuglans" + " is:\n" + 
                definition + ".\n"); 
                
        if(!dictionary.deleteWord(words[0]))
            System.out.println("ERROR, delete FAILED!!!");
        if(dictionary.getDefinition(words[0]) != null)
            System.out.println("ERROR, returned deleted definition.");

        System.out.println("Now checking the getRange method\n");
        String [] myWords = dictionary.getRange("ab","ac");
        int count = 0;
        for(int i=0; i < myWords.length; i++) {
            System.out.println(myWords[i] + "=" + dictionary.getDefinition(myWords[i]));
            count++;
        }
        System.out.println("COUNTED SIZE: " + count);
        
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
        System.out.println("Now checking the getRange method NULL\n");
        if (dictionary.getRange("zx","zz") == null) {
        	System.out.println("RETURNED NULL YAY");
        	// DELETE THIS LINE IF YOU ARE GOING TO TEST MORE
        	System.exit(0);
        }
        String [] myWords2 = dictionary.getRange("zx","zz");
        int count2 = 0;
        for(int i=0; i < myWords2.length; i++) {
            System.out.println(myWords2[i] + "=" + dictionary.getDefinition(myWords2[i]));
            count2++;
        }
        System.out.println("COUNTED SIZE: " + count2);        
    }
} 