import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	private class GameTreeNode {
		GameTreeNode left, right;
		String text;

		public GameTreeNode(String text) {
			this.text = text;
			left = right = null;
		}

		public String toString() {
			return text;
		}
	}

	GameTreeNode root;
	GameTreeNode current;
	String fileName;

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */

	public GameTree(String fileName) {
		this.fileName = fileName;
		try {
			Scanner s = new Scanner(new File(fileName));
			root = constructorHelper(s);
			current = root;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private GameTreeNode constructorHelper(Scanner scanner) {
		String line = scanner.nextLine().trim();
		GameTreeNode node = new GameTreeNode(line);

		if (line.endsWith("?")) {
			node.left = constructorHelper(scanner);
			node.right = constructorHelper(scanner);
		}
		return node;
	}


	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		String oldA = current.text;
		current.text = newQ;
		current.left = new GameTreeNode(newA);
		current.right = new GameTreeNode(oldA);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		if(current.text.endsWith("?")) {
			return false;
		}
		return true;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		return current.toString(); //replace
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if(yesOrNo == Choice.Yes) {
			current = current.left;
		}
		else {
			current = current.right;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		current = root;
	}

	@Override
	public String toString()
	{
		return toStringHelper(root, 0);
	}

	private String toStringHelper(GameTreeNode node, int level) {
		if (node == null)
			return "";

		String currentStr = "";
		for (int i = 0; i < level; i++) {
			currentStr += "- ";
		}
		currentStr += node.text + "\n";

		return toStringHelper(node.right, level + 1) + currentStr + toStringHelper(node.left, level + 1);
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		String outputFileName = fileName;
		PrintWriter diskFile = null;
		
		try { diskFile = new PrintWriter(new File(outputFileName)); }
		catch (IOException io) { System.out.println("Could not create file: " + outputFileName); }
		saveGameHelper(root, diskFile);
		diskFile.close();
	}
	
	private void saveGameHelper(GameTreeNode node, PrintWriter diskFile) {
		if(node != null) {
			diskFile.println(node.text);
			saveGameHelper(node.left, diskFile);
			saveGameHelper(node.right, diskFile);
		}
	}
}
