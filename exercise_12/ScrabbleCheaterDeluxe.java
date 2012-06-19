package exercise_12;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

public class ScrabbleCheaterDeluxe {
	private static HashMap<Integer, LinkedList<String>> dict;
	private static boolean hasPerms = false;
	
	public static void main(String[] args) {
		dict = new HashMap<Integer, LinkedList<String>>();
		fill();
		printStats();
		String random = generateRdmLetters(7);
		System.out.println(random);
		for(String s : recPerms(random)) {
			printPerms(s);
		}
		BufferedReader stdIn =
		        new BufferedReader(new InputStreamReader(System.in));
		    String string;
		    try {
				while ((string = stdIn.readLine()) != null) {
					hasPerms = false;
					ArrayList<String> perms = new ArrayList<String>();
					perms.addAll(recPerms(string));
					java.util.Collections.sort(perms, new CompareLength());
					for(String s : perms) {
					    printPerms(s);
					}
				    if(!hasPerms)
				    	System.out.println("No matches found.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
 	
	private static HashSet<String> recPerms ( String s ) {
		s = s.toLowerCase();
		char[] content = s.toCharArray();
		Arrays.sort(content);
		s = new String(content);
		HashSet<String> perms = new HashSet<String>();
		perms.add(s);
		if(s.length() > 2) {
			for(int i = 0; i < s.length(); i++) {
				String sub = s.substring(0, i) + s.substring(i+1, s.length());
				perms.addAll(recPerms(sub));
			}
		}
		return perms;
	}
	
	private static void printPerms(String string) {
		LinkedList<String> l = dict.get(calcKey(string));
	    if(l != null) {
	    	for(String s : l)
	    		if(isPermutation(string, s)) {
	    			System.out.println(s);
	    			hasPerms = true;
	    		}
		}
	}

	private static String generateRdmLetters(int n) {
		String s = "";
		for(int i = 0; i < n; i++) {
			Random rdm = new Random();
			char c = (char) ('a' + rdm.nextInt(26));
			s += c;
		}
		return s;
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
	
	private static boolean isPermutation(String a, String b) {
		a = a.toLowerCase();
		char[] content = a.toCharArray();
		Arrays.sort(content);
		b = b.toLowerCase();
		char[] content2 = b.toCharArray();
		Arrays.sort(content2);
		String first = new String(content);
		String second = new String(content2);
		if(first.equals(second))
			return true;
		else
			return false;
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
