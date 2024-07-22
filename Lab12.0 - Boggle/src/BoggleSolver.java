import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver
{
	String dictionaryName;
	ArrayList<String> dictionary;
	HashSet<String> longDictionaryPrefixes;
	HashSet<String> shortDictionaryPrefixes;
	
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		this.dictionaryName = dictionaryName;
		
		Scanner s;
		try {
			s = new Scanner(new File(this.dictionaryName));
			dictionary = new ArrayList<String>();
			longDictionaryPrefixes = new HashSet<String>();
			shortDictionaryPrefixes = new HashSet<String>();

			while(s.hasNext()) {
				String word = s.nextLine();
				dictionary.add(word);
				if(word.length() >= 5) {
					shortDictionaryPrefixes.add(word.substring(0,5));
					if(word.length() >= 7)
						longDictionaryPrefixes.add(word.substring(0,7));
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		HashSet<String> validWords = new HashSet<String>();
		int rows = board.rows();
		int cols = board.cols();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				getAllValidWords(board, i, j, "", rows, cols, validWords, new ArrayList<ArrayList<Integer>>());
			}
		}
		return validWords;
	}
	
	public void getAllValidWords(BoggleBoard board, int i, int j, String word, int rows, int cols, 
			HashSet<String> validWords, ArrayList<ArrayList<Integer>> usedSpaces) {
		
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(i);coords.add(j);
		if(usedSpaces.contains(coords)) 
			return;
		usedSpaces.add(coords);

		if(i >= rows || i < 0) 
			return;
		if(j >= cols || j < 0) 
			return;
		if(word.length() >= 5) {
			if(!shortDictionaryPrefixes.contains(word.substring(0,4))) {
				return;
			}
			if(word.length() >= 5)
				if(!longDictionaryPrefixes.contains(word.substring(0,7))) {
					return;
				}
		}
		
		String letter = "" + board.getLetter(i, j);
		if(letter.equals("Q")) 
			letter = "QU";
		word += letter;
		if(dictionary.contains(word)) 
			validWords.add(word);
		
		System.out.println(validWords);
		
//		for(int x = -1; x <= 1; x++) {
//			for(int y = -1; x <= 1; y++) {
//				if(x == 0 && y == 0)
//					continue;
//				getAllValidWords(board, i+x, j+y, word, rows, cols, validWords, new ArrayList<int[]>(usedSpaces)); 
//			}
//		}
		getAllValidWords(board, i+1, j, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //down
		getAllValidWords(board, i-1, j, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //up
		getAllValidWords(board, i, j+1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //right
		getAllValidWords(board, i, j-1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //left
		getAllValidWords(board, i-1, j-1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //up left
		getAllValidWords(board, i-1, j+1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //up right
		getAllValidWords(board, i+1, j-1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //down left
		getAllValidWords(board, i+1, j+1, word, rows, cols, validWords, new ArrayList<ArrayList<Integer>>(usedSpaces)); //down right
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if(!dictionary.contains(word) || word.length() <= 2) 
			return 0;
		else if(word.length() <= 4) 
			return 1;
		else if(word.length() == 5)
			return 2;
		else if(word.length() == 6)
			return 3;
		else if(word.length() == 7)
			return 5;
		else
			return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		BoggleBoard  board  = new BoggleBoard("board-q.txt");
		BoggleSolver solver = new BoggleSolver("data/dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
