/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Rajat Ahuja
 * RA29697
 * 15465
 * Slip days used: 0
 * Git URL: github.com/rajat-ahuja1997 
 * Spring 2018
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		ArrayList<String> ladder = new ArrayList<String>();
		ladder = parse(kb);
		if(ladder == null)	{
			return;
		}
		System.out.println("BFS Word Ladder:");
		printLadder(getWordLadderBFS(ladder.get(0), ladder.get(1)));
		System.out.println('\n' + "DFS Implementation:");
		printLadder(getWordLadderDFS(ladder.get(0), ladder.get(1)));
	}
	
	public static void initialize() {
		// unused
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> ladder = new ArrayList<String>();
		if (keyboard.hasNext("/quit"))	{
			return null;
		} else {
			ladder.add(keyboard.next());
			ladder.add(keyboard.next());
			if(ladder.contains("/quit")) {
				return null;
			}
			return ladder;
		}
	}
	
	/**
	 * 
	 * @param start String with our first word
	 * @param end String with our second word
	 * @return ArrayList that contains our DFS word ladder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		start = start.toUpperCase();
		end = end.toUpperCase();
		Set<String> dict = makeDictionary();
		Set<String> visited = new HashSet<String>();
		visited.add(start);
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		ladder = DFSRecurse(start, end, visited, dict, ladder);
		if (!ladder.contains(end))	{
			ladder.add(end);
		}
		return ladder;
	}
	
	/**
	 * a recursive function to help calculate our DFS word ladder
	 * @param start String that is our first word
	 * @param end String that is our second word
	 * @param v Set that represents our visited words
	 * @param dict Set dictionary that we use for dfs
	 * @param ladder ArrayList ladder we are constructing
	 * @return ArrayList that is our constructed ladder
	 */
	public static ArrayList<String> DFSRecurse(String start, String end, Set<String> visited, Set<String> dict, ArrayList<String> ladder)	{
		for (String word: dict)	{
			if ((visited.contains(word) == false) && isNeighbor(word, start) && (ladder.contains(end) == false))	{
				if (word == end)	{
					ladder.add(end);
					return ladder;
				} 
				else {
					ladder.add(word);
					visited.add(word);
					ladder = DFSRecurse(word, end, visited, dict, ladder);
					if (ladder.contains(end))	{
						return ladder;
					} 
					else {
						ladder.remove(word);
					}
				}
			}
		}
		return ladder;
	}
	
	/**
	 * A BFS function to calculate our word ladder
	 * @param start String that is our first word
	 * @param end String that is our end word
	 * @return ArrayList that is our word ladder
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		start = start.toUpperCase();
		end = end.toUpperCase();
		ArrayList<String> ladder = new ArrayList<String>();
		
		Set<String> dict = makeDictionary();
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		HashMap<String, String> parents = new HashMap<String, String>();
		Set<String> visited = new HashSet<String>();
		visited.add(start);
		
		while((queue.isEmpty() == false) && (queue.contains(end) == false)) {
			for(String word: dict) {
				if(isNeighbor(word, queue.peek()) && (visited.contains(word) == false)) {
					parents.put(word, queue.peek());
					queue.add(word);
					visited.add(word);
				}
			}
			queue.remove();
		}
		if((queue.contains(end) == false)) {
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}
		else {
			ladder.add(end);
			String parent = end;
			String child = null;
			while (child != start) {
				child = parents.get(parent);
				parent = child;
				ladder.add(child);
			}
			Collections.reverse(ladder);
			return ladder;
		}	
	}
    
    public static void printLadder(ArrayList<String> ladder) {
		if(ladder.size() == 2) {
			System.out.println("no word ladder can be found between " + ladder.get(0).toLowerCase() + " and " + ladder.get(1).toLowerCase());
		}
		else {
			int size = ladder.size() - 2;
			System.out.println("a " + size + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1));
			for(int i = 0; i < ladder.size(); i++) {
				System.out.println(ladder.get(i).toLowerCase());
			}
		}
	}
	
	public static boolean isNeighbor(String a, String b) {
		int diff = 0;
		for(int i = 0; i < a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) {
				diff++;
			}
		}
		if(diff > 1) {
			return false;
		}
		return true;
	}


	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}