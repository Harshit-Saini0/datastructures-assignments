public class Node implements Comparable<Node> {
	Character c;
	Node left, right;
	int freq;
	
	public Node(Character c, int freq) {
		this.c = c;
		this.freq = freq;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Character getC() {
		return c;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public int getFreq() {
		return freq;
	}

	public int compareTo(Node other) {
		return freq - other.freq;
	}
	
	public String toString() {
		if(c != null) {
			return "" + c;
		}
		else {
			return "" + freq;
		}
	}
}
