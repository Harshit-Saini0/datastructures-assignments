public class Tour 
{
	private class Node { 
		Point p;
		Node next;
	}

	Node head;

	public Tour()
	{
		head = new Node();
	}

	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		head = new Node();
		head.p = a;
		head.next = new Node(); 
		head.next.p = b;
		head.next.next = new Node(); 
		head.next.next.p = c;
		head.next.next.next = new Node(); 
		head.next.next.next.p = d;
		head.next.next.next.next = head;
	}

	/** print tour (one point per line) to std output */
	public void show()
	{
		if(head.next != null) {
			System.out.println(head.p);
			Node node = head.next;
			while(node != head && node.next != null) {
				System.out.println(node.p);
				node = node.next;
			}
		}
		else {
			System.out.println(head.p);
		}
	}

	/** draw the tour using StdDraw */
	public void draw()
	{
		if(head.next != null) {
			head.p.drawTo(head.next.p);
			Node node = head.next;
			while(node != head) {
				node.p.drawTo(node.next.p);
				node = node.next;
			}
		}
		else {
			head.p.draw();
		}
	}

	/** return number of nodes in the tour */
	public int size()
	{
		int size = 1;
		if(head.next != null) {
			Node node = head.next;

			while(node != head) {
				size++;
				node = node.next;
			}
		}
		return size;
	}

	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{
		double distance = 0;
		if(head.next != null) {

			distance += head.p.distanceTo(head.next.p);
			Node node = head.next;

			while(node != head) {
				distance += node.p.distanceTo(node.next.p);
				node = node.next;
			}
		}
		return distance;
	}

	/** insert p using order of list */
	public void insertInOrder(Point p) 
	{
		if(head.p == null) {
			head.p = p;
			head.next = head;
		}
		else {
			Node node = head;
			for(int i = 0; i < this.size(); i++) {
				node = node.next;
			}
			Node temp = node.next;
			node.next = new Node();
			node.next.p = p;
			node.next.next = temp;
		}
	}

	/** insert p using nearest neighbor heuristic */
	public void insertNearest(Point p) 
	{
		if(head.p == null) {
			head.p = p;
			head.next = head;
		}
		else {
			Node searchNode = head;
			Node minNode = head;
			double minDistance = Double.MAX_VALUE;
			int size = this.size();

			for(int i = 0; i < size; i++) {
				double thisDistance = searchNode.p.distanceTo(p);
				if(thisDistance < minDistance) {
					minDistance = thisDistance;
					minNode = searchNode;
				}
				searchNode = searchNode.next;
			}

			Node temp = minNode.next;
			minNode.next = new Node();
			minNode.next.p = p;
			minNode.next.next = temp;
		}
	}

	/** insert p using smallest increase heuristic */
	public void insertSmallest(Point p) {
		if(head.p == null) {
			head.p = p;
			head.next = head;
		}
		else {
			Node searchNode = head;
			Node minNode = head;
			double minDistance = Double.MAX_VALUE;
			int size = this.size();

			while(searchNode.next != head) {
				//add
				Node temp = searchNode.next;
				searchNode.next = new Node();
				searchNode.next.p = p;
				searchNode.next.next = temp;

				double thisDistance = this.distance();

				//remove
				searchNode.next = temp;

				if(thisDistance < minDistance) {
					minDistance = thisDistance;
					minNode = searchNode;
				}
				searchNode = searchNode.next;
			}

			Node temp = minNode.next;
			minNode.next = new Node();
			minNode.next.p = p;
			minNode.next.next = temp;
		}
	}


	public static void main(String[] args) {
		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 600);
		Point a = new Point(100.0, 100.0);
		Point b = new Point(500.0, 100.0);
		Point c = new Point(500.0, 500.0);
		Point d = new Point(100.0, 500.0);
		Tour squareTour = new Tour(a, b, c, d);
		squareTour.show();
		System.out.println(squareTour.distance());
		squareTour.draw();
	}
}