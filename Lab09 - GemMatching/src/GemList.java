public class GemList 
{
	private class Node {
		private Gem gem;
		private Node next;
		public Node(Gem gem) {
			this.gem = gem;
		}
	}
	private Node head;
	private int size;
	
	public GemList() {
		head = null;
		size = 0;
	}
	
	public GemList(Gem gem) {
		head = new Node(gem);
		size = 1;
	}

	
	public int size() {
		return size;
	}
	
	public void draw(double y) {
		Node node = head;
		int x = 1;
		while(node != null) {
			node.gem.draw(GemGame.indexToX(x),y);
			node = node.next;
			x++;
		}
	}
	
	public String toString() {
		String str = "[";
		Node node = head;
		if(head == null) {
			return str += "]";
		}
		while(node != null) {
			str = str + node.gem.getType() + node.gem.getPoints() + ", ";
			node = node.next;
		}
		return str.substring(0, str.length() - 2) + "]";
	}
	
	public void insertBefore(Gem gem, int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if(head == null) {
			head = new Node(gem);
		}
		else if(index >= size) {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node(gem);
		}
		else {
			Node node = head;
			int i = 0;
			while(node != null) {
				if(i == index && i == 0) {
					Node nextNode = head;
					head = new Node(gem);
					head.next = nextNode;
				}
				else if(i == index - 1) {
					Node nextNode = node.next;
					node.next = new Node(gem);
					node.next.next = nextNode;
				}
				node = node.next;
				i++;
			}
		}
		size++;
	}
	
	public int score() {
		if(head == null) {
			return 0;
		}
		int score = 0;
		int streak = 1;
		int streakSum = head.gem.getPoints();
		GemType prevColor = head.gem.getType();
		Node node = head.next;
		while(node != null) {
			if(node.gem.getType() == prevColor) {
				streak++;
				streakSum += node.gem.getPoints();
			}
			else {
				score += (streakSum * streak);
				streak = 1;
				streakSum = node.gem.getPoints();
				prevColor = node.gem.getType();
			}
			node = node.next;
		}
		score += (streakSum * streak);
		return score;
	}
	
	
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
}
