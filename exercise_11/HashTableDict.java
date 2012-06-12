package exercise_11;


import java.util.HashMap;
import java.io.*;

import exercise_06.LinkedList;

public class HashTableDict {

	public HashTableDict(String filename) {
		HashMap<Integer, LinkedList<String>> dict = new HashMap<Integer, LinkedList<String>>();
		while(!fileend) {
			String s = nextLine;
			s = s.toLowerCase();
		}
		
		String line = null;
		String content = "";
		
		try {	
			BufferedReader reader = new BufferedReader(new FileReader(filename));
	        while ((line = reader.readLine()) != null) {
	        	content += line;
	        }
		}  catch(IOException e) {
        	System.err.println("Speichern fehlgeschlagen!");
        }
	}
}
