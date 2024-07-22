import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordLadder {
	private String startWord;
	private String endWord;
	private Queue<Stack<String>> queue;
	private ArrayList<String> dictionary;
	private ArrayList<String> usedWords;


	public WordLadder() {	
		try {
			//make an array for dictionary
			dictionary = new ArrayList<String>();
			Scanner d = new Scanner(new File("dictionary.txt"));
			while(d.hasNextLine()) {
				dictionary.add(d.nextLine());
			}
			d.close();

			//load start and end word
			Scanner s = new Scanner(new File("input.txt"));
			String[] string = s.nextLine().split(" ");
			startWord = string[0].toUpperCase();
			endWord = string[1].toUpperCase();
			s.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		queue = new LinkedList<Stack<String>>();

		usedWords = new ArrayList<String>();
		usedWords.add(startWord);
	}

	public String getStartWord() {
		return startWord;
	}

	public String getEndWord() {
		return endWord;
	}

	public void searchDictionary(Stack<String> searchStack) {
		for(String word: dictionary) {
			String searchWord = searchStack.peek();
			//words same length
			if(word.length() != searchWord.length()) {
				continue;
			}

			//one letter difference
			int difference = 0;
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) != searchWord.charAt(i)) {
					difference++;
				}

				if(difference > 1) {
					break;
				}
			}
			
			//create new stack
			if(difference == 1 && usedWords.indexOf(word) < 0) {
				Stack<String> stack = new Stack<String>();

				//copying SearchStack
				Stack<String> searchStackCopy = new Stack<String>();
				Stack<String> searchStackHelper = new Stack<String>();
				int size = searchStack.size();
				for(int i = 0; i < size; i++) {
					String popped = searchStack.pop();
					searchStackCopy.push(popped);
					searchStackHelper.push(popped);
				}

				while(!searchStackHelper.isEmpty()) {
					searchStack.push(searchStackHelper.pop());
				}


				while(!searchStackCopy.isEmpty())
					stack.push(searchStackCopy.pop());
				stack.push(word);
				queue.offer(stack);
				usedWords.add(word);
			}
		}
	}

	public Stack<String> solve() {
		int numSearched = 0;
		Stack<String> start = new Stack<String>();
		start.push(startWord);
		searchDictionary(start);

		while(!queue.isEmpty()) {
			Stack<String> newStack = queue.poll();
			if(newStack.peek().equals(endWord)) {
				return newStack;
			}
			else {
				searchDictionary(newStack);
				numSearched++;
				System.out.println("Searched " + numSearched + " ladder(s)");
			}
		}

		Stack<String> notFound = new Stack<String>();
		notFound.push("no ladder found");
		return notFound;
	}

	public static void main(String[] args) {
		WordLadder w = new WordLadder();
		System.out.println("\nWORD LADDER from " + w.getStartWord() 
		+ " to " + w.getEndWord() + ": "+ w.solve());
	}
}
