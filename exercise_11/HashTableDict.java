package exercise_11;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;

public class HashTableDict {
	private HashMap<Integer, LinkedList<String>> dict;
	
	public void main(String[] args) {
		Iterator it = dict.entrySet().iterator();
		while(it.hasNext())
			Map.Entry<Integer, LinkedList<String>> pair = it.next();
			System.out.println();
	}

	public HashTableDict() {
		dict = new HashMap<Integer, LinkedList<String>>();		
		String line = null;
		String content = "";
		
		try {	
			BufferedReader reader = new BufferedReader(new FileReader("sevenwords.txt"));
	        while ((line = reader.readLine()) != null) {
	        	content += line + " ";
	        }
		}  catch(IOException e) {
        	System.err.println("Speichern fehlgeschlagen!");
        }
		
		String[] words = content.split(" "); 
		
		for(String s : words) {
			s = s.toLowerCase();
			String save = s;
			int sum = 0;
			int i = 0;
			while(s.length() > 0) {
				char c = s.charAt(s.length() - 1);
				char a = 'a';
				int value = c - a + 1;
				sum += value*Math.pow(26, i);
				s.substring(0 , s.length() - 1);
				i++;
			}
			sum = sum % 1000;
			LinkedList<String> values = dict.get(sum);
			if(values == null) {
				values = new LinkedList<String>();
			}
			values.add(save);
			dict.put(sum, values);
		}
	}
}
