package exercise_11;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class HashTableDict {
	private static HashMap<Integer, LinkedList<String>> dict;
	
	public static void main(String[] args) {
		dict = new HashMap<Integer, LinkedList<String>>();
		fill();
		printStats();
		BufferedReader stdIn =
		        new BufferedReader(new InputStreamReader(System.in));
		    String string;
		    try {
				while ((string = stdIn.readLine()) != null) {
				    LinkedList<String> l = dict.get(calcKey(string));
				    if(l != null)
				    	for(String s : l)
				    		System.out.println(s);
				    else
				    	System.out.println("No matches found.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private static void printStats() {
		int longestChain = 0;
		int entries = 0;
		for (Entry<Integer, LinkedList<String>> entry : dict.entrySet())
		{
			String result = entry.getKey() + "/" + entry.getValue().size() + "/";
		    for(String s : entry.getValue()) {
		    	result += s + ", ";
		    }
		    System.out.println(result);
		    if(entry.getValue().size() > longestChain)
		    	longestChain = entry.getValue().size();
		    entries++;
		}
		System.out.println("Longest Chain:" + longestChain);
		System.out.println(entries + " entries");
	}
	
	private static int calcKey(String s) {
		s = s.toLowerCase();
		char[] content = s.toCharArray();
		Arrays.sort(content);
		int i = content.length;
		double sum = 0;
		for(char c : content) {
			char a = 'a';
			int value = c - a + 1;
			sum += value*Math.pow(27, i);
			i--;
		}
		sum = sum % 4912;
		return (int) sum;
	}
	
	public static void fill() {		
		String line = null;
		String content = "";
		
		try {	
			BufferedReader reader = new BufferedReader(new FileReader("D:/Till/study/term_02/info2/excercises/Exercises_ReneTill/info_exercise/exercise_11/sevenwords.txt"));
	        while ((line = reader.readLine()) != null) {
	        	content += line + " ";
	        }
		}  catch(IOException e) {
        	System.err.println("Lesen fehlgeschlagen!");
        }
		
		String[] words = content.split(" ");

		
		for(String s : words) {
			String save = s;
			int key = calcKey(s);
			LinkedList<String> values = dict.get(key);
			if(values == null) {
				values = new LinkedList<String>();
			}
			values.add(save);
			dict.put(key, values);
		}
	}
}
