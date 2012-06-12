package exercise_11;


import java.util.HashMap;
import java.io.*;

public class HashTableDict {

	public HashTableDict(String filename) {
		HashMap<int, >
		
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
