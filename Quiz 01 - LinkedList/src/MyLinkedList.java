import java.util.*;

public class MyLinkedList
{
	public static final int ID = 212282; //add your student ID here
		
	private ListNode front;

	public class ListNode //******** INNER CLASS ********
	{
		int      data;
		ListNode next;

		public ListNode(int data) { this.data = data; }
		public ListNode(int data, ListNode next) { this.data = data; this.next = next; }

		@Override
		public String toString() { return "" + this.data; }
	}

	public MyLinkedList() //not actually necessary but included for clarity
	{
		front = null;
	}

	public MyLinkedList(int val)
	{
		front = new ListNode(val);
	}

	/** for ease of testing, you can construct a MLL object with initial values:
	 *     MyLinkedList list = new MyLinkedList(1, 2, 3, 4);
	 * 
	 *     if this doesn't make sense, just ignore this constructor!
	 */
	public MyLinkedList(int... vals)
	{
		if (front == null) front = new ListNode(vals[0]);

		ListNode current = front;

		for (int i = 1; i < vals.length; i++) {
			current.next = new ListNode(vals[i]);
			current = current.next;
		}
	}

	@Override
	public String toString()
	{
		String result = "[";

		ListNode current = front;

		while (current != null)
		{
			if (current.next == null) //reached last element in the list
				result += current.data;

			else
				result += current.data + ", ";

			current = current.next;
		}
		result += "]";

		return result;
	}

	public ListNode getFront() { return this.front; }

	/********************************************
	 ********** QUIZ METHODS BELOW HERE *********
	 ********************************************/

	//#1
	public void replaceLast(int n)
	{
		ListNode node = front;
		while(node.next != null) {
			node = node.next;
		}
		node.data = n;
	}

	//#2
	public int lastIndexOf(int val)
	{
		int index = 0;
		int targetIndex = -1;
		ListNode node = front;
		while(node != null) {
			if(node.data == val) {
				targetIndex = index;
			}
			index++;
			node = node.next;
		}
		return targetIndex;
	}

	//#3
	public int countDuplicates()
	{
		if(front.next == null) {
			return 0;
		}
		ListNode node = front.next;
		int currentVal = front.data;
		int numDuplicates = 0;
		while(node != null) {
			if(node.data == currentVal) {
				numDuplicates++;
			}
			else {
				currentVal = node.data;
			}
			node = node.next;
		}
		return numDuplicates;
	}

	//#4
	public void stutter()
	{
		ListNode node = front;
		while(node != null) {
			ListNode nextNode = node.next;
			node.next = new ListNode(node.data);
			node.next.next = nextNode;
			node = nextNode;
		}
	}

	//#5
	public void removeAll(int n)
	{
		if(front.data == n) {
			front = front.next;
		}
		ListNode node = front;
		while(node != null) {
			if(node.next != null && node.next.data == n) {
				node.next = node.next.next;
			}
			node = node.next;
		}
	}

	//#6
	public int deleteLast()
	{
		ListNode node = front;
		while(node.next.next != null) {
			node = node.next;
		}
		int last = node.next.data;
		node.next = null;

		return last;
	}

	/** You can test your methods below if you'd like */
	public static void main(String[] args)
	{
		System.out.println("replaceLast test");
		MyLinkedList list1 = new MyLinkedList(1,2,3,4,5,6);
		System.out.println(list1);
		list1.replaceLast(42);
		System.out.println("list1.replaceLast(42) = " + list1);
		
		System.out.println("\nlastIndexOf test");
		MyLinkedList list2 = new MyLinkedList(1,18,2,7,18,39,18,18);
		System.out.println(list2);
		System.out.println("lastIndexOf(18) = " + list2.lastIndexOf(18));
		
		System.out.println("\ncountDuplicates test");
		MyLinkedList list3 = new MyLinkedList(1,1,1,3,3,6,9,15,15,23,23,23,40,40);
		System.out.println(list3);
		System.out.println("numDuplicates() = " + list3.countDuplicates());
		
		System.out.println("\nstutter test");
		MyLinkedList list4 = new MyLinkedList(1,8,19,4,17);
		System.out.println(list4);
		list4.stutter();
		System.out.println(list4);
		
		System.out.println("\nremoveAll test");
		MyLinkedList list5 = new MyLinkedList(3,9,4,2,3,8,17,4,3,18,3);
		System.out.println(list5);
		list5.removeAll(3);
		System.out.println("list.removeAll(3) = " + list5);
		
		System.out.println("\ndeleteLast test");
		MyLinkedList list6 = new MyLinkedList(1,8,19,4,17);
		System.out.println(list6);
		System.out.println("Deleted element: " + list6.deleteLast());
		System.out.println(list6);
	}
}
